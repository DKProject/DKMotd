package net.pretronic.dkmotd.api.tablist;

public interface Tablist {

    String getHeader();

    boolean setHeader(String header);


    String getFooter();

    boolean setFooter(String footer);


    String getLabyModServerBannerUrl();

    boolean setLabyModServerBannerUrl(String url);
}
