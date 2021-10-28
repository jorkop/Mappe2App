package com.example.mappe2s344183s303045;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class BookingKlasse extends AppCompatActivity implements View.OnClickListener{
    DBHandler db;
    TextView dato, klokkeslett;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Spinner restaurant;
    ListView bookingliste;
    EditText venneid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking);
        db = new DBHandler(this);
        dato =(TextView)findViewById(R.id.visDato);
        klokkeslett =(TextView)findViewById(R.id.visTid);

        bookingliste = (ListView) findViewById(R.id.bookingliste);
        venneid = (EditText) findViewById(R.id.venneID);
        dato.setOnClickListener(this);
        klokkeslett.setOnClickListener(this);
        restaurant = (Spinner) findViewById(R.id.velgRestaurant);

        //Setter registrerte restauranter inn i dropdown liste
        ArrayList<String> liste = new ArrayList<String>();
        liste.add("Velg restaurant");
        ArrayList<Restaurant> restaurantliste = db.finnAlleRestauranter();
        for (Restaurant restaurant : restaurantliste) {
            String tekst = "";
            tekst = restaurant.getNavn();
            liste.add(tekst);
        }
        Spinner dropdown = findViewById(R.id.velgRestaurant);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, liste);
        dropdown.setAdapter(adapter);

    }


    public void leggTilBooking(View v) {


        try {
            Booking booking = new Booking(venneid.getText().toString(), restaurant.getSelectedItem().toString(), dato.getText().toString(), klokkeslett.getText().toString());

            ArrayList<Venn> venneliste = db.finnAlleVenner();
            ArrayList<String> sjekk = new ArrayList<>();
            for (Venn venn: venneliste) {
                String tekst = "";
                tekst = venn.get_ID().toString();
                sjekk.add(tekst);
            }

            if (booking.getRestaurantid().equals("Velg restaurant")) {
                Toast.makeText(getApplicationContext(), "Velg restaurant", Toast.LENGTH_SHORT).show();
            } else if(booking.getDato().equals("Velg dato")){
                Toast.makeText(getApplicationContext(), "Velg dato", Toast.LENGTH_SHORT).show();
            }else if(booking.getKlokkeslett().equals("Velg tid")){
                Toast.makeText(getApplicationContext(), "Velg klokkeslett", Toast.LENGTH_SHORT).show();
            } else if(booking.getVenneid().equals("")){
                Toast.makeText(getApplicationContext(), "Ta med en venn!", Toast.LENGTH_SHORT).show();
            }else if (!sjekk.contains(booking.getVenneid())){
                Toast.makeText(getApplicationContext(), "Venn finnes ikke!", Toast.LENGTH_SHORT).show();
            }

            else {
                db.leggTilBooking(booking);
                venneid.setText("");
                restaurant.setSelection(0);
                dato.setText("Velg dato");
                klokkeslett.setText("Velg tid");
                Toast.makeText(getApplicationContext(), "Lagret!", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            Log.d("Feil i leggtil: ", "Feilmelding: " + e);
            Toast.makeText(getApplicationContext(), "Kunne ikke lagre. Prøv igjen!", Toast.LENGTH_SHORT).show();
        }
    }

    public void finnAlleBookinger(View v) {
        ArrayList<String> utskrift = new ArrayList<>();
        ArrayList<Booking> bookinger = db.finnAlleBookinger();
        for (Booking booking : bookinger) {
            String tekst = "";
            tekst = tekst + "Id: " + booking.get_ID() + "\n" + "Restaurant ID: " +
                    booking.getRestaurantid() + "\n" + "VenneID: " + booking.getVenneid() + "\n" + "Dato: " +
                    booking.getDato() + "\n" + "Klokkeslett: " +
                    booking.getKlokkeslett();
            utskrift.add(tekst);
        }

        try {
            if (utskrift.size() < 1) {
                Toast.makeText(getApplicationContext(), "Ingen bookinger er registrert", Toast.LENGTH_SHORT).show();
                bookingliste.setAdapter(null);
            } else {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, utskrift);
                bookingliste.setAdapter(adapter);
            }

        } catch(Exception e) {
            Log.d("Feil i arrayadapter", "FEIL " + e);
        }
    }


    // Funksjon OnClick og TimeSet er hentet fra https://www.journaldev.com/9976/android-date-time-picker-dialog
    @Override
    public void onClick(View v) {

        if (v == dato) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            dato.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == klokkeslett) {

            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            if(minute < 10) {
                                String s = Integer.toString(minute);
                                s = "0" + s;
                                klokkeslett.setText(hourOfDay + ":" + s);
                            }
                            else {
                                klokkeslett.setText(hourOfDay + ":" + minute);
                            }
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}




