package com.example.mojred.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;
import com.example.mojred.Encrypt;
import com.example.mojred.R;


import org.json.JSONException;

import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;


public class Register extends AppCompatActivity {
    EditText etname,etlastname,etusername,etpassword;
    Button btregister;
    String user,pas;
   static Encrypt encrypt;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registracija);

        etname=(EditText) findViewById(R.id.ime);
        etlastname=(EditText) findViewById(R.id.ime2);
        etusername=(EditText) findViewById(R.id.korisnickoime);
        etpassword=(EditText) findViewById(R.id.lozinka);

        btregister=(Button) findViewById(R.id.submit1);

        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etname.getText().toString().isEmpty()) {
                    Toast.makeText(Register.this, "Niste unijeli ime", Toast.LENGTH_SHORT).show();
                } else if(etlastname.getText().toString().isEmpty()){

                    Toast.makeText(Register.this, "Niste  uneli prezime", Toast.LENGTH_SHORT).show();
                }
                else if(etusername.getText().toString().isEmpty()){

                    Toast.makeText(Register.this, "Niste  uneli korisnicko ime", Toast.LENGTH_SHORT).show();
                }
                else if(etpassword.getText().toString().isEmpty()){

                    Toast.makeText(Register.this, "Niste  uneli lozinku", Toast.LENGTH_SHORT).show();
                }
                else {
                    user = etusername.getText().toString();
                    pas = etpassword.getText().toString();
                    RequestQueue queue = Volley.newRequestQueue(Register.this);
                    JSONObject jobj = new JSONObject();
                    try {
                        jobj.put("ime", etname.getText().toString());
                        jobj.put("prezime", etlastname.getText().toString());
                        jobj.put("korisnickoime", encrypt.SHA256(user).replaceAll("[/+!@#$%^&*()_=-]", "").substring(0, 10));
                        jobj.put("lozinka", encrypt.SHA256(pas).replaceAll("[/+!@#$%^&*()_=-]", "").substring(0, 10));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest jar = new JsonObjectRequest(Request.Method.POST, "http://mojred.ddns.net:2555/mojred/korisnici/load", jobj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.getCache().clear();
                    queue.add(jar);
                    etname.setText("");
                    etlastname.setText("");
                    etusername.setText("");
                    etpassword.setText("");


                    startActivity(new Intent(Register.this, Login.class));
                }
            }
        });




//


    }}



