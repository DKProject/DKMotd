package net.pretronic.dkmotd.common.event.maintenance.timeout;

import net.pretronic.dkmotd.api.event.maintenance.timeout.MaintenanceTimeoutChangedEvent;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.maintenance.DefaultMaintenanceEvent;

public class DefaultMaintenanceTimeoutChangedEvent extends DefaultMaintenanceEvent implements MaintenanceTimeoutChangedEvent {

    private final long newTimeout;

    public DefaultMaintenanceTimeoutChangedEvent(DefaultDKMotd dkMotd, long newTimeout) {
        super(dkMotd);
        this.newTimeout = newTimeout;
    }

    @Override
    public long getNewTimeout() {
        return this.newTimeout;
    }
}
