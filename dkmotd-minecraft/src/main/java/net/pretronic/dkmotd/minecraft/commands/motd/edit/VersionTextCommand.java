package net.pretronic.dkmotd.minecraft.commands.motd.edit;

import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.minecraft.commands.motd.edit.object.EditStringCommand;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class VersionTextCommand extends EditStringCommand {

    public VersionTextCommand(ObjectOwner owner) {
        super(owner,  Messages.COMMAND_MOTD_VERSION_TEXT, "versionText");
    }

    @Override
    protected boolean change(MotdTemplate template, String text) {
        return template.setVersionText(text);
    }
}
