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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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






        //Om GPS er på
        if (SmartLocation.with(this).location().state().locationServicesEnabled()) {
            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {


                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {



                } else {


                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_FOR_LOCATION);


                }
            } else {
                findLocation();
            }
        }else {
            locationText.setText("Sett på GPS for å finne din lokasjon!");
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
                showLocation(location);
            }


        });

    }

    private void showLocation(final Location location) {
        if (location != null) {
            final String text = new String();

            // We are going to get the address for the current position
            SmartLocation.with(this).geocoding().reverse(location, new OnReverseGeocodingListener() {
                @Override
                public void onAddressResolved(Location original, List<Address> results) {
                    if (results.size() > 0) {
                        Address result = results.get(0);

                        result.getAddressLine(0);
                        //Splitter string opp på space sted[1] vil gi Postnummer
                        String[] sted = result.getAddressLine(1).split("\\s+");

                        try {
                            SokEtterSted(sted[0]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    } else {
                        locationText.setText("Finner ikke Lokasjonen din!");
                    }
                }

            });
        }
    }

    //Finner sted via og XML-link til sted via postnummer
    public void SokEtterSted(String postNr) throws IOException {
        Intent intent = new Intent(mContext, displaySok.class);
        String str;
        String postSted = "";
        StringBuffer buf = new StringBuffer();
        //Leser inn fila postnummer i res/raw mappen
        InputStream is = this.getResources().openRawResource(R.raw.postnummer);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            //Itererer over linjene for å søke etter sted
            if (is != null) {
                while ((str = reader.readLine()) != null) {
                    if (str.contains(postNr)) {
                        postSted = str;
                    }
                }
            }
            is.close();
            if (postSted.length() != 0) {
                //splitter opp String på tab
                String rader[] = postSted.split("\t");

                //rader[8] vil gi XML-link til varsel
                String url = rader[8];
                System.out.println(url);
                intent.putExtra("URL", url);
                startActivity(intent);



            } else {
                //Om et sted ikke blir funnet på postnummer
                locationText.setText("Finner ingen steder på Postnummer: "+postNr);
            }

    }


}