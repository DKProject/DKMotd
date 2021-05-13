package net.pretronic.dkmotd.common.event.motd.update;

import net.pretronic.dkmotd.api.event.motd.update.MotdTemplateUpdateNameEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;

public class DefaultMotdTemplateUpdateNameEvent extends DefaultMotdTemplateUpdateEvent<String> implements MotdTemplateUpdateNameEvent {

    public DefaultMotdTemplateUpdateNameEvent(MotdTemplate template, String newValue) {
        super(template, newValue);
    }
}
