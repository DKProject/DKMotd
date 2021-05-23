package net.pretronic.dkmotd.minecraft.commands.motd;

import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.motd.DefaultMotdTemplateManager;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class DeleteCommand extends ObjectCommand<MotdTemplate> {

    private final DefaultDKMotd dkMotd;

    public DeleteCommand(ObjectOwner owner, DefaultDKMotd dkMotd) {
        super(owner, CommandConfiguration.name("delete"));
        this.dkMotd = dkMotd;
    }

    @Override
    public void execute(CommandSender sender, MotdTemplate template, String[] args) {
        if(template.getName().equalsIgnoreCase(DefaultMotdTemplateManager.DEFAULT_TEMPLATE_NAME)
                || template.getName().equalsIgnoreCase(DefaultMotdTemplateManager.DEFAULT_MAINTENANCE_TEMPLATE_NAME)) {
            sender.sendMessage(Messages.ERROR_MOTD_TEMPLATE_DELETE_NOT_POSSIBLE, VariableSet.create()
                    .addDescribed("template", template));
            return;
        }
        if(this.dkMotd.getMotdTemplateManager().deleteTemplate(template)) {
            sender.sendMessage(Messages.COMMAND_MOTD_DELETE, VariableSet.create().addDescribed("template", template));
        }
    }
}
