package net.pretronic.dkmotd.api.joinmessage;

import java.util.Collection;

public interface JoinMessageTemplateManager {

    Collection<JoinMessageTemplate> getTemplates();

    JoinMessageTemplate getTemplate(String name);

    JoinMessageTemplate createTemplate(String name);

    boolean deleteTemplate(JoinMessageTemplate template);


    JoinMessageTemplate getActiveTemplate();

    boolean setActiveTemplate(JoinMessageTemplate template);
}
