package net.pretronic.dkmotd.minecraft.commands.joinmessage;

import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class InfoCommand extends ObjectCommand<JoinMessageTemplate> {

    public InfoCommand(ObjectOwner owner) {
        super(owner, CommandConfiguration.name("info"));
    }

    @Override
    public void execute(CommandSender sender, JoinMessageTemplate template, String[] args) {
        sender.sendMessage(Messages.COMMAND_JOINMESSAGE_INFO, VariableSet.create().addDescribed("template", template));
    }
}
