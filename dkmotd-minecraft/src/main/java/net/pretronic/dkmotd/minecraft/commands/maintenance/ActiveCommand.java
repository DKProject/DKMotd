package net.pretronic.dkmotd.minecraft.commands.maintenance;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.maintenance.Maintenance;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.BasicCommand;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class ActiveCommand extends BasicCommand {

    private final DKMotd dkMotd;

    public ActiveCommand(ObjectOwner owner, DKMotd dkMotd) {
        super(owner, CommandConfiguration.name("active", "toggle"));
        this.dkMotd = dkMotd;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Maintenance maintenance = dkMotd.getMaintenance();
        if(maintenance.setActive(!maintenance.isActive())) {
            if(maintenance.isActive()) {
                sender.sendMessage(Messages.COMMAND_MAINTENANCE_ACTIVE_ON);
            } else {
                sender.sendMessage(Messages.COMMAND_MAINTENANCE_ACTIVE_OFF);
            }
        }

    }
}
