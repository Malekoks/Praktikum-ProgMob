package com.example.modul1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.modul1.DBHelper;
import com.example.modul1.DetailMain;
import com.example.modul1.R;


public class MainActivity extends AppCompatActivity {

    int textSize = 0;
    String pilih = "";
    TextView textNama;
    EditText nama, alamat;
    TextView txtSeekBar;
    SeekBar seekBar;
    RadioGroup pilihan;
    RadioButton perempuan, laki;
    CheckBox min, plus, silinder;
    DBHelper dbHelper;
    Button submit;
    private boolean isEditMode = false;
    private String id, nama_pasien, alamat_pasien, jk_pasien, gejala_pasien, umur_pasien;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nama = findViewById(R.id.nama);
        textNama = findViewById(R.id.textnama);
        alamat = findViewById(R.id.alamat);
        txtSeekBar = findViewById(R.id.ratingvalue);
        min = findViewById(R.id.min);
        plus = findViewById(R.id.plus);
        silinder = findViewById(R.id.silinder);
        perempuan = findViewById(R.id.perempuan);
        laki = findViewById(R.id.laki);
        seekBar = findViewById(R.id.seekBar);
        txtSeekBar.setText(Integer.toString(textSize));
        submit = findViewById(R.id.submit);
        dbHelper = new DBHelper(this);
        TextView tvback = (TextView) findViewById(R.id.tvback);

        tvback.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ListPasienActivity.class)));
        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode", false);
        if (isEditMode) {
            //update data

            //get data
            id = intent.getStringExtra("id");
            nama_pasien = intent.getStringExtra("nama");
            alamat_pasien = intent.getStringExtra("alamat");
            jk_pasien = intent.getStringExtra("jenis_kelamin");
            gejala_pasien = intent.getStringExtra("gejala");
            umur_pasien = intent.getStringExtra("umur");

            nama.setText(nama_pasien);
            alamat.setText(alamat_pasien);

            //set radio button checked sesuai value dari resep
            if ("Perempuan".equals(jk_pasien)) {
                perempuan.setChecked(true);
            } else if ("Laki-Laki".equals(jk_pasien)) {
                laki.setChecked(true);
            } else {
                perempuan.setChecked(false);
                laki.setChecked(false);
            }
        } else {

        }
        seekBar.setProgress(0);
        seekBar.setMax(200);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                int progressD = (int) progressValue / 2;
                txtSeekBar.setText(Integer.toString(progressD));
            }
        });


        pilihan = findViewById(R.id.pilihan);
        pilihan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.perempuan:
                        pilih = "Perempuan";
                        break;
                    case R.id.laki:
                        pilih = "Laki-laki";
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View button) {

                showDialog();
            }
        });
    }



    private void showDialog(){
        String gejala = "";
        if(min.isChecked())
            gejala += "Minus ";
        if(plus.isChecked())
            gejala += "Plus ";
        if(silinder.isChecked())
            gejala += "Silinder ";

        if(nama.getText().toString().isEmpty() || alamat.getText().toString().isEmpty() ||
                pilih.equals("") || gejala.equals("")) {
            Toast.makeText(getApplicationContext(),"Data Belum Terisi",Toast.LENGTH_SHORT).show();
        }
        else {
            if (isEditMode) { //update data
                dbHelper.update("" + id,
                        "" + nama_pasien,
                        "" + alamat_pasien,
                        "" + pilih,
                        "" + gejala,
                        "" + umur_pasien
                );
                Intent intent = new Intent(MainActivity.this, ListPasienActivity.class);
                Toast.makeText(this, "Data berhasil Di Update! ", Toast.LENGTH_SHORT).show();
                intent.putExtra("id_pasien", id);
                startActivity(intent);
            } else {
                Intent intent = new Intent(MainActivity.this, ListPasienActivity.class);
                //   intent.putExtra("nama", nama.getText().toString());
                //   intent.putExtra("alamat", alamat.getText().toString());
                //   intent.putExtra("jeniskelamin", pilih);
                //   intent.putExtra("gejala", gejala);
                //  intent.putExtra("umur", txtSeekBar.getText().toString());
                ContentValues values = new ContentValues();
                values.put(DBHelper.row1_nama, nama.getText().toString());
                values.put(DBHelper.row2_alamat, alamat.getText().toString());
                values.put(DBHelper.row3_jeniskelamin, pilih);
                values.put(DBHelper.row4_gejala, gejala);
                values.put(DBHelper.row5_umur, txtSeekBar.getText().toString());
                dbHelper.insertDataPasien(values);
                Toast.makeText(this, "Data berhasil ditambahkan! ", Toast.LENGTH_SHORT).show();
                startActivity(intent);


                //alertDialogBuilder.setTitle("Data "+pilih);
                //         alertDialogBuilder
                //              .setMessage("Nama "+pilih+": " + nama.getText().toString()+
                //                     "\nGenre: " +genre+
                //                  "\nSinopsis: " + sinopsis.getText().toString()+
                //                "\nTanggal Rilis: " +tanggal.getText().toString()+
                //              "\nPemeran: " + pemeran.getText().toString()+
                //            "\nRating: " + txtSeekBar.getText().toString())
                //   .setIcon(R.drawable.file)
                // .setCancelable(false)
                //.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                //   public void onClick(DialogInterface dialog, int id) {
                //     dialog.cancel();
                //}
                // });

                //AlertDialog alertDialog = alertDialogBuilder.create();
                //alertDialog.show();
            }
        }
    }
}
