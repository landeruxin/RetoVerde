package com.example.inicioreto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDSQLiteLinea extends SQLiteOpenHelper {


    private String sqlCab = "create table Linea("+
            "idLiena INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idCabecera INTEGER," +//extraj
            "idarticulo,"+ //extraj
            "cantidad INTEGER,"+
            "Precio INTEGER)";



    public BDSQLiteLinea(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlCab);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}