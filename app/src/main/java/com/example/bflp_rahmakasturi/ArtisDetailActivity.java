package com.example.bflp_rahmakasturi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ArtisDetailActivity extends AppCompatActivity {
    TextView textView,tvTentang;
    ImageView imageView;
    YouTubePlayerView youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artis_detail);
        textView = (TextView) findViewById(R.id.namaband);
        tvTentang = findViewById(R.id.tvTentang);
        imageView = (ImageView)  findViewById( R.id.imgband);

            youTubePlayerView = (YouTubePlayerView) findViewById(R.id.vd1);
            getLifecycle().addObserver(youTubePlayerView);
//            Toast.makeText(ArtisDetailActivity.this,getIntent().getStringExtra("EXTRA VIDEOYT").toString(),Toast.LENGTH_LONG).show();

                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        youTubePlayer.loadVideo(getIntent().getStringExtra("EXTRA VIDEOYT"), 0);
                    }
                });



            String mURL = getIntent().getStringExtra("EXTRA IMAGE BAND");
            textView.setText(getIntent().getStringExtra("EXTRA NAMA BAND"));
            tvTentang.setText("    "+getIntent().getStringExtra("EXTRA TENTANG"));
            Picasso.get()
                    .load(mURL)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });

    }
}