package com.example.mappe2s344183s303045;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

public class VenneKlasse extends AppCompatActivity implements MyDialog.DialogClickListener {
    EditText fornavn;
    EditText etternavn;
    EditText tlf;
    EditText id;
    DBHandler db;
    ListView visvenner;

    @Override
    public void onYesClick() {
        String venneid = id.getText().toString();
        Long idforsletting = Long.parseLong(venneid);

        db.slettVenn(idforsletting);
        Toast.makeText(getApplicationContext(), "Venn slettet", Toast.LENGTH_SHORT).show();
        id.setText("");

    }


        /*
        Poenget er at man skal skrive inn en id før man trykker på slett og få opp
        "Vil du slette Arne Hansen med id x?". Da må overskriften endres inne i MyDialog setTitle.

         Burde også få opp bekreftelse på sletting
        */


    @Override
    public void onNoClick() {
        return;
    }

    @Override
    public void slettDialog(View v) {
        String venneid = (id.getText().toString());
        ArrayList<Venn> venneliste = db.finnAlleVenner();
        ArrayList<String> sjekk = new ArrayList<>();
        for (Venn venn: venneliste) {
            String tekst = "";
            tekst = venn.get_ID().toString();
            sjekk.add(tekst);
        }


        try {
            Long idforsletting = Long.parseLong(venneid);

            if(venneid.equals("")){
                Toast.makeText(getApplicationContext(), "Skriv inn en ID", Toast.LENGTH_SHORT).show();
            }else if (!sjekk.contains(venneid)){
                Toast.makeText(getApplicationContext(), "Ingen venner med ID " + venneid, Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.venner);
        fornavn = (EditText) findViewById(R.id.vennfornavn);
        etternavn = (EditText) findViewById(R.id.vennetternavn);
        tlf = (EditText) findViewById(R.id.venntlf);
        id = (EditText) findViewById(R.id.vennID);
        visvenner = (ListView) findViewById(R.id.venneliste);
        db = new DBHandler(this);
    }



    public void leggtil(View v) {
        try {
            Venn venn = new Venn(fornavn.getText().toString(), etternavn.getText().toString(), tlf.getText().toString());
            if (venn.getFornavn().equals("")){
                Toast.makeText(getApplicationContext(), "Skriv inn fornavn", Toast.LENGTH_SHORT).show();
            } else if(venn.getEtternavn().equals("")) {
                Toast.makeText(getApplicationContext(), "Skriv inn etternavn", Toast.LENGTH_SHORT).show();
            } else if(venn.getTelefon().equals("")){
                Toast.makeText(getApplicationContext(), "Skriv inn telefonnummer", Toast.LENGTH_SHORT).show();
            }else {
                db.leggTilVenn(venn);
                fornavn.setText("");
                etternavn.setText("");
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
        ArrayList<Venn> venner = db.finnAlleVenner();
        for (Venn venn : venner) {
            String tekst = "";
            tekst = tekst + "Id: " + venn.get_ID() + "\n" + "Navn: " +
                    venn.getFornavn() + " " + venn.getEtternavn() + "\n" + "Telefon: " +
                    venn.getTelefon();
            utskrift.add(tekst);
        }

        try {
            if (utskrift.size() < 1) {
                Toast.makeText(getApplicationContext(), "Ingen venner er registrert", Toast.LENGTH_SHORT).show();
                visvenner.setAdapter(null);
            } else {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, utskrift);
                visvenner.setAdapter(adapter);
            }

        } catch(Exception e) {
            Log.d("Feil i arrayadapter", "FEIL " + e);
        }
    }

    public void oppdaterVenn(View v) {
        Venn venn = new Venn(fornavn.getText().toString(), etternavn.getText().toString(), tlf.getText().toString());

        ArrayList<String> utskrift = new ArrayList<>();
        ArrayList<Venn> venner = db.finnAlleVenner();
        for (Venn venn1 : venner) {
            String tekst = "";
            tekst = venn1.get_ID().toString();
            utskrift.add(tekst);
        }

        String s = id.getText().toString();
        if (venn.getFornavn().equals("")) {
            Toast.makeText(getApplicationContext(), "Skriv inn fornavn", Toast.LENGTH_SHORT).show();
        } else if (venn.getEtternavn().equals("")) {
            Toast.makeText(getApplicationContext(), "Skriv inn etternavn!", Toast.LENGTH_SHORT).show();
        } else if (venn.getTelefon().equals("")) {
            Toast.makeText(getApplicationContext(), "Skriv inn telefon", Toast.LENGTH_SHORT).show();
        } else if (s.equals("")) {
            Toast.makeText(getApplicationContext(), "Skriv inn venne-ID!", Toast.LENGTH_SHORT).show();
        } else if (!utskrift.contains(s)) {
            Toast.makeText(getApplicationContext(), "Venne-ID eksisterer ikke!", Toast.LENGTH_SHORT).show();
        } else {

            venn.setFornavn(fornavn.getText().toString());
            venn.setEtternavn(etternavn.getText().toString());
            venn.setTelefon(tlf.getText().toString());
            venn.set_ID(Long.parseLong(id.getText().toString()));
            db.oppdaterVenn(venn);

            Toast.makeText(getApplicationContext(), "Venn endret!", Toast.LENGTH_SHORT).show();
            fornavn.setText("");
            id.setText("");
            etternavn.setText("");
            tlf.setText("");
        }

    }
}
