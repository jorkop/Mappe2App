package com.example.mappe2s344183s303045;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class VenneKlasse extends AppCompatActivity implements MyDialog.DialogClickListener{
    EditText fornavn;
    EditText etternavn;
    EditText tlf;
    EditText id;
    DBHandler db;

    @Override
    public void onYesClick() {
        return;
        /*
        Poenget er at man skal skrive inn en id før man trykker på slett og få opp
        "Vil du slette Arne Hansen med id x?". Da må overskriften endres inne i MyDialog setTitle.

         Burde også få opp bekreftelse på sletting
         */
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
        setContentView(R.layout.venner);
        fornavn = (EditText) findViewById(R.id.vennfornavn);
        etternavn = (EditText) findViewById(R.id.vennetternavn);
        tlf = (EditText) findViewById(R.id.venntlf);
        id = (EditText) findViewById(R.id.vennID);
        db = new DBHandler(this);

    }


    public void leggtil(View v) {
        Venn venn = new Venn(fornavn.getText().toString(),
                etternavn.getText().toString());
        db.leggTilVenn(venn);
        Log.d("Legg inn: ", "legger til kontakter");
    }

}
