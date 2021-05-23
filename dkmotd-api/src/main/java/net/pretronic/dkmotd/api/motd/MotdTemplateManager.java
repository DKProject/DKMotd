package net.pretronic.dkmotd.api.motd;

import net.pretronic.libraries.utility.annonations.NotNull;

import java.util.Collection;

public interface MotdTemplateManager {

    Collection<MotdTemplate> getTemplates();

    MotdTemplate getTemplate(String name);

    MotdTemplate createTemplate(String name);

    boolean deleteTemplate(MotdTemplate template);

    void replicateBaseLine(MotdTemplate template);


    @NotNull
    MotdTemplate getActiveTemplate();

    boolean setActiveTemplate(@NotNull MotdTemplate template);
}
