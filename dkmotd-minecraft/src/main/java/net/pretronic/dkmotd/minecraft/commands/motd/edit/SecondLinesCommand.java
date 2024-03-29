package net.pretronic.dkmotd.minecraft.commands.motd.edit;

import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.minecraft.commands.motd.edit.object.EditObjectListCommand;
import net.pretronic.dkmotd.minecraft.commands.motd.edit.object.ModifyCommand;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

import java.util.Collections;

public class SecondLinesCommand extends EditObjectListCommand {

    public SecondLinesCommand(ObjectOwner owner) {
        super(owner, "secondLines", Messages.COMMAND_MOTD_SECONDLINES_HELP, Messages.COMMAND_MOTD_SECONDLINES_ADD, Messages.COMMAND_MOTD_SECONDLINES_REMOVE,
                Messages.COMMAND_MOTD_SECONDLINES_SET, Messages.COMMAND_MOTD_SECONDLINES_CLEAR, Messages.COMMAND_MOTD_SECONDLINES_LIST);
        registerCommand(new ModifyCommand(owner, Messages.COMMAND_MOTD_SECONDLINES_MODIFY) {
            @Override
            protected boolean change(MotdTemplate template, int index, String value) {
                return template.modifySecondLine(index, value);
            }
        });
    }

    @Override
    protected boolean add(MotdTemplate template, String text) {
        return template.addSecondLine(text);
    }

    @Override
    protected boolean remove(MotdTemplate template, int index) {
        return template.removeSecondLine(index);
    }

    @Override
    protected boolean canRemove(MotdTemplate template, int index) {
        return template.getSecondLines() != null && template.getSecondLines().size() > index;
    }

    @Override
    protected boolean set(MotdTemplate template, String text) {
        return template.setSecondsLines(Collections.singletonList(text));
    }

    @Override
    protected boolean clear(MotdTemplate template) {
        return template.clearSecondLines();
    }
}
