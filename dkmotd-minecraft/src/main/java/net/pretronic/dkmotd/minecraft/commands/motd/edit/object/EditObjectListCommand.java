package net.pretronic.dkmotd.minecraft.commands.motd.edit.object;

import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.DefinedNotFindable;
import net.pretronic.libraries.command.command.object.MainObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.Textable;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public abstract class EditObjectListCommand extends MainObjectCommand<MotdTemplate> implements DefinedNotFindable<MotdTemplate> {

    private final Textable helpMessage;

    public EditObjectListCommand(ObjectOwner owner, String commandName, Textable helpMessage, Textable addSuccessMessage,
                                 Textable removeSuccessMessage, Textable setSuccessMessage, Textable clearSuccessMessage) {
        super(owner, CommandConfiguration.name(commandName));
        this.helpMessage = helpMessage;
        registerCommand(new EditStringCommand(owner, addSuccessMessage, "add") {
            @Override
            protected boolean change(MotdTemplate template, String text) {
                return add(template, text);
            }
        });
        registerCommand(new EditStringCommand(owner, setSuccessMessage, "set") {
            @Override
            protected boolean change(MotdTemplate template, String text) {
                return set(template, text);
            }
        });
        registerCommand(new RemoveCommand(owner, removeSuccessMessage) {
            @Override
            protected boolean remove(MotdTemplate template, int index) {
                return EditObjectListCommand.this.remove(template, index);
            }
        });
        registerCommand(new ClearCommand(owner, clearSuccessMessage) {
            @Override
            protected boolean clear(MotdTemplate template) {
                return EditObjectListCommand.this.clear(template);
            }
        });
    }

    @Override
    public MotdTemplate getObject(CommandSender sender, String name) {
        throw new UnsupportedOperationException("No objects available (Objects should be forwarded)");
    }

    @Override
    public void commandNotFound(CommandSender sender, MotdTemplate object, String command, String[] args) {
        sender.sendMessage(this.helpMessage);
    }

    protected abstract boolean add(MotdTemplate template, String text);

    protected abstract boolean remove(MotdTemplate template, int index);

    protected abstract boolean set(MotdTemplate template, String text);

    protected abstract boolean clear(MotdTemplate template);
}
