package net.pretronic.dkmotd.minecraft.commands.maintenance;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.maintenance.Maintenance;
import net.pretronic.dkmotd.minecraft.config.DKMotdConfig;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.BasicCommand;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.GeneralUtil;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

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
        long timeout = DKMotdConfig.parseDateFormat(rawTimeout);
        if(timeout == -1) {
            sender.sendMessage(Messages.ERROR_DATE_FORMAT_NOT_VALID, VariableSet.create().add("value", rawTimeout));
            return;
        }
        Maintenance maintenance = this.dkMotd.getMaintenance();
        if(maintenance.setTimeout(timeout)) {
            sender.sendMessage(Messages.COMMAND_MAINTENANCE_TIMEOUT, VariableSet.create()
                    .addDescribed("maintenance", maintenance));
        }
    }
}
