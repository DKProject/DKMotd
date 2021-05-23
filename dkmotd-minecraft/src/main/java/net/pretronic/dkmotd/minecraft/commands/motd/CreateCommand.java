package net.pretronic.dkmotd.minecraft.commands.motd;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class CreateCommand extends ObjectCommand<String> {

    private final DKMotd dkMotd;

    public CreateCommand(ObjectOwner owner, DKMotd dkMotd) {
        super(owner, CommandConfiguration.name("create"));
        this.dkMotd = dkMotd;
    }

    @Override
    public void execute(CommandSender commandSender, String name, String[] args) {
        MotdTemplate template = this.dkMotd.getMotdTemplateManager().getTemplate(name);
        if(template != null) {
            commandSender.sendMessage(Messages.ERROR_MOTD_TEMPLATE_ALREADY_EXISTS, VariableSet.create()
                    .addDescribed("template", template));
            return;
        }
        template = this.dkMotd.getMotdTemplateManager().createTemplate(name);
        if(template != null) {
            commandSender.sendMessage(Messages.COMMAND_MOTD_CREATE, VariableSet.create().addDescribed("template", template));
        }
    }
}
