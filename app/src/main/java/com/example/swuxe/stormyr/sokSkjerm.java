package com.example.swuxe.stormyr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class sokSkjerm extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sok_skjerm);


        final EditText sokFelt = (EditText) findViewById(R.id.sokFelt);
        final Button button = (Button) findViewById(R.id.sokBtn);

        final LinearLayout dynamicView = (LinearLayout)findViewById(R.id.linLay);


        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                dynamicView.removeAllViews();
                String sokPara = sokFelt.getText().toString();

                try{
                    SokEtterSted(sokPara.toLowerCase());
                }catch(IOException ex){
                    System.out.println("En Feil Oppstod");
                }


            }

        });


    }//End OnCreate


    //Søker opp sted etter hva bruker har skrevet inn
    public void SokEtterSted(String sokSted) throws IOException {
        final LinearLayout dynamicView = (LinearLayout)findViewById(R.id.linLay);
        String str;
        StringBuffer buf = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.noreg);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if(!sokSted.equals("")) {
            if (is != null) {
                while ((str = reader.readLine()) != null) {
                    if (str.toLowerCase().contains(sokSted)) {
                        buf.append(str + "\n");
                    }
                }
            }
            is.close();
            if (buf.length() != 0) {
                String texten = new String(buf);
                //Splitter opp Stringen på newlines slik
                String linjer[] = texten.split("\n");
                //Itererer over linjer[]
                for (int i = 0; i < linjer.length; i++) {
                    //splitter opp på tab
                    String rader[] = linjer[i].split("\t");
                    //Lager nye textviews med info om sted som er funnet
                    TextView tv = new TextView(this);
                    tv.setText(rader[1] + " " + rader[6] + " kommune");
                    tv.setTag(rader[12]);
                    tv.setPadding(0,8,0,8);
                    //OnClick Listener, om bruker trykker på TextView blir XML-URL til stedet som er trykt på sendt til neste intent
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(v.getContext(), displaySok.class);

                            String url = v.getTag().toString();
                            System.out.println(url);
                            intent.putExtra("URL", url);
                            startActivity(intent);
                        }
                    });
                    dynamicView.addView(tv);

                }
            } else {//Om Ingen steder blir funnet
                TextView tv = new TextView(this);
                tv.setText("Finner ingen steder med det navnet!");
                dynamicView.addView(tv);
            }

        }
        else{//Om tekstfeltet er tomt når bruker søker
            TextView tv = new TextView(this);
            tv.setText("Du må skrive noe i søkefeltet!");
            dynamicView.addView(tv);
        }
    }//End SokEtterSted
}//End sokSkjerm Klasse
