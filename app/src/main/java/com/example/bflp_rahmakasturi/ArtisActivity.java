package com.example.bflp_rahmakasturi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bflp_rahmakasturi.Adapter.GridAdapter;
import com.example.bflp_rahmakasturi.Adapter.OnItemClick;
import com.example.bflp_rahmakasturi.Model.DataArtis;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class    ArtisActivity extends AppCompatActivity {
    private DatabaseReference reference;

    ArrayList<DataArtis> list;
    GridAdapter adapter;
    private GridView mGridView;
    private LinearLayoutManager mManeger;

    int[] sampleImages = {R.drawable.gambar1, R.drawable.gambar2, R.drawable.gambar3, R.drawable.gambar4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artis);
        CarouselView slider = findViewById(R.id.slider);
        slider.setPageCount(sampleImages.length);
        slider.setImageListener(imageListener);

        reference = FirebaseDatabase.getInstance().getReference().child("listband");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
//                    Log.i("TAG", String.valueOf(dataSnapshot1.getValue()));
                    DataArtis dataArtis = dataSnapshot1.getValue(DataArtis.class);
                    list.add(dataArtis);
//                    Log.i("Band", String.valueOf(list));/
                }
                mGridView = findViewById(R.id.gridView);
//                mManeger = new LinearLayoutManager(getApplicationContext());

                adapter = new GridAdapter(getApplicationContext(), list);
                adapter.setOnItemClickCallback(new OnItemClick() {
                    @Override
                    public void onItemClick(DataArtis dataArtis) {
                        Log.d("TAG", "onItemClick: "+dataArtis.getProfilband()+" "+dataArtis.getImage()+" "+dataArtis.getVideoyt());
                        Intent moveIntent1 = new Intent(ArtisActivity.this, ArtisDetailActivity.class);
                        moveIntent1.putExtra("EXTRA NAMA BAND", dataArtis.getProfilband());
                        moveIntent1.putExtra("EXTRA IMAGE BAND",dataArtis.getImage());
                        moveIntent1.putExtra("EXTRA VIDEOYT", dataArtis.getVideoyt());
                        moveIntent1.putExtra("EXTRA TENTANG", dataArtis.getTentang());
                        startActivity(moveIntent1);
                    }
                });
                mGridView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast.LENGTH_LONG).show();

            }
        });

    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
}