package net.pretronic.dkmotd.api.event.maintenance.whitelist;

import net.pretronic.dkmotd.api.event.maintenance.MaintenanceEvent;
import net.pretronic.libraries.event.Cancellable;

import java.util.UUID;

public interface MaintenanceWhitelistRemoveEvent extends MaintenanceEvent, Cancellable {

    UUID getUniqueId();
}
