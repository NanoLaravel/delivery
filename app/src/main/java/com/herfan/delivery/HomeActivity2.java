package com.herfan.delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.checkerframework.common.subtyping.qual.Bottom;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity2 extends AppCompatActivity implements RecyclerViewInterface{
    private  RecyclerView reciclerProductos;
    private ArrayList<Producto> listaProductos;
    private RecyclerAdapter adaptador;

    RecyclerView rv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        Button btnNuevoProducto;
        Button btnDatosUsuario;
        Button btnUbicacion;

        btnNuevoProducto = findViewById(R.id.btnNuevoProducto);
        btnDatosUsuario  = findViewById(R.id.btnDatosUsuario);
        btnUbicacion = findViewById(R.id.btnUbicacion);

        btnNuevoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity2.this, AgregarProducto.class);
                startActivity(i);
            }
        });

        btnDatosUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity2.this, DatosUsuario.class);
                startActivity(i);
            }
        });

        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity2.this, MapsActivity.class);
                startActivity(i);
            }
        });

        reciclerProductos = findViewById(R.id.recyclerView2);
        Producto p1 = new Producto("aceite", "2000", R.drawable.aceite);
        Producto p2 = new Producto("arroz", "3000", R.drawable.arroz);
        Producto p3 = new Producto("azucar", "4000", R.drawable.azucar);
        Producto p4 = new Producto("leche", "2500", R.drawable.leche);
        Producto p5 = new Producto("mantequilla", "5000", R.drawable.mantequilla);
        Producto p6 = new Producto("yogurt", "2000", R.drawable.yogurt);
        Producto p7 = new Producto("cafe", "1000", R.drawable.producto1);
        Producto p8 = new Producto("jabon de baño", "3500", R.drawable.jabonbano);
        Producto p9 = new Producto("detergente", "5000", R.drawable.detergente);

        listaProductos = new ArrayList<Producto>();
        listaProductos.add(p1);
        listaProductos.add(p2);
        listaProductos.add(p3);
        listaProductos.add(p4);
        listaProductos.add(p5);
        listaProductos.add(p6);
        listaProductos.add(p7);
        listaProductos.add(p8);
        listaProductos.add(p9);
        adaptador = new RecyclerAdapter(listaProductos, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        reciclerProductos.setLayoutManager(mLayoutManager);
        reciclerProductos.setItemAnimator(new DefaultItemAnimator());
        reciclerProductos.setAdapter(adaptador);


    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(HomeActivity2.this, DetalleProducto.class);

        //intent.putExtra("id", listaProductos.get(position).getId_producto());
        intent.putExtra("nombre", listaProductos.get(position).getNombre());
        intent.putExtra("precio", listaProductos.get(position).getPrecio());
        intent.putExtra("imagen", listaProductos.get(position).getImagen());

        startActivity(intent);
    }
}


