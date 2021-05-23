package net.pretronic.dkmotd.common.event.joinmessage.delete;

import net.pretronic.dkmotd.api.event.joinmessage.delete.JoinMessageTemplateDeleteEvent;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.common.event.joinmessage.DefaultJoinMessageTemplateCancelAbleEvent;

public class DefaultJoinMessageTemplateDeleteEvent extends DefaultJoinMessageTemplateCancelAbleEvent implements JoinMessageTemplateDeleteEvent {

    public DefaultJoinMessageTemplateDeleteEvent(JoinMessageTemplate template) {
        super(template);
    }
}
