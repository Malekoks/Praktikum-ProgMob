package com.example.modul1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int textSize = 0;
    SeekBar seekBar;
    String pilih = "", s1 = "", s2 = "", s3 = "";
    TextView txtSeekBar;
    Button btnDaftar;
    EditText nama, email, pass, comfrmpass;
    CheckBox min, plus, silinder;
    RadioGroup pilihan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        min = findViewById(R.id.min);
        plus = findViewById(R.id.plus);
        silinder = findViewById(R.id.silinder);
        nama = findViewById(R.id.inputName);
        email = findViewById(R.id.inputEmail);
        btnDaftar = findViewById(R.id.button);
        pass = findViewById(R.id.inputPass);
        comfrmpass = findViewById(R.id.inputConfirmPass);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        txtSeekBar = findViewById(R.id.ratingvalue);
        txtSeekBar.setText(Integer.toString(textSize));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress = 0;

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                textSize = textSize + (progressValue - progress);
                progress = progressValue;
                txtSeekBar.setText(Integer.toString(textSize));
            }
        });

        pilihan = findViewById(R.id.radioGroup);
        pilihan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.rb1:
                        pilih = "Perempuan";
                        break;
                    case R.id.rb2:
                        pilih = "Laki-laki";
                }
            }
        });


        btnDaftar.setOnClickListener(new View.OnClickListener() { //method button
            @Override
            public void onClick(View view) {
                if (min.isChecked()) {
                    s1 = "\n Minus";
                }
                if (plus.isChecked()) {
                    s2 = "\n Plus";
                }
                if (silinder.isChecked()) {
                    s3 = "\n Silinder";
                }

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setIcon(R.drawable.file);
                alertDialogBuilder.setTitle("Data User");
                alertDialogBuilder.setMessage("Nama: " + nama.getText().toString() +
                        "\nEmail: " + email.getText().toString() +
                        "\nPassword : " + pass.getText().toString() +
                        "\nJenis Kelamin: " + pilih +
                        "\nGejala: " + s1 + s2 + s3 +
                        "\nUmur: " + textSize
                );

                alertDialogBuilder.setPositiveButton("Daftar", new DialogInterface.OnClickListener() { //method button positive desicion
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Data anda berhasil terdaftarkan !", Toast.LENGTH_SHORT).show();

                        Intent layout2 = new Intent(MainActivity.this, DetailMain.class);

                        layout2.putExtra("Nama", nama.getText().toString());
                        layout2.putExtra("Email", email.getText().toString());
                        layout2.putExtra("Password", pass.getText().toString());
                        layout2.putExtra("Jenis Kelamin", pilih);
                        layout2.putExtra("Gejala", s1 + s2 + s3);
                        layout2.putExtra("Umur", txtSeekBar.getText().toString());

                        startActivity(layout2);

                        finish();
                    }
                });

                alertDialogBuilder.setNegativeButton("Kembali", new DialogInterface.OnClickListener() { //method button negative desicion
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create(); //method get alert dan create alert
                alertDialog.show(); //to show alert
            }
        });
    }
}