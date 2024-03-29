package net.pretronic.dkmotd.common.maintenance;

import net.pretronic.dkmotd.api.event.maintenance.active.MaintenanceActiveChangeEvent;
import net.pretronic.dkmotd.api.event.maintenance.active.MaintenanceActiveChangedEvent;
import net.pretronic.dkmotd.api.event.maintenance.reason.MaintenanceReasonChangeEvent;
import net.pretronic.dkmotd.api.event.maintenance.reason.MaintenanceReasonChangedEvent;
import net.pretronic.dkmotd.api.event.maintenance.timeout.MaintenanceTimeoutChangeEvent;
import net.pretronic.dkmotd.api.event.maintenance.timeout.MaintenanceTimeoutChangedEvent;
import net.pretronic.dkmotd.api.event.maintenance.whitelist.MaintenanceWhitelistAddEvent;
import net.pretronic.dkmotd.api.event.maintenance.whitelist.MaintenanceWhitelistClearEvent;
import net.pretronic.dkmotd.api.event.maintenance.whitelist.MaintenanceWhitelistRemoveEvent;
import net.pretronic.dkmotd.api.maintenance.Maintenance;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.maintenance.active.DefaultMaintenanceActiveChangeEvent;
import net.pretronic.dkmotd.common.event.maintenance.active.DefaultMaintenanceActiveChangedEvent;
import net.pretronic.dkmotd.common.event.maintenance.reason.DefaultMaintenanceReasonChangeEvent;
import net.pretronic.dkmotd.common.event.maintenance.reason.DefaultMaintenanceReasonChangedEvent;
import net.pretronic.dkmotd.common.event.maintenance.timeout.DefaultMaintenanceTimeoutChangeEvent;
import net.pretronic.dkmotd.common.event.maintenance.timeout.DefaultMaintenanceTimeoutChangedEvent;
import net.pretronic.dkmotd.common.event.maintenance.whitelist.DefaultMaintenanceWhitelistAddEvent;
import net.pretronic.dkmotd.common.event.maintenance.whitelist.DefaultMaintenanceWhitelistClearEvent;
import net.pretronic.dkmotd.common.event.maintenance.whitelist.DefaultMaintenanceWhitelistRemoveEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class DefaultMaintenance implements Maintenance {

    private transient DefaultDKMotd dkMotd;

    private boolean active;
    private long timeout;
    private String reason;
    private final Collection<UUID> whitelist;

    public DefaultMaintenance(DefaultDKMotd dkMotd) {
        this.dkMotd = dkMotd;
        this.whitelist = new ArrayList<>();
    }

    @Override
    public boolean isActive() {
        return this.active || getTimeout() > System.currentTimeMillis();
    }

    @Override
    public boolean setActive(boolean active) {
        MaintenanceActiveChangeEvent event = new DefaultMaintenanceActiveChangeEvent(dkMotd, active);
        this.dkMotd.getEventBus().callEvent(MaintenanceActiveChangeEvent.class, event);
        if(event.isCancelled()) return false;
        this.active = event.isActive();
        this.dkMotd.updateMaintenanceStorage();

        this.dkMotd.getEventBus().callEvent(MaintenanceActiveChangedEvent.class, new DefaultMaintenanceActiveChangedEvent(dkMotd, this.active));
        return true;
    }

    @Override
    public boolean hasTimeout() {
        return isActive() && getTimeout() > 0;
    }

    @Override
    public long getTimeout() {
        return this.timeout;
    }

    @Override
    public boolean setTimeout(long timeout) {
        MaintenanceTimeoutChangeEvent event = new DefaultMaintenanceTimeoutChangeEvent(dkMotd, timeout);
        this.dkMotd.getEventBus().callEvent(MaintenanceTimeoutChangeEvent.class, event);
        if(event.isCancelled()) return false;
        this.timeout = event.getTimeout();
        this.dkMotd.updateMaintenanceStorage();

        this.dkMotd.getEventBus().callEvent(MaintenanceTimeoutChangedEvent.class, new DefaultMaintenanceTimeoutChangedEvent(dkMotd, this.timeout));
        return true;
    }

    @Override
    public long getRemaining() {
        if(this.timeout <= 0) return 0;
        return this.timeout-System.currentTimeMillis();
    }

    @Override
    public String getReason() {
        return this.reason;
    }

    @Override
    public boolean setReason(String reason) {
        MaintenanceReasonChangeEvent event = new DefaultMaintenanceReasonChangeEvent(dkMotd, reason);
        this.dkMotd.getEventBus().callEvent(MaintenanceReasonChangeEvent.class, event);
        if(event.isCancelled()) return false;
        this.reason = event.getReason();
        this.dkMotd.updateMaintenanceStorage();

        this.dkMotd.getEventBus().callEvent(MaintenanceReasonChangedEvent.class, new DefaultMaintenanceReasonChangedEvent(dkMotd, this.reason));
        return true;
    }

    @Override
    public Collection<UUID> getWhitelist() {
        return Collections.unmodifiableCollection(this.whitelist);
    }

    @Override
    public boolean isWhitelisted(UUID uniqueId) {
        return this.whitelist.contains(uniqueId);
    }

    @Override
    public boolean addWhitelist(UUID uniqueId) {
        if(isWhitelisted(uniqueId)) throw new IllegalArgumentException("UniqueId " + uniqueId + " is already whitelisted");

        MaintenanceWhitelistAddEvent event = new DefaultMaintenanceWhitelistAddEvent(this.dkMotd, uniqueId);
        this.dkMotd.getEventBus().callEvent(MaintenanceWhitelistAddEvent.class, event);
        if(event.isCancelled()) return false;

        this.whitelist.add(uniqueId);
        this.dkMotd.updateMaintenanceStorage();
        return true;
    }

    @Override
    public boolean removeWhitelist(UUID uniqueId) {
        if(!isWhitelisted(uniqueId)) throw new IllegalArgumentException("UniqueId " + uniqueId + " is not whitelisted");

        MaintenanceWhitelistRemoveEvent event = new DefaultMaintenanceWhitelistRemoveEvent(this.dkMotd, uniqueId);
        this.dkMotd.getEventBus().callEvent(MaintenanceWhitelistRemoveEvent.class, event);
        if(event.isCancelled()) return false;

        this.whitelist.remove(uniqueId);
        this.dkMotd.updateMaintenanceStorage();
        return true;
    }

    @Override
    public boolean clearWhitelist() {
        MaintenanceWhitelistClearEvent event = new DefaultMaintenanceWhitelistClearEvent(this.dkMotd);
        this.dkMotd.getEventBus().callEvent(MaintenanceWhitelistClearEvent.class, event);
        if(event.isCancelled()) return false;

        this.whitelist.clear();
        this.dkMotd.updateMaintenanceStorage();
        return true;
    }

    public DefaultMaintenance setDKMotd(DefaultDKMotd dkMotd) {
        this.dkMotd = dkMotd;
        return this;
    }
}