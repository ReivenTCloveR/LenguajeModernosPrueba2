package com.example.aplicacion2.Objetos.ListaProducto;

import java.io.Serializable;


public class ShoppingList implements Serializable {

    private int id_list;
    private String nombre_list;
    private int cantidad_productos;


    public ShoppingList(){}

    public ShoppingList(int id_list, String nombre_list, int cantidad_productos) {
        this.id_list = id_list;
        this.nombre_list = nombre_list;
        this.cantidad_productos = cantidad_productos;
    }

    public int getId_list() {
        return id_list;
    }

    public void setId_list(int id_list) {
        this.id_list = id_list;
    }

    public String getNombre_list() {
        return nombre_list;
    }

    public void setNombre_list(String nombre_list) {
        this.nombre_list = nombre_list;
    }

    public int getCantidad_productos() {
        return cantidad_productos;
    }

    public void setCantidad_productos(int cantidad_productos) {
        this.cantidad_productos = cantidad_productos;
    }
}
