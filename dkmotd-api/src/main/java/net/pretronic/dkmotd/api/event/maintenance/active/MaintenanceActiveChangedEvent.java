package net.pretronic.dkmotd.api.event.maintenance.active;

import net.pretronic.dkmotd.api.event.maintenance.MaintenanceEvent;

public interface MaintenanceActiveChangedEvent extends MaintenanceEvent {

    boolean getNewActive();
}
