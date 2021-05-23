package net.pretronic.dkmotd.common.event.maintenance.timeout;

import net.pretronic.dkmotd.api.event.maintenance.timeout.MaintenanceTimeoutChangeEvent;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.maintenance.DefaultMaintenanceCancelAbleEvent;

public class DefaultMaintenanceTimeoutChangeEvent extends DefaultMaintenanceCancelAbleEvent implements MaintenanceTimeoutChangeEvent {

    private long timeout;

    public DefaultMaintenanceTimeoutChangeEvent(DefaultDKMotd dkMotd, long timeout) {
        super(dkMotd);
        this.timeout = timeout;
    }

    @Override
    public long getTimeout() {
        return this.timeout;
    }

    @Override
    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
