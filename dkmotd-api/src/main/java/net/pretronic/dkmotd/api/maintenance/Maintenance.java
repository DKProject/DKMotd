package net.pretronic.dkmotd.api.maintenance;

import net.pretronic.dkmotd.api.motd.MotdTemplate;

public interface Maintenance {

    boolean isActive();

    void setActive(boolean active);


    MotdTemplate getTemplate();

    void setTemplate(MotdTemplate template);


    long getTimeout();

    void setTimeout(long timeout);
}
