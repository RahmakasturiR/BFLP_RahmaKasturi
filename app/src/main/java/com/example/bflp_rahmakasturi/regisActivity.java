package com.example.bflp_rahmakasturi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class regisActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        mAuth = FirebaseAuth.getInstance();
        TextView login = findViewById(R.id.tvlogin);
        EditText etnama = findViewById(R.id.etnama);
        EditText etAlamat = findViewById(R.id.etAlamat);
        EditText etusername = findViewById(R.id.etusername);
        EditText etpassword = findViewById(R.id.etpassword);
        EditText tvTGL = findViewById(R.id.tvTGL);
//        RelativeLayout rlTGL = findViewById(R.id.rlTGL);
        Button registrasi = findViewById(R.id.btndaftar);

        tvTGL.setInputType(InputType.TYPE_NULL);
        tvTGL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(regisActivity.this,"Tertekan",Toast.LENGTH_LONG).show();
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(regisActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                tvTGL.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String nama = etnama.getText().toString().trim();
                    String alamat = etAlamat.getText().toString().trim();
                    String tglLahir = tvTGL.getText().toString().trim();
                    String username = etusername.getText().toString().trim();
                    String password = etpassword.getText().toString().trim();

                    Log.i("TAG", "onClick: "+username+" "+password);

                    mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User user = new User(nama, username,alamat,tglLahir);
                                Log.i("TAG", "onComplete: ");
                                try {
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.i("TAG", "Pendaftaran berhasil");
                                            Toast.makeText(regisActivity.this, "Pendaftaran berhasil", Toast.LENGTH_LONG).show();
                                            Preferences.SetNama(regisActivity.this,nama);
                                            Preferences.SetUsername(regisActivity.this, username);
                                            Preferences.setAlamat(regisActivity.this, alamat);
                                            Preferences.setTgllahir(regisActivity.this, tglLahir);
                                            Preferences.setUuid(regisActivity.this,FirebaseAuth.getInstance().getCurrentUser().getUid());
                                            Intent i = new Intent(regisActivity.this,MainActivity.class);
                                            startActivity(i);
                                        } else {
                                            Log.i("TAG", "Pendaftaran gagal");
                                            Log.i("TAG", task.getException().getMessage());
                                            Toast.makeText(regisActivity.this, "Pendaftaran gagal", Toast.LENGTH_LONG).show();
                                        }
                                    }


                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("TAG", "onFailure: "+e.getMessage());
                                    }
                                });
                                }catch (Exception e){
                                    Toast.makeText(regisActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                                    Log.e("TAG", "onClick: ", e);
                                }
                            } else {
                                Log.i("TAG", "Pendaftaran gagal");
                                Log.i("TAG", task.getException().getMessage());
                                Toast.makeText(regisActivity.this, "Pendaftaran gagal", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(regisActivity.this,loginActivity.class);
                startActivity(i);
            }
        });
    }
}