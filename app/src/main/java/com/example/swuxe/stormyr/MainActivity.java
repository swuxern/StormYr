package com.example.swuxe.stormyr;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.Manifest;



import java.util.List;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.OnReverseGeocodingListener;
import io.nlopez.smartlocation.SmartLocation;

public class MainActivity extends AppCompatActivity {

    TextView locationText;
    private static final int MY_PERMISSIONS_REQUEST_FOR_LOCATION = 1;
    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intents = new Intent(this, sokSkjerm.class);
        mContext = this;

        final Button sokSted = (Button)findViewById(R.id.sokSted);
        locationText = (TextView) findViewById(R.id.lol);




        final String url = "http://www.yr.no/sted/Norge/Nordland/Rana/Mo/varsel.xml";

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_FOR_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            findLocation();
        }


        sokSted.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), sokSkjerm.class);
                startActivity(intent);
            }
        });

    }

    public void findLocation() {
        SmartLocation.with(this).location().oneFix().start(new OnLocationUpdatedListener() {
            @Override
            public void onLocationUpdated(Location location) {
                double lat = location.getLatitude();
                double lng = location.getLongitude();
                showLocation(location);
            }


        });

    }

    private void showLocation(Location location) {
        if (location != null) {
            final String text = new String();

            // We are going to get the address for the current position
            SmartLocation.with(this).geocoding().reverse(location, new OnReverseGeocodingListener() {
                @Override
                public void onAddressResolved(Location original, List<Address> results) {
                    if (results.size() > 0) {
                        Address result = results.get(0);

                        String by = result.getLocality();
                        locationText.setText(by);

                        Intent openThree = new Intent(mContext, sokSkjerm.class);
                        mContext.startActivity(openThree);

                    } else {
                        locationText.setText("Finner ikke Lokasjonen din!");
                    }
                }

            });
        }
    }
}