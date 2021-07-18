package net.pretronic.dkmotd.common.event.joinmessage.update;

import net.pretronic.dkmotd.api.event.joinmessage.update.JoinMessageTemplateUpdatedEvent;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.common.event.joinmessage.DefaultJoinMessageTemplateEvent;

public class DefaultJoinMessageTemplateUpdatedEvent extends DefaultJoinMessageTemplateEvent implements JoinMessageTemplateUpdatedEvent {

    private Object oldValue;

    public DefaultJoinMessageTemplateUpdatedEvent(JoinMessageTemplate template, Object oldValue) {
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
