package com.michiganlabs.migration.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

import com.michiganlabs.migration.database.versions.Migration;
import com.michiganlabs.migration.util.Ln;
import com.michiganlabs.migration.database.versions.VersionManager;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = VersionManager.getHeadVersion();

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        Ln.w("Creating new database using migrations");
        onUpgrade(db, connectionSource, 0, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Ln.w("Upgrading db from v%d to v%d...", oldVersion, newVersion);
        while (oldVersion++ < newVersion) {
            Migration m = VersionManager.getMigration(oldVersion);
            if (!m.doUpgrade(db, connectionSource)) {
                // Unsuccessful, stop migrations
                return;
            }
        }
        Ln.i("Database upgrade complete!");
    }
}
