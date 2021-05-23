package net.pretronic.dkmotd.api.event.joinmessage.update;

import net.pretronic.dkmotd.api.event.joinmessage.JoinMessageTemplateEvent;

public interface JoinMessageTemplateUpdatedEvent extends JoinMessageTemplateEvent {

    Object getOldValue();

    <T> T getOldValue(Class<T> clazz);
}
