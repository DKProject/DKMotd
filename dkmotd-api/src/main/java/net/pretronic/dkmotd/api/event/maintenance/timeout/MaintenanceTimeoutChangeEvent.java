package net.pretronic.dkmotd.api.event.maintenance.timeout;

import net.pretronic.dkmotd.api.event.maintenance.MaintenanceEvent;
import net.pretronic.libraries.event.Cancellable;

public interface MaintenanceTimeoutChangeEvent extends MaintenanceEvent, Cancellable {

    long getTimeout();

    void setTimeout(long timeout);
}
