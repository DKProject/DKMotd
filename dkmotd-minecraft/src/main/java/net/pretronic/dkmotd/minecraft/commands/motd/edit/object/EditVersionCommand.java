package net.pretronic.dkmotd.minecraft.commands.motd.edit.object;

import net.pretronic.libraries.message.Textable;
import net.pretronic.libraries.utility.GeneralUtil;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public abstract class EditVersionCommand extends EditObjectCommand<Integer> {

    public EditVersionCommand(ObjectOwner owner, Textable successMessage, Textable failedMessage, String commandName) {
        super(owner, successMessage, failedMessage, commandName);
    }

    @Override
    protected Integer parse(String[] args) {
        String rawVersion = args[0];
        if(!GeneralUtil.isNaturalNumber(rawVersion)) return null;//@Todo check for valid protocol version
        return Integer.parseInt(rawVersion);
    }
}
