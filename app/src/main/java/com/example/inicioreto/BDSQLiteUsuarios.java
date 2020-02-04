package com.example.inicioreto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDSQLiteUsuarios extends SQLiteOpenHelper {

    private String sql = "create table usuarios("+
            "idUsuario INTEGER PRIMARY KEY AUTOINCREMENT," +
            "usuario varchar(40)," +
            "contrasena varchar(20))";



    public BDSQLiteUsuarios(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
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
