package net.pretronic.dkmotd.minecraft.commands.joinmessage.secondmessages;

import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.GeneralUtil;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class RemoveCommand extends ObjectCommand<JoinMessageTemplate> {

    public RemoveCommand(ObjectOwner owner) {
        super(owner, CommandConfiguration.name("remove"));
    }

    @Override
    public void execute(CommandSender sender, JoinMessageTemplate template, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Messages.COMMAND_JOINMESSAGE_HELP);
            return;
        }
        String rawIndex = args[0];
        if(!GeneralUtil.isNaturalNumber(rawIndex)) {
            sender.sendMessage(Messages.ERROR_INDEX_NOT_VALID, VariableSet.create().add("index", rawIndex));
            return;
        }
        int index = Integer.parseInt(rawIndex)-1;
        if(template.removeSecondMessage(index)) {
            sender.sendMessage(Messages.COMMAND_JOINMESSAGE_SECONDMESSAGES_REMOVE, VariableSet.create()
                    .addDescribed("template", template));
        }
    }
}
