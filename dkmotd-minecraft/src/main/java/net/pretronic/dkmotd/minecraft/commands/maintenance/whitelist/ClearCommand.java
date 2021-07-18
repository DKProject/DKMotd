package net.pretronic.dkmotd.minecraft.commands.maintenance.whitelist;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.BasicCommand;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class ClearCommand extends BasicCommand {

    private final DKMotd dkMotd;

    public ClearCommand(ObjectOwner owner, DKMotd dkMotd) {
        super(owner, CommandConfiguration.name("clear"));
        this.dkMotd = dkMotd;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(dkMotd.getMaintenance().clearWhitelist()) {
            sender.sendMessage(Messages.COMMAND_MAINTENANCE_WHITELIST_CLEAR);
        }
    }
}
