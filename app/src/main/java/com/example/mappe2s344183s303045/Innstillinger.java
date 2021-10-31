package com.example.mappe2s344183s303045;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

public class Innstillinger extends AppCompatActivity {
    DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.innstillinger);

        boolean pref = getSharedPreferences("PREFERENCE",MODE_PRIVATE).getBoolean("SMSPreferanse",false);
        CheckBox cb = findViewById(R.id.smsPref);
        cb.setChecked(pref);
    }

    public void lagrePref(View v) {
        //String pref = getSharedPreferences("PREFERENCE",MODE_PRIVATE).getString("SMSPreferanse","");
        CheckBox input = ((CheckBox) v);
        if (input.isChecked()) {
            getSharedPreferences("PREFERENCE",MODE_PRIVATE)
                    .edit()
                    .putBoolean("SMSPreferanse",true)
                    .apply();
        } else {
            getSharedPreferences("PREFERENCE",MODE_PRIVATE)
                    .edit()
                    .putBoolean("SMSPreferanse",false)
                    .apply();
        }
    }
}