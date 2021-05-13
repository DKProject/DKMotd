package net.pretronic.dkmotd.api.motd;

import net.pretronic.dkmotd.api.Order;
import net.pretronic.libraries.message.Textable;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface MotdTemplate {

    UUID getId();


    String getName();

    boolean setName(String name);


    Textable getBaseLine();

    boolean setBaseLine(Textable baseLine);


    List<Textable> getSecondLines();

    boolean setSecondsLines(List<Textable> secondsLines);


    Order getOrder();

    boolean setOrder(Order order);


    Textable getVersionText();

    boolean setVersionText(Textable versionText);


    Textable getWrongVersionText();

    boolean setWrongVersionText(Textable wrongVersionText);


    Collection<Integer> getSupportedVersions();

    boolean setSupportedVersions(Collection<Integer> supportedVersions);


    String getFavicon();

    boolean setFavicon(String favicon);


    List<Textable> getPlayerInfo();

    boolean setPlayerInfo(List<Textable> playerInfo);
}
