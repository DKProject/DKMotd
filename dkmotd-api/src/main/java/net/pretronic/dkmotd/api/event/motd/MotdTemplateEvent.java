package net.pretronic.dkmotd.api.event.motd;

import net.pretronic.dkmotd.api.event.DKMotdEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;

public interface MotdTemplateEvent extends DKMotdEvent {

    MotdTemplate getTemplate();
}
