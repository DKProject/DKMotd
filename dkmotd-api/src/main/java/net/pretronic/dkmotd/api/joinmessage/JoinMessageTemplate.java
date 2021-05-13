package net.pretronic.dkmotd.api.joinmessage;

import net.pretronic.dkmotd.api.Order;
import net.pretronic.libraries.message.Textable;

import java.util.List;

public interface JoinMessageTemplate {

    int getId();

    String getName();

    Textable getBaseMessage();

    List<Textable> getSecondMessages();

    Order getOrder();
}
