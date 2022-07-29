package com.example.mojred.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mojred.Entities.Brojevi;
import com.example.mojred.Entities.Gradovi;
import com.example.mojred.Entities.Korisnici;
import com.example.mojred.Entities.Objekti;
import com.example.mojred.R;
import com.example.mojred.SingleTon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.Calendar;
import java.util.List;

public class Splashscreen extends AppCompatActivity {
    int start=0;
    SingleTon singleTon = SingleTon.getInstance();
        public void attemptswitch(){
            start++;
            if(start==2) {
               String name= mPref.getString("NameFirme","");
                String name1=  mPref.getString("Adresa","");
                int name3=  mPref.getInt("Idlokfirm",-1);
               String name4= mPref.getString("NazivUsluge","");
                Gson gson = new Gson();
                String json1 = mPref.getString("Korisnik", "");
                if (json1.isEmpty()) {
                    startActivity(new Intent(Splashscreen.this, LoginOrRegister.class));
                } else {
                    Korisnici obj1 = gson.fromJson(json1, Korisnici.class);
                    singleTon.setKorisnik(obj1);
                    singleTon.setNameusluge(name4);
                    singleTon.setIdlokfirm(name3);
                    singleTon.setNamefirme(name);
                    singleTon.setNameulice(name1);
                    String json = mPref.getString("Brojevi", "");
                    singleTon.setBroj(null);
                    singleTon.setCalendar(null);
                    singleTon.setBrojuredu(0);


                    if (!json.isEmpty()) {
                        Brojevi obj = gson.fromJson(json, Brojevi.class);
                        singleTon.setBroj(obj);
                        json = mPref.getString("Vreme", "");
                        int BruR=mPref.getInt("BruR",0);
                        if (!json.isEmpty()) {
                            Calendar vreme = gson.fromJson(json, Calendar.class);
                            singleTon.setCalendar(vreme);
                            singleTon.setBrojuredu(BruR);
                            Calendar k = Calendar.getInstance();
                            if (vreme.compareTo(k) < 0) {
                                singleTon.setBroj(null);
                                singleTon.setCalendar(null);
                                singleTon.setBrojuredu(0);
                                SharedPreferences.Editor prefsEditor = mPref.edit();
                                prefsEditor.putString("Brojevi", null);
                                prefsEditor.putString("Vreme", null);
                                prefsEditor.putInt("BruR", 0);
                                prefsEditor.commit();
                                Intent i = new Intent(getBaseContext(), LoginOrRegister.class);
                                startActivity(i);
                                finish();
                            } else {
                                Intent i = new Intent(getBaseContext(), /*LoginOrRegister*/Final.class);
                                startActivity(i);
                                finish();
                            }
                        } else {
                            singleTon.setBroj(null);
                            singleTon.setCalendar(null);
                            singleTon.setBrojuredu(0);
                            SharedPreferences.Editor prefsEditor = mPref.edit();
                            prefsEditor.putString("Brojevi", null);
                            prefsEditor.putString("Vreme", null);
                            prefsEditor.putInt("BruR", 0);
                            prefsEditor.commit();
                            Intent i = new Intent(getBaseContext(), LoginOrRegister.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        singleTon.setBroj(null);
                        singleTon.setCalendar(null);
                        singleTon.setBrojuredu(0);
                        SharedPreferences.Editor prefsEditor = mPref.edit();
                        prefsEditor.putString("Brojevi", null);
                        prefsEditor.putString("Vreme", null);
                        prefsEditor.putInt("BruR", 0);
                        prefsEditor.commit();
                        Intent i = new Intent(getBaseContext(), LoginOrRegister.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        }
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences mPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uvod);
        mPref = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);


        RequestQueue queue = Volley.newRequestQueue(Splashscreen.this);
        JsonArrayRequest jar =new JsonArrayRequest(Request.Method.GET, "http://mojred.ddns.net:2555/mojred/gradovi/all", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ObjectMapper op= new ObjectMapper();
                try {
                    SingleTon.getInstance().setGradovi(
                            op.readValue(response.toString(),new TypeReference<List<Gradovi>>(){})
                    );
                    attemptswitch();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        },null);



        queue.getCache().clear();
        queue.add(jar);
        JsonArrayRequest jar1 =new JsonArrayRequest(Request.Method.GET, "http://mojred.ddns.net:2555/mojred/objekti/all", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ObjectMapper op= new ObjectMapper();
                try {
                    SingleTon.getInstance().setObjekti(
                            op.readValue(response.toString(),new TypeReference<List<Objekti>>(){})
                    );
                    attemptswitch();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        },null);
        queue.getCache().clear();
        queue.add(jar1);

    }
//}
}
