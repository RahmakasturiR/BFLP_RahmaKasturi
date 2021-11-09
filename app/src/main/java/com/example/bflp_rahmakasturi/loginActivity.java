package com.example.bflp_rahmakasturi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("TAG", "onCreate: ");
        mAuth = FirebaseAuth.getInstance();
        TextView register = findViewById(R.id.register);

        EditText etUsername = findViewById(R.id.etusername);
        EditText etPassword = findViewById(R.id.etpassword);

        Button btnLogin = findViewById(R.id.btnlogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                try {


                    mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                databaseReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String nama = snapshot.child("nama").getValue().toString();
                                        String username = snapshot.child("username").getValue().toString();
                                        String alamat = snapshot.child("alamat").getValue().toString();
                                        String tglLahir = snapshot.child("tglLahir").getValue().toString();
                                        if (snapshot.child("tiket").exists()){
                                            Log.i("TAG", "Tidak ada tiket");
                                            Preferences.setTiket(loginActivity.this, snapshot.child("tiket").getValue().toString());
                                            Preferences.setHarga(loginActivity.this, snapshot.child("harga").getValue().toString());
                                        }
                                        Log.i("TAG", String.valueOf(snapshot.child("tiket").exists()));
                                        Preferences.SetNama(loginActivity.this, nama);
                                        Preferences.SetUsername(loginActivity.this, username);
                                        Preferences.setAlamat(loginActivity.this, alamat);
                                        Preferences.setTgllahir(loginActivity.this, tglLahir);
                                        Preferences.setUuid(loginActivity.this, FirebaseAuth.getInstance().getCurrentUser().getUid());
//                                    Log.i("TAG", "UUID: "+FirebaseAuth.getInstance().getCurrentUser().getUid());
                                        Log.e("TAG", "onDataChange: " + snapshot.toString());
                                        Toast.makeText(loginActivity.this, "Anda Berhasil Login", Toast.LENGTH_LONG).show();
                                        if(snapshot.child("nama").getValue().toString().equals("Admin")){
                                            Intent i = new Intent(loginActivity.this, AdminActivity.class);
                                            startActivity(i);
                                        }else{
                                            Intent i = new Intent(loginActivity.this, MainActivity.class);
                                            startActivity(i);
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Log.e("TAG", "onCancelled: " + error.toString());
                                    }
                                });
                            } else {
                                Toast.makeText(loginActivity.this, "Anda Gagal Login", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }catch (Exception e){
                    Log.e("TAG", "onClick: "+e.toString() );
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(loginActivity.this, regisActivity.class);
                startActivity(i);
            }
        });
    }
}