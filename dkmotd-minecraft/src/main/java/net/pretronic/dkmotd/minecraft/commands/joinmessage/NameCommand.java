package net.pretronic.dkmotd.minecraft.commands.joinmessage;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class NameCommand extends ObjectCommand<JoinMessageTemplate> {

    private final DKMotd dkMotd;

    public NameCommand(ObjectOwner owner, DKMotd dkMotd) {
        super(owner, CommandConfiguration.name("name"));
        this.dkMotd = dkMotd;
    }

    @Override
    public void execute(CommandSender sender, JoinMessageTemplate template, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Messages.COMMAND_JOINMESSAGE_HELP);
            return;
        }
        String name = args[0];
        JoinMessageTemplate template1 = this.dkMotd.getJoinMessageTemplateManager().getTemplate(name);
        if(template1 != null) {
            sender.sendMessage(Messages.ERROR_JOINMESSAGE_TEMPLATE_ALREADY_EXISTS, VariableSet.create().addDescribed("template", template1));
            return;
        }

        if(template.setName(name)) {
            sender.sendMessage(Messages.COMMAND_JOINMESSAGE_NAME, VariableSet.create()
                    .addDescribed("template", template));
        }
    }
}
