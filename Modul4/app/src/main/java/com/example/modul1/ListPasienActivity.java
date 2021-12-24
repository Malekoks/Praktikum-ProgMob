package com.example.modul1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListPasienActivity extends AppCompatActivity {
    private Button btnTambah;
    private RecyclerView listPasien;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pasien);

        btnTambah = findViewById(R.id.btnTambah);
        listPasien = findViewById(R.id.list_pasien);

        dbHelper = new DBHelper(this);
        TextView tvbacklogin = (TextView)findViewById(R.id.tvbacklogin);

        tvbacklogin.setOnClickListener(v -> startActivity(new Intent(ListPasienActivity.this, LoginActivity.class)));
        loadRecords();

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListPasienActivity.this, MainActivity.class);
                intent.putExtra("isEditMode", false);
                startActivity(intent);
            }
        });
    }

    private void loadRecords() {
        DBAdapter adapter = new DBAdapter(ListPasienActivity.this, dbHelper.ambilDataPasien());
        listPasien.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadRecords();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //handle item menu
        return super.onOptionsItemSelected(item);
    }

}