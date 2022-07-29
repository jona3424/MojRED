package com.example.mojredupravljac.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mojredupravljac.Entities.Brojevi;
import com.example.mojredupravljac.R;
import com.example.mojredupravljac.SingleTon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Odabir extends AppCompatActivity {
    SingleTon singleTon=SingleTon.getInstance();
    Button btIzbaci;
    TextView tvTrenutni,tvBrojljudi;
    LinearLayout linlaymain;
    Context c;

    Timer timer = new Timer();
    Timer timer1 = new Timer();

    TimerTask tt,tt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btIzbaci=findViewById(R.id.btIzbaci);
        tvTrenutni=findViewById(R.id.tvTrenutni);
        tvBrojljudi=findViewById(R.id.tvBrojljudi);
        linlaymain=findViewById(R.id.linlaymain);
 c=this;

        tt=new TimerTask() {
            @Override
            public void run() {
                RequestQueue queue = Volley.newRequestQueue(Odabir.this);
                JsonArrayRequest jar=new JsonArrayRequest(Request.Method.GET, "http://mojred.ddns.net:2555/mojred/brojevi/dalokacije/" + singleTon.getIdlokfirm(), null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ObjectMapper op1 = new ObjectMapper();
                        if(singleTon.getBrojeviList().toString().equals(response.toString())){
                        }
                        else{
                            linlaymain.removeAllViews();
                        try {


                            singleTon.setBrojeviList(null);
                            SingleTon.getInstance().setBrojeviList(
                                    op1.readValue(response.toString(), new TypeReference<List<Brojevi>>() {
                                    })
                            );

                            for(Brojevi b: singleTon.getBrojeviList() ){

                                final Button btnTag = new Button(c);
                                btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                                btnTag.setTextColor(Color.parseColor("#ED1B24"));
                                btnTag.setGravity(Gravity.CENTER);
                                btnTag.setPadding(5,5,5,5);
                                btnTag.setClickable(false);
                                btnTag.setBackgroundColor(Color.parseColor("#FEFEFE"));
                                btnTag.setWidth(1000);
                                btnTag.setText(b.getId().toString());
                                linlaymain.addView(btnTag);

                            }

                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }}
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.getCache().clear();
                queue.add(jar);

            }};timer.scheduleAtFixedRate(tt,
                0, 1000);

            tt1=new TimerTask() {
                @Override
                public void run() {
                    if(singleTon.getBrojeviList().isEmpty()){
                        tvTrenutni.setText("nema ljudi u redu");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvBrojljudi.setText("0");
                            }
                        });
                    }
                    else {
                        tvTrenutni.setText(singleTon.getBrojeviList().get(0).getId().toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvBrojljudi.setText(singleTon.getBrojeviList().size()+"");
                            }
                        });
                    }
                }
            };
            timer1.scheduleAtFixedRate(tt1,0,1000);


        btIzbaci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue1 = Volley.newRequestQueue(Odabir.this);
                StringRequest sr=new StringRequest(Request.Method.PUT, "http://mojred.ddns.net:2555/mojred/brojevi/update/" + singleTon.getBrojeviList().get(0).getId(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("ok")){
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                queue1.getCache().clear();
                queue1.add(sr);
            }
        });
    }


    public void onBackPressed(){
        final AlertDialog.Builder b= new AlertDialog.Builder(Odabir.this);
        b.setMessage("Da li ste sigurni da želite da izađete iz aplikacije?");
        b.setCancelable(true);
        b.setNegativeButton("DA", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int i) {
                finishAffinity();

            }
        });
        b.setPositiveButton("NE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();


            }
        });
        AlertDialog alertDialog =b.create();
        alertDialog.show();



    }

}
