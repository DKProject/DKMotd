package net.pretronic.dkmotd.api.event.maintenance;

import net.pretronic.dkmotd.api.event.DKMotdEvent;
import net.pretronic.dkmotd.api.maintenance.Maintenance;

public interface MaintenanceEvent extends DKMotdEvent {

    Maintenance getMaintenance();
}
