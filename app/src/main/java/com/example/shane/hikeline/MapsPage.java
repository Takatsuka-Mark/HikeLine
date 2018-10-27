package com.example.shane.hikeline;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MapsPage extends AppCompatActivity {

    private static final String TAG = "MapsPage";

    private static final int ERROR_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_page);

        if(isServicesOK()){
            init();
        }
    }

    private void init(){

    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking Google Maps Services version");

        int avaliable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MapsPage.this);

        boolean retVal = false;

        if(avaliable == ConnectionResult.SUCCESS) {
            // everything is working big good
            Log.d(TAG, "isServicesOK: Google Maps Services is working");
            retVal = true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(avaliable)){
            // there was an error but that is ok maybe
            Log.d(TAG, "isServicesOK: an error happened but it is maybe ok?");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MapsPage.this, avaliable, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();

        return retVal;
    }



    public void open_mainPage(View view)
    {
        Intent intent = new Intent(MapsPage.this, MenuActivity.class);
        startActivity(intent);
    }

}
