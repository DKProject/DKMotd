package net.pretronic.dkmotd.minecraft.commands.maintenance;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.maintenance.Maintenance;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.BasicCommand;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.duration.DurationProcessor;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

import java.time.Duration;

public class DurationCommand extends BasicCommand {

    private final DKMotd dkMotd;

    public DurationCommand(ObjectOwner owner, DKMotd dkMotd) {
        super(owner, CommandConfiguration.name("duration"));
        this.dkMotd = dkMotd;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length != 1) {
            sender.sendMessage(Messages.COMMAND_MAINTENANCE_HELP);
            return;
        }
        String rawDuration = args[0];
        Duration duration;
        try {
            duration = DurationProcessor.getStandard().parse(rawDuration);
        } catch (IllegalArgumentException exception) {
            sender.sendMessage(Messages.ERROR_DURATION_NOT_VALID, VariableSet.create().add("value", rawDuration));
            return;
        }
        long timeout = System.currentTimeMillis()+(duration.getSeconds()*1000);
        Maintenance maintenance = this.dkMotd.getMaintenance();
        if(maintenance.setTimeout(timeout)) {
            sender.sendMessage(Messages.COMMAND_MAINTENANCE_TIMEOUT, VariableSet.create()
                    .addDescribed("maintenance", maintenance));
        }
    }
}
