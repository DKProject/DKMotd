package net.pretronic.dkmotd.common.event.motd.update;

import net.pretronic.dkmotd.api.event.motd.update.MotdTemplateUpdateEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.common.event.motd.DefaultMotdTemplateCancelAbleEvent;

public class DefaultMotdTemplateUpdateEvent<T> extends DefaultMotdTemplateCancelAbleEvent implements MotdTemplateUpdateEvent<T> {

    private T newValue;

    public DefaultMotdTemplateUpdateEvent(MotdTemplate template, T newValue) {
        super(template);
        this.newValue = newValue;
    }

    @Override
    public T getNewValue() {
        return this.newValue;
    }

    @Override
    public void setNewValue(T value) {
        this.newValue = value;
    }
}
