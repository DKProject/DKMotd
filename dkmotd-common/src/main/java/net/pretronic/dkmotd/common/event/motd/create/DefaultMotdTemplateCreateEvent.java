package net.pretronic.dkmotd.common.event.motd.create;

import net.pretronic.dkmotd.api.event.motd.create.MotdTemplateCreateEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.common.event.motd.DefaultMotdTemplateCancelAbleEvent;

public class DefaultMotdTemplateCreateEvent extends DefaultMotdTemplateCancelAbleEvent implements MotdTemplateCreateEvent {

    public DefaultMotdTemplateCreateEvent(MotdTemplate template) {
        super(template);
    }
}
