package net.pretronic.dkmotd.common.event.motd.active;

import net.pretronic.dkmotd.api.event.motd.active.MotdTemplateActiveChangeEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.common.event.motd.DefaultMotdTemplateCancelAbleEvent;

public class DefaultMotdActiveChangeEvent extends DefaultMotdTemplateCancelAbleEvent implements MotdTemplateActiveChangeEvent {

    public DefaultMotdActiveChangeEvent(MotdTemplate template) {
        super(template);
    }
}
