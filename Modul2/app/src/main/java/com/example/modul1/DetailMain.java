package com.example.modul1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetailMain extends AppCompatActivity {

    TextView nama,email,password,jeniskelamin,gejala,umur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_main);

        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        jeniskelamin = findViewById(R.id.jk);
        gejala = findViewById(R.id.gej);
        umur = findViewById(R.id.umur);

        nama.setText(getIntent().getExtras().getString("Nama"));
        email.setText(getIntent().getExtras().getString("Email"));
        password.setText(getIntent().getExtras().getString("Password"));
        jeniskelamin.setText(getIntent().getExtras().getString("Jenis Kelamin"));
        gejala.setText(getIntent().getExtras().getString("Gejala"));
        umur.setText(getIntent().getExtras().getString("Umur"));
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