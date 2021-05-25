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
    public void delete(String key) {
        plugin.deleteSetting(key);
    }

    @Override
    public void set(String key, Object value) {
        if(value instanceof Document) value = DocumentFileType.JSON.getWriter().write((Document) value, false);
        System.out.println("DEBUG: DKMotd storage set " + key + ":" + value);
        plugin.setSetting(key, value);
    }
}
