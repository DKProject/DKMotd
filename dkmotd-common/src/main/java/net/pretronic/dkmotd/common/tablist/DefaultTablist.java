package net.pretronic.dkmotd.common.tablist;

import net.pretronic.dkmotd.api.tablist.Tablist;
import net.pretronic.dkmotd.common.DefaultDKMotd;

public class DefaultTablist implements Tablist {

    private transient DefaultDKMotd dkMotd;

    private String header;
    private String footer;
    private String labyModServerBannerUrl;

    public DefaultTablist(DefaultDKMotd dkMotd) {
        this.dkMotd = dkMotd;

        this.header = "";
        this.footer = "";
        this.labyModServerBannerUrl = "";
    }

    @Override
    public String getHeader() {
        return this.header;
    }

    @Override
    public boolean setHeader(String header) {
        this.header = header;
        this.dkMotd.updateTablistStorage();
        return true;
    }

    @Override
    public String getFooter() {
        return this.footer;
    }

    @Override
    public boolean setFooter(String footer) {
        this.footer = footer;
        this.dkMotd.updateTablistStorage();
        return true;
    }

    @Override
    public String getLabyModServerBannerUrl() {
        return this.labyModServerBannerUrl;
    }

    @Override
    public boolean setLabyModServerBannerUrl(String url) {
        this.labyModServerBannerUrl = url;
        this.dkMotd.updateTablistStorage();
        return true;
    }

    public DefaultTablist setDKMotd(DefaultDKMotd dkMotd) {
        this.dkMotd = dkMotd;
        return this;
    }
}
