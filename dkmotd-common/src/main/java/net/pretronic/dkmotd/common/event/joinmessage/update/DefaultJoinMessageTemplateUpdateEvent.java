package net.pretronic.dkmotd.common.event.joinmessage.update;

import net.pretronic.dkmotd.api.event.joinmessage.update.JoinMessageTemplateUpdateEvent;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.common.event.joinmessage.DefaultJoinMessageTemplateCancelAbleEvent;

public class DefaultJoinMessageTemplateUpdateEvent extends DefaultJoinMessageTemplateCancelAbleEvent implements JoinMessageTemplateUpdateEvent {

    private Object newValue;

    public DefaultJoinMessageTemplateUpdateEvent(JoinMessageTemplate template, Object newValue) {
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
