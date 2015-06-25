package com.michiganlabs.migration.ui.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.j256.ormlite.dao.Dao;
import com.michiganlabs.migration.database.DatabaseHelper;
import com.michiganlabs.migration.R;
import com.michiganlabs.migration.database.models.Example;
import com.michiganlabs.migration.util.Ln;

import java.sql.SQLException;

public class MainActivity extends ActionBarActivity {
    private DatabaseHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        dbHelper = new DatabaseHelper(this);
    }

    @OnClick(R.id.theButton)
    public void onTheButtonClicked() {
        try {
            Dao<Example, Integer> dao = dbHelper.getDao(Example.class);
            dao.create(new Example("Testing 123!"));
        } catch (SQLException e) {
            Ln.e(e);
        }
    }
}
