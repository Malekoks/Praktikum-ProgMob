package com.example.modul1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "db_login";
    public static final String table_name = "table_login";
    public static final String table_name2 = "table_user";
    public static final String row_id1 = "id";
    public static final String row1_nama = "nama";
    public static final String row3_jeniskelamin = "jenis_kelamin";
    public static final String row4_gejala = "gejala";
    public static final String row2_alamat = "alamat";
    public static final String row5_umur = "umur";
    public static final String row_id = "_id";
    public static final String row_email = "Email";
    public static final String row_password = "Password";

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, database_name, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_email + " TEXT," + row_password + " TEXT)";
        String query2 = "CREATE TABLE " + table_name2 + "(" + row_id1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + row1_nama + " TEXT," + row2_alamat + " TEXT," +row3_jeniskelamin+" TEXT," + row4_gejala +" TEXT," +row5_umur + " TEXT)";
        db.execSQL(query);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        db.execSQL("DROP TABLE IF EXISTS " + table_name2);
    }

    //Insert Data
    public void insertData(ContentValues values){
        db.insert(table_name, null, values);
    }


    public boolean checkUser(String username, String password){
        String[] columns = {row_id};
        SQLiteDatabase db = getReadableDatabase();
        String selection = row_email + "=?" + " and " + row_password + "=?";
        String[] selectionArgs = {username,password};
        Cursor cursor = db.query(table_name, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count>0)
            return true;
        else
            return false;
    }
    public void insertDataPasien(ContentValues values){
        db.insert(table_name2, null, values);
    }

    public ArrayList<Data> ambilDataPasien() {
        ArrayList<Data> arrayList = new ArrayList<>();

        String QUERY = "SELECT * FROM table_user";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY, null);

        if (cursor.moveToFirst()){
            do {
                Data pasien = new Data();
                pasien.setId(cursor.getString(0));
                pasien.setNama(cursor.getString(1));
                pasien.setAlamat(cursor.getString(2));
                pasien.setJeniskelamin(cursor.getString(3));
                pasien.setGejala(cursor.getString(4));
                pasien.setUmur(cursor.getString(5));
                arrayList.add(pasien);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }
    public void update(String row_id1, String row1_nama, String row2_alamat, String row3_jeniskelamin, String row4_gejala, String row5_umur){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("nama",row1_nama);
        values.put("alamat", row2_alamat);
        values.put("jenis_kelamin", row3_jeniskelamin);
        values.put("gejala", row4_gejala);
        values.put("umur", row5_umur);

        database.update("table_user", values, "id = ?", new String[]{row_id1});

        database.close();


    }


    public void delete(String row_id1){
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete("table_user", "id = ?", new String[]{row_id1});
        database.close();

    }
}







