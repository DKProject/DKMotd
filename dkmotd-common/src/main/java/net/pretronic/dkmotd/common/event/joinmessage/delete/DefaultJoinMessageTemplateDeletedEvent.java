package net.pretronic.dkmotd.common.event.joinmessage.delete;

import net.pretronic.dkmotd.api.event.joinmessage.delete.JoinMessageTemplateDeletedEvent;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.common.event.joinmessage.DefaultJoinMessageTemplateEvent;

public class DefaultJoinMessageTemplateDeletedEvent extends DefaultJoinMessageTemplateEvent implements JoinMessageTemplateDeletedEvent {

    public DefaultJoinMessageTemplateDeletedEvent(JoinMessageTemplate template) {
        super(template);
    }
}
