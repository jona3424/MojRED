package com.example.mojred.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mojred.Entities.Brojevi;
import com.example.mojred.Entities.Korisnici;
import com.example.mojred.R;
import com.example.mojred.SingleTon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;

public class Zakazi extends Activity {

    int start=0;
    public void attemptswitch(){
        start++;
        if(start==2){
            startActivity(new Intent(Zakazi.this,Final.class));
        }
    }

    TextView tvstatus6,tvPitanje;
    Button btLogoutZ,btzakazi;
    SingleTon singleTon=SingleTon.getInstance();
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakazi);
        sharedPreferences = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);

        tvstatus6=findViewById(R.id.tvStatus6);
        btLogoutZ=findViewById(R.id.btLogoutZ);
        tvPitanje=findViewById(R.id.tvPitanje);
        btzakazi=findViewById(R.id.btzakazi);
        tvstatus6.setText("Ime: "+ singleTon.getKorisnik().getIme()+ " Prezime: " + singleTon.getKorisnik().getPrezime());

        tvPitanje.setText("Preuzmite svoj redni broj \npritiskom na dugme.");

        btLogoutZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleTon.setKorisnik(null);
                singleTon.setUsluge(null);
                singleTon.setLokacije(null);
                singleTon.setFirme(null);
                singleTon.setFkfirme(null);
                singleTon.setIdlokfirm(null);
                singleTon.setIdusluga(null);
                singleTon.setNamefirme("");
                singleTon.setUslugeFirmi(null);
                startActivity(new Intent(Zakazi.this,LoginOrRegister.class));
            }
        });

        btzakazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btLogoutZ.setClickable(false);
                RequestQueue queue = Volley.newRequestQueue(Zakazi.this);
                JSONObject jobj = new JSONObject();
                try {
                    jobj.put("fkKorisnici", singleTon.getKorisnik().getId());
                    jobj.put("fkUsluge", singleTon.getUslugeFirmi().getId());
                    jobj.put("fkLokacije", singleTon.getIdlokfirm());
                    jobj.put("uredu", true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jar = new JsonObjectRequest(Request.Method.POST,
                        "http://mojred.ddns.net:2555/mojred/brojevi/load", jobj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ObjectMapper op = new ObjectMapper();
                        try {
                            SingleTon.getInstance().setBroj
                                    (op.readValue(response.toString(), Brojevi.class));
                            startActivity(new Intent(Zakazi.this,Final.class));
                        }

                        catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        if(singleTon.getBroj()!=null){
                            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                            Gson gson = new Gson();
                            String json = gson.toJson(singleTon.getBroj());
                            prefsEditor.putString("Brojevi", json);
                            prefsEditor.commit();
                        }

                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.getCache().clear();
                queue.add(jar);


//            }
            }
        });







    }

    @Override
    public void onBackPressed() {
        singleTon.setIdusluga(null);
        startActivity(new Intent(Zakazi.this, Usluga.class));
    }
}
