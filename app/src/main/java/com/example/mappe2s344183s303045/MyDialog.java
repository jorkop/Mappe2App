package com.example.mappe2s344183s303045;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment {

    private DialogClickListener callback;

    public interface DialogClickListener {
        public void onYesClick();
        public void onNoClick();

        void slettDialog(View v);
    }



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        try{
            callback=(DialogClickListener)getActivity();
        }
        catch (ClassCastException e){
            throw new ClassCastException("Kallende klasse må implementere interfacet!");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity()).setTitle("Er du sikker på at du vil slette?").setPositiveButton("Slett",
                new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton){
                        callback.onYesClick();
                    }
                }).setNegativeButton("Ikke slett",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog,int whichButton)
                    { callback.onNoClick();
                    }}).create();
    }




}