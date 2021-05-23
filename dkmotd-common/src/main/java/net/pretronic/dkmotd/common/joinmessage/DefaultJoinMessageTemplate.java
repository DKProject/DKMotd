package net.pretronic.dkmotd.common.joinmessage;

import net.pretronic.dkmotd.api.event.joinmessage.update.JoinMessageTemplateUpdateEvent;
import net.pretronic.dkmotd.api.event.joinmessage.update.JoinMessageTemplateUpdatedEvent;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.joinmessage.update.DefaultJoinMessageTemplateUpdateEvent;
import net.pretronic.dkmotd.common.event.joinmessage.update.DefaultJoinMessageTemplateUpdatedEvent;
import net.pretronic.libraries.utility.annonations.Internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DefaultJoinMessageTemplate implements JoinMessageTemplate {

    private transient DefaultDKMotd dkMotd;

    private String name;
    private String baseMessage;
    private Collection<String> secondMessages;

    public DefaultJoinMessageTemplate(DefaultDKMotd dkMotd, String name) {
        this(dkMotd, name, null, null);
    }

    public DefaultJoinMessageTemplate(DefaultDKMotd dkMotd, String name, String baseMessage, Collection<String> secondMessages) {
        this.dkMotd = dkMotd;
        this.name = name;
        this.baseMessage = baseMessage;
        this.secondMessages = secondMessages;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean setName(String name) {
        if(this.dkMotd.getJoinMessageTemplateManager().getTemplate(name) != null) {
            throw new IllegalArgumentException("An joinmessage template with the name " + name + " already exists");
        }

        JoinMessageTemplateUpdateEvent event = new DefaultJoinMessageTemplateUpdateEvent(this, name);
        this.dkMotd.getEventBus().callEvent(JoinMessageTemplateUpdateEvent.class, event);
        if(event.isCancelled()) return false;

        String oldValue = this.name;
        this.name = event.getNewValue(String.class);

        this.dkMotd.getJoinMessageTemplateManager().updateTemplatesStorage();

        this.dkMotd.getEventBus().callEvent(JoinMessageTemplateUpdatedEvent.class, new DefaultJoinMessageTemplateUpdatedEvent(this, oldValue));
        return true;
    }

    @Override
    public String getBaseMessage() {
        return this.baseMessage;
    }

    @Override
    public boolean setBaseMessage(String message) {
        JoinMessageTemplateUpdateEvent event = new DefaultJoinMessageTemplateUpdateEvent(this, message);
        this.dkMotd.getEventBus().callEvent(JoinMessageTemplateUpdateEvent.class, event);
        if(event.isCancelled()) return false;


        String oldValue = this.baseMessage;
        this.baseMessage = event.getNewValue(String.class);

        this.dkMotd.getJoinMessageTemplateManager().updateTemplatesStorage();

        this.dkMotd.getEventBus().callEvent(JoinMessageTemplateUpdatedEvent.class, new DefaultJoinMessageTemplateUpdatedEvent(this, oldValue));
        return true;
    }

    @Override
    public Collection<String> getSecondMessages() {
        if(secondMessages == null) return null;
        return Collections.unmodifiableCollection(this.secondMessages);
    }

    @Override
    public boolean setSecondMessages(Collection<String> messages) {
        JoinMessageTemplateUpdateEvent event = new DefaultJoinMessageTemplateUpdateEvent(this, messages);
        this.dkMotd.getEventBus().callEvent(JoinMessageTemplateUpdateEvent.class, event);
        if(event.isCancelled()) return false;

        Collection<String> oldValue = this.secondMessages;
        this.secondMessages = (List<String>) event.getNewValue();

        this.dkMotd.getJoinMessageTemplateManager().updateTemplatesStorage();

        this.dkMotd.getEventBus().callEvent(JoinMessageTemplateUpdatedEvent.class, new DefaultJoinMessageTemplateUpdatedEvent(this, oldValue));
        return true;
    }

    @Override
    public boolean addSecondMessage(String message) {
        Collection<String> lines = new ArrayList<>(getOrCopyCollection(this.secondMessages));
        lines.add(message);
        return setSecondMessages(lines);
    }

    @Override
    public boolean removeSecondMessage(int index) {
        List<String> lines = new ArrayList<>(getOrCopyCollection(this.secondMessages));
        if(index >= lines.size()) throw new IllegalArgumentException("Index is greater then size of second lines");
        lines.remove(index);
        return setSecondMessages(lines);
    }

    @Override
    public boolean clearSecondMessages() {
        return setSecondMessages(new ArrayList<>());
    }

    private <T> Collection<T> getOrCopyCollection(Collection<T> collection) {
        if(collection == null) return new ArrayList<>();
        return new ArrayList<>(collection);
    }

    @Internal
    public void setDKMotd(DefaultDKMotd dkMotd) {
        this.dkMotd = dkMotd;
    }
}
