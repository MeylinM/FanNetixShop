package com.example.fannetixshop;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder> {
    private List<Articulo> articulosCarrito;
    private Context context;
    private Carrito carrito;

    public CarritoAdapter(List<Articulo> articulosCarrito, Carrito carrito) {
        this.articulosCarrito = articulosCarrito;
        this.carrito = carrito;
    }

    @Override
    public CarritoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos la vista para cada ítem del RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrito, parent, false);
        context = parent.getContext();
        return new CarritoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarritoViewHolder holder, int position) {
        Articulo articulo = articulosCarrito.get(position);

        // Asignamos los valores de cada artículo a los componentes de la vista
        holder.tvTitulo.setText(articulo.getTitulo());
        holder.tvPrecio.setText(String.format("%.2f €", articulo.getPrecio()));

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

        // Configurar el CheckBox si es necesario
        holder.checkBox.setChecked(false); // Aquí puedes agregar la lógica para manejar el estado del CheckBox
    }

    @Override
    public int getItemCount() {
        return articulosCarrito.size();
    }

    public static class CarritoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvPrecio;
        ImageView ivArticulo;
        CheckBox checkBox;

        public CarritoViewHolder(View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTituloCarrito);
            tvPrecio = itemView.findViewById(R.id.tvPrecioCarrito);
            ivArticulo = itemView.findViewById(R.id.ivArticuloCarrito);
            checkBox = itemView.findViewById(R.id.checkBox); // Si necesitas el CheckBox para seleccionar artículos
        }
    }

    // Aquí puedes agregar cualquier método adicional si lo necesitas
}
