package com.example.mappe2s344183s303045;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout forsteknapp = (LinearLayout) findViewById(R.id.bookingKnapp);
        forsteknapp.setOnClickListener(this::onClick);
        LinearLayout andreknapp = (LinearLayout) findViewById(R.id.vennerKnapp);
        andreknapp.setOnClickListener(this::onClick);
        LinearLayout tredjeknapp = (LinearLayout) findViewById(R.id.restaurantKnapp);
        tredjeknapp.setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.bookingKnapp) {
            Intent intent = new Intent(this, BookingKlasse.class);
            startActivity(intent);
        } else if (v.getId() == R.id.vennerKnapp) {
            Intent intent = new Intent(this, VenneKlasse.class);
            startActivity(intent);
        } else if (v.getId() == R.id.restaurantKnapp) {
            Intent intent = new Intent(this, RestaurantKlasse.class);
            startActivity(intent);
        }
    }
}