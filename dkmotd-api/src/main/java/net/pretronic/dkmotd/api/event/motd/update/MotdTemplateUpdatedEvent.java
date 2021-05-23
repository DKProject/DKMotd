package net.pretronic.dkmotd.api.event.motd.update;

import net.pretronic.dkmotd.api.event.motd.MotdTemplateEvent;

public interface MotdTemplateUpdatedEvent extends MotdTemplateEvent {

    Object getOldValue();

    <T> T getOldValue(Class<T> clazz);
}
