package com.example.aplicacion2.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "producto.db";
    public static final String TABLE_PRODUCTO = "t_producto";
    public static final String TABLE_LIST_PRODUCTOS = "t_lista_productos";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PRODUCTO + "(" +
                "id_producto INTEGER PRIMARY KEY AUTOINCREMENT," +
                "producto TEXT NOT NULL," +
                "precio INTEGER NOT NULL," +
                "cantidad INTEGER NOT NULL," +
                "ubicacion TEXT NOT NULL," +
                "tipo TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_LIST_PRODUCTOS + "(" +
                "id_lista_producto INTEGER  NOT NULL," +
                "nombreLista TEXT NOT NULL," +
                "id_producto INTEGER NOT NULL,"+
                "cantidad_producto INTEGER NOT NULL)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PRODUCTO);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_LIST_PRODUCTOS);
        onCreate(sqLiteDatabase);

    }
}