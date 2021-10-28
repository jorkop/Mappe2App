package com.example.mappe2s344183s303045;

public class Restaurant {
    Long _ID;

    public Restaurant(Long _ID, String navn, String kategori, String adresse, String telefon) {
        this._ID = _ID;
        this.navn = navn;
        this.kategori = kategori;
        this.adresse = adresse;
        this.telefon = telefon;
    }

    public Restaurant(String navn, String kategori, String adresse, String telefon) {
        this.navn = navn;
        this.kategori = kategori;
        this.adresse = adresse;
        this.telefon = telefon;
    }

    public Restaurant() {
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

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }




    String navn;
    String kategori;
    String adresse;
    String telefon;
}

