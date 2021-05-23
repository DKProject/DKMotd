package net.pretronic.dkmotd.common.event.joinmessage.create;

import net.pretronic.dkmotd.api.event.joinmessage.create.JoinMessageTemplateCreatedEvent;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.common.event.joinmessage.DefaultJoinMessageTemplateEvent;

public class DefaultJoinMessageTemplateCreatedEvent extends DefaultJoinMessageTemplateEvent implements JoinMessageTemplateCreatedEvent {

    public DefaultJoinMessageTemplateCreatedEvent(JoinMessageTemplate template) {
        super(template);
    }
}
