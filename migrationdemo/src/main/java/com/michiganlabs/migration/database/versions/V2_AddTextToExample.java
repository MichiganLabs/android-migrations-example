package com.michiganlabs.migration.database.versions;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.michiganlabs.migration.database.models.Example;
import com.michiganlabs.migration.util.Ln;

import java.sql.SQLException;

/**
 * Created by josh on 6/25/15.
 */
public class V2_AddTextToExample extends Migration {
    public String getDescription() {
        return "Add text column to example table";
    }

    public int getVersion() {
        return 2;
    }

    public void upgrade(SQLiteDatabase db, ConnectionSource connectionSource) throws SQLException {
        try {
            DaoManager.lookupDao(connectionSource, Example.class)
                .executeRaw(
                    "ALTER TABLE `" + DatabaseTableConfig.extractTableName(Example.class) + "`"
                        + " ADD COLUMN " + Example.TEXT + " VARCHAR;"
                );
        } catch (SQLException e) {
            Ln.i("example.text field already exists");
        }
    }
}
