package com.herfan.delivery;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>  {
    private List<Producto> listaProductos;
    private final RecyclerViewInterface recyclerViewInterface;

    public RecyclerAdapter(List<Producto> listaProductos, RecyclerViewInterface recyclerViewInterface){
        this.listaProductos = listaProductos;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View filaProductos = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemproducto2, parent, false);
        return new MyViewHolder(filaProductos, recyclerViewInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Producto p = listaProductos.get(position);
        String nombre = p.getNombre();
        String precio = p.getPrecio();
        int imagen = p.getImagen();
        //int[] imagen = {R.drawable.aceite, R.drawable.arroz, R.drawable.azucar, R.drawable.clorox, R.drawable.jabonbano, R.drawable.leche, R.drawable.yogurt, R.drawable.mantequilla};


        holder.nombre.setText(nombre);
        holder.precio.setText(precio);
        holder.imagen.setImageResource(listaProductos.get(position).getImagen());



    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
       public TextView nombre, precio;
       public ImageView imagen;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvProducto);
            precio = itemView.findViewById(R.id.tvprecio);
            imagen = itemView.findViewById(R.id.imgItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });


        }
    }

}


