package net.pretronic.dkmotd.minecraft.commands.joinmessage.secondmessages;

import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class ListCommand extends ObjectCommand<JoinMessageTemplate> {

    public ListCommand(ObjectOwner owner) {
        super(owner, CommandConfiguration.name("list"));
    }

    @Override
    public void execute(CommandSender sender, JoinMessageTemplate template, String[] args) {
        sender.sendMessage(Messages.COMMAND_JOINMESSAGE_SECONDMESSAGES_LIST, VariableSet.create().addDescribed("template", template));
    }
}
