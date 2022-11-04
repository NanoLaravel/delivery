package com.herfan.delivery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<Producto> listaProductos;


    public void setListaProductos(List<Producto> listaProductos){
        this.listaProductos = listaProductos;
    }
    public RecyclerAdapter(List<Producto> productos){
        this.listaProductos = productos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View filaProducto = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemproducto2, parent, false);
        return new MyViewHolder(filaProducto);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Producto p = listaProductos.get(position);
        String nombre = p.getNombre();
        String precio = p.getPrecio();
        int[] fotos = {R.drawable.aceite, R.drawable.arroz, R.drawable.azucar, R.drawable.clorox, R.drawable.jabonbano, R.drawable.leche, R.drawable.yogurt, R.drawable.mantequilla};


        holder.nombre.setText(nombre);
        holder.precio.setText(precio);

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, precio;
        ImageView img1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvProducto);
            precio = itemView.findViewById(R.id.tvprecio);
            img1 = itemView.findViewById(R.id.imgItem);

        }
    }
}


