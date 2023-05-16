package com.example.aplicacion2.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.aplicacion2.Objetos.ListaProducto.ShoppingList;

import java.util.ArrayList;

public class DbTablaProducto extends DbHelper{
    Context context;
    SQLiteDatabase db;
    public DbTablaProducto(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    //Insertar
    public long insertarTablaProducto(int idLista, String nombreLista, int idProducto, int cantidadProducto) throws Exception {

        long id = 0;

        try {
            DbHelper dbhelper = new DbHelper(context);
            this.db = dbhelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("id_lista_producto", idLista);
            values.put("nombreLista", nombreLista);
            values.put("id_producto", idProducto);
            values.put("cantidad_producto", cantidadProducto);

            id = db.insert(TABLE_LIST_PRODUCTOS, null, values);
        } catch (SQLException ex) {
            throw new Exception("Error al insertar en tabla producto: " + ex.getMessage());
        } finally {
            db.close();
        }return id;
    }

    @SuppressLint("Range")
    public int obtenerUltimaIdListaProductos() {
        int ultimaId = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT MAX(id_lista_producto) AS INSERT_ID FROM " + TABLE_LIST_PRODUCTOS;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            ultimaId = cursor.getInt(cursor.getColumnIndex("INSERT_ID"));
        }
        cursor.close();
        return ultimaId + 1;
    }

/*
    public void insertTablaProducto(int idLista, String nombreLista, int idProducto, int cantidadProducto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_lista_producto", idLista);
        values.put("nombreLista", nombreLista);
        values.put("id_producto", idProducto);
        values.put("cantidad_producto", cantidadProducto);
        db.insert(TABLE_LIST_PRODUCTOS, null, values);
        db.close();
    }
*/













    //mostrar
    @SuppressLint("Recycle")
    public ArrayList<ShoppingList> mostrarTablaProducto(){


        DbHelper dbhelper = new DbHelper(context);
        this.db = dbhelper.getWritableDatabase();

        ArrayList<ShoppingList> listshoppingList = new ArrayList<>();
        ShoppingList shoppingList;
        Cursor cursorListaProducto;

        cursorListaProducto = db.rawQuery("SELECT * FROM " + TABLE_LIST_PRODUCTOS, null);

        if(cursorListaProducto.moveToFirst()){
            do{
                shoppingList = new ShoppingList();
                shoppingList.setId_list(cursorListaProducto.getInt(0));
                shoppingList.setNombre_list(cursorListaProducto.getString(1));

                listshoppingList.add(shoppingList);
            }while (cursorListaProducto.moveToNext());
        }
        return listshoppingList;
    }




}
