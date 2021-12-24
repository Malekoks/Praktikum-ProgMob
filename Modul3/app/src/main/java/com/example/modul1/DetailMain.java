package com.example.modul1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetailMain extends AppCompatActivity {

    TextView nama,alamat,jeniskelamin,gejala,umur;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_main);

        nama = findViewById(R.id.nama);
        alamat = findViewById(R.id.alamat);
        jeniskelamin = findViewById(R.id.jeniskelamin);
        gejala = findViewById(R.id.gejala);
        umur = findViewById(R.id.umur);

        nama.setText(getIntent().getExtras().getString("nama"));
        alamat.setText(getIntent().getExtras().getString("alamat"));
        jeniskelamin.setText(getIntent().getExtras().getString("jeniskelamin"));
        gejala.setText(getIntent().getExtras().getString("gejala"));
        umur.setText(getIntent().getExtras().getString("umur"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Application On Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Application On Restart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Application On Resume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Application On Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Selamat Tinggal", Toast.LENGTH_SHORT).show();
    }
}