package net.pretronic.dkmotd.minecraft.commands.motd.edit;

import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.minecraft.commands.motd.edit.object.EditObjectListCommand;
import net.pretronic.dkmotd.minecraft.commands.motd.edit.object.ModifyCommand;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

import java.util.Collections;

public class PlayerInfoCommand extends EditObjectListCommand {

    public PlayerInfoCommand(ObjectOwner owner) {
        super(owner, "playerInfo", Messages.COMMAND_MOTD_PLAYERINFO_HELP, Messages.COMMAND_MOTD_PLAYERINFO_ADD, Messages.COMMAND_MOTD_PLAYERINFO_REMOVE,
                Messages.COMMAND_MOTD_PLAYERINFO_SET, Messages.COMMAND_MOTD_PLAYERINFO_CLEAR, Messages.COMMAND_MOTD_PLAYERINFO_LIST);
        registerCommand(new ModifyCommand(owner, Messages.COMMAND_MOTD_PLAYERINFO_MODIFY) {
            @Override
            protected boolean change(MotdTemplate template, int index, String value) {
                return template.modifyPlayerInfo(index, value);
            }
        });
    }

    @Override
    protected boolean add(MotdTemplate template, String text) {
        return template.addPlayerInfo(text);
    }

    @Override
    protected boolean remove(MotdTemplate template, int index) {
        return template.removePlayerInfo(index);
    }

    @Override
    protected boolean set(MotdTemplate template, String text) {
        return template.setPlayerInfo(Collections.singletonList(text));
    }

    @Override
    protected boolean clear(MotdTemplate template) {
        return template.clearPlayerInfo();
    }
}
