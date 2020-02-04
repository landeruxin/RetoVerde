package com.example.inicioreto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDSQLiteEventos extends SQLiteOpenHelper {

    private String sql = "create table eventos("+
            "idEvento INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombreEvento varchar(40)," +
            "ubicacion varchar(60)," +
            "fechadesde date," +
            "horadesde time," +
            "fechahasta date," +
            "horahasta time," +
            "descripcion varchar(60))";




    public BDSQLiteEventos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
