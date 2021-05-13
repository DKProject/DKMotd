package net.pretronic.dkmotd.common.motd;

import net.pretronic.dkmotd.api.Order;
import net.pretronic.dkmotd.api.event.motd.update.MotdTemplateUpdateBaseLineEvent;
import net.pretronic.dkmotd.api.event.motd.update.MotdTemplateUpdateEvent;
import net.pretronic.dkmotd.api.event.motd.update.MotdTemplateUpdateNameEvent;
import net.pretronic.dkmotd.api.event.motd.update.MotdTemplateUpdateSecondLinesEvent;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.common.DefaultDKMotd;
import net.pretronic.dkmotd.common.event.motd.update.DefaultMotdTemplateUpdateBaseLineEvent;
import net.pretronic.dkmotd.common.event.motd.update.DefaultMotdTemplateUpdateEvent;
import net.pretronic.dkmotd.common.event.motd.update.DefaultMotdTemplateUpdateNameEvent;
import net.pretronic.dkmotd.common.event.motd.update.DefaultMotdTemplateUpdateSecondLinesEvent;
import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.message.Textable;
import net.pretronic.libraries.utility.annonations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class DefaultMotdTemplate implements MotdTemplate {

    private transient final DefaultDKMotd dkMotd;

    private final UUID id;

    private transient String name;
    private transient Textable baseLine;
    private transient List<Textable> secondLines;
    private transient Order order;
    private transient Textable versionText;
    private transient Textable wrongVersionText;
    private transient Collection<Integer> supportedVersions;
    private transient String favicon;
    private transient List<Textable> playerInfo;

    private transient boolean initData;

    public DefaultMotdTemplate(@NotNull DefaultDKMotd dkMotd, @NotNull UUID id, @NotNull String name) {
        this.dkMotd = dkMotd;
        this.id = id;
        this.name = name;
        this.order = Order.SORTED;

        this.initData = true;
    }

    public DefaultMotdTemplate(@NotNull DefaultDKMotd dkMotd, @NotNull UUID id, @NotNull String name, Textable baseLine,
                               List<Textable> secondLines, @NotNull Order order, Textable versionText, Textable wrongVersionText,
                               Collection<Integer> supportedVersions, String favicon, List<Textable> playerInfo) {

        this.dkMotd = dkMotd;
        this.id = id;
        this.name = name;
        this.baseLine = baseLine;
        this.secondLines = secondLines;
        this.order = order;
        this.versionText = versionText;
        this.wrongVersionText = wrongVersionText;
        this.supportedVersions = supportedVersions;
        this.favicon = favicon;
        this.playerInfo = playerInfo;

        this.initData = true;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public String getName() {
        initData();
        return this.name;
    }

    @Override
    public boolean setName(@NotNull String name) {
        if(this.dkMotd.getMotdTemplateManager().getTemplate(name) != null) {
            throw new IllegalArgumentException("An motd template with the name " + name + " already exists");
        }

        MotdTemplateUpdateNameEvent event = new DefaultMotdTemplateUpdateNameEvent(this, name);
        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdateNameEvent.class, event);
        if(event.isCancelled()) return false;

        this.dkMotd.getStorage().getMotdTemplates().update()
                .set("Name", event.getNewValue())
                .execute();

        this.name = event.getNewValue();
        return true;
    }

    @Override
    public Textable getBaseLine() {
        initData();
        return this.baseLine;
    }

    @Override
    public boolean setBaseLine(Textable baseLine) {
        MotdTemplateUpdateBaseLineEvent event = new DefaultMotdTemplateUpdateBaseLineEvent(this, baseLine);
        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdateBaseLineEvent.class, event);
        if(event.isCancelled()) return false;

        this.dkMotd.getStorage().getMotdTemplates().update()
                .set("BaseLine", DocumentFileType.JSON.getWriter().write(event.getNewValue().toDocument(), false))
                .execute();

        this.baseLine = event.getNewValue();
        return true;
    }

    @Override
    public List<Textable> getSecondLines() {
        initData();
        return this.secondLines;
    }

    @Override
    public boolean setSecondsLines(List<Textable> secondsLines) {
        MotdTemplateUpdateSecondLinesEvent event = new DefaultMotdTemplateUpdateSecondLinesEvent(this, secondsLines);
        this.dkMotd.getEventBus().callEvent(MotdTemplateUpdateSecondLinesEvent.class, event);
        if(event.isCancelled()) return false;

        this.dkMotd.getStorage().getMotdTemplates().update()
                .set("SecondLines", DocumentFileType.JSON.getWriter().write(Document.newDocument(event.getNewValue()), false))
                .execute();

        this.secondLines = event.getNewValue();
        return true;
    }

    @Override
    public Order getOrder() {
        initData();
        return this.order;
    }

    @Override
    public boolean setOrder(Order order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Textable getVersionText() {
        initData();
        return this.versionText;
    }

    @Override
    public boolean setVersionText(Textable versionText) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Textable getWrongVersionText() {
        initData();
        return this.wrongVersionText;
    }

    @Override
    public boolean setWrongVersionText(Textable wrongVersionText) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Integer> getSupportedVersions() {
        initData();
        return this.supportedVersions;
    }

    @Override
    public boolean setSupportedVersions(Collection<Integer> supportedVersions) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getFavicon() {
        initData();
        return this.favicon;
    }

    @Override
    public boolean setFavicon(String favicon) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Textable> getPlayerInfo() {
        initData();
        return this.playerInfo;
    }

    @Override
    public boolean setPlayerInfo(List<Textable> playerInfo) {
        throw new UnsupportedOperationException();
    }

    private void initData() {
        if(!initData) {

            throw new UnsupportedOperationException();

            //initData = true;
        }
    }
}
