package net.pretronic.dkmotd.minecraft.commands.joinmessage;

import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class BaseMessageCommand extends ObjectCommand<JoinMessageTemplate> {

    public BaseMessageCommand(ObjectOwner owner) {
        super(owner, CommandConfiguration.name("baseMessage"));
    }

    @Override
    public void execute(CommandSender sender, JoinMessageTemplate template, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Messages.COMMAND_JOINMESSAGE_HELP);
            return;
        }
        String text = args[0];

        if(template.setBaseMessage(text)) {
            sender.sendMessage(Messages.COMMAND_JOINMESSAGE_BASE_MESSAGE, VariableSet.create()
                    .addDescribed("template", template));
        }
    }
}
