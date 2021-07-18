package net.pretronic.dkmotd.minecraft.commands.maintenance.whitelist;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.NotFindable;
import net.pretronic.libraries.command.command.MainCommand;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class WhitelistCommand extends MainCommand implements NotFindable {

    public WhitelistCommand(ObjectOwner owner, DKMotd dkMotd) {
        super(owner, CommandConfiguration.name("whitelist"));
        registerCommand(new AddCommand(owner, dkMotd));
        registerCommand(new RemoveCommand(owner, dkMotd));
        registerCommand(new InfoCommand(owner, dkMotd));
        registerCommand(new ClearCommand(owner, dkMotd));
    }

    @Override
    public void commandNotFound(CommandSender sender, String command, String[] args) {
        sender.sendMessage(Messages.COMMAND_MAINTENANCE_WHITELIST_HELP);
    }
}
