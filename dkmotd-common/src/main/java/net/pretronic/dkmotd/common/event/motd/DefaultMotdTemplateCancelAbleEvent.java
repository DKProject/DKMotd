package net.pretronic.dkmotd.common.event.motd;

import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.libraries.event.Cancellable;

public class DefaultMotdTemplateCancelAbleEvent extends DefaultMotdTemplateEvent implements Cancellable {

    private boolean cancelled;

    public DefaultMotdTemplateCancelAbleEvent(MotdTemplate template) {
        super(template);
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
