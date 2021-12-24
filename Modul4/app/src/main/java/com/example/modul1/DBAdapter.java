package com.example.modul1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DBAdapter extends RecyclerView.Adapter<DBAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Data> arrayList;
    DBHelper databaseHelper;

    //constructor
    public DBAdapter(Context context, ArrayList<Data> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        databaseHelper = new DBHelper(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namapasien,jeniskelamin , gejala, umur;
        Button editBtn, deleteBtn;

        public ViewHolder(@NonNull View view) {
            super(view);

            namapasien = view.findViewById(R.id.nama);
            jeniskelamin = view.findViewById(R.id.txJeniskelamin);
            gejala = view.findViewById(R.id.gejala);
            umur = view.findViewById(R.id.umur);
            editBtn = view.findViewById(R.id.editBtn);
            deleteBtn = view.findViewById(R.id.deleteBtn);
        }
    }

    @NonNull
    @Override
    public DBAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //infalate layout
        View view = LayoutInflater.from(context).inflate(R.layout.activity_pasien, parent, false);
        return new ViewHolder(view);
    }

    //get data, set data, handle view click in method
    @Override
    public void onBindViewHolder(@NonNull DBAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //get data
        Data Pasien = arrayList.get(position);
        String id = Pasien.getId();
        String nama = Pasien.getNama();
        String alamat = Pasien.getAlamat();
        String jeniskelamin = Pasien.getJeniskelamin();
        String gejala = Pasien.getGejala();
        String umur = Pasien.getUmur();



        //set data ke views
        holder.namapasien.setText(nama);
        holder.jeniskelamin.setText(jeniskelamin);
        holder.gejala.setText(gejala);
        holder.umur.setText(umur);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pass record id to next activity to show details of that records
                Intent intent = new Intent(context, DetailMain.class);
                intent.putExtra("id", id);
                context.startActivity(intent);

            }
        });

        //handle tombol more untuk edit, delete
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("nama", nama);
                intent.putExtra("alamat", alamat);
                intent.putExtra("jeniskelamin", jeniskelamin);
                intent.putExtra("gejala", gejala);
                intent.putExtra("umur", umur);
                intent.putExtra("isEditMode", true);
                context.startActivity(intent);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.delete(id);
                ((ListPasienActivity) context).onResume();
            }
        });

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

