package net.pretronic.dkmotd.common.event.motd.active;

import net.pretronic.dkmotd.api.event.motd.active.MotdTemplateActiveChangedEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.common.event.motd.DefaultMotdTemplateCancelAbleEvent;
import net.pretronic.dkmotd.common.event.motd.DefaultMotdTemplateEvent;

public class DefaultMotdActiveChangedEvent extends DefaultMotdTemplateEvent implements MotdTemplateActiveChangedEvent {

    public DefaultMotdActiveChangedEvent(MotdTemplate template) {
        super(template);
    }
}
