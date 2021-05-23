package net.pretronic.dkmotd.minecraft.commands.motd;

import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class InfoCommand extends ObjectCommand<MotdTemplate> {

    public InfoCommand(ObjectOwner owner) {
        super(owner, CommandConfiguration.name("info"));
    }

    @Override
    public void execute(CommandSender commandSender, MotdTemplate template, String[] args) {
        commandSender.sendMessage(Messages.COMMAND_MOTD_INFO, VariableSet.create().addDescribed("template", template));
    }
}
