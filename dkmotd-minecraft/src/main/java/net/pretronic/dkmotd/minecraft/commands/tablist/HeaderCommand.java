package net.pretronic.dkmotd.minecraft.commands.tablist;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.minecraft.commands.CommandUtil;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.BasicCommand;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

public class HeaderCommand extends BasicCommand {

    private final DKMotd dkMotd;

    public HeaderCommand(ObjectOwner owner, DKMotd dkMotd) {
        super(owner, CommandConfiguration.name("header"));
        this.dkMotd = dkMotd;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Messages.COMMAND_TABLIST_HELP);
            return;
        }
        String header = CommandUtil.readStringFromArguments(args, 0);
        if(this.dkMotd.getTablist().setHeader(header)) {
            sender.sendMessage(Messages.COMMAND_TABLIST_HEADER, VariableSet.create().addDescribed("tablist", dkMotd.getTablist()));
        }
    }
}
