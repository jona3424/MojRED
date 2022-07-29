package com.example.mojredupravljac.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mojredupravljac.Entities.Brojevi;
import com.example.mojredupravljac.Entities.Lokacije;
import com.example.mojredupravljac.R;
import com.example.mojredupravljac.SingleTon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.util.List;


public class Lokacija_Vreme extends Activity {
    SingleTon singleTon=SingleTon.getInstance();
    Context c;
    LinearLayout linlayLokacija;
    Lokacije lok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokacija__vreme);

        linlayLokacija=findViewById(R.id.linlayLokacija);

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
                            System.out.println(singleTon.getIdlokfirm());


                            final AlertDialog dialogBuilder = new AlertDialog.Builder(Lokacija_Vreme.this).create();
                            LayoutInflater inflater =LayoutInflater.from(Lokacija_Vreme.this);
                            View dialogView = inflater.inflate(R.layout.dialog_layout, null);
                            final EditText etpas =  dialogView.findViewById(R.id.etpas);
                            Button btdalje = (Button) dialogView.findViewById(R.id.btdalje);
                            Button btnazad = (Button) dialogView.findViewById(R.id.btnazad);

                            btdalje.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(etpas.getText().toString().isEmpty()){
                                            Toast.makeText(Lokacija_Vreme.this, "Niste  uneli lozinku", Toast.LENGTH_SHORT).show();
                                        }
                                         else if(etpas.getText().toString().equals("Admin")){
                                        RequestQueue queue = Volley.newRequestQueue(Lokacija_Vreme.this);
                                        JsonArrayRequest jar=new JsonArrayRequest(Request.Method.GET, "http://mojred.ddns.net:2555/mojred/brojevi/dalokacije/" + singleTon.getIdlokfirm(), null, new Response.Listener<JSONArray>() {
                                            @Override
                                            public void onResponse(JSONArray response) {
                                                ObjectMapper op1 = new ObjectMapper();
                                                try {
                                                    SingleTon.getInstance().setBrojeviList(
                                                            op1.readValue(response.toString(), new TypeReference<List<Brojevi>>() {
                                                            })
                                                    );
                                                    startActivity(new Intent(Lokacija_Vreme.this, Odabir.class));
                                                } catch (JsonProcessingException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                            }
                                        });
                                        queue.getCache().clear();
                                        queue.add(jar);


                                         }
                                         else{
                                             Toast.makeText(Lokacija_Vreme.this, "Pogresna lozinka", Toast.LENGTH_SHORT).show();
                                         }
                                }
                            });
                            btnazad.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    btnTag1.setBackgroundColor(Color.GRAY);
                                    dialogBuilder.dismiss();
                                }
                            });

                            dialogBuilder.setView(dialogView);
                            dialogBuilder.show();
                            dialogBuilder.setCanceledOnTouchOutside(false);
                        }
                    }
                        }
            });

        }


    }






    @Override
    public void onBackPressed() {
        singleTon.setLokacije(null);
        startActivity(new Intent(Lokacija_Vreme.this, OdaberiFirmu.class));
    }
    }
