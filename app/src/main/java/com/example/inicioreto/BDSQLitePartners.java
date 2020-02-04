package com.example.inicioreto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDSQLitePartners extends SQLiteOpenHelper {


    private String sqlCli = "create table clientes("+
            "idCliente INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombreCliente varchar(40)," +
            "apellidosCli varchar(60)," +
            "empresaCli varchar(60)," +
            "direccionEntrega varchar(60)," +
            "direccionFatura varchar(60)," +
            "poblacionCli varchar(60)," +
            "telefonoCli INTEGER," +
            "codComer INTEGER)";




    public BDSQLitePartners(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlCli);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
