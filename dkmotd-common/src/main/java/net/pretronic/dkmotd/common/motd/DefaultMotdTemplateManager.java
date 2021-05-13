package net.pretronic.dkmotd.common.motd;

import net.pretronic.databasequery.api.query.result.QueryResultEntry;
import net.pretronic.dkmotd.api.Order;
import net.pretronic.dkmotd.api.event.motd.create.MotdTemplateCreateEvent;
import net.pretronic.dkmotd.api.event.motd.create.MotdTemplateCreatedEvent;
import net.pretronic.dkmotd.api.event.motd.delete.MotdTemplateDeleteEvent;
import net.pretronic.dkmotd.api.event.motd.delete.MotdTemplateDeletedEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.api.motd.MotdTemplateManager;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.motd.create.DefaultMotdTemplateCreateEvent;
import net.pretronic.dkmotd.common.event.motd.create.DefaultMotdTemplateCreatedEvent;
import net.pretronic.dkmotd.common.event.motd.delete.DefaultMotdTemplateDeleteEvent;
import net.pretronic.dkmotd.common.event.motd.delete.DefaultMotdTemplateDeletedEvent;
import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.message.Textable;
import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.Validate;
import net.pretronic.libraries.utility.annonations.Internal;
import net.pretronic.libraries.utility.annonations.NotNull;
import net.pretronic.libraries.utility.reflect.TypeReference;

import java.util.*;

public class DefaultMotdTemplateManager implements MotdTemplateManager {

    private final DefaultDKMotd dkMotd;

    private final Collection<MotdTemplate> templates;
    private MotdTemplate activeTemplate;

    public DefaultMotdTemplateManager(DefaultDKMotd dkMotd) {
        this.dkMotd = dkMotd;
        this.templates = new ArrayList<>();

        loadTemplates();
    }

    @Override
    public Collection<MotdTemplate> getTemplates() {
        return Collections.unmodifiableCollection(this.templates);
    }

    @Override
    public MotdTemplate getTemplate(UUID id) {
        return Iterators.findOne(this.templates, template -> template.getId().equals(id));
    }

    @Override
    public MotdTemplate getTemplate(@NotNull String name) {
        return Iterators.findOne(this.templates, template -> template.getName().equalsIgnoreCase(name));
    }

    @Override
    public MotdTemplate createTemplate(@NotNull String name) {
        if(getTemplate(name) != null) throw new IllegalArgumentException("A motd template with the name " + name + " already exists");

        MotdTemplate template = new DefaultMotdTemplate(dkMotd, UUID.randomUUID(), name);

        MotdTemplateCreateEvent event = new DefaultMotdTemplateCreateEvent(template);
        this.dkMotd.getEventBus().callEvent(MotdTemplateCreateEvent.class, event);
        if(event.isCancelled()) return null;

        this.dkMotd.getStorage().getMotdTemplates().insert()
                .set("Id", template.getId())
                .set("Name", template.getName())
                .set("Order", template.getOrder())
                .execute();

        this.dkMotd.getEventBus().callEvent(MotdTemplateCreatedEvent.class, new DefaultMotdTemplateCreatedEvent(template));

        return template;
    }

    @Override
    public boolean deleteTemplate(@NotNull MotdTemplate template) {
        if(!this.templates.contains(template)) throw new IllegalArgumentException("The motd template is not registered in template manager");

        MotdTemplateDeleteEvent event = new DefaultMotdTemplateDeleteEvent(template);
        this.dkMotd.getEventBus().callEvent(MotdTemplateDeleteEvent.class, event);
        if(event.isCancelled()) return false;

        this.dkMotd.getStorage().getMotdTemplates().delete()
                .where("Id", template.getId())
                .execute();

        this.dkMotd.getEventBus().callEvent(MotdTemplateDeletedEvent.class, new DefaultMotdTemplateDeletedEvent(template));

        return true;
    }

    @Override
    public void replicateBaseLine(MotdTemplate template) {

    }

    @Override
    public MotdTemplate getActiveTemplate() {
        return this.activeTemplate;
    }

    @Override
    public void setActiveTemplate(MotdTemplate template) {
        this.activeTemplate = template;
    }

    private void loadTemplates() {
        this.dkMotd.getStorage().getMotdTemplates().find().execute().loadIn(this.templates, this::loadTemplate);
    }

    @Internal
    public MotdTemplate loadTemplate(@NotNull QueryResultEntry resultEntry) {
        return new DefaultMotdTemplate(this.dkMotd,
                resultEntry.getUniqueId("Id"),
                resultEntry.getString("Name"),
                convertTextable(resultEntry.getString("BaseLine")),
                convertTextableList(resultEntry.getString("SecondLines")),
                Order.parse(resultEntry.getString("Order")),
                convertTextable(resultEntry.getString("VersionText")),
                convertTextable(resultEntry.getString("WrongVersionText")),
                loadSupportedVersions(resultEntry.getString("SupportedVersions")),
                resultEntry.getString("Favicon"),
                convertTextableList(resultEntry.getString("PlayerInfo")));
    }

    private List<Textable> convertTextableList(String data) {
        Document document = DocumentFileType.JSON.getReader().read(data);
        Validate.notNull(document, "Data can't be loaded");

        return document.getAsObject(new TypeReference<List<Textable>>(){});
    }

    private Textable convertTextable(String data) {
        if(data == null) return null;
        Document document = DocumentFileType.JSON.getReader().read(data);
        Validate.notNull(document, "Data can't be loaded");

        return convertTextable(document);
    }

    private Textable convertTextable(@NotNull Document document) {
        Textable textable = document.getAsObject(Textable.class);
        Validate.notNull(textable, "Can't convert document data to Textable");
        return textable;
    }

    private Collection<Integer> loadSupportedVersions(String data) {
        if(data == null) return null;

        Document document = DocumentFileType.JSON.getReader().read(data);
        Validate.notNull(document, "Supported versions data can't be loaded");

        return document.getAsObject(new TypeReference<Collection<Integer>>(){});
    }
}
