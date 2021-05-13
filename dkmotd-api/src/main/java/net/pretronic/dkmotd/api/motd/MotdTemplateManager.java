package net.pretronic.dkmotd.api.motd;

import java.util.Collection;
import java.util.UUID;

public interface MotdTemplateManager {

    Collection<MotdTemplate> getTemplates();

    MotdTemplate getTemplate(UUID id);

    MotdTemplate getTemplate(String name);

    MotdTemplate createTemplate(String name);

    boolean deleteTemplate(MotdTemplate template);

    void replicateBaseLine(MotdTemplate template);


    MotdTemplate getActiveTemplate();

    void setActiveTemplate(MotdTemplate template);
}
