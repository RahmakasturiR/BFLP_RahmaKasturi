package com.example.bflp_rahmakasturi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView zXingScannerView;
    TextView tvQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qractivity);
        tvQR = findViewById(R.id.tvQR);
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
    }
    @Override
    public void onResume() {
        super.onResume();
        zXingScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        zXingScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {

        tvQR.setText(rawResult.getText());
        Intent i = new Intent(ScanQRActivity.this,CekTiketActivity.class);
        i.putExtra("ID USER",rawResult.getText());
        startActivity(i);
        Log.i("TAG", "handleResult: "+rawResult.getText());
    }
}