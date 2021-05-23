package net.pretronic.dkmotd.common.event.joinmessage.create;

import net.pretronic.dkmotd.api.event.joinmessage.create.JoinMessageTemplateCreateEvent;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.common.event.joinmessage.DefaultJoinMessageTemplateCancelAbleEvent;

public class DefaultJoinMessageTemplateCreateEvent extends DefaultJoinMessageTemplateCancelAbleEvent implements JoinMessageTemplateCreateEvent {

    public DefaultJoinMessageTemplateCreateEvent(JoinMessageTemplate template) {
        super(template);
    }
}
