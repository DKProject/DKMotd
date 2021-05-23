package net.pretronic.dkmotd.common.event.joinmessage.active;

import net.pretronic.dkmotd.api.event.joinmessage.active.JoinMessageTemplateActiveChangeEvent;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.dkmotd.common.event.joinmessage.DefaultJoinMessageTemplateCancelAbleEvent;

public class DefaultJoinMessageTemplateActiveChangeEvent extends DefaultJoinMessageTemplateCancelAbleEvent implements JoinMessageTemplateActiveChangeEvent {

    public DefaultJoinMessageTemplateActiveChangeEvent(JoinMessageTemplate template) {
        super(template);
    }
}
