package net.pretronic.dkmotd.minecraft.commands.maintenance;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.minecraft.commands.maintenance.whitelist.WhitelistCommand;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.NotFindable;
import net.pretronic.libraries.command.command.MainCommand;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.utility.annonations.NotNull;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class MaintenanceCommand extends MainCommand implements NotFindable {

    public MaintenanceCommand(@NotNull ObjectOwner owner, @NotNull CommandConfiguration configuration, @NotNull DKMotd dkMotd) {
        super(owner, configuration);
        registerCommand(new ActiveCommand(owner, dkMotd));
        registerCommand(new TimeoutCommand(owner, dkMotd));
        registerCommand(new DurationCommand(owner, dkMotd));
        registerCommand(new InfoCommand(owner, dkMotd));
        registerCommand(new ReasonCommand(owner, dkMotd));
        registerCommand(new WhitelistCommand(owner, dkMotd));
    }

    @Override
    public void commandNotFound(CommandSender sender, String command, String[] args) {
        sender.sendMessage(Messages.COMMAND_MAINTENANCE_HELP);
    }
}
