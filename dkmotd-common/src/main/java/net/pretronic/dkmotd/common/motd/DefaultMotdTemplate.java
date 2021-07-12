package net.pretronic.dkmotd.common.motd;

import net.pretronic.dkmotd.api.event.motd.update.MotdTemplateUpdateEvent;
import net.pretronic.dkmotd.api.event.motd.update.MotdTemplateUpdatedEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.motd.update.DefaultMotdTemplateUpdateEvent;
import net.pretronic.dkmotd.common.event.motd.update.DefaultMotdTemplateUpdatedEvent;
import net.pretronic.libraries.utility.annonations.Internal;
import net.pretronic.libraries.utility.annonations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DefaultMotdTemplate implements MotdTemplate {

    private transient DefaultDKMotd dkMotd;

    private String name;
    private String baseLine;
    private Collection<String> secondLines;
    private String versionText;
    private String wrongVersionText;
    private Collection<Integer> supportedVersions;
    private String favicon;
    private List<String> playerInfo;

    public DefaultMotdTemplate(@NotNull DefaultDKMotd dkMotd, @NotNull String name) {
        this(dkMotd, name, null, null, null, null,
                null, null, null);
    }

    public DefaultMotdTemplate(@NotNull DefaultDKMotd dkMotd, @NotNull String name, String baseLine,
                               Collection<String> secondLines, String versionText, String wrongVersionText,
                               Collection<Integer> supportedVersions, String favicon, List<String> playerInfo) {

        this.dkMotd = dkMotd;
        this.name = name;
        this.baseLine = baseLine;
        this.secondLines = secondLines;
        this.versionText = versionText;
        this.wrongVersionText = wrongVersionText;
        this.supportedVersions = supportedVersions;
        this.favicon = favicon;
        this.playerInfo = playerInfo;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean setName(@NotNull String name) {
        if(this.dkMotd.getMotdTemplateManager().getTemplate(name) != null) {
            throw new IllegalArgumentException("An motd template with the name " + name + " already exists");
        }

        MotdTemplateUpdateEvent event = new DefaultMotdTemplateUpdateEvent(this, name);
        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdateEvent.class, event);
        if(event.isCancelled()) return false;

        String oldValue = this.name;
        this.name = event.getNewValue(String.class);

        this.dkMotd.getMotdTemplateManager().updateTemplatesStorage();

        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdatedEvent.class, new DefaultMotdTemplateUpdatedEvent(this, oldValue));
        return true;
    }

    @Override
    public String getBaseLine() {
        return this.baseLine;
    }

    @Override
    public boolean setBaseLine(String baseLine) {
        MotdTemplateUpdateEvent event = new DefaultMotdTemplateUpdateEvent(this, baseLine);
        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdateEvent.class, event);
        if(event.isCancelled()) return false;


        String oldValue = this.baseLine;
        this.baseLine = event.getNewValue(String.class);

        this.dkMotd.getMotdTemplateManager().updateTemplatesStorage();

        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdatedEvent.class, new DefaultMotdTemplateUpdatedEvent(this, oldValue));
        return true;
    }

    @Override
    public Collection<String> getSecondLines() {
        if(secondLines == null) return null;
        return Collections.unmodifiableCollection(this.secondLines);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean setSecondsLines(Collection<String> secondsLines) {
        MotdTemplateUpdateEvent event = new DefaultMotdTemplateUpdateEvent(this, secondsLines);
        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdateEvent.class, event);
        if(event.isCancelled()) return false;

        Collection<String> oldValue = this.secondLines;
        this.secondLines = (List<String>) event.getNewValue();

        this.dkMotd.getMotdTemplateManager().updateTemplatesStorage();

        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdatedEvent.class, new DefaultMotdTemplateUpdatedEvent(this, oldValue));
        return true;
    }

    @Override
    public boolean addSecondLine(String line) {
        List<String> lines = new ArrayList<>(getOrCopyCollection(this.secondLines));
        lines.add(line);
        return setSecondsLines(lines);
    }

    @Override
    public boolean modifySecondLine(int index, String line) {
        List<String> lines = new ArrayList<>(getOrCopyCollection(this.secondLines));
        if(index >= lines.size()) throw new IllegalArgumentException("Index is greater then size of second lines");
        lines.set(index, line);
        return setSecondsLines(lines);
    }

    @Override
    public boolean removeSecondLine(int index) {
        List<String> lines = new ArrayList<>(getOrCopyCollection(this.secondLines));
        if(index >= lines.size()) throw new IllegalArgumentException("Index is greater then size of second lines");
        lines.remove(index);
        return setSecondsLines(lines);
    }

    @Override
    public boolean clearSecondLines() {
        return setSecondsLines(new ArrayList<>());
    }

    @Override
    public String getVersionText() {
        return this.versionText;
    }

    @Override
    public boolean setVersionText(String versionText) {
        MotdTemplateUpdateEvent event = new DefaultMotdTemplateUpdateEvent(this, versionText);
        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdateEvent.class, event);
        if(event.isCancelled()) return false;

        String oldValue = this.versionText;
        this.versionText = (String) event.getNewValue();

        this.dkMotd.getMotdTemplateManager().updateTemplatesStorage();

        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdatedEvent.class, new DefaultMotdTemplateUpdatedEvent(this, oldValue));
        return true;
    }

    @Override
    public String getWrongVersionText() {
        return this.wrongVersionText;
    }

    @Override
    public boolean setWrongVersionText(String wrongVersionText) {
        MotdTemplateUpdateEvent event = new DefaultMotdTemplateUpdateEvent(this, wrongVersionText);
        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdateEvent.class, event);
        if(event.isCancelled()) return false;

        String oldValue = this.wrongVersionText;
        this.wrongVersionText = (String) event.getNewValue();

        this.dkMotd.getMotdTemplateManager().updateTemplatesStorage();

        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdatedEvent.class, new DefaultMotdTemplateUpdatedEvent(this, oldValue));
        return true;
    }

    @Override
    public Collection<Integer> getSupportedVersions() {
        return this.supportedVersions;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean setSupportedVersions(Collection<Integer> supportedVersions) {
        MotdTemplateUpdateEvent event = new DefaultMotdTemplateUpdateEvent(this, supportedVersions);
        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdateEvent.class, event);
        if(event.isCancelled()) return false;

        Collection<Integer> oldValue = this.supportedVersions;
        this.supportedVersions = (Collection<Integer>) event.getNewValue();

        this.dkMotd.getMotdTemplateManager().updateTemplatesStorage();

        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdatedEvent.class, new DefaultMotdTemplateUpdatedEvent(this, oldValue));
        return true;
    }

    @Override
    public boolean addSupportedVersion(int version) {
        Collection<Integer> versions = new ArrayList<>(getOrCopyCollection(this.supportedVersions));
        versions.add(version);
        return setSupportedVersions(versions);
    }

    @Override
    public boolean removeSupportedVersion(int version) {
        Collection<Integer> versions = new ArrayList<>(getOrCopyCollection(this.supportedVersions));
        if(!versions.remove(version)) throw new IllegalArgumentException(version + " is not configured as supported version and can't be removed");
        return setSupportedVersions(versions);
    }

    @Override
    public boolean clearSupportedVersions() {
        return setSupportedVersions(new ArrayList<>());
    }

    @Override
    public String getFavicon() {
        return this.favicon;
    }

    @Override
    public boolean setFavicon(String favicon) {
        MotdTemplateUpdateEvent event = new DefaultMotdTemplateUpdateEvent(this, favicon);
        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdateEvent.class, event);
        if(event.isCancelled()) return false;

        String oldValue = this.favicon;
        this.favicon = (String) event.getNewValue();

        this.dkMotd.getMotdTemplateManager().updateTemplatesStorage();

        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdatedEvent.class, new DefaultMotdTemplateUpdatedEvent(this, oldValue));
        return true;
    }

    @Override
    public List<String> getPlayerInfo() {
        if(playerInfo == null) return null;
        return Collections.unmodifiableList(this.playerInfo);
    }

    @Override
    public boolean setPlayerInfo(List<String> playerInfo) {
        MotdTemplateUpdateEvent event = new DefaultMotdTemplateUpdateEvent(this, playerInfo);
        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdateEvent.class, event);
        if(event.isCancelled()) return false;

        List<String> oldValue = this.playerInfo;
        this.playerInfo = (List<String>) event.getNewValue();

        this.dkMotd.getMotdTemplateManager().updateTemplatesStorage();

        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdatedEvent.class, new DefaultMotdTemplateUpdatedEvent(this, oldValue));
        return true;
    }

    @Override
    public boolean addPlayerInfo(String playerInfo) {
        List<String> lines = new ArrayList<>(getOrCopyList(this.playerInfo));
        lines.add(playerInfo);
        return setPlayerInfo(lines);
    }

    @Override
    public boolean modifyPlayerInfo(int index, String playerInfo) {
        List<String> lines = new ArrayList<>(getOrCopyCollection(this.playerInfo));
        if(index >= lines.size()) throw new IllegalArgumentException("Index is greater then size of player info");
        lines.set(index, playerInfo);
        return setPlayerInfo(lines);
    }

    @Override
    public boolean removePlayerInfo(int index) {
        List<String> lines = new ArrayList<>(getOrCopyList(this.playerInfo));
        if(index >= playerInfo.size()) throw new IllegalArgumentException("Index is greater then size of player info");
        lines.remove(index);
        return setPlayerInfo(lines);
    }

    @Override
    public boolean clearPlayerInfo() {
        return setPlayerInfo(new ArrayList<>());
    }

    private <T> List<T> getOrCopyList(List<T> list) {
        if(list == null) return new ArrayList<>();
        return new ArrayList<>(list);
    }

    private <T> Collection<T> getOrCopyCollection(Collection<T> collection) {
        if(collection == null) return new ArrayList<>();
        return new ArrayList<>(collection);
    }

    @Internal
    public void setDKMotd(DefaultDKMotd dkMotd) {
        this.dkMotd = dkMotd;
    }
}
