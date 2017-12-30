package com.projet.florianepeltier.mobileproject.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by root on 27/12/17.
 */

public abstract class DAOBase {

    // Version de l'application
    private final static int VERSION = 3;

    // Le nom du fichier qui représente ma base
    private final static String NOM = "database.db";

    SQLiteDatabase mDb = null;
    private DatabaseHandler mHandler = null;

    DAOBase(Context pContext) {
        this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
    }

    public void open() {
        // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
        mDb = mHandler.getWritableDatabase();
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}

