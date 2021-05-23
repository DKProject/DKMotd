package net.pretronic.dkmotd.minecraft;

import net.pretronic.dkmotd.common.joinmessage.DefaultJoinMessageTemplate;
import net.pretronic.dkmotd.common.maintenance.DefaultMaintenance;
import net.pretronic.dkmotd.common.motd.DefaultMotdTemplate;
import net.pretronic.libraries.message.bml.variable.describer.VariableDescriber;
import net.pretronic.libraries.message.bml.variable.describer.VariableDescriberRegistry;
import net.pretronic.libraries.utility.duration.DurationProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DescriberRegistrar {

    public static void register() {
        VariableDescriber<DefaultMotdTemplate> motdDescriber = VariableDescriberRegistry.registerDescriber(DefaultMotdTemplate.class);
        motdDescriber.registerFunction("name", template -> template.getName() == null ? "unset" : template.getName());
        motdDescriber.registerFunction("baseLine", template -> template.getBaseLine() == null ? "unset" : template.getBaseLine());
        motdDescriber.registerFunction("versionText", template -> template.getVersionText() == null ? "unset" : template.getVersionText());
        motdDescriber.registerFunction("wrongVersionText", template -> template.getWrongVersionText() == null ? "unset" : template.getWrongVersionText());
        motdDescriber.registerFunction("favicon", template -> template.getFavicon() == null ? "unset" : template.getFavicon());

        VariableDescriber<DefaultMaintenance> maintenanceDescriber = VariableDescriberRegistry.registerDescriber(DefaultMaintenance.class);
        maintenanceDescriber.registerFunction("formattedTimeout", maintenance -> {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return maintenance.getTimeout() == -1 ? "no timeout" : format.format(new Date(maintenance.getTimeout()));
        });

        VariableDescriber<DefaultJoinMessageTemplate> joinMessageDescriber = VariableDescriberRegistry.registerDescriber(DefaultJoinMessageTemplate.class);
        joinMessageDescriber.registerFunction("name", template -> template.getName() == null ? "unset" : template.getName());
        joinMessageDescriber.registerFunction("baseMessage", template -> template.getBaseMessage() == null ? "unset" : template.getBaseMessage());
    }
}
