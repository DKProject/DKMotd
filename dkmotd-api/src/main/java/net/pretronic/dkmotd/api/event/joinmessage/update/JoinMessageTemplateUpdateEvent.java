package net.pretronic.dkmotd.api.event.joinmessage.update;

import net.pretronic.dkmotd.api.event.joinmessage.JoinMessageTemplateEvent;
import net.pretronic.libraries.event.Cancellable;

public interface JoinMessageTemplateUpdateEvent extends JoinMessageTemplateEvent, Cancellable {

    Object getNewValue();

    <T> T getNewValue(Class<T> clazz);

    void setNewValue(Object value);
}
