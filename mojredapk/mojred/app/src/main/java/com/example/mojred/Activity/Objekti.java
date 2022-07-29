package com.example.mojred.Activity;

import androidx.annotation.RequiresApi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mojred.Entities.Firme;
import com.example.mojred.R;
import com.example.mojred.SingleTon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.util.List;

public class Objekti extends Activity {
    Button mtel,banka,mup,posta;

    TextView tvstatus4;
    Button btLogoutZR;
    SingleTon singleTon = SingleTon.getInstance();
    String link ="http://mojred.ddns.net:2555/mojred/firme_objekata/dajFirme/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_zakazired);
        mtel=findViewById(R.id.poslovnice);
        mup=findViewById(R.id.mup);
        banka=findViewById(R.id.bank);
        posta=findViewById(R.id.posta);
        tvstatus4=findViewById(R.id.tvStatus4);
        btLogoutZR=findViewById(R.id.btLogoutZR);
        tvstatus4.setText("Ime: "+ singleTon.getKorisnik().getIme()+ " Prezime: " + singleTon.getKorisnik().getPrezime());
        btLogoutZR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleTon.setKorisnik(null);
                startActivity(new Intent(Objekti.this,LoginOrRegister.class));
            }
        });



        mtel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btLogoutZR.setClickable(false);
                link=link+"3";
                RequestQueue queue = Volley.newRequestQueue(Objekti.this);
                JsonArrayRequest jar =new JsonArrayRequest(Request.Method.GET, link, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ObjectMapper op= new ObjectMapper();
                        try {
                            SingleTon.getInstance().setFirme(
                                    op.readValue(response.toString(),new TypeReference<List<Firme>>(){})
                            );
                            startActivity(new Intent(Objekti.this,OdaberiFirmu.class));

                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }
                },null);



                queue.getCache().clear();
                queue.add(jar);


            }
        });

        banka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btLogoutZR.setClickable(false);
                link=link+"2";
                RequestQueue queue = Volley.newRequestQueue(Objekti.this);
                JsonArrayRequest jar =new JsonArrayRequest(Request.Method.GET, link, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ObjectMapper op= new ObjectMapper();
                        try {
                            SingleTon.getInstance().setFirme(
                                    op.readValue(response.toString(),new TypeReference<List<Firme>>(){})
                            );
                            startActivity(new Intent(Objekti.this,OdaberiFirmu.class));

                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }
                },null);



                queue.getCache().clear();
                queue.add(jar);



 

            }
        });

        posta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder b= new AlertDialog.Builder(Objekti.this);
                b.setMessage("Ova opcija ce biti dostupna u sledecoj verziji aplikacije");
                b.setCancelable(true);
                b.setNegativeButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog =b.create();
                alertDialog.show();
            }
        });
        mup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder b= new AlertDialog.Builder(Objekti.this);
                b.setMessage("Ova opcija ce biti dostupna u sledecoj verziji aplikacije");
                b.setCancelable(true);
                b.setNegativeButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog =b.create();
                alertDialog.show();
            }
        });
    }
    @Override
    public void onBackPressed(){
            final AlertDialog.Builder b= new AlertDialog.Builder(Objekti.this);
            b.setMessage("Da li ste sigurni da želite da izađete iz aplikacije?");
            b.setCancelable(true);
            b.setNegativeButton("DA", new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();
                }
            });
            b.setPositiveButton("NE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            AlertDialog alertDialog =b.create();
            alertDialog.show();



    }

}
