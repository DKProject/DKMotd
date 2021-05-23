package net.pretronic.dkmotd.minecraft.commands.motd.edit.object;

import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.Textable;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public abstract class EditObjectCommand<O> extends ObjectCommand<MotdTemplate> {

    private final Textable successMessage;
    private final Textable failedMessage;

    public EditObjectCommand(ObjectOwner owner, Textable successMessage, Textable failedMessage, String commandName) {
        super(owner, CommandConfiguration.name(commandName));
        this.successMessage = successMessage;
        this.failedMessage = failedMessage;
    }

    @Override
    public void execute(CommandSender sender, MotdTemplate template, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Messages.COMMAND_MOTD_HELP);
            return;
        }
        O value = parse(args);
        if(value == null) {
            sender.sendMessage(failedMessage);
            return;
        }
        if(change(template, value)) {
            sender.sendMessage(this.successMessage, VariableSet.create()
                    .addDescribed("template", template));
            System.out.println(template.getBaseLine());
        }
    }

    protected abstract boolean change(MotdTemplate template, O value);

    protected abstract O parse(String[] args);
}
