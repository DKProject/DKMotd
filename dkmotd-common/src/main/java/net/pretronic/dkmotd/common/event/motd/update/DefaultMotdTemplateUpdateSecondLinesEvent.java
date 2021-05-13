package net.pretronic.dkmotd.common.event.motd.update;

import net.pretronic.dkmotd.api.event.motd.update.MotdTemplateUpdateSecondLinesEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.libraries.message.Textable;

import java.util.List;

public class DefaultMotdTemplateUpdateSecondLinesEvent extends DefaultMotdTemplateUpdateEvent<List<Textable>> implements MotdTemplateUpdateSecondLinesEvent {

    public DefaultMotdTemplateUpdateSecondLinesEvent(MotdTemplate template, List<Textable> newValue) {
        super(template, newValue);
    }
}
