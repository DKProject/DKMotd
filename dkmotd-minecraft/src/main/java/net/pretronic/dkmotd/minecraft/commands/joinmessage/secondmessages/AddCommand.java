package net.pretronic.dkmotd.minecraft.commands.joinmessage.secondmessages;

import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.minecraft.commands.CommandUtil;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class AddCommand extends ObjectCommand<JoinMessageTemplate> {

    public AddCommand(ObjectOwner owner) {
        super(owner, CommandConfiguration.name("add"));
    }

    @Override
    public void execute(CommandSender sender, JoinMessageTemplate template, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Messages.COMMAND_JOINMESSAGE_SECONDMESSAGES_HELP);
            return;
        }
        String value = CommandUtil.readStringFromArguments(args, 0);
        if(template.addSecondMessage(value)) {
            sender.sendMessage(Messages.COMMAND_JOINMESSAGE_SECONDMESSAGES_ADD, VariableSet.create()
                    .addDescribed("template", template));
        }
    }
}
