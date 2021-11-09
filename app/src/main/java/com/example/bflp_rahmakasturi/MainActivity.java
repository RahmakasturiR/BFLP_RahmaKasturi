package com.example.bflp_rahmakasturi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout btnSch = findViewById(R.id.btnsch);
        LinearLayout btnInfo = findViewById(R.id.btninfo);
        LinearLayout btnMap = findViewById(R.id.btnmap);
        LinearLayout btnTiket = findViewById(R.id.btntiket);
        LinearLayout btnArtis = findViewById(R.id.btnArtis);
        Button btnlogin = findViewById(R.id.btnlogin);
        Log.i("TAG", "onCreate: hj"+Preferences.getTiket(MainActivity.this));
        if (Preferences.getUSERNAME(MainActivity.this)==""){
            btnlogin.setText("LOGIN");
            btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this, loginActivity.class);
                    startActivity(i);
                }
            });
        } else {
            btnlogin.setText("LOGOUT");
            btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Preferences.SetUsername(MainActivity.this,"");
                    Preferences.SetNama(MainActivity.this,"");
                    Preferences.setHarga(MainActivity.this,"");
                    Preferences.setTiket(MainActivity.this,"");
                    Preferences.setTgllahir(MainActivity.this,"");
                    Preferences.setAlamat(MainActivity.this,"");
                    Preferences.setUuid(MainActivity.this,"");
                    btnlogin.setText("LOGIN");
                    finish();
                    startActivity(getIntent());
                }
            });

        }

        btnTiket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,TiketActivity.class);
                startActivity(i);
            }
        });
        btnSch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,JadwalActivity.class);
                startActivity(i);
            }
        });
        btnInfo.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,InfoActivity.class);
            startActivity(i);
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,PetaActivity.class);
                startActivity(i);
            }
        });
        btnArtis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ArtisActivity.class);
                startActivity(i);
            }
        });

    }
}