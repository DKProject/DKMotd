package net.pretronic.dkmotd.common.event.motd;

import net.pretronic.dkmotd.api.event.motd.MotdTemplateEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;

public class DefaultMotdTemplateEvent implements MotdTemplateEvent {

    private final MotdTemplate template;

    public DefaultMotdTemplateEvent(MotdTemplate template) {
        this.template = template;
    }

    @Override
    public MotdTemplate getTemplate() {
        return this.template;
    }
}
