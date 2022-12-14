package com.herfan.delivery;


import java.io.Serializable;

public class Producto implements Serializable {
    private int id_producto;
    private String nombre;
    private String precio;
    private int imagen;


    public Producto() {
    }

    public Producto(int id_producto, String nombre, String precio) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;

    }

    public Producto(String nombre, String precio, int imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id_producto=" + id_producto +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", imagen=" + imagen +
                '}';
    }
}
