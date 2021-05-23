package net.pretronic.dkmotd.common.maintenance;

import net.pretronic.dkmotd.api.event.maintenance.active.MaintenanceActiveChangeEvent;
import net.pretronic.dkmotd.api.event.maintenance.active.MaintenanceActiveChangedEvent;
import net.pretronic.dkmotd.api.event.maintenance.timeout.MaintenanceTimeoutChangeEvent;
import net.pretronic.dkmotd.api.event.maintenance.timeout.MaintenanceTimeoutChangedEvent;
import net.pretronic.dkmotd.api.maintenance.Maintenance;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.maintenance.active.DefaultMaintenanceActiveChangeEvent;
import net.pretronic.dkmotd.common.event.maintenance.active.DefaultMaintenanceActiveChangedEvent;
import net.pretronic.dkmotd.common.event.maintenance.timeout.DefaultMaintenanceTimeoutChangeEvent;
import net.pretronic.dkmotd.common.event.maintenance.timeout.DefaultMaintenanceTimeoutChangedEvent;

public class DefaultMaintenance implements Maintenance {

    private transient DefaultDKMotd dkMotd;

    private boolean active;
    private long timeout;

    public DefaultMaintenance(DefaultDKMotd dkMotd) {
        this.dkMotd = dkMotd;
    }

    @Override
    public boolean isActive() {
        return this.active || getTimeout() > System.currentTimeMillis();
    }

    @Override
    public boolean setActive(boolean active) {
        MaintenanceActiveChangeEvent event = new DefaultMaintenanceActiveChangeEvent(dkMotd, active);
        this.dkMotd.getEventBus().callEvent(MaintenanceActiveChangeEvent.class, event);
        if(event.isCancelled()) return false;
        this.active = event.isActive();
        this.dkMotd.updateMaintenanceStorage();

        this.dkMotd.getEventBus().callEvent(MaintenanceActiveChangedEvent.class, new DefaultMaintenanceActiveChangedEvent(dkMotd, this.active));
        return true;
    }

    @Override
    public long getTimeout() {
        return this.timeout;
    }

    @Override
    public boolean setTimeout(long timeout) {
        MaintenanceTimeoutChangeEvent event = new DefaultMaintenanceTimeoutChangeEvent(dkMotd, timeout);
        this.dkMotd.getEventBus().callEvent(MaintenanceTimeoutChangeEvent.class, event);
        if(event.isCancelled()) return false;
        this.timeout = event.getTimeout();
        this.dkMotd.updateMaintenanceStorage();

        this.dkMotd.getEventBus().callEvent(MaintenanceTimeoutChangedEvent.class, new DefaultMaintenanceTimeoutChangedEvent(dkMotd, this.timeout));
        return true;
    }

    public DefaultMaintenance setDKMotd(DefaultDKMotd dkMotd) {
        this.dkMotd = dkMotd;
        return this;
    }
}