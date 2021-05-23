package net.pretronic.dkmotd.common.event.maintenance;

import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.libraries.event.Cancellable;

public class DefaultMaintenanceCancelAbleEvent extends DefaultMaintenanceEvent implements Cancellable {

    private boolean cancelled;

    public DefaultMaintenanceCancelAbleEvent(DefaultDKMotd dkMotd) {
        super(dkMotd);
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
