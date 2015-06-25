package com.michiganlabs.migration.database.models;

import com.j256.ormlite.field.DatabaseField;

public abstract class BaseModel {
    @DatabaseField(
        generatedId = true
    )
    private Integer id;

    public BaseModel() {}

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + id + "}";
    }
}
