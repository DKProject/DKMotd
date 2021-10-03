package net.pretronic.dkmotd.minecraft.commands.tablist;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.NotFindable;
import net.pretronic.libraries.command.command.MainCommand;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class TablistCommand extends MainCommand implements NotFindable {

    public TablistCommand(ObjectOwner owner, CommandConfiguration configuration, DKMotd dkMotd) {
        super(owner, configuration);
        registerCommand(new HeaderCommand(owner, dkMotd));
        registerCommand(new FooterCommand(owner, dkMotd));
        registerCommand(new LabyModServerBannerCommand(owner, dkMotd));
    }

    @Override
    public void commandNotFound(CommandSender sender, String command, String[] args) {
        sender.sendMessage(Messages.COMMAND_TABLIST_HELP);
    }
}
