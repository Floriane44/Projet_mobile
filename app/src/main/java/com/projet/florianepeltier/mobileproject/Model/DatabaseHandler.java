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
            ", 10 AS " + PRENOM_LIKES + ", 0 AS " + PRENOM_DISLIKES +
            " UNION ALL SELECT 1, 'Anna', 'Elsa', 0, 0" +
            " UNION ALL SELECT 2, 'Rosalya', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 3, 'Abel', 'Rémi78', 0, 0" +
            " UNION ALL SELECT 4, 'Anicet', 'Rémi78', 0, 0" +
            " UNION ALL SELECT 5, 'Anne-Lyze', 'Laure', 0, 0" +
            " UNION ALL SELECT 6, 'Bénilde', 'Serugu', 0, 0" +
            " UNION ALL SELECT 7, 'Bertholf', 'Adolf', 0, 0" +
            " UNION ALL SELECT 8, 'Brithwold', 'Raoul93', 0, 0" +
            " UNION ALL SELECT 9, 'Calypso', 'Rémi78', 0, 0" +
            " UNION ALL SELECT 10, 'Cerise', 'Floriane', 0, 0" +
            " UNION ALL SELECT 11, 'Armin', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 12, 'Alexy', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 13, 'Castiel', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 14, 'Cunégonde', 'Floriane', 0, 0" +
            " UNION ALL SELECT 15, 'Cyprien', 'Raoul93', 0, 0" +
            " UNION ALL SELECT 16, 'Daisy', 'Mario', 0, 0" +
            " UNION ALL SELECT 17, 'Dieudonné', 'Serugu', 0, 0" +
            " UNION ALL SELECT 18, 'Dominique', 'Dominique', 0, 0" +
            " UNION ALL SELECT 19, 'Droctovée', 'Rémi78', 0, 0" +
            " UNION ALL SELECT 20, 'Eanfleda', 'Rémi78', 0, 0" +
            " UNION ALL SELECT 21, 'Eri', 'Floriane', 0, 0" +
            " UNION ALL SELECT 22, 'Airi', 'Floriane', 0, 0" +
            " UNION ALL SELECT 23, 'Emeraude', 'Eric45', 0, 0" +
            " UNION ALL SELECT 24, 'Eric', 'Eric45', 0, 0" +
            " UNION ALL SELECT 25, 'Ambre', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 26, 'Charlotte', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 27, 'Angeline', 'Floriane', 0, 0" +
            " UNION ALL SELECT 28, 'Florence', 'Dominique', 0, 0" +
            " UNION ALL SELECT 29, 'Guenièvre', 'Eric45', 0, 0" +
            " UNION ALL SELECT 30, 'Hiltrude', 'Raoul93', 0, 0" +
            " UNION ALL SELECT 31, 'Iris', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 32, 'Ismérie', 'Raoul93', 0, 0" +
            " UNION ALL SELECT 33, 'Juthaël', 'Rémi78', 0, 0" +
            " UNION ALL SELECT 34, 'Kentin', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 35, 'Kim', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 36, 'Kentigern', 'Titouan', 0, 0" +
            " UNION ALL SELECT 37, 'Li', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 38, 'Lysandre', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 39, 'Laure', 'Laure', 0, 0" +
            " UNION ALL SELECT 40, 'Léonce', 'Titouan', 0, 0" +
            " UNION ALL SELECT 41, 'Maribelle', 'Floriane', 0, 0" +
            " UNION ALL SELECT 42, 'Kizito', 'Floriane', 0, 0" +
            " UNION ALL SELECT 43, 'Marilyn', 'Meimei', 0, 0" +
            " UNION ALL SELECT 44, 'Meimi', 'Meimei', 0, 0" +
            " UNION ALL SELECT 45, 'Mélina', 'Floriane', 0, 0" +
            " UNION ALL SELECT 46, 'Koharu', 'Floriane', 0, 0" +
            " UNION ALL SELECT 47, 'Nathaniel', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 48, 'Narcisse', 'Serugu', 0, 0" +
            " UNION ALL SELECT 49, 'Napoléon', 'Serugu', 0, 0" +
            " UNION ALL SELECT 50, 'Noël', 'Titouan', 0, 0" +
            " UNION ALL SELECT 51, 'Odia', 'Floriane', 0, 0" +
            " UNION ALL SELECT 52, 'Leigh', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 53, 'Olaf', 'Elsa', 0, 0" +
            " UNION ALL SELECT 54, 'Panthéa', 'Eric45', 0, 0" +
            " UNION ALL SELECT 55, 'Privaël', 'Eric45', 0, 0" +
            " UNION ALL SELECT 56, 'Peggy', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 57, 'Prya', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 58, 'Queenie', 'Raoul93', 0, 0" +
            " UNION ALL SELECT 59, 'Reina', 'Floriane', 0, 0" +
            " UNION ALL SELECT 60, 'Rabab', 'Floriane', 0, 0" +
            " UNION ALL SELECT 61, 'Rosemarie', 'Rémi78', 0, 0" +
            " UNION ALL SELECT 62, 'Sachiko', 'Floriane', 0, 0" +
            " UNION ALL SELECT 63, 'Sayumi', 'Floriane', 0, 0" +
            " UNION ALL SELECT 64, 'Sakura', 'Floriane', 0, 0" +
            " UNION ALL SELECT 65, 'Sanseviéra', 'Rémi78', 0, 0" +
            " UNION ALL SELECT 66, 'Serge', 'Serugu', 0, 0" +
            " UNION ALL SELECT 67, 'Sue', 'Titouan', 0, 0" +
            " UNION ALL SELECT 68, 'Melody', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 69, 'Capucine', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 70, 'Nina', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 71, 'Leati', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 72, 'Risa', 'Floriane', 0, 0" +
            " UNION ALL SELECT 73, 'Théodoric', 'Titouan', 0, 0" +
            " UNION ALL SELECT 74, 'Timothée', 'Titouan', 0, 0" +
            " UNION ALL SELECT 75, 'Tortank', 'Serugu', 0, 0" +
            " UNION ALL SELECT 76, 'Ultane', 'Rémi78', 0, 0" +
            " UNION ALL SELECT 77, 'Violette', 'Sglowim', 0, 0" +
            " UNION ALL SELECT 78, 'Vanille', 'Floriane', 0, 0" +
            " UNION ALL SELECT 79, 'Viriane', 'Eric45', 0, 0" +
            " UNION ALL SELECT 80, 'Weigela', 'Eric45', 0, 0" +
            " UNION ALL SELECT 81, 'Xanthé', 'Raoul93', 0, 0" +
            " UNION ALL SELECT 82, 'Youri', 'Titouan', 0, 0" +
            " UNION ALL SELECT 83, 'Zelda', 'Mario', 0, 0" +
            " UNION ALL SELECT 84, 'Zénon', 'Rémi78', 0, 0";

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
