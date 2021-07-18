package net.pretronic.dkmotd.common.event.maintenance.whitelist;

import net.pretronic.dkmotd.api.event.maintenance.whitelist.MaintenanceWhitelistRemoveEvent;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.maintenance.DefaultMaintenanceCancelAbleEvent;

import java.util.UUID;

public class DefaultMaintenanceWhitelistRemoveEvent extends DefaultMaintenanceCancelAbleEvent implements MaintenanceWhitelistRemoveEvent {

    private final UUID uniqueId;

    public DefaultMaintenanceWhitelistRemoveEvent(DefaultDKMotd dkMotd, UUID uniqueId) {
        super(dkMotd);
        this.uniqueId = uniqueId;
    }

    @Override
    public UUID getUniqueId() {
        return this.uniqueId;
    }
}
