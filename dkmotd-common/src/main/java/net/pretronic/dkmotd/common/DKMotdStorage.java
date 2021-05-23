package net.pretronic.dkmotd.common;

import net.pretronic.libraries.document.Document;

public interface DKMotdStorage {

    Object getObject(String key);

    Document get(String key);


    void insertObject(String key, Object value);

    void insert(String key, Document document);


    void delete(String key);


    void update(String key, Object value);

    void update(String key, Document document);
}
