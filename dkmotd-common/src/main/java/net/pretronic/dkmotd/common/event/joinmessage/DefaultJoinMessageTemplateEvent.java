package net.pretronic.dkmotd.common.event.joinmessage;

import net.pretronic.dkmotd.api.event.joinmessage.JoinMessageTemplateEvent;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;

public class DefaultJoinMessageTemplateEvent implements JoinMessageTemplateEvent {

    private final JoinMessageTemplate template;

    public DefaultJoinMessageTemplateEvent(JoinMessageTemplate template) {
        this.template = template;
    }

    @Override
    public JoinMessageTemplate getTemplate() {
        return this.template;
    }
}
