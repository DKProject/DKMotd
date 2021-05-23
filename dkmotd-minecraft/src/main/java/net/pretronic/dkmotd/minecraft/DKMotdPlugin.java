package net.pretronic.dkmotd.minecraft;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.maintenance.Maintenance;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.motd.DefaultMotdTemplateManager;
import net.pretronic.dkmotd.minecraft.commands.joinmessage.JoinMessageCommand;
import net.pretronic.dkmotd.minecraft.commands.maintenance.MaintenanceCommand;
import net.pretronic.dkmotd.minecraft.commands.motd.MotdCommand;
import net.pretronic.dkmotd.minecraft.config.DKMotdConfig;
import net.pretronic.dkmotd.minecraft.listener.PerformListener;
import net.pretronic.libraries.plugin.lifecycle.Lifecycle;
import net.pretronic.libraries.plugin.lifecycle.LifecycleState;
import net.pretronic.libraries.utility.GeneralUtil;
import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.annonations.Internal;
import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.network.component.server.ServerStatusResponse;
import org.mcnative.runtime.api.plugin.MinecraftPlugin;
import org.mcnative.runtime.api.protocol.MinecraftEdition;
import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;
import org.mcnative.runtime.api.text.Text;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

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

        getLogger().info("DKMotd started successfully");
    }

    private void registerListeners() {
        getRuntime().getLocal().getEventBus().subscribe(this, new PerformListener(this.dkMotd));
    }

    private void registerCommands() {
        getRuntime().getLocal().getCommandManager().registerCommand(new MotdCommand(this, DKMotdConfig.COMMAND_MOTD, dkMotd));
        getRuntime().getLocal().getCommandManager().registerCommand(new MaintenanceCommand(this, DKMotdConfig.COMMAND_MAINTENANCE, dkMotd));
        getRuntime().getLocal().getCommandManager().registerCommand(new JoinMessageCommand(this, DKMotdConfig.COMMAND_JOIN_MESSAGE, dkMotd));
    }


}
