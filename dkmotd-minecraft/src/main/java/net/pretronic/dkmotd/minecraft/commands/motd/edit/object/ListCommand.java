package net.pretronic.dkmotd.minecraft.commands.motd.edit.object;

import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.Textable;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class ListCommand extends ObjectCommand<MotdTemplate> {

    private final Textable listMessage;

    public ListCommand(ObjectOwner owner, Textable listMessage) {
        super(owner, CommandConfiguration.name("list"));
        this.listMessage = listMessage;
    }

    @Override
    public void execute(CommandSender sender, MotdTemplate template, String[] args) {
        sender.sendMessage(this.listMessage, VariableSet.create().addDescribed("template", template));
    }
}
