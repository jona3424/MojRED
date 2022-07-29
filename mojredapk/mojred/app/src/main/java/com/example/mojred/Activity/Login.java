package com.example.mojred.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mojred.Encrypt;
import com.example.mojred.Entities.Korisnici;
import com.example.mojred.R;
import com.example.mojred.SingleTon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;


public class Login extends AppCompatActivity {

    EditText etlogusername,etlogpassword;
    TextView tvreg;
    Button btlogin;
    Boolean postoji;
    SharedPreferences mPref;
    public String userprovera,passprovera,cuvajuser,cuvajpass;
    static Encrypt encrypt;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SingleTon singleTon=SingleTon.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prijava);
        mPref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        etlogusername=(EditText) findViewById(R.id.korisnicko_ime);
        etlogpassword=(EditText) findViewById(R.id.lozinka);
        btlogin=(Button) findViewById(R.id.submit);

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etlogusername.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Niste unijeli username", Toast.LENGTH_SHORT).show();
                } else if(etlogpassword.getText().toString().isEmpty()){

                    Toast.makeText(Login.this, "Niste  uneli password", Toast.LENGTH_SHORT).show();
                }
                else {
                    RequestQueue queue = Volley.newRequestQueue(Login.this);
                    userprovera = etlogusername.getText().toString();
                    passprovera = etlogpassword.getText().toString();
                    try {
                        cuvajuser = encrypt.SHA256(userprovera).replaceAll("[/+!@#$%^&*()_=-]", "").substring(0, 10);
                        cuvajpass = encrypt.SHA256(passprovera).replaceAll("[/+!@#$%^&*()_=-]", "").substring(0, 10);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }

                    String link = "http://mojred.ddns.net:2555/mojred/korisnici/dajUser/" + cuvajuser + " & " + cuvajpass;
                    JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            postoji = response == null;

                                ObjectMapper op = new ObjectMapper();
                                try {
                                    SingleTon.getInstance().setKorisnik(op.readValue(response.toString(), Korisnici.class));
                                } catch (JsonProcessingException e) {
                                    e.printStackTrace();
                                }
                            SharedPreferences.Editor prefsEditor = mPref.edit();
                            Gson gson = new Gson();
                            String json = gson.toJson(singleTon.getKorisnik());
                            prefsEditor.putString("Korisnik", json);
                            prefsEditor.commit();

                            startActivity(new Intent(Login.this, Naslovna.class));
                                finish();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login.this, "Nesto je poslo naopako...molimo vas pokusajte ponovo", Toast.LENGTH_SHORT).show();
                        }
                    });



                    queue.add(jor);


                }

            }});








    }


}
