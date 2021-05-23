package net.pretronic.dkmotd.api.event.motd.update;

import net.pretronic.dkmotd.api.event.motd.MotdTemplateEvent;
import net.pretronic.libraries.event.Cancellable;

public interface MotdTemplateUpdateEvent extends MotdTemplateEvent, Cancellable {

    Object getNewValue();

    <T> T getNewValue(Class<T> clazz);

    void setNewValue(Object value);
}
