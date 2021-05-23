package net.pretronic.dkmotd.minecraft.commands.motd;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.minecraft.commands.motd.edit.*;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.DefinedNotFindable;
import net.pretronic.libraries.command.command.object.MainObjectCommand;
import net.pretronic.libraries.command.command.object.ObjectNotFindable;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.annonations.NotNull;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

import java.util.Arrays;

public class MotdCommand extends MainObjectCommand<MotdTemplate> implements DefinedNotFindable<MotdTemplate>, ObjectNotFindable {

    private final DefaultDKMotd dkMotd;

    private final InfoCommand infoCommand;
    private final ListCommand listCommand;
    private final CreateCommand createCommand;

    public MotdCommand(@NotNull ObjectOwner owner, @NotNull CommandConfiguration configuration, @NotNull DefaultDKMotd dkMotd) {
        super(owner, configuration);
        this.dkMotd = dkMotd;

        this.infoCommand = new InfoCommand(owner);
        this.listCommand = new ListCommand(owner, dkMotd);
        this.createCommand = new CreateCommand(owner, dkMotd);
        registerCommand(infoCommand);

        registerCommand(new BaseLineCommand(owner));
        registerCommand(new VersionTextCommand(owner));
        registerCommand(new WrongVersionTextCommand(owner));
        registerCommand(new FaviconCommand(owner));
        registerCommand(new ActiveCommand(owner, dkMotd));
        registerCommand(new SecondLinesCommand(owner));
        registerCommand(new PlayerInfoCommand(owner));
        registerCommand(new NameCommand(owner,dkMotd));
        registerCommand(new DeleteCommand(owner, dkMotd));
    }

    @Override
    public MotdTemplate getObject(CommandSender commandSender, String name) {
        if(name.equalsIgnoreCase("list")) return null;
        return this.dkMotd.getMotdTemplateManager().getTemplate(name);
    }

    @Override
    public void commandNotFound(CommandSender commandSender, MotdTemplate template, String command, String[] args) {
        if(template != null && command == null) {
            infoCommand.execute(commandSender, template, args);
        } else {
            commandSender.sendMessage(Messages.COMMAND_MOTD_HELP);
        }
    }

    @Override
    public void objectNotFound(CommandSender commandSender, String command, String[] args) {
        if(command.equalsIgnoreCase("list") || command.equalsIgnoreCase("l")) {
            this.listCommand.execute(commandSender, args);
        } else if(args.length > 0 && (args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("c"))) {
            this.createCommand.execute(commandSender, command, Arrays.copyOfRange(args, 1, args.length));
        } else {
            commandSender.sendMessage(Messages.ERROR_MOTD_TEMPLATE_NOT_EXISTS, VariableSet.create()
                    .add("name", command));
        }
    }
}
