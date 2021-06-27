package net.pretronic.dkmotd.minecraft.listener;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.api.maintenance.Maintenance;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.common.motd.DefaultMotdTemplateManager;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.dkmotd.minecraft.config.Permissions;
import net.pretronic.libraries.event.EventPriority;
import net.pretronic.libraries.event.Listener;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.GeneralUtil;
import net.pretronic.libraries.utility.Iterators;
import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.event.player.login.MinecraftPlayerLoginEvent;
import org.mcnative.runtime.api.event.player.login.MinecraftPlayerPostLoginEvent;
import org.mcnative.runtime.api.event.service.local.LocalServicePingEvent;
import org.mcnative.runtime.api.network.component.server.ServerStatusResponse;
import org.mcnative.runtime.api.player.OnlineMinecraftPlayer;
import org.mcnative.runtime.api.protocol.MinecraftEdition;
import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;
import org.mcnative.runtime.api.text.Text;
import org.mcnative.runtime.api.text.components.MessageComponent;

import java.util.Collection;

public class PerformListener {

    private final DKMotd dkMotd;

    public PerformListener(DKMotd dkMotd) {
        this.dkMotd = dkMotd;
    }

    @Listener(priority = EventPriority.HIGH)
    public void onPing(LocalServicePingEvent event) {
        setServerStatusResponse(event.getResponse());
    }

    @Listener
    public void onLogin(MinecraftPlayerLoginEvent event) {
        if(event.isCancelled()) return;
        Maintenance maintenance = dkMotd.getMaintenance();
        if(maintenance.isActive() && !event.getOnlinePlayer().hasPermission(Permissions.MAINTENANCE_BYPASS)) {
            event.setCancelled(true);
            MessageComponent<?> cancelMessage = maintenance.hasTimeout()
                    ? Messages.MAINTENANCE_MESSAGE_TIMEOUT
                    : Messages.MAINTENANCE_MESSAGE_PERMANENT;
            event.setCancelReason(cancelMessage, VariableSet.create().addDescribed("maintenance", maintenance));
        }
    }

    @Listener(priority = EventPriority.LOW)
    public void onPostLogin(MinecraftPlayerPostLoginEvent event) {
        sendJoinMessage(event.getOnlinePlayer());
    }

    private void setServerStatusResponse(ServerStatusResponse response) {
        MotdTemplate template = this.dkMotd.getMaintenance().isActive()
                ? this.dkMotd.getMotdTemplateManager().getTemplate(DefaultMotdTemplateManager.DEFAULT_MAINTENANCE_TEMPLATE_NAME)
                : this.dkMotd.getMotdTemplateManager().getActiveTemplate();

        if(template != null) {
            if(template.getBaseLine() != null || template.getSecondLines() != null) {
                String baseLine = template.getBaseLine() != null ? template.getBaseLine() : "";
                String secondLine = "";
                if(template.getSecondLines() != null && !template.getSecondLines().isEmpty()) {
                    secondLine = GeneralUtil.getRandomItem(template.getSecondLines());
                }
                response.setDescription(Text.parse(baseLine), Text.parse(secondLine));
            }

            Collection<MinecraftProtocolVersion> supportedVersions = null;
            if(template.getSupportedVersions() != null && !template.getSupportedVersions().isEmpty()) {
                supportedVersions = Iterators.map(template.getSupportedVersions(), version -> MinecraftProtocolVersion.of(MinecraftEdition.JAVA, version));
            } else {
                supportedVersions = McNative.getInstance().getPlatform().getJoinableProtocolVersions();
            }
            boolean supported = false;
            for (MinecraftProtocolVersion supportedVersion : supportedVersions) {
                if(response.getVersion().getProtocol() == supportedVersion) {
                    supported = true;
                    break;
                }
            }
            if(supported) {
                if(template.getVersionText() != null) {
                    response.setVersion(Text.translateAlternateColorCodes('&', template.getVersionText()), MinecraftProtocolVersion.UNKNOWN);
                }
            } else if(template.getWrongVersionText() != null) {
                response.setVersion(Text.translateAlternateColorCodes('&', template.getWrongVersionText()), MinecraftProtocolVersion.UNKNOWN);
            }

            if(template.getFavicon() != null) {
                response.setFavicon(template.getFavicon());
            }

            if(template.getPlayerInfo() != null && !template.getPlayerInfo().isEmpty()) {
                response.clearPlayerInfo();
                for (String text : template.getPlayerInfo()) {
                    response.addPlayerInfo(Text.translateAlternateColorCodes('&', text));
                }
            }
        }
    }

    private void sendJoinMessage(OnlineMinecraftPlayer player) {
        JoinMessageTemplate template = this.dkMotd.getJoinMessageTemplateManager().getActiveTemplate();
        if(template != null) {
            if(template.getBaseMessage() != null) {
                player.sendMessage(Text.parse(template.getBaseMessage()));
            }
            if(template.getSecondMessages() != null && !template.getSecondMessages().isEmpty()) {
                String message = GeneralUtil.getRandomItem(template.getSecondMessages());
                player.sendMessage(message);
            }
        }
    }
}
