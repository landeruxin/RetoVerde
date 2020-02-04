package com.example.inicioreto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDSQLiteCabecera extends SQLiteOpenHelper {


    private String sqlCab = "create table Cabecera("+
            "idCabecera INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idComercial INTEGER," +
            "FechaPedido Date)";

            //partner extranj.

    public BDSQLiteCabecera(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
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