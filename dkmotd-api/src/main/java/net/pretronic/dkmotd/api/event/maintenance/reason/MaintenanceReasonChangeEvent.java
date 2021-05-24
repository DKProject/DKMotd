package net.pretronic.dkmotd.api.event.maintenance.reason;

import net.pretronic.dkmotd.api.event.maintenance.MaintenanceEvent;
import net.pretronic.libraries.event.Cancellable;

public interface MaintenanceReasonChangeEvent extends MaintenanceEvent, Cancellable {

    String getReason();

    void setReason(String reason);
}
