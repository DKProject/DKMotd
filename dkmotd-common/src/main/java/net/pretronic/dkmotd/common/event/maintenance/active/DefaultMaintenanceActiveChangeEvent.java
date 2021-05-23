package net.pretronic.dkmotd.common.event.maintenance.active;

import net.pretronic.dkmotd.api.event.maintenance.active.MaintenanceActiveChangeEvent;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.maintenance.DefaultMaintenanceCancelAbleEvent;

public class DefaultMaintenanceActiveChangeEvent extends DefaultMaintenanceCancelAbleEvent implements MaintenanceActiveChangeEvent {

    private boolean active;

    public DefaultMaintenanceActiveChangeEvent(DefaultDKMotd dkMotd, boolean active) {
        super(dkMotd);
        this.active = active;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
