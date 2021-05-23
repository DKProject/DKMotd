package net.pretronic.dkmotd.minecraft.commands.joinmessage.secondmessages;

import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class ClearCommand extends ObjectCommand<JoinMessageTemplate> {

    public ClearCommand(ObjectOwner owner) {
        super(owner, CommandConfiguration.name("clear"));
    }

    @Override
    public void execute(CommandSender sender, JoinMessageTemplate template, String[] args) {
        if(template.clearSecondMessages()) {
            sender.sendMessage(Messages.COMMAND_JOINMESSAGE_SECONDMESSAGES_CLEAR, VariableSet.create()
                    .addDescribed("template", template));
        }
    }
}
