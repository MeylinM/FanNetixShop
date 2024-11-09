package com.example.fannetixshop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloAdapter.ArticuloViewHolder> {
    private List<Articulo> articulos;
    private Context context;

    public ArticuloAdapter(Context context, List<Articulo> articulos) {
        this.context = context;
        this.articulos = articulos;
    }

    @Override
    public ArticuloViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ArticuloViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticuloViewHolder holder, int position) {
        Articulo articulo = articulos.get(position);
        holder.tvTipo.setText(articulo.getTipo().name());
        holder.tvTitulo.setText(articulo.getTitulo());
        holder.tvDescripcion.setText(articulo.getDescripcion());
        holder.tvPrecio.setText(String.valueOf(String.format("%.2f", articulo.getPrecio()))+"€");
        String path = articulo.getPath();
        if (path.startsWith("drawable/")) {
            // Si el path es algo como "drawable/mi_imagen"
            String resourceName = path.substring("drawable/".length());
            int resourceId = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
            if (resourceId != 0) {
                holder.ivArticulo.setImageResource(resourceId);  // Establecer la imagen
            } else {
                holder.ivArticulo.setImageResource(R.drawable.default_image);  // Imagen por defecto si no se encuentra el recurso
            }
        } else{
            holder.ivArticulo.setImageURI(Uri.parse(path));
        }

    }


    @Override
    public int getItemCount() {
        return articulos.size();
    }

    public static class ArticuloViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvDescripcion, tvPrecio, tvTipo;
        ImageView ivArticulo;

        public ArticuloViewHolder(View itemView) {
            super(itemView);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            ivArticulo = itemView.findViewById(R.id.ivArticulo);
        }
    }
}