package net.pretronic.dkmotd.minecraft;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.minecraft.config.DKMotdConfig;
import net.pretronic.libraries.plugin.lifecycle.Lifecycle;
import net.pretronic.libraries.plugin.lifecycle.LifecycleState;
import org.mcnative.runtime.api.plugin.MinecraftPlugin;

public class DKMotdPlugin extends MinecraftPlugin {

    @Lifecycle(state = LifecycleState.LOAD)
    public void onLoad(LifecycleState state){
        getLogger().info("DKMotd is starting, please wait..");

        getConfiguration().load(DKMotdConfig.class);

        DefaultDKMotd dkMotd = new DefaultDKMotd(getDescription().getVersion().getName()
                ,getDatabaseOrCreate()
                ,getRuntime().getLocal().getEventBus());

        DescriberRegistrar.register();
        registerListeners(dkMotd);
        registerCommands(dkMotd);

        getRuntime().getRegistry().registerService(this, DKMotd.class,dkMotd);

        getLogger().info("DKMotd started successfully");
    }

    private void registerListeners(DefaultDKMotd dkMotd){

    }

    private void registerCommands(DefaultDKMotd dkMotd){

    }
}
