package net.pretronic.dkmotd.common.event.maintenance.whitelist;

import net.pretronic.dkmotd.api.event.maintenance.whitelist.MaintenanceWhitelistClearEvent;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.maintenance.DefaultMaintenanceCancelAbleEvent;

public class DefaultMaintenanceWhitelistClearEvent extends DefaultMaintenanceCancelAbleEvent implements MaintenanceWhitelistClearEvent {

    public DefaultMaintenanceWhitelistClearEvent(DefaultDKMotd dkMotd) {
        super(dkMotd);
    }
}
