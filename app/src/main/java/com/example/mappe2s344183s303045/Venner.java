package com.example.mappe2s344183s303045;

public class Venner {
    Long _ID;
    String navn;
    String telefon;

    public Venner(Long _ID, String navn, String telefon) {
        this._ID = _ID;
        this.navn = navn;
        this.telefon = telefon;
    }

    public Venner(String navn, String telefon) {
        this.navn = navn;
        this.telefon = telefon;
    }

    public Venner() {
    }

    public Long get_ID() {
        return _ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
