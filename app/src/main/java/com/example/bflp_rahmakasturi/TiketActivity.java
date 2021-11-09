package com.example.bflp_rahmakasturi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class TiketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiket);
        ImageView imgQR = findViewById(R.id.imgQR);
        TextView tvUUID = findViewById(R.id.tvUUID);
        TextView tvNama = findViewById(R.id.tvNama);
        TextView tvAlamat = findViewById(R.id.tvAlamat);
        TextView tvTglLahir = findViewById(R.id.tvTglLahir);
        TextView tvHarga = findViewById(R.id.tvHarga);
        TextView tvTiket = findViewById(R.id.tvTiket);
        Spinner spTiket = findViewById(R.id.spTiket);
        Button btnBeli = findViewById(R.id.btnBeli);
        tvNama.setText(Preferences.getNAMA(TiketActivity.this));
        tvAlamat.setText(Preferences.getAlamat(TiketActivity.this));
        tvTglLahir.setText(Preferences.getTgllahir(TiketActivity.this));
        if (Preferences.getUuid(TiketActivity.this)==""){
            Intent i = new Intent(TiketActivity.this, loginActivity.class);
            startActivity(i);
        }
        spTiket.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                String harga="";
                String StrHarga = "";
                switch (selectedItemText){
                    case "Standar":
                            harga="100000";
                            StrHarga="Rp.100.000";
                            break;
                    case "VIP":
                        harga="150000";
                        StrHarga="Rp.150.000";
                        break;
                    case "VVIP":
                        harga="200000";
                        StrHarga="Rp.200.000";
                        break;
                    default:break;
                }
                tvHarga.setText(StrHarga);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nama = Preferences.getNAMA(TiketActivity.this);
                String username = Preferences.getUSERNAME(TiketActivity.this);
                String alamat = Preferences.getAlamat(TiketActivity.this);
                String tglLahir = Preferences.getTgllahir(TiketActivity.this);
                String tiket = spTiket.getSelectedItem().toString();
                String harga = tvHarga.getText().toString();
                Log.e("TAG", "onClick: "+spTiket.getSelectedItem().toString() );

                User user = new User(nama, username,alamat,tglLahir,tiket,harga);
//
                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.i("TAG", "Pendaftaran berhasil");
                            Toast.makeText(TiketActivity.this, "Pendaftaran berhasil", Toast.LENGTH_LONG).show();
                            Preferences.SetNama(TiketActivity.this, nama);
                            Preferences.SetUsername(TiketActivity.this, username);
                            Preferences.setAlamat(TiketActivity.this, alamat);
                            Preferences.setTgllahir(TiketActivity.this, tglLahir);
                            Preferences.setTiket(TiketActivity.this, tiket);
                            Preferences.setHarga(TiketActivity.this, harga);
                            Preferences.setUuid(TiketActivity.this, Preferences.getUuid(TiketActivity.this));
                            Intent i = new Intent(TiketActivity.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            Log.i("TAG", "Pendaftaran gagal");
                            Log.i("TAG", task.getException().getMessage());
                            Toast.makeText(TiketActivity.this, "Pendaftaran gagal", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        Log.i("TAG", "onCreate: "+Preferences.getTiket(TiketActivity.this));
        if (Preferences.getTiket(TiketActivity.this)==""){
            spTiket.setVisibility(View.VISIBLE);
            btnBeli.setVisibility(View.VISIBLE);
            tvTiket.setVisibility(View.GONE);
            tvNama.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            tvAlamat.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            tvTglLahir.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            tvTiket.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            tvHarga.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        }else{
            tvTiket.setVisibility(View.VISIBLE);
            spTiket.setVisibility(View.GONE);
            btnBeli.setVisibility(View.GONE);
            tvNama.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tvAlamat.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tvTglLahir.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tvTiket.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tvHarga.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tvTiket.setText(Preferences.getTiket(TiketActivity.this));
            tvHarga.setText(Preferences.getHarga(TiketActivity.this));

            String text=Preferences.getUuid(TiketActivity.this); // Whatever you need to encode in the QR code
            Log.i("TAG", "onCreate UUID: "+text);
//            tvUUID.setText(text);
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,500,500);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                imgQR.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }


    }
}