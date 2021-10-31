package com.example.mappe2s344183s303045;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class RestaurantKlasse extends AppCompatActivity implements MyDialog.DialogClickListener{

    EditText navn;
    EditText adresse;
    EditText tlf;
    EditText kategori;
    EditText id;
    ListView visrestaurant;
    DBHandler db;

    @Override
    public void onYesClick() {
        String restaurantid = (id.getText().toString());
        Long idforsletting = Long.parseLong(restaurantid);

        db.slettRestaurant(idforsletting);
        Toast.makeText(getApplicationContext(), "Restaurant slettet", Toast.LENGTH_SHORT).show();
        id.setText("");

    }

    @Override
    public void onNoClick() {
        return;
    }

    @Override
    public void slettDialog(View v) {
        String resid = id.getText().toString();
        ArrayList<Restaurant> resliste = db.finnAlleRestauranter();
        ArrayList<String> sjekk = new ArrayList<>();
        for (Restaurant res: resliste) {
            String tekst = "";
            tekst = res.get_ID().toString();
            sjekk.add(tekst);
        }


        try {
            Long idforsletting = Long.parseLong(resid);

            if(resid.equals("")){
                Toast.makeText(getApplicationContext(), "Skriv inn en ID", Toast.LENGTH_SHORT).show();
            }else if (!sjekk.contains(resid)){
                Toast.makeText(getApplicationContext(), "Ingen restaurant med ID " + resid, Toast.LENGTH_SHORT).show();
            }
            else {
                DialogFragment dialog = new MyDialog();
                dialog.show(getSupportFragmentManager(), "Avslutt");
            }
        }catch(Exception e) {
            Toast.makeText(getApplicationContext(), "Skriv inn gyldig ID", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restauranter);
        navn = (EditText) findViewById(R.id.restaurantnavn);
        adresse = (EditText) findViewById(R.id.restaurantadresse);
        kategori = (EditText) findViewById(R.id.restaurantkategori);
        tlf = (EditText) findViewById(R.id.restauranttlf);
        id = (EditText) findViewById(R.id.restaurantID);
        visrestaurant = (ListView) findViewById(R.id.restaurantliste);
        db = new DBHandler(this);
    }

    public void leggtil(View v) {
        try {
            Restaurant restaurant = new Restaurant(navn.getText().toString(), kategori.getText().toString(), adresse.getText().toString(), tlf.getText().toString());
            if (restaurant.getNavn().equals("")){
                Toast.makeText(getApplicationContext(), "Skriv inn navn", Toast.LENGTH_SHORT).show();
            } else if(restaurant.getKategori().equals("")) {
                Toast.makeText(getApplicationContext(), "Skriv inn kategori", Toast.LENGTH_SHORT).show();
            }else if(restaurant.getAdresse().equals("")) {
                Toast.makeText(getApplicationContext(), "Skriv inn adresse" + restaurant.getAdresse(), Toast.LENGTH_SHORT).show();
            } else if(restaurant.getTelefon().equals("")){
                Toast.makeText(getApplicationContext(), "Skriv inn telefonnummer", Toast.LENGTH_SHORT).show();
            }else {
                db.leggTilRestaurant(restaurant);
                navn.setText("");
                kategori.setText("");
                adresse.setText("");
                tlf.setText("");
                Toast.makeText(getApplicationContext(), "Lagret!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.d("Feil i leggtil: ", "Feilmelding: " + e);
            Toast.makeText(getApplicationContext(), "Kunne ikke lagre. Prøv igjen!", Toast.LENGTH_SHORT).show();
        }
    }

    public void visalle(View v) {
        ArrayList<String> utskrift = new ArrayList<>();
        ArrayList<Restaurant> restauranter = db.finnAlleRestauranter();
        for (Restaurant restaurant : restauranter) {
            String tekst = "";
            tekst = tekst + "Id: " + restaurant.get_ID() + "\n" + "Navn: " +
                    restaurant.getNavn() + "\n" + "Kategori: " + restaurant.getKategori() + "\n" + "Adresse: " +
                    restaurant.getAdresse() + "\n" + "Telefon: " + restaurant.getTelefon();
            utskrift.add(tekst);
        }

        try {
            if (utskrift.size() < 1) {
                Toast.makeText(getApplicationContext(), "Ingen restauranter er registrert", Toast.LENGTH_SHORT).show();
                visrestaurant.setAdapter(null);
            } else {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, utskrift);
                visrestaurant.setAdapter(adapter);
            }

        } catch(Exception e) {
            Log.d("Feil i arrayadapter", "FEIL " + e);
        }
    }

    public void oppdaterRestaurant(View v) {
        Restaurant restaurant = new Restaurant(navn.getText().toString(), kategori.getText().toString(), adresse.getText().toString(), tlf.getText().toString());

        ArrayList<String> utskrift = new ArrayList<>();
        ArrayList<Restaurant> restauranter = db.finnAlleRestauranter();
        for (Restaurant restaurant1 : restauranter) {
            String tekst = "";
            tekst = restaurant1.get_ID().toString();
            utskrift.add(tekst);
        }

        String s = id.getText().toString();
        if (restaurant.getNavn().equals("")) {
            Toast.makeText(getApplicationContext(), "Skriv inn navn", Toast.LENGTH_SHORT).show();
        } else if(restaurant.getKategori().equals("")){
            Toast.makeText(getApplicationContext(), "Skriv inn kategori!", Toast.LENGTH_SHORT).show();
        }else if(restaurant.getAdresse().equals("")){
            Toast.makeText(getApplicationContext(), "Skriv inn adresse", Toast.LENGTH_SHORT).show();
        }else if(restaurant.getTelefon().equals("")){
            Toast.makeText(getApplicationContext(), "Skriv inn telefon", Toast.LENGTH_SHORT).show();
        } else if (s.equals("")){
            Toast.makeText(getApplicationContext(), "Skriv inn booking-ID!", Toast.LENGTH_SHORT).show();
        } else if (!utskrift.contains(s)){
            Toast.makeText(getApplicationContext(), "Booking-ID eksisterer ikke!", Toast.LENGTH_SHORT).show();
        }else {

            restaurant.setNavn(navn.getText().toString());
            restaurant.setKategori(kategori.getText().toString());
            restaurant.setAdresse(adresse.getText().toString());
            restaurant.setTelefon(tlf.getText().toString());
            restaurant.set_ID(Long.parseLong(id.getText().toString()));
            db.oppdaterRestaurant(restaurant);

            Toast.makeText(getApplicationContext(), "Restaurant endret!", Toast.LENGTH_SHORT).show();
            navn.setText("");
            id.setText("");
            adresse.setText("");
            kategori.setText("");
            tlf.setText("");
        }
    }

}