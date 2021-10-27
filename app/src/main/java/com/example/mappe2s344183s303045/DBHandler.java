package com.example.mappe2s344183s303045;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {
    static String TABLE_VENNER = "Vennene";
    static String KEY_ID = "_ID";
    static String KEY_FIRSTNAME = "Fornavn";
    static String KEY_LASTNAME = "Etternavn";
    static String KEY_PH_NO = "Telefon";
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "Venneliste";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String LAG_TABELL = "CREATE TABLE " + TABLE_VENNER + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_FIRSTNAME + " TEXT," + KEY_LASTNAME + " TEXT," + KEY_PH_NO + " TEXT" + ")";
        Log.d("SQL", LAG_TABELL);
        db.execSQL(LAG_TABELL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENNER);
        onCreate(db);
    }

    public void leggTilVenn(Venn venn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, venn.getFornavn());
        values.put(KEY_LASTNAME, venn.getEtternavn());
        values.put(KEY_PH_NO, venn.getTelefon());
        db.insert(TABLE_VENNER, null, values);
        db.close();

    }
}


/*
Dette er kun skallet for hva som skal inn. Lurer på om vi må ha to dghandler, en til venner og en til
restauranter, kanskje til og med en til en kombo av begge?? Jeg skjønner ikke helt hvordan man skal
kombinere begge databasene, hvis man skal ha to.
 */