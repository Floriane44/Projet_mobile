package com.projet.florianepeltier.mobileproject.Controller;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.projet.florianepeltier.mobileproject.Model.DatabaseHandler;
import com.projet.florianepeltier.mobileproject.Model.PrenomDAO;
import com.projet.florianepeltier.mobileproject.R;

import java.io.File;

/**
 * Created by root on 29/12/17.
 */

public class ShowAllFragment extends Fragment {
    DatabaseHandler db;
    private PrenomDAO row;
    private ShowAllAdapter _adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.recycler, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycle);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        _adapter = new ShowAllAdapter(getActivity(), (ShowAllAdapter.PrenomLoader) getActivity());
        rv.setAdapter(_adapter);

        db = new DatabaseHandler(getActivity(), "prenom", null, 1);
        Log.e("database", db.getWritableDatabase().getPath());
        Log.e("database", String.valueOf(doesDatabaseExist(getActivity(), "prenom")));
        Log.e("database", String.valueOf(checkDataBase()));
        if (doesDatabaseExist(getActivity(), "prenom") && checkDataBase()){
            Toast.makeText(getActivity(), R.string.databasesuccess, Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(getActivity(), R.string.databaseerror, Toast.LENGTH_LONG).show();

        row = new PrenomDAO(getActivity());
        row.open();
        row.showAll();

        Button add = (Button) view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), AddActivity.class);
                startActivity(myIntent);
            }
        });
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
}
