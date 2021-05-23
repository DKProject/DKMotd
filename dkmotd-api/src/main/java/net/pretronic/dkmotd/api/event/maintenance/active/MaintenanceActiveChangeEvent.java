package net.pretronic.dkmotd.api.event.maintenance.active;

import net.pretronic.dkmotd.api.event.maintenance.MaintenanceEvent;
import net.pretronic.libraries.event.Cancellable;

public interface MaintenanceActiveChangeEvent extends MaintenanceEvent, Cancellable {

    boolean isActive();

    void setActive(boolean active);
}
