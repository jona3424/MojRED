package com.example.mojredupravljac.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mojredupravljac.Activity.Objekti;
import com.example.mojredupravljac.Entities.Gradovi;
import com.example.mojredupravljac.R;
import com.example.mojredupravljac.SingleTon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.util.List;

public class Splashscreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.uvod);
        RequestQueue queue = Volley.newRequestQueue(Splashscreen.this);
        JsonArrayRequest jar =new JsonArrayRequest(Request.Method.GET, "http://mojred.ddns.net:2555/mojred/gradovi/all", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ObjectMapper op= new ObjectMapper();
                try {
                    SingleTon.getInstance().setGradovi(
                            op.readValue(response.toString(),new TypeReference<List<Gradovi>>(){})
                    );
                    startActivity(new Intent(Splashscreen.this,Naslovna.class));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        },null);



        queue.getCache().clear();
        queue.add(jar);


}
}
