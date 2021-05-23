package net.pretronic.dkmotd.minecraft.commands.motd;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class ActiveCommand extends ObjectCommand<MotdTemplate> {

    private final DKMotd dkMotd;

    public ActiveCommand(ObjectOwner owner, DKMotd dkMotd) {
        super(owner, CommandConfiguration.name("active"));
        this.dkMotd = dkMotd;
    }

    @Override
    public void execute(CommandSender sender, MotdTemplate template, String[] args) {
        this.dkMotd.getMotdTemplateManager().setActiveTemplate(template);
        sender.sendMessage(Messages.COMMAND_MOTD_ACTIVE, VariableSet.create().addDescribed("template", template));
    }
}
