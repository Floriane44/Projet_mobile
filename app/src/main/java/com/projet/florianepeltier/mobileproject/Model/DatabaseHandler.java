package com.projet.florianepeltier.mobileproject.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 27/12/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String PRENOM_ID = "_id";
    private static final String PRENOM_INTITULE = "intitule";
    private static final String PRENOM_REQUESTER = "requester";
    private static final String PRENOM_LIKES = "likes";
    private static final String PRENOM_DISLIKES = "dislikes";

    private static final String PRENOM_TABLE_NAME = "Prenom";
    private static final String PRENOM_TABLE_CREATE = "CREATE TABLE " + PRENOM_TABLE_NAME + " (" +
            PRENOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PRENOM_INTITULE + " TEXT, " +
            PRENOM_REQUESTER + " TEXT, " +
            PRENOM_LIKES + " INTEGER, " +
            PRENOM_DISLIKES + " INTEGER);";
    private static final String PRENOM_TABLE_INSERT = "INSERT INTO " + PRENOM_TABLE_NAME +
            " SELECT 0 AS " + PRENOM_ID + ", 'Floriane' AS " + PRENOM_INTITULE + ", 'Floriane' AS " + PRENOM_REQUESTER +
            ", 8 AS " + PRENOM_LIKES + ", 1 AS " + PRENOM_DISLIKES +
            " UNION ALL SELECT 1, 'Anna', 'Floriane', 3, 2 UNION ALL SELECT 2, 'Rosalya', 'Floriane', 5, 1";
    private static final String PRENOM_TABLE_DROP = "DROP TABLE IF EXISTS " + PRENOM_TABLE_NAME + ";";


    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PRENOM_TABLE_CREATE);
        db.execSQL(PRENOM_TABLE_INSERT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PRENOM_TABLE_DROP);
        onCreate(db);
    }
}
