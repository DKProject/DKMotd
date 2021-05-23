package net.pretronic.dkmotd.minecraft.commands.joinmessage;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
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
    public void execute(CommandSender sender, String name, String[] args) {
        JoinMessageTemplate template = this.dkMotd.getJoinMessageTemplateManager().getTemplate(name);
        if(template != null) {
            sender.sendMessage(Messages.ERROR_JOINMESSAGE_TEMPLATE_ALREADY_EXISTS, VariableSet.create()
                    .addDescribed("template", template));
            return;
        }
        template = this.dkMotd.getJoinMessageTemplateManager().createTemplate(name);
        if(template != null) {
            sender.sendMessage(Messages.COMMAND_JOINMESSAGE_CREATE, VariableSet.create().addDescribed("template", template));
        }
    }
}
