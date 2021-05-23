package net.pretronic.dkmotd.api.event.joinmessage;

import net.pretronic.dkmotd.api.event.DKMotdEvent;
import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;

public interface JoinMessageTemplateEvent extends DKMotdEvent {

    JoinMessageTemplate getTemplate();
}
