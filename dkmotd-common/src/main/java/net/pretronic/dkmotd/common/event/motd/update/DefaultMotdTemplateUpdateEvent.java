package net.pretronic.dkmotd.common.event.motd.update;

import net.pretronic.dkmotd.api.event.motd.update.MotdTemplateUpdateEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.common.event.motd.DefaultMotdTemplateCancelAbleEvent;

public class DefaultMotdTemplateUpdateEvent extends DefaultMotdTemplateCancelAbleEvent implements MotdTemplateUpdateEvent {

    private Object newValue;

    public DefaultMotdTemplateUpdateEvent(MotdTemplate template, Object newValue) {
        super(template);
        this.newValue = newValue;
    }


    @Override
    public Object getNewValue() {
        return this.newValue;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getNewValue(Class<T> clazz) {
        return (T) getNewValue();
    }

    @Override
    public void setNewValue(Object value) {
        this.newValue = value;
    }
}
