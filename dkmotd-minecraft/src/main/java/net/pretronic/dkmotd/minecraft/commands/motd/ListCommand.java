package net.pretronic.dkmotd.minecraft.commands.motd;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.BasicCommand;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class ListCommand extends BasicCommand {

    private final DKMotd dkMotd;

    public ListCommand(ObjectOwner owner, DKMotd dkMotd) {
        super(owner, CommandConfiguration.name("list"));
        this.dkMotd = dkMotd;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(Messages.COMMAND_MOTD_LIST, VariableSet.create()
                .addDescribed("templates", dkMotd.getMotdTemplateManager().getTemplates()));
    }
}
