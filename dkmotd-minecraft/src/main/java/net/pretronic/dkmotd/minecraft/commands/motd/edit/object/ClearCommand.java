package net.pretronic.dkmotd.minecraft.commands.motd.edit.object;

import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.Textable;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public abstract class ClearCommand extends ObjectCommand<MotdTemplate> {

    private final Textable successMessage;

    public ClearCommand(ObjectOwner owner, Textable successMessage) {
        super(owner, CommandConfiguration.name("clear"));
        this.successMessage = successMessage;
    }

    @Override
    public void execute(CommandSender sender, MotdTemplate template, String[] args) {
        if(clear(template)) {
            sender.sendMessage(this.successMessage, VariableSet.create()
                    .addDescribed("template", template));
        }
    }

    protected abstract boolean clear(MotdTemplate template);
}
