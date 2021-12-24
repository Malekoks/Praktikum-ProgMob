package com.example.modul1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
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
            switch (id){
                case R.id.rb1:
                    pilih = "Perempuan";
                    break;
                case R.id.rb2:
                    pilih = "Laki-laki";
            }
        }
    });
        }
    public void submit(View view) {
        showDialog();
    }
    private void showDialog(){
        if(min.isChecked()){
            s1 = "\n- Minus";
        }
        if(plus.isChecked()){
            s2 = "\n- Plus";
        }
        if(silinder.isChecked()){
            s3 = "\n- Silinder";
        }

        if(nama.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
                pilih == "" || pass.getText().toString().isEmpty() || comfrmpass.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Data belum lengkap",Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            alertDialogBuilder.setTitle("Data User");
            alertDialogBuilder
                    .setMessage("Nama: " +nama.getText().toString()+
                            "\nEmail: " + email.getText().toString()+
                            "\nPassword : " +pass.getText().toString()+
                            "\nJenis Kelamin: " +pilih+
                            "\nGejala: " +s1+s2+s3+
                            "\nUmur: " + textSize)
                    .setIcon(R.drawable.file)
                    .setCancelable(false)
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

    }
}