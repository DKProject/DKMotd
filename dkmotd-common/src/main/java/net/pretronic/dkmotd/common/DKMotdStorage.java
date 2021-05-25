package net.pretronic.dkmotd.common;

import net.pretronic.libraries.document.Document;

public interface DKMotdStorage {

    Object getObject(String key);

    Document get(String key);

    void delete(String key);

    void set(String key, Object value);
}
