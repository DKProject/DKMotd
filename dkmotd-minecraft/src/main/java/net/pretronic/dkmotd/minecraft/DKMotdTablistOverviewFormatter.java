package net.pretronic.dkmotd.minecraft;

import net.pretronic.dkmotd.api.DKMotd;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.player.tablist.TablistOverviewFormatter;
import org.mcnative.runtime.api.text.Text;
import org.mcnative.runtime.api.text.components.MessageComponent;

public class DKMotdTablistOverviewFormatter implements TablistOverviewFormatter {

    private final DKMotd dkMotd;

    public DKMotdTablistOverviewFormatter(DKMotd dkMotd) {
        this.dkMotd = dkMotd;
    }

    @Override
    public MessageComponent<?> formatHeader(ConnectedMinecraftPlayer receiver, VariableSet headerVariables, VariableSet footerVariables) {
        String header = dkMotd.getTablist().getHeader();
        return Text.parse(headerVariables.add("player", receiver).replace(header));
    }

    @Override
    public MessageComponent<?> formatFooter(ConnectedMinecraftPlayer receiver, VariableSet headerVariables, VariableSet footerVariables) {
        String footer = dkMotd.getTablist().getFooter();
        return Text.parse(footerVariables.add("player", receiver).replace(footer));
    }
}
