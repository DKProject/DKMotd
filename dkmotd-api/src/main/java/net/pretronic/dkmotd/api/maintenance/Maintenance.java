package net.pretronic.dkmotd.api.maintenance;

import java.util.Collection;
import java.util.UUID;

public interface Maintenance {

    boolean isActive();

    boolean setActive(boolean active);


    boolean hasTimeout();

    long getTimeout();

    boolean setTimeout(long timeout);


    long getRemaining();


    String getReason();

    boolean setReason(String reason);


    Collection<UUID> getWhitelist();

    boolean isWhitelisted(UUID uniqueId);

    boolean addWhitelist(UUID uniqueId);

    boolean removeWhitelist(UUID uniqueId);

    boolean clearWhitelist();
}
