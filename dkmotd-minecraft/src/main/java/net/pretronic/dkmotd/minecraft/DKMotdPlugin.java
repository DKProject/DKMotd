package net.pretronic.dkmotd.minecraft;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.minecraft.commands.joinmessage.JoinMessageCommand;
import net.pretronic.dkmotd.minecraft.commands.maintenance.MaintenanceCommand;
import net.pretronic.dkmotd.minecraft.commands.motd.MotdCommand;
import net.pretronic.dkmotd.minecraft.config.DKMotdConfig;
import net.pretronic.dkmotd.minecraft.listener.PerformListener;
import net.pretronic.libraries.plugin.lifecycle.Lifecycle;
import net.pretronic.libraries.plugin.lifecycle.LifecycleState;
import org.mcnative.licensing.context.platform.McNativeLicenseIntegration;
import org.mcnative.licensing.exceptions.CloudNotCheckoutLicenseException;
import org.mcnative.licensing.exceptions.LicenseNotValidException;
import org.mcnative.runtime.api.plugin.MinecraftPlugin;

public class DKMotdPlugin extends MinecraftPlugin {

    public static String RESOURCE_ID ="9304d68a-bbdf-11eb-8ba0-0242ac180002";
    public static String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkzlaMKAoRUdoFOnCbd+ehZXQN4etqIWkalsGZjbKkdMnxpwhInWPtKptWqOZBa7/r8y/vZ0E0KjFXwj/dVDpjb5iCfAKw/keHsAQDF4I0vAknKtSajQjpO1DL4jFD8yaXnfbu3I4J1xbSsUgybUyvR+Mv6zyouJ4rIQd84Njd1+fRdLZnblmeFHXKjNyizSfyrJlgYOECmxccCjWMoJjZpk2CBNwqyCQOqB1/t8WZchPC0ZpXyLyY2PW3coB1YgmUqrve6t+Eyts6vsMTijRXBaHohKrXhmTYjqz/Bc4OeiggL2nPNbvDI99nIUu+2IY42AleZQu08HcbQ0TS5EZ5wIDAQAB";

    private DefaultDKMotd dkMotd;

    @Lifecycle(state = LifecycleState.LOAD)
    public void onLoad(LifecycleState state){
        getLogger().info("DKMotd is starting, please wait..");

        try{
            McNativeLicenseIntegration.newContext(this,RESOURCE_ID,PUBLIC_KEY).verifyOrCheckout();
        }catch (LicenseNotValidException | CloudNotCheckoutLicenseException e){
            getLogger().error("--------------------------------");
            getLogger().error("-> Invalid license");
            getLogger().error("-> Error: "+e.getMessage());
            getLogger().error("--------------------------------");
            getLogger().info("DKMotd is shutting down");
            getLoader().shutdown();
            return;
        }

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
