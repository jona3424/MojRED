package com.example.mojredupravljac.Activity;

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


import com.example.mojredupravljac.Entities.Gradovi;
import com.example.mojredupravljac.R;
import com.example.mojredupravljac.SingleTon;


public class Naslovna extends Activity {
    private SingleTon singleTon = SingleTon.getInstance();
    private Context c;
    LinearLayout linlaygradovi;
    TextView tvnaslovne;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naslovna);
    tvnaslovne=findViewById(R.id.tvnaslovne);
    tvnaslovne.setText("Dobrodošli u MojRed!Molimo vas odaberite grad:");


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
