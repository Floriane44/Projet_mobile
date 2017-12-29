package com.projet.florianepeltier.mobileproject.Controller;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.projet.florianepeltier.mobileproject.Model.DatabaseHandler;
import com.projet.florianepeltier.mobileproject.Model.Prenom;
import com.projet.florianepeltier.mobileproject.Model.PrenomDAO;
import com.projet.florianepeltier.mobileproject.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by root on 29/12/17.
 */

public class ShowAllFragment extends Fragment implements SearchView.OnQueryTextListener {
    DatabaseHandler db;
    private PrenomDAO row;
    private ShowAllAdapter _adapter;

    private static final Comparator<Prenom> ALPHABETICAL_COMPARATOR = new Comparator<Prenom>() {
        @Override
        public int compare(Prenom a, Prenom b) {
            return a.getIntitule().compareTo(b.getIntitule());
        }
    };

    private List<Prenom> prenoms;
    private RecyclerView rv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.recycler, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = (RecyclerView) view.findViewById(R.id.recycle);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        _adapter = new ShowAllAdapter(getActivity(), (ShowAllAdapter.PrenomLoader) getActivity(), ALPHABETICAL_COMPARATOR);
        rv.setAdapter(_adapter);

        db = new DatabaseHandler(getActivity(), "prenom", null, 1);
        if (doesDatabaseExist(getActivity(), "prenom") && checkDataBase()){
            Toast.makeText(getActivity(), R.string.databasesuccess, Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getActivity(), R.string.databaseerror, Toast.LENGTH_LONG).show();

        row = new PrenomDAO(getActivity());
        row.open();
        prenoms = row.showAll();
        _adapter.add(prenoms);

        Button add = (Button) view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), AddActivity.class));
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        super.onCreateOptionsMenu(menu,inflater);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<Prenom> filteredPrenomList = filter(prenoms, query);
        _adapter.replaceAll(filteredPrenomList);
        rv.scrollToPosition(0);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private static List<Prenom> filter(List<Prenom> prenoms, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<Prenom> filteredPrenomList = new ArrayList<>();
        for (Prenom prenom : prenoms) {
            final String intitule = prenom.getIntitule().toLowerCase();
            if (intitule.contains(lowerCaseQuery)) {
                filteredPrenomList.add(prenom);
            }
        }
        return filteredPrenomList;
    }
}
