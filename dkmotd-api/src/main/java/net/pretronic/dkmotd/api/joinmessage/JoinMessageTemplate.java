package net.pretronic.dkmotd.api.joinmessage;

import java.util.Collection;
import java.util.List;

public interface JoinMessageTemplate {

    String getName();

    boolean setName(String name);


    String getBaseMessage();

    boolean setBaseMessage(String message);


    Collection<String> getSecondMessages();

    boolean setSecondMessages(Collection<String> messages);

    boolean addSecondMessage(String message);

    boolean removeSecondMessage(int index);

    boolean clearSecondMessages();
}
