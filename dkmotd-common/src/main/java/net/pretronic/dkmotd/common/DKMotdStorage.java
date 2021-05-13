package net.pretronic.dkmotd.common;

import net.pretronic.databasequery.api.Database;
import net.pretronic.databasequery.api.collection.DatabaseCollection;
import net.pretronic.databasequery.api.collection.field.FieldOption;
import net.pretronic.databasequery.api.datatype.DataType;

public class DKMotdStorage {

    private final Database database;

    private final DatabaseCollection motdTemplates;

    public DKMotdStorage(Database database) {
        this.database = database;

        this.motdTemplates = createMotdTemplates();
    }

    public DatabaseCollection getMotdTemplates() {
        return motdTemplates;
    }

    private DatabaseCollection createMotdTemplates() {
        return this.database.createCollection("dkmotd_motd_templates")
                .field("Id", DataType.UUID, FieldOption.PRIMARY_KEY)
                .field("Name", DataType.STRING, FieldOption.NOT_NULL, FieldOption.UNIQUE)
                .field("BaseLine", DataType.STRING)
                .field("SecondLines", DataType.STRING)
                .field("Order", DataType.STRING, FieldOption.NOT_NULL)
                .field("VersionText", DataType.STRING)
                .field("WrongVersionText", DataType.STRING)
                .field("SupportedVersions", DataType.STRING)
                .field("Favicon", DataType.STRING)
                .field("PlayerInfo", DataType.STRING)
                .create();
    }
}
