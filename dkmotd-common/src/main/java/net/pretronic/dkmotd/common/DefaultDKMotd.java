package net.pretronic.dkmotd.common;

import net.pretronic.databasequery.api.Database;
import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplateManager;
import net.pretronic.dkmotd.api.maintenance.Maintenance;
import net.pretronic.dkmotd.api.motd.MotdTemplateManager;
import net.pretronic.dkmotd.common.motd.DefaultMotdTemplateManager;
import net.pretronic.libraries.document.DocumentRegistry;
import net.pretronic.libraries.event.EventBus;
import net.pretronic.libraries.message.Textable;
import net.pretronic.libraries.message.TextableDocumentAdapter;
import net.pretronic.libraries.utility.annonations.Internal;
import net.pretronic.libraries.utility.annonations.NotNull;

public class DefaultDKMotd implements DKMotd {

    private final String version;
    private final DefaultMotdTemplateManager motdTemplateManager;
    private final EventBus eventBus;

    private final DKMotdStorage storage;

    public DefaultDKMotd(@NotNull String version, @NotNull Database database, @NotNull EventBus eventBus) {
        this.version = version;
        DocumentRegistry.getDefaultContext().registerAdapter(Textable.class, new TextableDocumentAdapter());

        this.eventBus = eventBus;
        this.motdTemplateManager = new DefaultMotdTemplateManager(this);
        this.storage = new DKMotdStorage(database);
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public MotdTemplateManager getMotdTemplateManager() {
        return this.motdTemplateManager;
    }

    @Override
    public JoinMessageTemplateManager getJoinMessageTemplateManager() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Maintenance getMaintenance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public EventBus getEventBus() {
        return this.eventBus;
    }

    @Internal
    public DKMotdStorage getStorage() {
        return storage;
    }
}
