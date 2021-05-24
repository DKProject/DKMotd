package net.pretronic.dkmotd.api.maintenance;

public interface Maintenance {

    boolean isActive();

    boolean setActive(boolean active);


    boolean hasTimeout();

    long getTimeout();

    boolean setTimeout(long timeout);


    long getRemaining();


    String getReason();

    boolean setReason(String reason);
}
