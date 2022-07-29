package com.example.mojredupravljac.Activity;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mojredupravljac.Entities.Firme;
import com.example.mojredupravljac.Entities.Lokacije;
import com.example.mojredupravljac.R;
import com.example.mojredupravljac.SingleTon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.List;

public class OdaberiFirmu extends Activity {
Context c;
SingleTon singleTon=SingleTon.getInstance();
LinearLayout linlayfirmi;
    TextView textdrugogact;
    Firme of;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odaberi_firmu);
        linlayfirmi=findViewById(R.id.linleyfirmi);
        textdrugogact=findViewById(R.id.tvinstrukcija2);

        textdrugogact.setText("Odaberite neku od ponudjenih firmi:");


    c=this;
        for(int i=0;i<singleTon.getFirme().size();i++){
            of=singleTon.getFirme().get(i);

            final Button btnTag1 = new Button(c);
            btnTag1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            btnTag1.setTextColor(Color.WHITE);
            btnTag1.setGravity(Gravity.CENTER);
            btnTag1.setPadding(5,5,5,5);
            btnTag1.setBackgroundColor(Color.GRAY);

            btnTag1.setWidth(1000);
            btnTag1.setText(of.getNaziv());
            if(of.getNaziv().equals("Telekom") || of.getNaziv().equals("Telenor")){
                btnTag1.setClickable(false);
                btnTag1.setBackgroundColor(Color.parseColor("#c9c9c9"));
            }else{
            btnTag1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Firme oof : singleTon.getFirme()) {
                        if (oof.getNaziv().equals(btnTag1.getText())) {
                            btnTag1.setBackgroundColor(Color.RED);
                            RequestQueue queue = Volley.newRequestQueue(OdaberiFirmu.this);
                            JsonArrayRequest jar = new JsonArrayRequest(Request.Method.GET,
                                    "http://mojred.ddns.net:2555/mojred/lokacije_firmi/dajLokacije/" + oof.getId()
                                    , null, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    ObjectMapper op = new ObjectMapper();
                                    try {
                                        SingleTon.getInstance().setLokacije(
                                                op.readValue(response.toString(), new TypeReference<List<Lokacije>>() {
                                                })

                                        );
                                        startActivity(new Intent(OdaberiFirmu.this, Lokacija_Vreme.class));
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

            });}
            linlayfirmi.addView(btnTag1);
        }

    }



    @Override
    public void onBackPressed(){
        singleTon.setFirme(null);
         startActivity(new Intent(OdaberiFirmu.this,Objekti.class));


    }
}
