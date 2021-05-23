package net.pretronic.dkmotd.common.event.maintenance.active;

import net.pretronic.dkmotd.api.event.maintenance.active.MaintenanceActiveChangedEvent;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.maintenance.DefaultMaintenanceEvent;

public class DefaultMaintenanceActiveChangedEvent extends DefaultMaintenanceEvent implements MaintenanceActiveChangedEvent {

    private final boolean newActive;

    public DefaultMaintenanceActiveChangedEvent(DefaultDKMotd dkMotd, boolean newActive) {
        super(dkMotd);
        this.newActive = newActive;
    }


    @Override
    public boolean getNewActive() {
        return this.newActive;
    }
}
