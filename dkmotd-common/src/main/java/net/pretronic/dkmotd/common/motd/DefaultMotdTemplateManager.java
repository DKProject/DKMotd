package net.pretronic.dkmotd.common.motd;

import net.pretronic.dkmotd.api.event.motd.active.MotdTemplateActiveChangeEvent;
import net.pretronic.dkmotd.api.event.motd.active.MotdTemplateActiveChangedEvent;
import net.pretronic.dkmotd.api.event.motd.create.MotdTemplateCreateEvent;
import net.pretronic.dkmotd.api.event.motd.create.MotdTemplateCreatedEvent;
import net.pretronic.dkmotd.api.event.motd.delete.MotdTemplateDeleteEvent;
import net.pretronic.dkmotd.api.event.motd.delete.MotdTemplateDeletedEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.api.motd.MotdTemplateManager;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.motd.active.DefaultMotdActiveChangeEvent;
import net.pretronic.dkmotd.common.event.motd.active.DefaultMotdActiveChangedEvent;
import net.pretronic.dkmotd.common.event.motd.create.DefaultMotdTemplateCreateEvent;
import net.pretronic.dkmotd.common.event.motd.create.DefaultMotdTemplateCreatedEvent;
import net.pretronic.dkmotd.common.event.motd.delete.DefaultMotdTemplateDeleteEvent;
import net.pretronic.dkmotd.common.event.motd.delete.DefaultMotdTemplateDeletedEvent;
import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.annonations.Internal;
import net.pretronic.libraries.utility.annonations.NotNull;
import net.pretronic.libraries.utility.reflect.TypeReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class DefaultMotdTemplateManager implements MotdTemplateManager {

    private static final String DKMOTD_LOGO_BASE64 = "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";

    private static final String STORAGE_MOTD_TEMPLATES = "MotdTemplates";
    private static final String STORAGE_ACTIVE_MOTD_TEMPLATE = "ActiveMotdTemplate";

    public static final String DEFAULT_TEMPLATE_NAME = "default";
    public static final String DEFAULT_MAINTENANCE_TEMPLATE_NAME = "maintenance";

    private static final MotdTemplate DEFAULT_TEMPLATE = new DefaultMotdTemplate(null, DEFAULT_TEMPLATE_NAME,
            "&b&lDKMotd &8|&f &a&lBetter Motd solution",
            Arrays.asList("&7Powered by Pretronic"),
            null,
            null,
            null,
            DKMOTD_LOGO_BASE64,
            Arrays.asList("&8» &7Website&8: &ehttps://pretronic.net/",
                    "&8» &7Discord&8: &ehttps://discord.pretronic.net/",
                    "&8» &7Docs&8: &ehttps://docs.pretronic.net/",
                    "&8» &7Donate&8: &ehttps://paypal.me/pretronic"));

    private static final MotdTemplate DEFAULT_MAINTENANCE_TEMPLATE = new DefaultMotdTemplate(null, DEFAULT_MAINTENANCE_TEMPLATE_NAME,
            "&b&lDKMotd &8|&f &a&lBetter Motd solution",
            Arrays.asList("&7Powered by Pretronic"),
            "&7&l➜ &4&lMaintenance",
            "&7&l➜ &4&lMaintenance",
            null,
            DKMOTD_LOGO_BASE64,
            Arrays.asList("&8» &7Website&8: &ehttps://pretronic.net/",
                    "&8» &7Discord&8: &ehttps://discord.pretronic.net/",
                    "&8» &7Docs&8: &ehttps://docs.pretronic.net/",
                    "&8» &7Donate&8: &ehttps://paypal.me/pretronic"));


    private final DefaultDKMotd dkMotd;

    private final Collection<MotdTemplate> templates;
    private String activeTemplateName;

    public DefaultMotdTemplateManager(@NotNull DefaultDKMotd dkMotd) {
        this.dkMotd = dkMotd;
        this.templates = loadTemplates();
        //@TODO Temporary

        this.activeTemplateName = loadActiveTemplateName();
    }

    @Override
    public Collection<MotdTemplate> getTemplates() {
        return Collections.unmodifiableCollection(this.templates);
    }

    @Override
    public MotdTemplate getTemplate(@NotNull String name) {
        return Iterators.findOne(this.templates, template -> template.getName().equalsIgnoreCase(name));
    }

    @Override
    public MotdTemplate createTemplate(@NotNull String name) {
        if(getTemplate(name) != null) throw new IllegalArgumentException("A motd template with the name " + name + " already exists");

        MotdTemplate template = new DefaultMotdTemplate(dkMotd, name);

        MotdTemplateCreateEvent event = new DefaultMotdTemplateCreateEvent(template);
        this.dkMotd.getEventBus().callEvent(MotdTemplateCreateEvent.class, event);
        if(event.isCancelled()) return null;

        this.templates.add(template);
        updateTemplatesStorage();

        this.dkMotd.getEventBus().callEvent(MotdTemplateCreatedEvent.class, new DefaultMotdTemplateCreatedEvent(template));

        return template;
    }

    @Override
    public boolean deleteTemplate(@NotNull MotdTemplate template) {
        if(!this.templates.contains(template)) throw new IllegalArgumentException("The motd template is not registered in template manager");
        if(template.getName().equalsIgnoreCase(DEFAULT_TEMPLATE_NAME)) throw new IllegalArgumentException("It is not possible to delete the default motd template");
        if(template.getName().equalsIgnoreCase(DEFAULT_MAINTENANCE_TEMPLATE_NAME)) throw new IllegalArgumentException("It is not possible to delete the maintenance motd template");

        MotdTemplateDeleteEvent event = new DefaultMotdTemplateDeleteEvent(template);
        this.dkMotd.getEventBus().callEvent(MotdTemplateDeleteEvent.class, event);
        if(event.isCancelled()) return false;

        this.templates.remove(template);
        updateTemplatesStorage();

        this.dkMotd.getEventBus().callEvent(MotdTemplateDeletedEvent.class, new DefaultMotdTemplateDeletedEvent(template));

        if(this.activeTemplateName.equals(template.getName())) setActiveTemplate(getTemplate(DEFAULT_TEMPLATE_NAME));

        return true;
    }

    @Override
    public void replicateBaseLine(MotdTemplate template) {
        throw new UnsupportedOperationException();
    }

    @Override
    public @NotNull MotdTemplate getActiveTemplate() {
        return getTemplate(this.activeTemplateName);
    }

    @Override
    public boolean setActiveTemplate(@NotNull MotdTemplate template) {
        MotdTemplateActiveChangeEvent event = new DefaultMotdActiveChangeEvent(template);
        this.dkMotd.getEventBus().callEvent(MotdTemplateActiveChangeEvent.class, event);
        if(event.isCancelled()) return false;

        this.activeTemplateName = template.getName();

        this.dkMotd.getStorage().set(STORAGE_ACTIVE_MOTD_TEMPLATE, this.activeTemplateName);
        this.dkMotd.getEventBus().callEvent(MotdTemplateActiveChangedEvent.class, new DefaultMotdActiveChangedEvent(template));
        return true;
    }

    @Internal
    public void updateTemplatesStorage() {
        this.dkMotd.getStorage().set(STORAGE_MOTD_TEMPLATES, Document.newDocument(this.templates));
    }

    private Collection<MotdTemplate> loadTemplates() {
        Document document = this.dkMotd.getStorage().get(STORAGE_MOTD_TEMPLATES);
        if(document == null) {
            Collection<MotdTemplate> templates = new ArrayList<>();
            templates.add(DEFAULT_TEMPLATE);
            templates.add(DEFAULT_MAINTENANCE_TEMPLATE);

            this.dkMotd.getStorage().set(STORAGE_MOTD_TEMPLATES, Document.newDocument(templates));
            return templates;
        }
        Collection<MotdTemplate> templates = new ArrayList<>(document.getAsObject(new TypeReference<Collection<DefaultMotdTemplate>>(){}));;
        for (MotdTemplate template : templates) {
            ((DefaultMotdTemplate)template).setDKMotd(dkMotd);
        }
        return templates;
    }

    private String loadActiveTemplateName() {
        Object value = this.dkMotd.getStorage().getObject(STORAGE_ACTIVE_MOTD_TEMPLATE);
        if(value == null) {
            this.dkMotd.getStorage().set(STORAGE_ACTIVE_MOTD_TEMPLATE, DEFAULT_TEMPLATE_NAME);
            return DEFAULT_TEMPLATE_NAME;
        }
        return (String) value;
    }

    @Internal
    public void reload() {
        this.templates.clear();
        this.templates.addAll(loadTemplates());
        this.activeTemplateName = loadActiveTemplateName();
    }
}
