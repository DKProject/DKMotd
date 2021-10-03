package net.pretronic.dkmotd.common;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.maintenance.Maintenance;
import net.pretronic.dkmotd.api.tablist.Tablist;
import net.pretronic.dkmotd.common.joinmessage.DefaultJoinMessageTemplateManager;
import net.pretronic.dkmotd.common.maintenance.DefaultMaintenance;
import net.pretronic.dkmotd.common.motd.DefaultMotdTemplateManager;
import net.pretronic.dkmotd.common.tablist.DefaultTablist;
import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.event.EventBus;
import net.pretronic.libraries.utility.annonations.Internal;
import net.pretronic.libraries.utility.annonations.NotNull;

public class DefaultDKMotd implements DKMotd {

    private static final String STORAGE_MAINTENANCE = "Maintenance";
    private static final String STORAGE_TABLIST = "Tablist";

    private final String version;
    private final DefaultMotdTemplateManager motdTemplateManager;
    private final DefaultJoinMessageTemplateManager joinMessageTemplateManager;
    private DefaultMaintenance maintenance;
    private DefaultTablist tablist;
    private final EventBus eventBus;
    private final DKMotdStorage storage;

    public DefaultDKMotd(@NotNull String version, @NotNull EventBus eventBus, @NotNull DKMotdStorage storage) {
        this.version = version;

        this.eventBus = eventBus;
        this.storage = storage;
        this.motdTemplateManager = new DefaultMotdTemplateManager(this);
        this.joinMessageTemplateManager = new DefaultJoinMessageTemplateManager(this);
        this.maintenance = loadMaintenance();
        this.tablist = loadTablist();
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public DefaultMotdTemplateManager getMotdTemplateManager() {
        return this.motdTemplateManager;
    }

    @Override
    public DefaultJoinMessageTemplateManager getJoinMessageTemplateManager() {
        return this.joinMessageTemplateManager;
    }

    @Override
    public Maintenance getMaintenance() {
        return this.maintenance;
    }

    @Override
    public Tablist getTablist() {
        return this.tablist;
    }

    @Override
    public EventBus getEventBus() {
        return this.eventBus;
    }

    @Internal
    public DKMotdStorage getStorage() {
        return storage;
    }

    @Internal
    public void updateMaintenanceStorage() {
        getStorage().set(STORAGE_MAINTENANCE, Document.newDocument(this.maintenance));
    }

    @Internal
    public void updateTablistStorage() {
        getStorage().set(STORAGE_TABLIST, Document.newDocument(this.tablist));
    }

    private DefaultMaintenance loadMaintenance() {
        Document value = getStorage().get(STORAGE_MAINTENANCE);
        if(value == null) {
            DefaultMaintenance maintenance = new DefaultMaintenance(this);
            getStorage().set(STORAGE_MAINTENANCE, Document.newDocument(maintenance));
            return maintenance;
        }
        return value.getAsObject(DefaultMaintenance.class).setDKMotd(this);
    }

    private DefaultTablist loadTablist() {
        Document value = getStorage().get(STORAGE_TABLIST);
        if(value == null) {
            DefaultTablist tablist = new DefaultTablist(this);
            getStorage().set(STORAGE_TABLIST, Document.newDocument(tablist));
            return tablist;
        }
        return value.getAsObject(DefaultTablist.class).setDKMotd(this);
    }

    @Internal
    public void reloadMaintenance() {
        this.maintenance = loadMaintenance();
    }

    @Internal
    public void reloadTablist() {
        this.tablist = loadTablist();
    }
}
