package net.pretronic.dkmotd.common.event.maintenance.whitelist;

import net.pretronic.dkmotd.api.event.maintenance.whitelist.MaintenanceWhitelistAddEvent;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.maintenance.DefaultMaintenanceCancelAbleEvent;

import java.util.UUID;

public class DefaultMaintenanceWhitelistAddEvent extends DefaultMaintenanceCancelAbleEvent implements MaintenanceWhitelistAddEvent {

    private final UUID uniqueId;

    public DefaultMaintenanceWhitelistAddEvent(DefaultDKMotd dkMotd, UUID uniqueId) {
        super(dkMotd);
        this.uniqueId = uniqueId;
    }

    @Override
    public UUID getUniqueId() {
        return this.uniqueId;
    }
}
