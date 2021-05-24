package net.pretronic.dkmotd.api.event.maintenance.reason;

import net.pretronic.dkmotd.api.event.maintenance.MaintenanceEvent;

public interface MaintenanceReasonChangedEvent extends MaintenanceEvent {

    String getNewReason();
}
