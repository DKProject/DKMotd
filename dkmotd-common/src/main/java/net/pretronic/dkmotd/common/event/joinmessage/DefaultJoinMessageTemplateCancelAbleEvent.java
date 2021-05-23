package net.pretronic.dkmotd.common.event.joinmessage;

import net.pretronic.dkmotd.api.joinmessage.JoinMessageTemplate;
import net.pretronic.libraries.event.Cancellable;

public class DefaultJoinMessageTemplateCancelAbleEvent extends DefaultJoinMessageTemplateEvent implements Cancellable {

    private boolean cancelled;

    public DefaultJoinMessageTemplateCancelAbleEvent(JoinMessageTemplate template) {
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
