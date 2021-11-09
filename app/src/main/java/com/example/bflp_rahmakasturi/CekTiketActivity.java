package com.example.bflp_rahmakasturi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CekTiketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_tiket);
        ImageView imgQR = findViewById(R.id.imgQR);
        TextView tvKonfir = findViewById(R.id.tvKonfir);
        TextView tvNama = findViewById(R.id.tvNama);
        TextView tvAlamat = findViewById(R.id.tvAlamat);
        TextView tvTglLahir = findViewById(R.id.tvTglLahir);
        TextView tvTiket = findViewById(R.id.tvTiket);
        TextView tvHarga = findViewById(R.id.tvHarga);
        LinearLayout llTiket = findViewById(R.id.llTiket);
        String idUser = getIntent().getStringExtra("ID USER");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(idUser);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nama = snapshot.child("nama").getValue().toString();
                String username = snapshot.child("username").getValue().toString();
                String alamat = snapshot.child("alamat").getValue().toString();
                String tglLahir = snapshot.child("tglLahir").getValue().toString();

                tvNama.setText(nama);
                tvAlamat.setText(alamat);
                tvTglLahir.setText(tglLahir);
                if (snapshot.child("tiket").exists()){
                    tvTiket.setVisibility(View.VISIBLE);
                    tvHarga.setVisibility(View.VISIBLE);
                    tvKonfir.setText("Pesanan Tiket Terkonfirmasi");
                    tvTiket.setText(snapshot.child("tiket").getValue().toString());
                    tvHarga.setText(snapshot.child("harga").getValue().toString());
                }else{
                    tvKonfir.setText("Pesanan Tiket Tidak Terkonfirmasi");
                    tvTiket.setVisibility(View.GONE);
                    tvHarga.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}