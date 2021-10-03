package net.pretronic.dkmotd.minecraft.commands.tablist;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.minecraft.commands.CommandUtil;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.BasicCommand;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import net.pretronic.libraries.utility.io.FileUtil;

import javax.imageio.ImageIO;
import java.io.IOException;

public class LabyModServerBannerCommand extends BasicCommand {

    private final DKMotd dkMotd;

    public LabyModServerBannerCommand(ObjectOwner owner, DKMotd dkMotd) {
        super(owner, CommandConfiguration.name("labyModServerBanner"));
        this.dkMotd = dkMotd;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Messages.COMMAND_TABLIST_HELP);
            return;
        }
        String bannerUrl = CommandUtil.readStringFromArguments(args, 0);
        if(bannerUrl.equalsIgnoreCase("unset")) {
            bannerUrl = null;
        }else if(!bannerUrl.startsWith("http")) {
            sender.sendMessage(Messages.ERROR_FAVICON_NOT_VALID, VariableSet.create().add("value", bannerUrl));
            return;
        }
        if(this.dkMotd.getTablist().setLabyModServerBannerUrl(bannerUrl)) {
            sender.sendMessage(Messages.COMMAND_TABLIST_LABYMODSERVERBANNER, VariableSet.create().addDescribed("tablist", dkMotd.getTablist()));
        }
    }
}
