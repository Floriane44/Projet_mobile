package com.projet.florianepeltier.mobileproject;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

/**
 * Created by root on 27/12/17.
 */

public class ShowAllActivity extends AppCompatActivity {
    DatabaseHandler db;
    private PrenomDAO row;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycler);
        setTitle(R.string.list);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.recycle);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new ShowAllAdapter(this));

        db = new DatabaseHandler(this, "prenom", null, 1);
        Log.e("database", db.getWritableDatabase().getPath());
        Log.e("database", String.valueOf(doesDatabaseExist(this, "prenom")));
        Log.e("database", String.valueOf(checkDataBase()));
        if (doesDatabaseExist(this, "prenom") && checkDataBase()){
            Toast.makeText(this, R.string.databasesuccess, Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, R.string.databaseerror, Toast.LENGTH_SHORT).show();

        row = new PrenomDAO(this);
        row.open();
        row.showAll();
    }

    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase("/data/data/com.projet.florianepeltier.mobileproject/databases/prenom", null, SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            // base de données n'existe pas.
        }
        return checkDB != null;
    }

    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    public void addOnClick(View view){
        Intent myIntent = new Intent(ShowAllActivity.this, AddActivity.class);
        startActivity(myIntent);
    }
}
