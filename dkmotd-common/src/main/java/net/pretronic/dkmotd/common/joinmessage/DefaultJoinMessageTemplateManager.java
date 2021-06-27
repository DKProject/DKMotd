package net.pretronic.dkmotd.common.joinmessage;

import net.pretronic.dkmotd.api.event.joinmessage.active.JoinMessageTemplateActiveChangeEvent;
import net.pretronic.dkmotd.api.event.joinmessage.active.JoinMessageTemplateActiveChangedEvent;
import net.pretronic.dkmotd.api.event.joinmessage.create.JoinMessageTemplateCreateEvent;
import net.pretronic.dkmotd.api.event.joinmessage.create.JoinMessageTemplateCreatedEvent;
import net.pretronic.dkmotd.api.event.joinmessage.delete.JoinMessageTemplateDeleteEvent;
import net.pretronic.dkmotd.api.event.joinmessage.delete.JoinMessageTemplateDeletedEvent;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplateManager;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.joinmessage.active.DefaultJoinMessageTemplateActiveChangeEvent;
import net.pretronic.dkmotd.common.event.joinmessage.active.DefaultJoinMessageTemplateActiveChangedEvent;
import net.pretronic.dkmotd.common.event.joinmessage.create.DefaultJoinMessageTemplateCreateEvent;
import net.pretronic.dkmotd.common.event.joinmessage.create.DefaultJoinMessageTemplateCreatedEvent;
import net.pretronic.dkmotd.common.event.joinmessage.delete.DefaultJoinMessageTemplateDeleteEvent;
import net.pretronic.dkmotd.common.event.joinmessage.delete.DefaultJoinMessageTemplateDeletedEvent;
import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.annonations.Internal;
import net.pretronic.libraries.utility.reflect.TypeReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DefaultJoinMessageTemplateManager implements JoinMessageTemplateManager {

    private static final String STORAGE_JOIN_MESSAGE_TEMPLATES = "JoinMessageTemplates";
    private static final String STORAGE_ACTIVE_JOIN_MESSAGE_TEMPLATE = "ActiveJoinMessageTemplate";

    private final DefaultDKMotd dkMotd;

    private final Collection<JoinMessageTemplate> templates;
    private String activeTemplateName;

    public DefaultJoinMessageTemplateManager(DefaultDKMotd dkMotd) {
        this.dkMotd = dkMotd;

        this.templates = loadTemplates();

        this.activeTemplateName = loadActiveTemplateName();
    }

    @Override
    public Collection<JoinMessageTemplate> getTemplates() {
        return Collections.unmodifiableCollection(this.templates);
    }

    @Override
    public JoinMessageTemplate getTemplate(String name) {
        return Iterators.findOne(this.templates, template -> template.getName().equalsIgnoreCase(name));
    }

    @Override
    public JoinMessageTemplate createTemplate(String name) {
        if(getTemplate(name) != null) throw new IllegalArgumentException("A join message template with the name " + name + " already exists");

        JoinMessageTemplate template = new DefaultJoinMessageTemplate(dkMotd, name);

        JoinMessageTemplateCreateEvent event = new DefaultJoinMessageTemplateCreateEvent(template);
        this.dkMotd.getEventBus().callEvent(JoinMessageTemplateCreateEvent.class, event);
        if(event.isCancelled()) return null;

        this.templates.add(template);
        updateTemplatesStorage();

        this.dkMotd.getEventBus().callEvent(JoinMessageTemplateCreatedEvent.class, new DefaultJoinMessageTemplateCreatedEvent(template));
        return template;
    }

    @Override
    public boolean deleteTemplate(JoinMessageTemplate template) {
        if(!this.templates.contains(template)) throw new IllegalArgumentException("The join message template is not registered in template manager");

        JoinMessageTemplateDeleteEvent event = new DefaultJoinMessageTemplateDeleteEvent(template);
        this.dkMotd.getEventBus().callEvent(JoinMessageTemplateDeleteEvent.class, event);
        if(event.isCancelled()) return false;

        this.templates.remove(template);
        updateTemplatesStorage();

        this.dkMotd.getEventBus().callEvent(JoinMessageTemplateDeletedEvent.class, new DefaultJoinMessageTemplateDeletedEvent(template));

        if(this.activeTemplateName.equals(template.getName())) setActiveTemplate(null);
        return true;
    }

    @Override
    public JoinMessageTemplate getActiveTemplate() {
        return getTemplate(this.activeTemplateName);
    }

    @Override
    public boolean setActiveTemplate(JoinMessageTemplate template) {
        JoinMessageTemplateActiveChangeEvent event = new DefaultJoinMessageTemplateActiveChangeEvent(template);
        this.dkMotd.getEventBus().callEvent(JoinMessageTemplateActiveChangeEvent.class, event);
        if(event.isCancelled()) return false;

        this.activeTemplateName = template.getName();

        this.dkMotd.getStorage().set(STORAGE_ACTIVE_JOIN_MESSAGE_TEMPLATE, this.activeTemplateName);

        this.dkMotd.getEventBus().callEvent(JoinMessageTemplateActiveChangedEvent.class, new DefaultJoinMessageTemplateActiveChangedEvent(template));
        return true;
    }

    @Internal
    public void updateTemplatesStorage() {
        this.dkMotd.getStorage().set(STORAGE_JOIN_MESSAGE_TEMPLATES, Document.newDocument(this.templates));
    }

    private Collection<JoinMessageTemplate> loadTemplates() {
        Document document = this.dkMotd.getStorage().get(STORAGE_JOIN_MESSAGE_TEMPLATES);
        if(document == null) {
            Collection<JoinMessageTemplate> templates = new ArrayList<>();
            this.dkMotd.getStorage().set(STORAGE_JOIN_MESSAGE_TEMPLATES, Document.newDocument(templates));
            return templates;
        }
        Collection<JoinMessageTemplate> templates = new ArrayList<>(document.getAsObject(new TypeReference<Collection<DefaultJoinMessageTemplate>>(){}));
        for (JoinMessageTemplate template : templates) {
            ((DefaultJoinMessageTemplate)template).setDKMotd(dkMotd);
        }
        return templates;
    }

    private String loadActiveTemplateName() {
        Object value = this.dkMotd.getStorage().getObject(STORAGE_ACTIVE_JOIN_MESSAGE_TEMPLATE);
        if(value == null) {
            return null;
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
