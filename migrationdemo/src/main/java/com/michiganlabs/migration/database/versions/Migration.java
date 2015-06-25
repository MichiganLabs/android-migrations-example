package com.michiganlabs.migration.database.versions;

import java.sql.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.support.ConnectionSource;
import com.michiganlabs.migration.util.Ln;


public abstract class Migration {
    protected void prepare(SQLiteDatabase db) {
        Ln.i("Running migration to v%d: \"%s\"", getVersion(), getDescription());
        db.beginTransaction();
    }

    protected void finalize(SQLiteDatabase db, boolean success) {
        if (success) {
            db.setTransactionSuccessful();
        }
        db.endTransaction();
    }

    public abstract void upgrade(SQLiteDatabase db, ConnectionSource connectionSource) throws SQLException;
    public abstract String getDescription();
    public abstract int getVersion();

    public boolean doUpgrade(SQLiteDatabase db, ConnectionSource connectionSource) {
        boolean success = true;
        prepare(db);
        try {
            upgrade(db, connectionSource);
            Ln.i("Migration to v%d OK!", getVersion());
        } catch (final SQLException e) {
            Ln.e(e, "Unable to upgrade to version %d", getVersion());
            success = false;
        } finally {
            finalize(db, success);
        }
        return success;
    }
}
