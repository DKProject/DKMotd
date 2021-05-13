package net.pretronic.dkmotd.api.joinmessage;

import java.util.Collection;

public interface JoinMessageTemplateManager {

    Collection<JoinMessageTemplate> getTemplates();

    JoinMessageTemplate getTemplate(int id);

    JoinMessageTemplate createTemplate(String name);

    boolean deleteTemplate(JoinMessageTemplate template);


    JoinMessageTemplate getActiveTemplate();

    void setActiveTemplate(JoinMessageTemplate template);
}
