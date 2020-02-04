package com.example.inicioreto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDSQLiteArticulos extends SQLiteOpenHelper {


    private String sqlArti = "create table Articulos("+
            "idArticulo INTEGER PRIMARY KEY AUTOINCREMENT," +
            "PrecioVenta INTEGER," +
            "Descripcion varchar(200))";

    public BDSQLiteArticulos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlArti);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}