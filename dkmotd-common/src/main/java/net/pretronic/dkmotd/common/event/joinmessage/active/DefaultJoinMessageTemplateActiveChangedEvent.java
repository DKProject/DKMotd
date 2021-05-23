package net.pretronic.dkmotd.common.event.joinmessage.active;

import net.pretronic.dkmotd.api.event.joinmessage.active.JoinMessageTemplateActiveChangedEvent;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.common.event.joinmessage.DefaultJoinMessageTemplateEvent;

public class DefaultJoinMessageTemplateActiveChangedEvent extends DefaultJoinMessageTemplateEvent implements JoinMessageTemplateActiveChangedEvent {

    public DefaultJoinMessageTemplateActiveChangedEvent(JoinMessageTemplate template) {
        super(template);
    }
}
