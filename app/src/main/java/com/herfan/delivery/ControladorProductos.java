package com.herfan.delivery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class ControladorProductos {
    private BaseDatos db;
    private String NOMBRE_TABLA= "productos";

    /*public ControladorProductos(Context context){
        db = new BaseDatos(context);
    }

    public ArrayList<Producto> listarProductos(){
        ArrayList<Producto> productos = new ArrayList<>();
        /*SQLiteDatabase basedatos = db.getReadableDatabase();
        String[] datosConsultar = {"nombre", "precio","id"};
        Cursor cursor = basedatos.query(
                NOMBRE_TABLA,
                datosConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null) return productos;

        if (!cursor.moveToFirst()) return productos;

        do{
            String nombre = cursor.getString(0);
            String precio = cursor.getString(1);
            int id = cursor.getInt(2);

            Producto p = new Producto();
            p.setNombre(nombre);
            p.setPrecio(precio);
            p.setId_producto(id);
            productos.add(p);

        }while (cursor.moveToNext());

        return productos;
    }


    public long agregarProducto(Producto p){

        SQLiteDatabase basedatos = db.getWritableDatabase();
        ContentValues valoresInsertar = new ContentValues();
        valoresInsertar.put("nombre", p.getNombre());
        valoresInsertar.put("edad", p.getPrecio());

        return basedatos.insert(NOMBRE_TABLA, null, valoresInsertar);

    }


    public long editarProducto(Producto p){

        SQLiteDatabase basedatos = db.getWritableDatabase();
        ContentValues valorEditar = new ContentValues();
        valorEditar.put("nombre", p.getNombre());
        valorEditar.put("edad", p.getPrecio());


        String idActualizar = "id = ?";
        String[] id = {String.valueOf(p.getId_producto())};

        return basedatos.update(NOMBRE_TABLA, valorEditar, idActualizar, id);

    }

    public long elimarProducto(Producto p){
        SQLiteDatabase basedatos = db.getWritableDatabase();
        String[] argumentos = {String.valueOf(p.getId_producto())};
        Log.d("prueba", String.valueOf(p.getId_producto()));
        return  basedatos.delete(NOMBRE_TABLA, "id = ?", argumentos );

    }*/

}
