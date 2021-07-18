package net.pretronic.dkmotd.minecraft.commands.joinmessage.secondmessages;

import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.DefinedNotFindable;
import net.pretronic.libraries.command.command.object.MainObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class SecondMessagesCommand extends MainObjectCommand<JoinMessageTemplate> implements DefinedNotFindable<JoinMessageTemplate> {

    public SecondMessagesCommand(ObjectOwner owner) {
        super(owner, CommandConfiguration.name("secondMessages"));
        registerCommand(new AddCommand(owner));
        registerCommand(new RemoveCommand(owner));
        registerCommand(new SetCommand(owner));
        registerCommand(new ClearCommand(owner));
        registerCommand(new ListCommand(owner));
    }

    @Override
    public JoinMessageTemplate getObject(CommandSender sender, String name) {
        throw new UnsupportedOperationException("No objects available (Objects should be forwarded)");
    }

    @Override
    public void commandNotFound(CommandSender sender, JoinMessageTemplate object, String command, String[] args) {
        sender.sendMessage(Messages.COMMAND_JOINMESSAGE_HELP);
    }
}
