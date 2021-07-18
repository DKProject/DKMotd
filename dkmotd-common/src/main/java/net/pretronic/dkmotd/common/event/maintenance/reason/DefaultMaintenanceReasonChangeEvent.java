package net.pretronic.dkmotd.common.event.maintenance.reason;

import net.pretronic.dkmotd.api.event.maintenance.reason.MaintenanceReasonChangeEvent;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.maintenance.DefaultMaintenanceCancelAbleEvent;

public class DefaultMaintenanceReasonChangeEvent extends DefaultMaintenanceCancelAbleEvent implements MaintenanceReasonChangeEvent {

    private String reason;

    public DefaultMaintenanceReasonChangeEvent(DefaultDKMotd dkMotd, String reason) {
        super(dkMotd);
        this.reason = reason;
    }

    @Override
    public String getReason() {
        return this.reason;
    }

    @Override
    public void setReason(String reason) {
        this.reason = reason;
    }
}
