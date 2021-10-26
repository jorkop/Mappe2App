package com.example.mappe2s344183s303045;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    static String TABLE_VENNER = "Venner";
    static String KEY_ID = "_ID";
    static String KEY_NAME = "Navn";
    static String KEY_PH_NO = "Telefon";
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "Venner";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

    public void leggTilVenn(Venn venn){
        //Legge til sql for å lagre til database
    }
}


/*
Dette er kun skallet for hva som skal inn. Lurer på om vi må ha to dghandler, en til venner og en til
restauranter, kanskje til og med en til en kombo av begge?? Jeg skjønner ikke helt hvordan man skal
kombinere begge databasene, hvis man skal ha to.
 */