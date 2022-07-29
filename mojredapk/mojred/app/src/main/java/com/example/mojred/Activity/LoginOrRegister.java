package com.example.mojred.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mojred.R;

public class LoginOrRegister extends AppCompatActivity {
    Button logdugme,regdugme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginregister);
        logdugme=findViewById(R.id.prijava4);
        regdugme=findViewById(R.id.registracija);

        logdugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginOrRegister.this, Login.class));
            }
        });

        regdugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginOrRegister.this, Register.class));

            }
        });
    }



    @Override
    public void onBackPressed(){
        final AlertDialog.Builder b= new AlertDialog.Builder(LoginOrRegister.this);
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
