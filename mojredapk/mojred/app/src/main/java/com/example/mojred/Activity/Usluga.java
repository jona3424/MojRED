package com.example.mojred.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mojred.Entities.Usluge;
import com.example.mojred.Entities.UslugeFirmi;
import com.example.mojred.R;
import com.example.mojred.SingleTon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

public class Usluga extends Activity {
    TextView tvstatus5;
    Button btLogoutU;
    SingleTon singleTon=SingleTon.getInstance();
    Context c;
    LinearLayout linlayUsluga;
    Usluge u;
    Boolean postoji;
    SharedPreferences mPref;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usluga);


        mPref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);



        tvstatus5=findViewById(R.id.tvStatus5);
        btLogoutU=findViewById(R.id.btLogoutU);
        linlayUsluga=findViewById(R.id.linlayUsluga);
        tvstatus5.setText("Ime: "+ singleTon.getKorisnik().getIme()+ " Prezime: " + singleTon.getKorisnik().getPrezime());
        btLogoutU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleTon.setKorisnik(null);
                singleTon.setUsluge(null);
                singleTon.setLokacije(null);
                singleTon.setFirme(null);
                singleTon.setFkfirme(null);
                singleTon.setIdlokfirm(null);
                singleTon.setNamefirme("");

                startActivity(new Intent(Usluga.this,LoginOrRegister.class));
            }
        });

        c=this;

        for(int i=0;i<singleTon.getUsluge().size();i++) {
            u=singleTon.getUsluge().get(i);
            final Button btnTag1 = new Button(c);
            btnTag1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            btnTag1.setTextColor(Color.WHITE);
            btnTag1.setGravity(Gravity.CENTER);
            btnTag1.setPadding(5, 5, 5, 5);
            btnTag1.setBackgroundColor(Color.GRAY);
            btnTag1.setWidth(1000);
            btnTag1.setText(u.getNaziv());

            btnTag1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btLogoutU.setClickable(false);
                    for (Usluge oof : singleTon.getUsluge()) {
                        if (oof.getNaziv().equals(btnTag1.getText())) {
                    btnTag1.setBackgroundColor(Color.RED);
                    singleTon.setIdusluga(oof.getId());
                    singleTon.setNameusluge(oof.getNaziv());

                            SharedPreferences.Editor prefsEditor = mPref.edit();
                            prefsEditor.putString("NazivUsluge", singleTon.getNameusluge());
                            prefsEditor.commit();


                            RequestQueue queue = Volley.newRequestQueue(Usluga.this);
                            JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET,"http://mojred.ddns.net:2555/mojred/usluge_firmi/dajUslugeFirmi/"
                                    + singleTon.getFkfirme()+" & "+singleTon.getIdusluga()
                                    , null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    postoji = response == null;

                                    ObjectMapper op = new ObjectMapper();
                                    try {
                                        SingleTon.getInstance().setUslugeFirmi(op.readValue(response.toString(), UslugeFirmi.class));
                                        startActivity(new Intent(Usluga.this,Zakazi.class));
                                    } catch (JsonProcessingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, null
                            );
                            queue.getCache().clear();
                            queue.add(jor);






                }

                    }}
            });
            linlayUsluga.addView(btnTag1);

    }
}


    @Override
    public void onBackPressed() {
        singleTon.setUsluge(null);
        singleTon.setIdlokfirm(null);
        startActivity(new Intent(Usluga.this, Lokacija_Vreme.class));
    }



}

