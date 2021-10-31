package com.example.mappe2s344183s303045;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "22";
    DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();


        LinearLayout forsteknapp = (LinearLayout) findViewById(R.id.bookingKnapp);
        forsteknapp.setOnClickListener(this::onClick);
        LinearLayout andreknapp = (LinearLayout) findViewById(R.id.vennerKnapp);
        andreknapp.setOnClickListener(this::onClick);
        LinearLayout tredjeknapp = (LinearLayout) findViewById(R.id.restaurantKnapp);
        tredjeknapp.setOnClickListener(this::onClick);

        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putString("SMSBESKJED", "Påminnelse: Du har en restaurant-avtale i dag.")
                .apply();

        Intent intent = new Intent();
        intent.setAction("com.example.mappe2s344183s303045");
        sendBroadcast(intent);
        SMS();

    }

    public void SMS() {
        try {
            boolean sjekk = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("SMSPreferanse", true);

            String telefon = db.smsTilDagensBookinger();
            System.out.println("Telefon: " +telefon);
            int MY_PERMISSIONS_REQUEST_SEND_SMS = ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
            int MY_PHONE_STATE_PERMISSION = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
            if (sjekk) {
                if (MY_PERMISSIONS_REQUEST_SEND_SMS == PackageManager.PERMISSION_GRANTED && MY_PHONE_STATE_PERMISSION ==
                        PackageManager.PERMISSION_GRANTED) {
                    String message = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("SMSBESKJED", "");
                    System.out.println("Melding1 " + message);
                    if (message == null){
                        message = "Melding";
                    }
                    System.out.println("Melding2 " + message);
                    SmsManager smsMan = SmsManager.getDefault();
                    smsMan.sendTextMessage(telefon, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "Har sendt sms", Toast.LENGTH_SHORT).show();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE}, 0);
                }
            }
        } catch (Exception e) {
            System.out.println("Feil: " + e);
        }
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




    private void createNotificationChannel() {
// Create the NotificationChannel, but only on API 26+ because
// the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
// Register the channel with the system; you can't change the importance
// or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}