package com.example.mappe2s344183s303045;

public class Restauranter {
    Long _ID;

    public Restauranter(Long _ID, String navn, String telefon, String adresse, String kategori) {
        this._ID = _ID;
        this.navn = navn;
        this.telefon = telefon;
        this.adresse = adresse;
        this.kategori = kategori;
    }

    public Restauranter(String navn, String telefon, String adresse, String kategori) {
        this.navn = navn;
        this.telefon = telefon;
        this.adresse = adresse;
        this.kategori = kategori;
    }

    public Restauranter() {
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    String navn;
    String telefon;
    String adresse;
    String kategori;
}

