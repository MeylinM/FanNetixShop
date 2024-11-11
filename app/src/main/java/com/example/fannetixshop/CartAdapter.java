package com.example.fannetixshop;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Articulo> articulosCarrito;
    private Context context;
    private List<Articulo> articulosSeleccionados;

    private double total = 0;

    public CartAdapter(List<Articulo> articulosCarrito, Context context) {
        this.articulosCarrito = articulosCarrito;
        this.context = context;
        this.articulosSeleccionados = new ArrayList<>();
    }

    public List<Articulo> getArticulosSeleccionados() {
        return articulosSeleccionados;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_carrito, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Articulo articulo = articulosCarrito.get(position);
        holder.tvTituloCarrito.setText(articulo.getTitulo());
        holder.tvDescription.setText(articulo.getDescripcion());
        holder.tvPrecioCarrito.setText(String.format("$%.2f", articulo.getPrecio()));

        // Cargar la imagen dependiendo del path (drawable o URL)
        String path = articulo.getPath();
        if (path.startsWith("drawable/")) {
            // Cargar imagen desde drawable
            String resourceName = path.substring("drawable/".length());
            int resourceId = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
            if (resourceId != 0) {
                holder.ivArticulo.setImageResource(resourceId);
            } else {
                holder.ivArticulo.setImageResource(R.drawable.default_image); // Imagen por defecto si no se encuentra
            }
        } else {
            // Cargar imagen desde una URL (o cualquier otro tipo de path)
            holder.ivArticulo.setImageURI(Uri.parse(path));
        }
        // Establecer el checkbox para el artículo
        holder.checkBox.setChecked(articulo.isSeleccionado());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            articulo.setSeleccionado(isChecked);
            if (isChecked) {
                articulosSeleccionados.add(articulo);
            } else {
                articulosSeleccionados.remove(articulo);
            }
            // Llamar a actualizarTotal para recalcular el total
            actualizarTotal();
        });

    }

    @Override
    public int getItemCount() {
        return articulosCarrito.size();
    }

    private void actualizarTotal() {
        total = 0;
        for (Articulo articulo : articulosCarrito) {
            if (articulo.isSeleccionado()) {
                total += articulo.getPrecio();
            }
        }
        // Actualizar el total en el activity (a través de un método)
        if (context instanceof CartActivity) {
            ((CartActivity) context).actualizarTotal(total);
        }
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView tvTituloCarrito, tvPrecioCarrito, tvDescription;
        ImageView ivArticulo;
        CheckBox checkBox;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTituloCarrito = itemView.findViewById(R.id.tvTituloCarrito);
            tvPrecioCarrito = itemView.findViewById(R.id.tvPrecioCarrito);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivArticulo = itemView.findViewById(R.id.ivArticuloCarrito);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}




