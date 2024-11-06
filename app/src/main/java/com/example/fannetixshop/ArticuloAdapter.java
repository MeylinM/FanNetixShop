package com.example.fannetixshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloAdapter.ArticuloViewHolder> {
    private List<Articulo> listaArticulos;
    private Context context;

    // Constructor
    public ArticuloAdapter(Context context, List<Articulo> listaArticulos) {
        this.context = context;
        this.listaArticulos = listaArticulos;
    }

    @NonNull
    @Override
    public ArticuloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.articulo_agregado, parent, false);
        return new ArticuloViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticuloViewHolder holder, int position) {
        Articulo articulo = listaArticulos.get(position);

        // Asignamos los valores a los elementos del layout
        holder.tvTipo.setText(articulo.getTipo().name());
        holder.tvTitulo.setText(articulo.getTitulo());
        holder.tvDescripcion.setText(articulo.getDescripcion());
        holder.tvPrecio.setText(String.valueOf(articulo.getPrecio()));
    }

    @Override
    public int getItemCount() {
        return listaArticulos.size();
    }

    // ViewHolder para los elementos de la lista
    public static class ArticuloViewHolder extends RecyclerView.ViewHolder {
        TextView tvTipo, tvTitulo, tvDescripcion, tvPrecio;
        ImageView ivImagen;

        public ArticuloViewHolder(View itemView) {
            super(itemView);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecioArt);
            ivImagen = itemView.findViewById(R.id.ivArtImage1);
        }
    }
}

