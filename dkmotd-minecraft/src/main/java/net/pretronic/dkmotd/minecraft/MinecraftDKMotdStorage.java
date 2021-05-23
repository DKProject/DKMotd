package net.pretronic.dkmotd.minecraft;

import net.pretronic.dkmotd.common.DKMotdStorage;
import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.type.DocumentFileType;
import org.mcnative.runtime.api.Setting;

public class MinecraftDKMotdStorage implements DKMotdStorage {

    private final DKMotdPlugin plugin;

    public MinecraftDKMotdStorage(DKMotdPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Object getObject(String key) {
        Setting setting = plugin.getSetting(key);
        if(setting == null) return null;
        return setting.getObjectValue();
    }

    @Override
    public Document get(String key) {
        Setting setting = plugin.getSetting(key);
        if(setting == null) return null;
        return setting.getDocumentValue();
    }

    @Override
    public void insertObject(String key, Object value) {
        plugin.setSetting(key, value);
    }

    @Override
    public void insert(String key, Document document) {
        plugin.setSetting(key, DocumentFileType.JSON.getWriter().write(document, false));
    }

    @Override
    public void delete(String key) {
        plugin.deleteSetting(key);
    }

    @Override
    public void update(String key, Object value) {
        plugin.getSetting(key).setValue(value);
    }

    @Override
    public void update(String key, Document document) {
        plugin.getSetting(key).setValue(DocumentFileType.JSON.getWriter().write(document, false));
    }
}
