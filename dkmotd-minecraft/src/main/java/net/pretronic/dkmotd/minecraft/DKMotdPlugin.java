package net.pretronic.dkmotd.minecraft;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.minecraft.commands.DKMotdCommand;
import net.pretronic.dkmotd.minecraft.commands.joinmessage.JoinMessageCommand;
import net.pretronic.dkmotd.minecraft.commands.maintenance.MaintenanceCommand;
import net.pretronic.dkmotd.minecraft.commands.motd.MotdCommand;
import net.pretronic.dkmotd.minecraft.commands.tablist.TablistCommand;
import net.pretronic.dkmotd.minecraft.config.DKMotdConfig;
import net.pretronic.dkmotd.minecraft.listener.PerformListener;
import net.pretronic.libraries.plugin.lifecycle.Lifecycle;
import net.pretronic.libraries.plugin.lifecycle.LifecycleState;
import org.mcnative.licensing.context.platform.McNativeLicenseIntegration;
import org.mcnative.licensing.exceptions.CloudNotCheckoutLicenseException;
import org.mcnative.licensing.exceptions.LicenseNotValidException;
import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.player.tablist.Tablist;
import org.mcnative.runtime.api.plugin.MinecraftPlugin;

public class DKMotdPlugin extends MinecraftPlugin {
    
    private DefaultDKMotd dkMotd;

    @Lifecycle(state = LifecycleState.LOAD)
    public void onLoad(LifecycleState state){
        getLogger().info("DKMotd is starting, please wait..");

        getConfiguration().load(DKMotdConfig.class);

        DefaultDKMotd dkMotd = new DefaultDKMotd(getDescription().getVersion().getName(),
                getRuntime().getLocal().getEventBus(),
                new MinecraftDKMotdStorage(this));
        this.dkMotd = dkMotd;

        DescriberRegistrar.register();
        registerListeners();
        registerCommands();

        getRuntime().getRegistry().registerService(this, DKMotd.class,dkMotd);

        if(DKMotdConfig.TABLIST_ENABLED) {
            Tablist tablist = McNative.getInstance().getLocal().getServerTablist();
            if(tablist == null) {
                tablist = Tablist.newTablist();
                McNative.getInstance().getLocal().setServerTablist(tablist);
            }
            tablist.setOverviewFormatter(new DKMotdTablistOverviewFormatter(dkMotd));
        }

        getLogger().info("DKMotd started successfully");
    }

    private void registerListeners() {
        getRuntime().getLocal().getEventBus().subscribe(this, new PerformListener(this.dkMotd));
    }

    private void registerCommands() {
        getRuntime().getLocal().getCommandManager().registerCommand(new MotdCommand(this, DKMotdConfig.COMMAND_MOTD, dkMotd));
        getRuntime().getLocal().getCommandManager().registerCommand(new MaintenanceCommand(this, DKMotdConfig.COMMAND_MAINTENANCE, dkMotd));
        getRuntime().getLocal().getCommandManager().registerCommand(new JoinMessageCommand(this, DKMotdConfig.COMMAND_JOIN_MESSAGE, dkMotd));
        getRuntime().getLocal().getCommandManager().registerCommand(new TablistCommand(this, DKMotdConfig.COMMAND_TABLIST, dkMotd));
        getRuntime().getLocal().getCommandManager().registerCommand(new DKMotdCommand(this));
    }
}
