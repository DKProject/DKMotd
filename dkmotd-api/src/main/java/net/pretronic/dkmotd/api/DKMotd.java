package net.pretronic.dkmotd.api;

import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplateManager;
import net.pretronic.dkmotd.api.maintenance.Maintenance;
import net.pretronic.dkmotd.api.motd.MotdTemplateManager;
import net.pretronic.dkmotd.api.tablist.Tablist;
import net.pretronic.libraries.event.EventBus;

public interface DKMotd {

    String getVersion();

    MotdTemplateManager getMotdTemplateManager();

    JoinMessageTemplateManager getJoinMessageTemplateManager();

    Maintenance getMaintenance();

    Tablist getTablist();

    EventBus getEventBus();
}
