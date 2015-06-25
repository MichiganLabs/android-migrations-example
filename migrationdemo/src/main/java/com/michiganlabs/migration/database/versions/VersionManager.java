package com.michiganlabs.migration.database.versions;


public class VersionManager {
    private static Migration[] migrations = {
        new V1_AddExampleTable(),
        new V2_AddTextToExample(),
    };

    public static Migration getMigration(int versionNum) {
        try {
            return migrations[versionNum - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public static int getHeadVersion() {
        return migrations[migrations.length - 1].getVersion();
    }
}
