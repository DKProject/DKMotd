package net.pretronic.dkmotd.minecraft.commands.motd.edit.object;

import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.minecraft.commands.CommandUtil;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.Textable;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.GeneralUtil;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public abstract class ModifyCommand extends ObjectCommand<MotdTemplate> {

    private final Textable successMessage;

    public ModifyCommand(ObjectOwner owner, Textable successMessage) {
        super(owner, CommandConfiguration.name("modify"));
        this.successMessage = successMessage;
    }

    @Override
    public void execute(CommandSender sender, MotdTemplate template, String[] args) {
        if(args.length < 2) {
            sender.sendMessage(Messages.COMMAND_MOTD_HELP);
            return;
        }
        String rawIndex = args[0];
        if(!GeneralUtil.isNaturalNumber(rawIndex)) {
            sender.sendMessage(Messages.ERROR_INDEX_NOT_VALID, VariableSet.create().add("index", rawIndex));
            return;
        }
        int index = Integer.parseInt(rawIndex)-1;
        String value = CommandUtil.readStringFromArguments(args, 0);
        if(change(template, index, value)) {
            sender.sendMessage(this.successMessage, VariableSet.create()
                    .addDescribed("template", template));
        }
    }

    protected abstract boolean change(MotdTemplate template, int index, String value);
}
