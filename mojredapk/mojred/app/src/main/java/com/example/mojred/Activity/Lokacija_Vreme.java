package com.example.mojred.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mojred.Entities.Brojevi;
import com.example.mojred.Entities.Lokacije;
import com.example.mojred.Entities.Usluge;
import com.example.mojred.R;
import com.example.mojred.SingleTon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.util.List;

public class Lokacija_Vreme extends Activity {



    int a = 0;
    TextView tvstatus2;
    Button btLogoutLV;
    SingleTon singleTon=SingleTon.getInstance();
    Context c;
    LinearLayout linlayLokacija,linlayvreme;
    Lokacije lok;
    SharedPreferences mPref;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokacija__vreme);
        tvstatus2=findViewById(R.id.tvStatus2);
        btLogoutLV=findViewById(R.id.btLogoutLV);
        linlayLokacija=findViewById(R.id.linlayLokacija);
        mPref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        tvstatus2.setText("Ime: "+ singleTon.getKorisnik().getIme()+ " Prezime: " + singleTon.getKorisnik().getPrezime());
        btLogoutLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleTon.setKorisnik(null);
                singleTon.setLokacije(null);
                singleTon.setFirme(null);
                singleTon.setFkfirme(null);
                singleTon.setNamefirme("");

                startActivity(new Intent(Lokacija_Vreme.this,LoginOrRegister.class));
            }
        });
        c=this;


        for(int i=0;i<singleTon.getLokacije().size();i++) {
            lok=singleTon.getLokacije().get(i);

            final Button btnTag1 = new Button(c);
            btnTag1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            btnTag1.setTextColor(Color.WHITE);
            btnTag1.setGravity(Gravity.CENTER);
            btnTag1.setPadding(5, 5, 5, 5);
            btnTag1.setBackgroundColor(Color.GRAY);

            btnTag1.setWidth(1000);
            btnTag1.setText(lok.getAdresa());
            linlayLokacija.addView(btnTag1);


            btnTag1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (Lokacije oof : singleTon.getLokacije()) {
                        if (oof.getAdresa().equals(btnTag1.getText())) {
                            btnTag1.setBackgroundColor(Color.RED);
                            singleTon.setIdlokfirm(oof.getId());
                            singleTon.setNameulice(oof.getAdresa());

                            SharedPreferences.Editor prefsEditor = mPref.edit();
                            prefsEditor.putString("Adresa", singleTon.getNameulice());
                            prefsEditor.putInt("Idlokfirm", singleTon.getIdlokfirm());
                            prefsEditor.commit();



                            RequestQueue queue = Volley.newRequestQueue(Lokacija_Vreme.this);
                            JsonArrayRequest jar = new JsonArrayRequest
                                    (Request.Method.GET, "http://mojred.ddns.net:2555/mojred/brojevi/dalokacije/"
                                            + singleTon.getIdlokfirm(),
                                            null, new Response.Listener<JSONArray>() {
                                        @Override
                                        public void onResponse(JSONArray response) {
                                            ObjectMapper op1 = new ObjectMapper();
                                            try {
                                                SingleTon.getInstance().setBrojeviList(
                                                        op1.readValue(response.toString(), new TypeReference<List<Brojevi>>() {
                                                        })
                                                );

                                                for (int i = 0; i < singleTon.getBrojeviList().size(); i++) {

                                                    a = a + singleTon.getBrojeviList().get(i).getFkUsluge().getFkUsluge().getVremeUsluge();

                                                }
                                                final AlertDialog.Builder b = new AlertDialog.Builder(Lokacija_Vreme.this);
                                                b.setMessage("Moguce vreme cekanja u redu iznosi " + a + " minuta.Da li ste sigurni da zelite stati u red?");
                                                b.setCancelable(true);
                                                b.setNegativeButton("DA", new DialogInterface.OnClickListener() {
                                                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
//old
                                                        RequestQueue queue1 = Volley.newRequestQueue(Lokacija_Vreme.this);
                                                        btLogoutLV.setClickable(false);
                                                        JsonArrayRequest jar2 = new JsonArrayRequest(Request.Method.GET,
                                                                "http://mojred.ddns.net:2555/mojred/usluge_firmi/dajUsluge/" + singleTon.getFkfirme()
                                                                , null, new Response.Listener<JSONArray>() {
                                                            @Override
                                                            public void onResponse(JSONArray response) {
                                                                ObjectMapper op1 = new ObjectMapper();
                                                                try {
                                                                    SingleTon.getInstance().setUsluge(
                                                                            op1.readValue(response.toString(), new TypeReference<List<Usluge>>() {
                                                                            })
                                                                    );            startActivity(new Intent(Lokacija_Vreme.this, Usluga.class));


                                                                } catch (JsonProcessingException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }
                                                        }, null);
                                                        queue1.getCache().clear();
                                                        queue1.add(jar2);

//old
                                                    }
                                                });
                                                b.setPositiveButton("NE", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        a=0;
                                                        singleTon.setUsluge(null);
                                                        btnTag1.setBackgroundColor(Color.GRAY);
                                                        dialog.cancel();

                                                    }
                                                });
                                                AlertDialog alertDialog = b.create();
                                                alertDialog.show();

                                            } catch (JsonProcessingException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }, null);
                            queue.getCache().clear();
                            queue.add(jar);

                        }
                    }
                        }
            });

        }


//        odje ide tvoja zamisao
    }






    @Override
    public void onBackPressed() {
        singleTon.setLokacije(null);
        singleTon.setFkfirme(null);
        singleTon.setNamefirme("");
        startActivity(new Intent(Lokacija_Vreme.this, OdaberiFirmu.class));
    }
    }
