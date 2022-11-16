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

public class HomeActivity2 extends AppCompatActivity {
    private  RecyclerView reciclerProductos;
    private ArrayList<Producto> listaProductos;
    private RecyclerAdapter adaptador;
    //String[] productos = {"Aceite", "Arroz", "Azucar", "Clorox", "Detergente", "Jabon de Baño", "Leche", "yogurt", "Mantequilla"};
    //float[] precios = {1900, 3200, 3900, 700, 1400, 2200, 1200, 1000};
    //int[] fotos = {R.drawable.aceite, R.drawable.arroz, R.drawable.azucar, R.drawable.clorox, R.drawable.jabonbano, R.drawable.leche, R.drawable.yogurt, R.drawable.mantequilla};
    RecyclerView rv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        Button btnNuevoProducto;
        Button btnDatosUsuario;
        btnNuevoProducto = findViewById(R.id.btnNuevoProducto);
        btnDatosUsuario  = findViewById(R.id.btnDatosUsuario);

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

        reciclerProductos = findViewById(R.id.recyclerView2);
        Producto p1 = new Producto("aceite", "2000");
        Producto p2 = new Producto("arroz", "3000");
        Producto p3 = new Producto("azucar", "4000");
        Producto p4 = new Producto("leche", "2500");
        Producto p5 = new Producto("mantequilla", "5000");
        Producto p6 = new Producto("ypgurt", "2000");
        Producto p7 = new Producto("sal", "1000");
        Producto p8 = new Producto("jabon de baño", "3500");
        Producto p9 = new Producto("detergente", "5000");

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
        adaptador = new RecyclerAdapter(listaProductos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        reciclerProductos.setLayoutManager(mLayoutManager);
        reciclerProductos.setItemAnimator(new DefaultItemAnimator());
        reciclerProductos.setAdapter(adaptador);


    }
}


