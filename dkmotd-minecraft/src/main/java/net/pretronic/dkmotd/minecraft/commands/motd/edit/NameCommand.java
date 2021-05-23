package net.pretronic.dkmotd.minecraft.commands.motd.edit;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.minecraft.commands.motd.edit.object.EditStringCommand;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class NameCommand extends EditStringCommand {

    private final DKMotd dkMotd;

    public NameCommand(ObjectOwner owner, DKMotd dkMotd) {
        super(owner, Messages.COMMAND_MOTD_NAME, Messages.ERROR_MOTD_TEMPLATE_ALREADY_EXISTS, "name");
        this.dkMotd = dkMotd;
    }

    @Override
    protected boolean change(MotdTemplate template, String value) {
        return template.setName(value);
    }

    @Override
    protected String parse(String[] args) {
        String name = args[0];
        MotdTemplate template1 = this.dkMotd.getMotdTemplateManager().getTemplate(name);
        if(template1 != null) {
            return null;
        }
        return name;
    }
}
