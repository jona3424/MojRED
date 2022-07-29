package com.example.mojred.Activity;

import androidx.annotation.RequiresApi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.mojred.Entities.Gradovi;
import com.example.mojred.R;
import com.example.mojred.SingleTon;




public class Naslovna extends Activity {
    private SingleTon singleTon = SingleTon.getInstance();
    private Context c;
    LinearLayout linlaygradovi;
    TextView tvnaslovne,tvstatus;
    Button lgout;
    Gradovi grad=new Gradovi();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naslovna);
    tvnaslovne=findViewById(R.id.tvnaslovne);
    tvstatus=findViewById(R.id.tvStatus);
    lgout=findViewById(R.id.btLogout);
tvnaslovne.setText("Dobrodošli u MojRed!Molimo vas odaberite grad:");
        tvstatus.setText("Ime: "+ singleTon.getKorisnik().getIme()+ " Prezime: " + singleTon.getKorisnik().getPrezime());


        c=this;
         linlaygradovi=findViewById(R.id.linlaygradovi);

                for(Gradovi g: singleTon.getGradovi()){
                    final Button btnTag = new Button(c);
                    btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                   btnTag.setTextColor(Color.WHITE);
                   btnTag.setGravity(Gravity.CENTER);
                    btnTag.setPadding(5,5,5,5);

                    btnTag.setWidth(1000);
                    btnTag.setText(g.getNaziv());
                    if(g.getNaziv().equals("Podgorica")){
                        btnTag.setBackgroundColor(Color.GRAY);
                   btnTag.setClickable(true);
                        btnTag.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                lgout.setClickable(false);
                                btnTag.setBackgroundColor(Color.RED);
                                Thread background = new Thread() {
                                    public void run() {
                                        try {
                                            sleep(500);

                                            Intent i=new Intent(getBaseContext(), Objekti.class);
                                            startActivity(i);

                                            finish();
                                        } catch (Exception e) {
                                        }
                                    }
                                };
                                background.start();
                            }
                        });
                   }
                   else{
                       btnTag.setClickable(false);
                        btnTag.setBackgroundColor(Color.parseColor("#c9c9c9"));

                    }

                    linlaygradovi.addView(btnTag);
            }


                lgout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        singleTon.setKorisnik(null);
                        startActivity(new Intent(Naslovna.this,LoginOrRegister.class));
                    }
                });


    }
    @Override
    public void onBackPressed(){
        final AlertDialog.Builder b= new AlertDialog.Builder(Naslovna.this);
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
