package net.pretronic.dkmotd.minecraft.commands.maintenance;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.maintenance.Maintenance;
import net.pretronic.dkmotd.minecraft.commands.CommandUtil;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.BasicCommand;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class ReasonCommand extends BasicCommand {

    private final DKMotd dkMotd;

    public ReasonCommand(ObjectOwner owner, DKMotd dkMotd) {
        super(owner, CommandConfiguration.name("reason"));
        this.dkMotd = dkMotd;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length < 1) {
            sender.sendMessage(Messages.COMMAND_MAINTENANCE_HELP);
            return;
        }
        String reason = CommandUtil.readStringFromArguments(args, 0);
        Maintenance maintenance = this.dkMotd.getMaintenance();
        if(maintenance.setReason(reason)) {
            sender.sendMessage(Messages.COMMAND_MAINTENANCE_REASON, VariableSet.create()
                    .addDescribed("maintenance", maintenance));
        }
    }
}
