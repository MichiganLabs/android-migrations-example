package com.michiganlabs.migration.database.versions;

import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import com.michiganlabs.migration.database.models.Example;

import java.sql.SQLException;

/**
 * Created by josh on 6/25/15.
 */
public class V1_AddExampleTable extends Migration {
    public String getDescription() {
        return "Add example table";
    }

    public int getVersion() {
        return 1;
    }

    public void upgrade(SQLiteDatabase db, ConnectionSource connectionSource) throws SQLException {
        TableUtils.createTable(connectionSource, Example.class);
    }
}
