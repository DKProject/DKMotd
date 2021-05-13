package net.pretronic.dkmotd.common.event.motd.create;

import net.pretronic.dkmotd.api.event.motd.create.MotdTemplateCreatedEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.common.event.motd.DefaultMotdTemplateEvent;

public class DefaultMotdTemplateCreatedEvent extends DefaultMotdTemplateEvent implements MotdTemplateCreatedEvent {

    public DefaultMotdTemplateCreatedEvent(MotdTemplate template) {
        super(template);
    }
}
