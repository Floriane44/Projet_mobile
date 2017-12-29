package com.projet.florianepeltier.mobileproject.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by root on 27/12/17.
 */

public class PrenomDAO extends DAOBase {
    public static final String TABLE_NAME = "Prenom";
    public static final String ID = "_id";
    public static final String INTITULE = "intitule";
    public static final String REQUESTER = "requester";
    public static final String LIKES = "likes";
    public static final String DISLIKES = "dislikes";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            INTITULE + " TEXT, " +
            REQUESTER + " TEXT, " +
            LIKES + " INTEGER, " +
            DISLIKES + " INTEGER);";
    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public PrenomDAO(Context pContext) {
        super(pContext);
    }

    public Prenom add(String intitule, String requester) {
        ContentValues value = new ContentValues();
        value.put(INTITULE, intitule);
        value.put(REQUESTER, requester);
        mDb.insert(TABLE_NAME, null, value);

        Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " ORDER BY _id DESC LIMIT 1", null);
        cursor.moveToFirst();
        long id = cursor.getLong(0);
        long likes = cursor.getLong(3);
        long dislikes = cursor.getLong(4);

        Prenom m = new Prenom(id, intitule, requester, likes, dislikes);
        return m;
    }

    /**
     * @param id l'identifiant du prénom à supprimer
     */
    public void delete(long id) {
        mDb.delete(TABLE_NAME, ID + " = ?", new String[] {String.valueOf(id)});
    }

    /**
     * @param m le prénom modifié
     */
    public void update(Prenom m) {
        ContentValues value = new ContentValues();
        value.put(INTITULE, m.getIntitule());
        value.put(REQUESTER, m.getRequester());
        mDb.update(TABLE_NAME, value, ID  + " = ?", new String[] {String.valueOf(m.getId())});
    }

    /**
     * @param m le prénom modifié
     */
    public void increaseLikes(Prenom m){
        ContentValues value = new ContentValues();
        m.setLikes(m.getLikes() + 1);
        value.put(LIKES, m.getLikes());
        mDb.update(TABLE_NAME, value, ID  + " = ?", new String[] {String.valueOf(m.getId())});
    }

    /**
     * @param m le prénom modifié
     */
    public void increaseDislikes(Prenom m){
        ContentValues value = new ContentValues();
        m.setDislikes(m.getDislikes() + 1);
        value.put(DISLIKES, m.getDislikes());
        mDb.update(TABLE_NAME, value, ID  + " = ?", new String[] {String.valueOf(m.getId())});
    }

    /**
     * @param id l'identifiant du prénom à récupérer
     */
    public Prenom show(long id) {

        Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " WHERE _id=" + id, null);
        cursor.moveToFirst();

        String intitule = cursor.getString(1);
        String requester = cursor.getString(2);
        long likes = cursor.getLong(3);
        long dislikes = cursor.getLong(4);
        Prenom m = new Prenom(id, intitule, requester, likes, dislikes);

        cursor.close();

        return m;
    }

    public ArrayList<Prenom> showAll() {

        ArrayList<Prenom> listSelect = new ArrayList<Prenom>();
        Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " ORDER BY " + INTITULE + " ASC", null);

        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            String intitule = cursor.getString(1);
            String requester = cursor.getString(2);
            long likes = cursor.getLong(3);
            long dislikes = cursor.getLong(4);
            listSelect.add(new Prenom(id, intitule, requester, likes, dislikes));
        }

        cursor.close();

        return listSelect;
    }
}
