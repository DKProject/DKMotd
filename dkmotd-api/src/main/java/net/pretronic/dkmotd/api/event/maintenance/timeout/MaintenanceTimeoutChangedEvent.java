package net.pretronic.dkmotd.api.event.maintenance.timeout;

import net.pretronic.dkmotd.api.event.maintenance.MaintenanceEvent;

public interface MaintenanceTimeoutChangedEvent extends MaintenanceEvent {

    long getNewTimeout();
}
