package com.herfan.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DetalleProducto extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        //String id = getIntent().getStringExtra("id");
        String nombre = getIntent().getStringExtra("nombre");
        String precio = getIntent().getStringExtra("precio");
        int imagen = getIntent().getIntExtra("imagen", 0);

        TextView tvNombre = findViewById(R.id.tvNombre);
        TextView tvprecio = findViewById(R.id.tvPrecio);
        ImageView img1 = findViewById(R.id.img1);

        tvNombre.setText(nombre);
        tvprecio.setText(precio);
        img1.setImageResource(imagen);




    }
}