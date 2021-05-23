package net.pretronic.dkmotd.minecraft.commands.motd.edit.object;

import net.pretronic.dkmotd.minecraft.commands.CommandUtil;
import net.pretronic.libraries.message.Textable;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public abstract class EditStringCommand extends EditObjectCommand<String> {

    public EditStringCommand(ObjectOwner owner, Textable successMessage, Textable failedMessage, String commandName) {
        super(owner, successMessage, failedMessage, commandName);
    }

    public EditStringCommand(ObjectOwner owner, Textable successMessage, String commandName) {
        super(owner, successMessage, null, commandName);
    }

    @Override
    protected String parse(String[] args) {
        return CommandUtil.readStringFromArguments(args, 0);
    }
}
