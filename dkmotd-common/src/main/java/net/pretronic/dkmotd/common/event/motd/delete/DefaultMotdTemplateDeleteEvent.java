package net.pretronic.dkmotd.common.event.motd.delete;

import net.pretronic.dkmotd.api.event.motd.delete.MotdTemplateDeleteEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.common.event.motd.DefaultMotdTemplateCancelAbleEvent;

public class DefaultMotdTemplateDeleteEvent extends DefaultMotdTemplateCancelAbleEvent implements MotdTemplateDeleteEvent {

    public DefaultMotdTemplateDeleteEvent(MotdTemplate template) {
        super(template);
    }
}
