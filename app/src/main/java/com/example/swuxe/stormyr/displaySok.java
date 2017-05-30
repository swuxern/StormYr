package com.example.swuxe.stormyr;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class displaySok extends AppCompatActivity implements GetData.AsyncResponse {

    LinearLayout ll;
    Forecast forecast;

    TextView imgsTv;
    TextView dagDato;
    TextView infoTvs;
    Button sokSted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sok);

        ll = (LinearLayout) findViewById(R.id.llhs);

        dagDato = (TextView) findViewById(R.id.textView1);
        imgsTv = (TextView) findViewById(R.id.imgTv);
        infoTvs = (TextView) findViewById(R.id.infoTv);

        sokSted = (Button) findViewById(R.id.sokSted);

        GetData data = new GetData();
        data.delegate = this;

        String url = getIntent().getStringExtra("URL");
        data.execute(url);



    }

    @Override
    public void processFinish(WeatherData feed) {
        forecast = feed.getForecast();

        String sted = feed.getLocation().getName();

        Time times = feed.getForecast().getTimeList().get(0);

        String symbolet = "sym"+times.getSymbol().getVar();

        int resId = getResources().getIdentifier(symbolet, "drawable", getPackageName());
        imgsTv.setBackgroundResource(resId);
        imgsTv.setText(times.getTemperature().getValue()+" C");
        imgsTv.setGravity(Gravity.CENTER);

        String datoT = times.getFrom();
        String arrd[] = datoT.split("T");
        String arrsd[] = arrd[0].split("-");
        String dato = arrsd[2] + "/" + arrsd[1];
        String tid = arrd[1];

        dagDato.setText(sted+"\n"+dato+"\nFra "+tid);


        dagDato.setText(sted+"\n"+dato+"\nFra "+tid);


        String wind = times.getWindSpeed().getName()+", "+times.getWindSpeed().getMps()+" ms/s\nfra "
                +times.getWindDirection().getName();

        String lufttrykk = times.pressure.getValue()+times.pressure.getUnit();

        infoTvs.setText("Vind: "+wind+"\n\n\n"+"Luftrykk: "+lufttrykk);




        //Kjører funksjonen for å legge til langtidsvarsel;
        makeTW(4);
        makeTW(8);
        makeTW(12);
        makeTW(16);
        makeTW(20);
        makeTW(24);
        makeTW(28);
        makeTW(32);
        makeTW(35);

        //OnClickListener, går til søkeskjerm for å søke etter nytt sted
        sokSted.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), sokSkjerm.class);
                startActivity(intent);
            }
        });

    }

        /*
        *   Funksjon for å lage TextView med data for Langtidsvarsel, legges inn i LinearView ll
        *   Henter temperatur, dato og symbolnavn for været, legger symbol som background med
        *   dato og temperatur som tekst
        */
        public void makeTW(int indx) {
            Time time = forecast.getTimeList().get(indx);

            String symbol = "sym" + time.getSymbol().getVar();
            String temps = time.getTemperature().getValue() + " C";
            String datoT = time.getFrom();
            //Splitter opp dato og formaterer slik at vi får bare dd/mm
            String arr[] = datoT.split("T");
            String arrs[] = arr[0].split("-");
            String dato = arrs[2] + "/" + arrs[1];

            TextView tv = new TextView(this);
            int resId = getResources().getIdentifier(symbol, "drawable", getPackageName());
            tv.setBackgroundResource(resId);
            tv.setText(dato + "\n" + temps);
            tv.setTextSize(14);
            tv.setGravity(Gravity.CENTER);
            DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(300, 300);
            tv.setLayoutParams(params);
            ll.addView(tv);

        }

}