package net.pretronic.dkmotd.minecraft.commands.maintenance;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.maintenance.Maintenance;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.BasicCommand;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.GeneralUtil;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

import java.time.Duration;

public class TimeoutCommand extends BasicCommand {

    private final DKMotd dkMotd;

    public TimeoutCommand(ObjectOwner owner, DKMotd dkMotd) {
        super(owner, CommandConfiguration.name("timeout"));
        this.dkMotd = dkMotd;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length != 1) {
            sender.sendMessage(Messages.COMMAND_MAINTENANCE_HELP);
            return;
        }
        String rawTimeout = args[0];
        if(!GeneralUtil.isNaturalNumber(rawTimeout)) {
            sender.sendMessage(Messages.ERROR_NUMBER_NOT_VALID, VariableSet.create().add("value", rawTimeout));
            return;
        }
        long timeout = Long.parseLong(rawTimeout);
        Maintenance maintenance = this.dkMotd.getMaintenance();
        if(maintenance.setTimeout(timeout)) {
            sender.sendMessage(Messages.COMMAND_MAINTENANCE_TIMEOUT, VariableSet.create()
                    .addDescribed("maintenance", maintenance));
        }
    }
}
