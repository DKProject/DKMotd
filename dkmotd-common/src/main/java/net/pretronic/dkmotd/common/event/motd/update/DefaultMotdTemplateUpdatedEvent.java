package net.pretronic.dkmotd.common.event.motd.update;

import net.pretronic.dkmotd.api.event.motd.update.MotdTemplateUpdatedEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.common.event.motd.DefaultMotdTemplateEvent;

public class DefaultMotdTemplateUpdatedEvent extends DefaultMotdTemplateEvent implements MotdTemplateUpdatedEvent {

    private Object oldValue;

    public DefaultMotdTemplateUpdatedEvent(MotdTemplate template, Object oldValue) {
        super(template);
        this.oldValue = oldValue;
    }


    @Override
    public Object getOldValue() {
        return this.oldValue;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getOldValue(Class<T> clazz) {
        return (T) getOldValue();
    }
}
