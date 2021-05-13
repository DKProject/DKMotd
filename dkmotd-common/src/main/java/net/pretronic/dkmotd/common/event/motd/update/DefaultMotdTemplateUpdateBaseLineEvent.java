package net.pretronic.dkmotd.common.event.motd.update;

import net.pretronic.dkmotd.api.event.motd.update.MotdTemplateUpdateBaseLineEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.libraries.message.Textable;

public class DefaultMotdTemplateUpdateBaseLineEvent extends DefaultMotdTemplateUpdateEvent<Textable> implements MotdTemplateUpdateBaseLineEvent {

    public DefaultMotdTemplateUpdateBaseLineEvent(MotdTemplate template, Textable newValue) {
        super(template, newValue);
    }
}
