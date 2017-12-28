package com.projet.florianepeltier.mobileproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 27/12/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String PRENOM_ID = "_id";
    public static final String PRENOM_INTITULE = "intitule";
    public static final String PRENOM_REQUESTER = "requester";
    public static final String PRENOM_LIKES = "likes";
    public static final String PRENOM_DISLIKES = "dislikes";

    public static final String PRENOM_TABLE_NAME = "Prenom";
    public static final String PRENOM_TABLE_CREATE = "CREATE TABLE " + PRENOM_TABLE_NAME + " (" +
            PRENOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PRENOM_INTITULE + " TEXT, " +
            PRENOM_REQUESTER + " TEXT, " +
            PRENOM_LIKES + " INTEGER, " +
            PRENOM_DISLIKES + " INTEGER);";
    public static final String PRENOM_TABLE_DROP = "DROP TABLE IF EXISTS " + PRENOM_TABLE_NAME + ";";


    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PRENOM_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PRENOM_TABLE_DROP);
        onCreate(db);
    }
}
