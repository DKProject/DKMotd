package net.pretronic.dkmotd.common.event.maintenance;

import net.pretronic.dkmotd.api.event.maintenance.MaintenanceEvent;
import net.pretronic.dkmotd.api.maintenance.Maintenance;
import net.pretronic.dkmotd.common.DefaultDKMotd;

public class DefaultMaintenanceEvent implements MaintenanceEvent {

    private final DefaultDKMotd dkMotd;

    public DefaultMaintenanceEvent(DefaultDKMotd dkMotd) {
        this.dkMotd = dkMotd;
    }

    @Override
    public Maintenance getMaintenance() {
        return this.dkMotd.getMaintenance();
    }
}
