package com.example.mappe2s344183s303045;

public class Booking {
    Long _ID;
    String venneid;
    String restaurantid;
    String dato;
    String klokkeslett;

    public Booking(Long _ID, String venneid, String restaurantid, String dato, String klokkeslett) {
        this._ID = _ID;
        this.venneid = venneid;
        this.restaurantid = restaurantid;
        this.dato = dato;
        this.klokkeslett = klokkeslett;
    }

    public Booking(String venneid, String restaurantid, String dato, String klokkeslett) {
        this.venneid = venneid;
        this.restaurantid = restaurantid;
        this.dato = dato;
        this.klokkeslett = klokkeslett;
    }

    public Booking() {
    }

    public Long get_ID() {
        return _ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }

    public String getVenneid() {
        return venneid;
    }

    public void setVenneid(String venneid) {
        this.venneid = venneid;
    }

    public String getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(String restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getKlokkeslett() {
        return klokkeslett;
    }

    public void setKlokkeslett(String klokkeslett) {
        this.klokkeslett = klokkeslett;
    }
}
