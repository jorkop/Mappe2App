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
        try {
            Long idforsletting = Long.parseLong(restaurantid);

            if(restaurantid.equals("")) {
                Toast.makeText(getApplicationContext(), "Skriv inn en ID", Toast.LENGTH_SHORT).show();
            }
            else {
                db.slettVenn(idforsletting);
                Toast.makeText(getApplicationContext(), "Venn slettet", Toast.LENGTH_SHORT).show();
            }

        } catch(Exception e){
            Toast.makeText(getApplicationContext(), "Skriv inn gyldig ID", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNoClick() {
        return;
    }

    @Override
    public void slettDialog(View v) {
        DialogFragment dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(),"Avslutt");
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

                System.out.println("I navn:    " + restaurant);

            } else if(restaurant.getKategori().equals("")) {
                Toast.makeText(getApplicationContext(), "Skriv inn kategori", Toast.LENGTH_SHORT).show();

                System.out.println("I kategori:    " + restaurant);

            }else if(restaurant.getAdresse().equals("")) {
                Toast.makeText(getApplicationContext(), "Skriv inn adresse" + restaurant.getAdresse(), Toast.LENGTH_SHORT).show();

                System.out.println("I adresse:    " + restaurant);

            } else if(restaurant.getTelefon().equals("")){
                Toast.makeText(getApplicationContext(), "Skriv inn telefonnummer", Toast.LENGTH_SHORT).show();

                System.out.println("I tlf:    " + restaurant);

            }else {
                db.leggTilRestaurant(restaurant);
                Log.d("Leggtil: ", "legger til restaurant i database");
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
            tekst = tekst + "Id: " + restaurant.get_ID() + ",Navn: " +
                    restaurant.getNavn() + ",Kategori: " + restaurant.getKategori() + ",Adresse: " +
                    restaurant.getAdresse() +" ,Telefon: " + restaurant.getTelefon();
            Log.d("Navn: ", tekst );
            utskrift.add(tekst);
            Log.d("Array: ", ""  +  utskrift);
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

}