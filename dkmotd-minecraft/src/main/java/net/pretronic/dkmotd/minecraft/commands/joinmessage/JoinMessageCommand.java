package net.pretronic.dkmotd.minecraft.commands.joinmessage;

import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.minecraft.commands.joinmessage.secondmessages.SecondMessagesCommand;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.DefinedNotFindable;
import net.pretronic.libraries.command.command.object.MainObjectCommand;
import net.pretronic.libraries.command.command.object.ObjectNotFindable;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

import java.util.Arrays;

public class JoinMessageCommand extends MainObjectCommand<JoinMessageTemplate> implements DefinedNotFindable<JoinMessageTemplate>, ObjectNotFindable {

    private final DefaultDKMotd dkMotd;

    private final InfoCommand infoCommand;
    private final ListCommand listCommand;
    private final CreateCommand createCommand;

    public JoinMessageCommand(ObjectOwner owner, CommandConfiguration configuration, DefaultDKMotd dkMotd) {
        super(owner, configuration);
        this.dkMotd = dkMotd;

        this.infoCommand = new InfoCommand(owner);
        this.listCommand = new ListCommand(owner, dkMotd);
        this.createCommand = new CreateCommand(owner, dkMotd);
        registerCommand(new ActiveCommand(owner, dkMotd));
        registerCommand(new BaseMessageCommand(owner));
        registerCommand(new NameCommand(owner, dkMotd));
        registerCommand(new SecondMessagesCommand(owner));
    }

    @Override
    public JoinMessageTemplate getObject(CommandSender sender, String name) {
        if(name.equalsIgnoreCase("list")) return null;
        return this.dkMotd.getJoinMessageTemplateManager().getTemplate(name);
    }

    @Override
    public void commandNotFound(CommandSender commandSender, JoinMessageTemplate template, String command, String[] args) {
        if(template != null && command == null) {
            infoCommand.execute(commandSender, template, args);
        } else {
            commandSender.sendMessage(Messages.COMMAND_JOINMESSAGE_HELP);
        }
    }

    @Override
    public void objectNotFound(CommandSender commandSender, String command, String[] args) {
        if(command.equalsIgnoreCase("list") || command.equalsIgnoreCase("l")) {
            this.listCommand.execute(commandSender, args);
        } else if(args.length > 0 && (args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("c"))) {
            this.createCommand.execute(commandSender, command, Arrays.copyOfRange(args, 1, args.length));
        } else {
            commandSender.sendMessage(Messages.ERROR_JOINMESSAGE_TEMPLATE_NOT_EXISTS, VariableSet.create()
                    .add("name", command));
        }
    }
}
