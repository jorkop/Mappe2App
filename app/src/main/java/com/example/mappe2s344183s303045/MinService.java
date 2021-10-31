package com.example.mappe2s344183s303045;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MinService extends Service {

    @Override
public IBinder onBind(Intent arg0) {
        return null;
}


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        DBHandler db = new DBHandler(this);
        ArrayList<Booking> bookingliste = db.finnAlleBookinger();
        ArrayList<String> sjekk = new ArrayList<>();
        for (Booking booking : bookingliste) {
            String tekst = "";
            tekst = booking.getDato();
            sjekk.add(tekst);
        }

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        if (sjekk.contains(currentDate)) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Intent i = new Intent(this, Resultat.class);
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, i, 0);
            Notification notifikasjon = new NotificationCompat.Builder(this, "22")
                    .setContentTitle("SPIS")
                    .setContentText("Du skal på restaurant i dag")
                    .setSmallIcon(R.mipmap.logo)
                    .setContentIntent(pIntent).build();
            notifikasjon.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(0, notifikasjon);

            

        }
        return super.onStartCommand(intent, flags, startId);
    }
}