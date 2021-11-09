package com.example.bflp_rahmakasturi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        LinearLayout btnScan = findViewById(R.id.btnScan);
        Button btnLogout = findViewById(R.id.btnlogin);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this,ScanQRActivity.class);
                startActivity(i);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.SetUsername(AdminActivity.this,"");
                Preferences.SetNama(AdminActivity.this,"");
                Preferences.setHarga(AdminActivity.this,"");
                Preferences.setTiket(AdminActivity.this,"");
                Preferences.setTgllahir(AdminActivity.this,"");
                Preferences.setAlamat(AdminActivity.this,"");
                Preferences.setUuid(AdminActivity.this,"");
                Intent i = new Intent(AdminActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}