package com.example.aplicacion2.Objetos.Productos;
import java.io.Serializable;

public class Productos implements Serializable {

    private int id;
    private String nombre;
    private int precio;
    private int cantidad;
    private String ubicacion;
    private String tipo;

    private int mClickCount = 0;


    public Productos() {

    }

    public Productos(int id, String nombre, int precio, int cantidad, String ubicacion, String tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {this.precio = precio; }
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    //Esto es para la cantidad

    public int getClickCount() {
        return mClickCount;
    }
    public void aumentarContador() {
        mClickCount++;
    }

}
