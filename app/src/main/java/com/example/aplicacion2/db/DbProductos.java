package com.example.aplicacion2.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;

import androidx.annotation.Nullable;

import com.example.aplicacion2.Objetos.Productos.Productos;

import java.util.ArrayList;


public class DbProductos extends DbHelper {

    Context context;
    SQLiteDatabase db;
    public DbProductos(@Nullable Context context) {
        super(context);
        this.context = context;

    }

    public long insertarProducto(String producto, int precio, int cantidad, String ubicacion, String tipo) {

        long id = 0;

        try {

            DbHelper dbhelper = new DbHelper(context);
            this.db = dbhelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("producto", producto);
            values.put("precio", precio);
            values.put("cantidad", cantidad);
            values.put("ubicacion", ubicacion);
            values.put("tipo", tipo);

            id = db.insert(TABLE_PRODUCTO, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    @SuppressLint("Recycle")
    public ArrayList<Productos> mostrarPoduct(){


        DbHelper dbhelper = new DbHelper(context);
        this.db = dbhelper.getWritableDatabase();

        ArrayList<Productos> listProductos = new ArrayList<>();
        Productos productos;
        Cursor cursorProductos;

        cursorProductos = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTO, null);

        if(cursorProductos.moveToFirst()){
            do{
                productos = new Productos();
                productos.setId(cursorProductos.getInt(0));
                productos.setNombre(cursorProductos.getString(1));
                productos.setPrecio(cursorProductos.getInt(2));
                productos.setCantidad(cursorProductos.getInt(3));
                productos.setUbicacion(cursorProductos.getString(4));
                productos.setTipo(cursorProductos.getString(5));
                listProductos.add(productos);
            }while (cursorProductos.moveToNext());
        }
        return listProductos;
    }



    public Productos verProducto(int id) {


        DbHelper dbhelper = new DbHelper(context);
        this.db = dbhelper.getWritableDatabase();

        Productos productos = null;
        Cursor cursorProductos;

        cursorProductos = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTO + " WHERE id_producto = '" + id + "' LIMIT 1", null);

        if (cursorProductos.moveToFirst()) {
            productos = new Productos();
            productos.setId(cursorProductos.getInt(0));
            productos.setNombre(cursorProductos.getString(1));
            productos.setPrecio(cursorProductos.getInt(2));
            productos.setCantidad(cursorProductos.getInt(3));
            productos.setUbicacion(cursorProductos.getString(4));
            productos.setTipo(cursorProductos.getString(5));
        }
        cursorProductos.close();
        return productos;
    }




    public boolean editProducto(int id, Editable precio, Editable cantidad, String ubicacion) {

        boolean ready;
        DbHelper dbhelper = new DbHelper(context);
        this.db = dbhelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + TABLE_PRODUCTO + " SET precio ='" + precio + "', cantidad ='" + cantidad + "', ubicacion ='" + ubicacion + "' WHERE id_producto = '" + id + "'");
            ready = true;
        } catch (Exception ex) {
            ex.toString();
            ready = false;
        }finally {
            db.close();
        }

        return ready;
    }

    public boolean eliminarProducto(int id) {

        boolean ready;
        DbHelper dbhelper = new DbHelper(context);
        this.db = dbhelper.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_PRODUCTO + " WHERE id_producto='" + id + "'");
            ready = true;
        } catch (Exception ex) {
            ex.toString();
            ready = false;
        }finally {
            db.close();
        }

        return ready;
    }




}
