package com.michiganlabs.migration.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Example extends BaseModel {
    public static final String TEXT = "text";
    @DatabaseField(
        columnName = TEXT
    )
    private String text;

    public Example() {}

    public Example(String text) {
        setText(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
