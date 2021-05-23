package net.pretronic.dkmotd.api.maintenance;

public interface Maintenance {

    boolean isActive();

    boolean setActive(boolean active);

    long getTimeout();

    boolean setTimeout(long timeout);
}
