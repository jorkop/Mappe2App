package com.example.mappe2s344183s303045;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    static String KEY_PHONE = "Tlf";

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

        String LAG_TABELL2 = "CREATE TABLE " + TABLE_RESTAURANTER + "(" + KEY_RES_ID + " INTEGER PRIMARY KEY," + KEY_NAME +
                " TEXT," + KEY_CAT + " TEXT," + KEY_ADDRESS + " TEXT," + KEY_PHONE + " TEXT" + ")";
        db.execSQL(LAG_TABELL2);

        String LAG_TABELL3 = "CREATE TABLE " + TABLE_BOOKINGER + "(" + KEY_BOOKING_ID + " INTEGER PRIMARY KEY," + KEY_FRIEND_ID +
                " TEXT," + KEY_RESTAURANT_ID + " TEXT," + KEY_DATE + " TEXT," + KEY_TIME + " TEXT" + ")";
        db.execSQL(LAG_TABELL3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENNER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKINGER);
        this.onCreate(db);
    }

    /*

    <------------------FUNKSJONER FOR VENNER--------------------->

     */

    public void leggTilVenn(Venn venn) {
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
            do {
                Venn venn = new Venn();
                venn.set_ID(cursor.getLong(0));
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

    public int oppdaterVenn(Venn venn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, venn.getFornavn());
        values.put(KEY_LASTNAME, venn.getEtternavn());
        values.put(KEY_PH_NO, venn.getTelefon());
        int endret = db.update(TABLE_VENNER, values, KEY_ID + "= ?", new String[]{String.valueOf(venn.get_ID())});
        db.close();
        return endret;
    }


    /*

    <------------------FUNKSJONER FOR RESTAURANTER--------------------->

     */


    public void leggTilRestaurant(Restaurant restaurant) {
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
            do {
                Restaurant restaurant = new Restaurant();
                restaurant.set_ID(cursor.getLong(0));
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

    public int oppdaterRestaurant(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, restaurant.getNavn());
        values.put(KEY_CAT, restaurant.getKategori());
        values.put(KEY_ADDRESS, restaurant.getAdresse());
        values.put(KEY_PHONE, restaurant.getTelefon());
        int endret = db.update(TABLE_RESTAURANTER, values, KEY_RES_ID + "= ?", new String[]{String.valueOf(restaurant.get_ID())});
        db.close();
        return endret;
    }

/*

    <------------------FUNKSJONER FOR BOOKINGER--------------------->

     */


    public void leggTilBooking(Booking booking) {
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
            do {
                Booking booking = new Booking();
                booking.set_ID(cursor.getLong(0));
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

    //FÅR IKKE SMS TIL Å FUNKE
    public String smsTilDagensBookinger() {
        String s = "";
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        System.out.println("Dato for sms: " + currentDate);
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT " + KEY_PH_NO + " FROM " + TABLE_VENNER + ", " + TABLE_BOOKINGER + " WHERE " + KEY_DATE + " = " + currentDate + " AND " + TABLE_VENNER + "." + KEY_ID + " = " + TABLE_BOOKINGER + "." + KEY_FRIEND_ID + ";";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            System.out.println("INNE I IF");
            cursor.moveToFirst();
            int c = cursor.getColumnIndex("Telefon");
            s = cursor.getString(c);
            System.out.println("TELEFON " + s);
            System.out.println("Index " + c);

            cursor.moveToNext();
            cursor.close();
            db.close();
            Log.d("Count", cursor.getCount() + "");
        }
        return s;
    }
    public int oppdaterBooking(Booking booking) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FRIEND_ID, booking.getVenneid());
        values.put(KEY_RESTAURANT_ID, booking.getRestaurantid());
        values.put(KEY_DATE, booking.getDato());
        values.put(KEY_TIME, booking.getKlokkeslett());
        int endret = db.update(TABLE_BOOKINGER, values, KEY_ID + "= ?", new String[]{String.valueOf(booking.get_ID())});
        db.close();
        return endret;
    }
}





