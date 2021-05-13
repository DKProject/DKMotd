package net.pretronic.dkmotd.common.event.motd.delete;

import net.pretronic.dkmotd.api.event.motd.delete.MotdTemplateDeletedEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.common.event.motd.DefaultMotdTemplateEvent;

public class DefaultMotdTemplateDeletedEvent extends DefaultMotdTemplateEvent implements MotdTemplateDeletedEvent {

    public DefaultMotdTemplateDeletedEvent(MotdTemplate template) {
        super(template);
    }
}
