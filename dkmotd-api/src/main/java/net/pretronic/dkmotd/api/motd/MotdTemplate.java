package net.pretronic.dkmotd.api.motd;

import java.util.Collection;
import java.util.List;

public interface MotdTemplate {

    String getName();

    boolean setName(String name);


    String getBaseLine();

    boolean setBaseLine(String baseLine);


    Collection<String> getSecondLines();

    boolean setSecondsLines(Collection<String> secondsLines);

    boolean addSecondLine(String line);

    boolean removeSecondLine(int index);

    boolean clearSecondLines();


    String getVersionText();

    boolean setVersionText(String versionText);


    String getWrongVersionText();

    boolean setWrongVersionText(String wrongVersionText);


    Collection<Integer> getSupportedVersions();

    boolean setSupportedVersions(Collection<Integer> supportedVersions);

    boolean addSupportedVersion(int version);

    boolean removeSupportedVersion(int version);

    boolean clearSupportedVersions();


    String getFavicon();

    boolean setFavicon(String favicon);


    List<String> getPlayerInfo();

    boolean setPlayerInfo(List<String> playerInfo);

    boolean addPlayerInfo(String playerInfo);

    boolean removePlayerInfo(int index);

    boolean clearPlayerInfo();
}
