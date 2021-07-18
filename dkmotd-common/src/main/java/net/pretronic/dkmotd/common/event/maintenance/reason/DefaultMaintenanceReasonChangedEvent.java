package net.pretronic.dkmotd.common.event.maintenance.reason;

import net.pretronic.dkmotd.api.event.maintenance.active.MaintenanceActiveChangedEvent;
import net.pretronic.dkmotd.api.event.maintenance.reason.MaintenanceReasonChangedEvent;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.maintenance.DefaultMaintenanceEvent;

public class DefaultMaintenanceReasonChangedEvent extends DefaultMaintenanceEvent implements MaintenanceReasonChangedEvent {

    private final String newReason;

    public DefaultMaintenanceReasonChangedEvent(DefaultDKMotd dkMotd, String newReason) {
        super(dkMotd);
        this.newReason = newReason;
    }


    @Override
    public String getNewReason() {
        return this.newReason;
    }
}
