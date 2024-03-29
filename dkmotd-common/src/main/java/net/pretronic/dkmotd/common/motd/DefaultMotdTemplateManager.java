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

    private static final String DKMOTD_LOGO_BASE64 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAEbElEQVR4Xu2bv08UQRTHCUZUKhuQaAUF2gCWQkWvf8K3EWqFRCgl2KgxUiC9NHYKiD2V8T+wQCmAAgv1D1ATf7znu4uXr7O3b3b27pZkv8knIXfz3rx5tzvzZnbp66tVq1atWt0Tjj9dEMaFG8Jsg+nGZxe4/amXDGpCuCdsC0fCL+F3BvqdttG2S8Ik+zsVksAvC/eFj4FBxnIA83WZ+6mcJMhR4bnwPTCQVNSn+h7lfnsuCWpQeNgIkgMvG+3jkTDIcfREsMmsjEs9Fu1zmuPpqiSAO8KPQHDdQvte4Lg6Lum0X9gIBNQrJJaTMxxnRwQb/ItAEL1GY+rneEsX0n55Xec/wNb5Z8Jqg3VhS9hvtGE7Lxscb6mSDu4GOvXwTpgXRtgnS9oMCbeFPRRLRmfmBNhsX3TC08FEL1tiMynsBvy1Q2OcYV9J0uCRvtQVSoJK7G4KJwGfWWishfoKClbkcCdFSEnCJeFtwGcWj9lHIcHK22+BDoqSkgTdSb4J+AyhMY+xj2jB6m92nkpqErxXwibbRwm2q+tUfZ+SBL0dPHOCxn6F7d2CbUPZaZmkJEEnRvYXYoVt3UL6zO8hJQmeJfKA7VyCneSws05RKAmwOsFTLMWfLMGOpNgRoxWeBs+fF6FoEjz9L7FdrmD1OjtitLzVIskThIfoJEj7uYAfZoftcgU7nGRHreil97e2R/lJGOB4siRth5F/GxyzXVvB1to8px/JpswkPG31nSfYDpN9tKJj8R+5w87o2QmzHbArKwm6fufuHJuCbaXZBzPOdpmCPahgB8w626lQXhLm2HeWYOcJbM/4d4iwpzXsgFllu6ZQThKesN8swQ5V2J6ZZbtMaeOAA2aV7VqF9CTEJOBBwJ6ZZbtMIeEWaBXSkjDP/rIEO15je8Z/hA7fJLjFdiGhWBJiJ0FPzRI1CXqWwX22yxLik7DGPtoJ+XuWn8J5tmsr+AqhYbbLEvxJ0Dbn2D5L0vas8CXgp5VDtssVfJeVe6lSSfsB4SnCZwz62RoiBt+U2EwJXwM+m7xkm1zBtxnaYzuPxG4EVsM/aaB/u+/5kGC718+BGJVFbp8r2FaTHTF6G0yxba8ksVxH+Ha4xm1dQv7kouyyXS8F++Far4T33MYt+I/EbrFtL9VIQvNKWObv3YL/UFQPKJPu4bLVSMKxMMTfRQn+Y3E9qvZvObsgiecifxYtxD0Y0YcWlUpCKYK9k8ODzUKvhErdDsmCVXGeFaGJzgmVmhiTBdshxj4e13P7ytQJyZLBLAQGmYcWS1rfa7WXu3eQNmf5s0oJ5b8io4cZ6nMH9laort/xDzK6JXTnJSlNwgT3XRnhqCuvyWk5W+35A/bSVOzEGEO1rwQVbHWIWSJj0SuhunOCClYnaLHkrRhjqfbE2JQEOSZswreBikU3Num1fTcE20WuwJY1Hkgs74VlpO7qeiXYtlSP117DfsV2p816ensovBIWhavs79QL//5pagb//9NU3NF1rVq1atVK0x+B+Oo/giE/MgAAAABJRU5ErkJggg==";

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
            "&7&l➜ &c&lMaintenance",
            "&7&l➜ &c&lMaintenance",
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
            for (MotdTemplate template : templates) {
                ((DefaultMotdTemplate)template).setDKMotd(dkMotd);
            }
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
