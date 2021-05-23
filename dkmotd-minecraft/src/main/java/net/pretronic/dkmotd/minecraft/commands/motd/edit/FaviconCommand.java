package net.pretronic.dkmotd.minecraft.commands.motd.edit;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

/*

 */
public class FaviconCommand extends ObjectCommand<MotdTemplate> {

    public FaviconCommand(ObjectOwner owner) {
        super(owner, CommandConfiguration.name("favicon"));
    }

    @Override
    public void execute(CommandSender sender, MotdTemplate template, String[] args) {
        if(args.length != 1) {
            sender.sendMessage(Messages.COMMAND_MOTD_HELP);
            return;
        }
        String favicon = args[0];

        if(template.setFavicon(favicon)) {

        }
    }
}
