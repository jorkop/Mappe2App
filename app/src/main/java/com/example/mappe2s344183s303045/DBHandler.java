package com.example.mappe2s344183s303045;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
//Tabell for venner
    static String TABLE_VENNER = "Venner";
    static String KEY_ID = "_ID";
    static String KEY_FIRSTNAME = "Fornavn";
    static String KEY_LASTNAME = "Etternavn";
    static String KEY_PH_NO = "Telefon";

//Tabell for restauranter
    static String TABLE_RESTAURANTER = "Restauranter";
    static String KEY_RES_ID = "_ID";
    static String KEY_NAME = "Navn";
    static String KEY_CAT = "Kategori";
    static String KEY_ADDRESS = "Adresse";
    static String KEY_PHONE = "Telefon";

//Tabell for bookinger
    static String TABLE_BOOKINGER = "Bookinger";
    static String KEY_BOOKING_ID = "_ID";
    static String KEY_FRIEND_ID = "VenneID";
    static String KEY_RESTAURANT_ID = "ResID";
    static String KEY_DATE = "Dato";
    static String KEY_TIME = "Klokkeslett";

    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "Spisedatabase";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String LAG_TABELL = "CREATE TABLE " + TABLE_VENNER + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_FIRSTNAME + " TEXT," + KEY_LASTNAME + " TEXT," + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(LAG_TABELL);

        String LAG_TABELL2 ="CREATE TABLE " + TABLE_RESTAURANTER + "(" + KEY_RES_ID + " INTEGER PRIMARY KEY," + KEY_NAME +
                " TEXT," + KEY_CAT + " TEXT," + KEY_ADDRESS + " TEXT," + KEY_PHONE + " TEXT" + ")";
        db.execSQL(LAG_TABELL2);

        String LAG_TABELL3 = "CREATE TABLE " + TABLE_BOOKINGER + "(" + KEY_BOOKING_ID + " INTEGER PRIMARY KEY,"+ KEY_FRIEND_ID +
                " TEXT," + KEY_RESTAURANT_ID + " TEXT," + KEY_DATE + " TEXT," + KEY_TIME + " TEXT" + ")";
        db.execSQL(LAG_TABELL3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENNER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKINGER);
        onCreate(db);
    }

    /*

    <------------------FUNKSJONER FOR VENNER--------------------->

     */

    public void leggTilVenn(Venn venn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, venn.getFornavn());
        values.put(KEY_LASTNAME, venn.getEtternavn());
        values.put(KEY_PH_NO, venn.getTelefon());
        db.insert(TABLE_VENNER, null, values);
        db.close();
    }

    public ArrayList<Venn> finnAlleVenner() {
        ArrayList<Venn> venneListe = new ArrayList<Venn>();
        String selectQuery = "SELECT * FROM " + TABLE_VENNER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {Venn venn = new Venn(); venn.set_ID(cursor.getLong(0));
                venn.setFornavn(cursor.getString(1));
                venn.setEtternavn(cursor.getString(2));
                venn.setTelefon(cursor.getString(3));
                venneListe.add(venn);
            }
            while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return venneListe;
    }
    public void slettVenn(Long inn_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VENNER, KEY_ID + " =? ", new String[]{Long.toString(inn_id)});
        db.close();
    }


    /*

    <------------------FUNKSJONER FOR RESTAURANTER--------------------->

     */


    public void leggTilRestaurant (Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, restaurant.getNavn());
        values.put(KEY_CAT, restaurant.getKategori());
        values.put(KEY_ADDRESS, restaurant.getAdresse());
        values.put(KEY_PHONE, restaurant.getTelefon());
        db.insert(TABLE_RESTAURANTER, null, values);
        db.close();
    }

    public ArrayList<Restaurant> finnAlleRestauranter() {
        ArrayList<Restaurant> restaurantListe = new ArrayList<Restaurant>();
        String selectQuery = "SELECT * FROM " + TABLE_RESTAURANTER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {Restaurant restaurant = new Restaurant(); restaurant.set_ID(cursor.getLong(0));
                restaurant.setNavn(cursor.getString(1));
                restaurant.setKategori(cursor.getString(2));
                restaurant.setAdresse(cursor.getString(3));
                restaurant.setTelefon(cursor.getString(4));
                restaurantListe.add(restaurant);
            }
            while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return restaurantListe;
    }

    public void slettRestaurant(Long inn_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESTAURANTER, KEY_ID + " =? ", new String[]{Long.toString(inn_id)});
        db.close();
    }

/*

    <------------------FUNKSJONER FOR BOOKINGER--------------------->

     */


    public void leggTilBooking (Booking booking) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FRIEND_ID, booking.getVenneid());
        values.put(KEY_RESTAURANT_ID, booking.getRestaurantid());
        values.put(KEY_DATE, booking.getDato());
        values.put(KEY_TIME, booking.getKlokkeslett());
        db.insert(TABLE_BOOKINGER, null, values);
        db.close();
    }

    public ArrayList<Booking> finnAlleBookinger() {
        ArrayList<Booking> bookingListe = new ArrayList<Booking>();
        String selectQuery = "SELECT * FROM " + TABLE_BOOKINGER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {Booking booking = new Booking(); booking.set_ID(cursor.getLong(0));
                booking.setVenneid(cursor.getString(1));
                booking.setRestaurantid(cursor.getString(2));
                booking.setDato(cursor.getString(3));
                booking.setKlokkeslett(cursor.getString(4));
                bookingListe.add(booking);
            }
            while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return bookingListe;
    }

    public void slettBooking(Long inn_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOKINGER, KEY_ID + " =? ", new String[]{Long.toString(inn_id)});
        db.close();
    }


}


