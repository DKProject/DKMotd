package net.pretronic.dkmotd.minecraft.commands.maintenance.whitelist;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.maintenance.Maintenance;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.BasicCommand;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.player.MinecraftPlayer;

public class AddCommand extends BasicCommand {

    private final DKMotd dkMotd;

    public AddCommand(ObjectOwner owner, DKMotd dkMotd) {
        super(owner, CommandConfiguration.name("add"));
        this.dkMotd = dkMotd;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Messages.COMMAND_MAINTENANCE_WHITELIST_HELP);
            return;
        }
        String playerName = args[0];
        MinecraftPlayer target = McNative.getInstance().getPlayerManager().getPlayer(playerName);
        if(target == null) {
            sender.sendMessage(Messages.ERROR_PLAYER_NOT_FOUND, VariableSet.create().add("name", playerName));
            return;
        }
        Maintenance maintenance = dkMotd.getMaintenance();
        if(maintenance.isWhitelisted(target.getUniqueId())) {
            sender.sendMessage(Messages.ERROR_MAINTENANCE_WHITELIST_PLAYER_ALREADY, VariableSet.create().addDescribed("player", target));
            return;
        }
        if(maintenance.addWhitelist(target.getUniqueId())) {
            sender.sendMessage(Messages.COMMAND_MAINTENANCE_WHITELIST_ADD, VariableSet.create().addDescribed("player", target));
        }
    }
}
