package com.example.mojred.Activity;

import androidx.annotation.RequiresApi;


import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mojred.R;
import com.example.mojred.SingleTon;
import com.google.gson.Gson;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class Final extends Activity {
    boolean temp = false;
    TextView poslovnica, ulica, usluga, brooj, brooj2, labelavreme;
    Button odustani;
    SingleTon singleTon = SingleTon.getInstance();
    public static final String MyPREFERENCES = "MyPrefs";

    Timer timer = new Timer();
    Timer timer1 = new Timer();
    TimerTask tt1, tt, ttas;
    SharedPreferences mPref;
    int counter = -1;
    Calendar lendar, jaguar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        mPref = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        poslovnica = findViewById(R.id.poslovnica);
        poslovnica.setText(singleTon.getNamefirme());
        ulica = findViewById(R.id.ulica);
        ulica.setText(singleTon.getNameulice());
        usluga = findViewById(R.id.usluga);
        usluga.setText(singleTon.getNameusluge());
        brooj = findViewById(R.id.brooj);
        brooj2 = findViewById(R.id.brooj2);
        odustani = findViewById(R.id.odustani);
        labelavreme = findViewById(R.id.labelavreme);
        brooj2.setText(singleTon.getBroj().getId() + "");

        final RequestQueue queue = Volley.newRequestQueue(Final.this);
        System.out.println(singleTon.getBroj().getFkLokacije().getId() + "ezsrdtfcgvhbjnkml;o.o,imunytbreazsfdgxfhcvlb;l");
        ttas = new TimerTask() {
            @Override
            public void run() {
                if (singleTon.getBroj() != null) {
                    StringRequest prvoGledac = new StringRequest(Request.Method.GET, "http://mojred.ddns.net:2555/mojred/brojevi/syncAttempt/" + singleTon.getBroj().getFkLokacije().getId(), new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Calendar l = Calendar.getInstance();
                            singleTon.setKadCePrvi(Long.parseLong(response));
                            if (!temp) {
                                if (l.getTimeInMillis() > Long.parseLong(response)) {
                                    StringRequest r = new StringRequest(Request.Method.GET, "http://mojred.ddns.net:2555/mojred/brojevi/reset/" + singleTon.getBroj().getFkLokacije().getId(),
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {

                                                    singleTon.setKadCePrvi(Long.parseLong(response));

                                                    timer.scheduleAtFixedRate(tt,
                                                            0, 5000);
                                                    temp = true;
                                                }
                                            }, null);
                                    queue.getCache().clear();
                                    queue.add(r);
                                } else {
                                    temp = true;
                                    timer.scheduleAtFixedRate(tt,
                                            0, 5000);
                                }
                            }
                        }
                    }, null);
                    queue.getCache().clear();
                    queue.add(prvoGledac);
                }
            }
        };
        timer.scheduleAtFixedRate(ttas, 0, 5000);

        tt = new TimerTask() {
            @Override
            public void run() {
                StringRequest sr = new StringRequest(Request.Method.GET, "http://mojred.ddns.net:2555/mojred/brojevi/dajfkLok/" + singleTon.getBroj().getFkLokacije().getId() + "&" + singleTon.getBroj().getId(),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(final String response) {
                                int tren = Integer.parseInt(response);
                                if (response.equals("0")) {
                                    SharedPreferences.Editor prefsEditor = mPref.edit();
                                    prefsEditor.clear().commit();
                                    singleTon.setBroj(null);
                                    singleTon.setCalendar(null);
                                    singleTon.setBrojuredu(0);
                                    singleTon.setKorisnik(null);
                                    singleTon.setUsluge(null);
                                    singleTon.setLokacije(null);
                                    singleTon.setFirme(null);
                                    singleTon.setFkfirme(null);
                                    singleTon.setIdlokfirm(null);
                                    singleTon.setIdusluga(null);
                                    singleTon.setNamefirme("");
                                    singleTon.setUslugeFirmi(null);


                                    tt1.cancel();
                                    tt.cancel();

                                    ttas.cancel();
                                    startActivity(new Intent(Final.this, LoginOrRegister.class));
                                } else {///


                                    brooj.setText(response);
                                    Calendar cal = Calendar.getInstance();
                                    System.out.println("KACEPRVI-----------------" + singleTon.getKadCePrvi());
                                    if (singleTon.getKadCePrvi() != null) {
                                        cal.setTimeInMillis(singleTon.getKadCePrvi());

                                    }
                                    if (counter == -1) {
                                        int a = (int) (singleTon.getKadCePrvi() - Calendar.getInstance().getTimeInMillis());
                                        counter = (int) (60 - a / 5000);

                                        if (response.equals("1")) {
                                            StringRequest r = new StringRequest(Request.Method.GET, "http://mojred.ddns.net:2555/mojred/brojevi/reset/" + singleTon.getBroj().getFkLokacije().getId(),
                                                    new Response.Listener<String>() {
                                                        @Override
                                                        public void onResponse(String response) {
                                                            singleTon.setKadCePrvi(Long.parseLong(response));
                                                            int a = (int) (singleTon.getKadCePrvi() - Calendar.getInstance().getTimeInMillis());
                                                            counter = (int) (60 - a / 5000);
                                                        }
                                                    }, null);
                                            queue.getCache().clear();
                                            queue.add(r);
                                        }
                                    }

                                    if (!singleTon.getBrojuredu().equals(tren)) {
                                        int minuti = Integer.parseInt(response) * 5 - 5;
                                        if (singleTon.getBrojuredu() != 0) {

                                            counter = 0;
                                            minuti += 5;
                                            cal = Calendar.getInstance();
                                            System.out.println("EtoI OVOSM");
                                            StringRequest r = new StringRequest(Request.Method.GET, "http://mojred.ddns.net:2555/mojred/brojevi/reset/" + singleTon.getBroj().getFkLokacije().getId(),
                                                    new Response.Listener<String>() {
                                                        @Override
                                                        public void onResponse(String response) {
                                                            singleTon.setKadCePrvi(Long.parseLong(response));
                                                            int a = (int) (singleTon.getKadCePrvi() - Calendar.getInstance().getTimeInMillis());
                                                            counter = (int) (60 - a / 5000);
                                                        }
                                                    }, null);
                                            queue.getCache().clear();
                                            queue.add(r);
                                        }

                                        singleTon.setBrojuredu(tren);
                                        System.out.println(cal.getTime() + " ZUERST BIN ICH HIER ----------------------------");
                                        cal.add(Calendar.MINUTE, minuti);
                                        singleTon.setCalendar(cal);
                                        System.out.println(cal.getTime() + " ZUERST DADADADADADAdwa ICH HIER ----------------------------");
                                        SharedPreferences.Editor prefsEditor = mPref.edit();
                                        Gson gson = new Gson();
                                        String json = gson.toJson(singleTon.getCalendar());
                                        prefsEditor.putString("Vreme", json);
                                        prefsEditor.putInt("BruR", singleTon.getBrojuredu());
                                        prefsEditor.commit();
                                    } else {
                                        counter++;

                                        if (counter == 60) {
                                            counter = 0;
                                            singleTon.setKadCePrvi(singleTon.getKadCePrvi() + (5 * 60 * 1000));
                                            if (singleTon.getBrojuredu() == 1) {

                                                StringRequest r = new StringRequest(Request.Method.GET, "http://mojred.ddns.net:2555/mojred/brojevi/reset/" + singleTon.getBroj().getFkLokacije().getId(),
                                                        new Response.Listener<String>() {
                                                            @Override
                                                            public void onResponse(String response) {
                                                                singleTon.setKadCePrvi(Long.parseLong(response));
                                                            }
                                                        }, null);
                                                queue.getCache().clear();
                                                queue.add(r);
                                            }
                                            int minuti = Integer.parseInt(response) * 5;
                                            Calendar cale = Calendar.getInstance();
                                            cale.add(Calendar.MINUTE, minuti);
                                            singleTon.setCalendar(cale);
                                            SharedPreferences.Editor prefsEditor = mPref.edit();
                                            Gson gson = new Gson();
                                            String json = gson.toJson(singleTon.getCalendar());
                                            prefsEditor.putString("Vreme", json);
                                            prefsEditor.putInt("BruR", singleTon.getBrojuredu());
                                            prefsEditor.commit();
                                            Toast.makeText(Final.this, "" +
                                                    "Jedan od korisnika se na salteru zadrzao duze od ocekivanog.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }, null);
                queue.getCache().clear();
                queue.add(sr);
            }
        };


        tt1 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("radi li ovo");
                if (singleTon.getCalendar() != null) {
                    lendar = Calendar.getInstance();
                    jaguar = singleTon.getCalendar();
                    if (lendar.compareTo(jaguar) < 0) {
                        long millis = jaguar.getTimeInMillis() - lendar.getTimeInMillis();
                        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                        Date date = new Date(millis);
                        formatter.setTimeZone(TimeZone.getTimeZone("UTC+1"));
                        final String result = formatter.format(date);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                labelavreme.setText(result);
                            }
                        });
                    } else {
                        RequestQueue queue = Volley.newRequestQueue(Final.this);


                    }
                }

            }


        };
        timer1.scheduleAtFixedRate(tt1,
                0, 1000);


        odustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue queue1 = Volley.newRequestQueue(Final.this);
                StringRequest sr = new StringRequest(Request.Method.PUT, "http://mojred.ddns.net:2555/mojred/brojevi/update/" + singleTon.getBroj().getId(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("ok")) {
                            SharedPreferences.Editor prefsEditor = mPref.edit();
                            prefsEditor.clear().commit();
                            singleTon.setBroj(null);
                            System.out.println("--------------------------------------------------------------------------Lol");
                            singleTon.setCalendar(null);
                            singleTon.setBrojuredu(0);
                            singleTon.setKorisnik(null);
                            singleTon.setUsluge(null);
                            singleTon.setLokacije(null);
                            singleTon.setFirme(null);
                            singleTon.setFkfirme(null);
                            singleTon.setIdlokfirm(null);
                            singleTon.setIdusluga(null);
                            singleTon.setNamefirme("");
                            singleTon.setUslugeFirmi(null);


                            tt1.cancel();
                            tt.cancel();

                            ttas.cancel();
                            startActivity(new Intent(Final.this, LoginOrRegister.class));
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

    public void onBackPressed() {
        final AlertDialog.Builder b = new AlertDialog.Builder(Final.this);
        b.setMessage("Vase mjesto u redu ce ostati rezervisano sve dok ne pritisnete dugme odustani.");
        b.setCancelable(true);
        b.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        AlertDialog alertDialog = b.create();
        alertDialog.show();


    }
}
