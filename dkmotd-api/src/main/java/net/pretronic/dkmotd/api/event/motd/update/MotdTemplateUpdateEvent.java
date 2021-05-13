package net.pretronic.dkmotd.api.event.motd.update;

import net.pretronic.dkmotd.api.event.motd.MotdTemplateEvent;
import net.pretronic.libraries.event.Cancellable;

public interface MotdTemplateUpdateEvent<T> extends MotdTemplateEvent, Cancellable {

    T getNewValue();

    void setNewValue(T value);
}
