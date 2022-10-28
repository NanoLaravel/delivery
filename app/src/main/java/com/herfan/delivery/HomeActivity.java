package com.herfan.delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends  AppCompatActivity {
    String[] productos = {"Aceite", "Arroz", "Azucar", "Clorox", "Detergente", "Jabon de Baño", "Leche", "yogurt", "Mantequilla"};
    float[] precios = {1900, 3200, 3900, 700, 1400, 2200, 1200, 1000};
    int[] fotos = {R.drawable.aceite, R.drawable.arroz, R.drawable.azucar, R.drawable.clorox, R.drawable.jabonbano, R.drawable.leche, R.drawable.yogurt, R.drawable.mantequilla};
    RecyclerView rv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rv1 = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv1.setLayoutManager(linearLayoutManager);
        rv1.setAdapter(new AdaptadorProductos());

    }

    private class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.AdaptadorProductosHolder> {

        @NonNull
        @Override
        public AdaptadorProductosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorProductosHolder(getLayoutInflater().inflate(R.layout.itemproducto, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorProductosHolder holder, int position) {
            holder.Imprimir(position);
        }

        @Override
        public int getItemCount() {
            return productos.length;
        }

        private class AdaptadorProductosHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView tvprecio, tvnombreproducto;
            ImageView img1;

            public AdaptadorProductosHolder(@NonNull View itemView) {
                super(itemView);
                tvprecio = itemView.findViewById(R.id.tvprecio);
                tvnombreproducto = itemView.findViewById(R.id.tvnombreproducto);
                img1 = itemView.findViewById(R.id.img_aceite);
                itemView.setOnClickListener(this);
            }

            public void Imprimir(int position) {
                img1.setImageResource(fotos[position]);
                tvnombreproducto.setText(productos[position]);
                tvprecio.setText(String.valueOf(precios[position]));
            }

            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, productos[getLayoutPosition()], Toast.LENGTH_SHORT).show();
            }
        }
    }
}
