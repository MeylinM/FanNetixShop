package com.example.fannetixshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloAdapter.ArticuloViewHolder> {
    private List<Articulo> articulos;
    private Context context;
    private DatabaseHelper dbHelper;

    public ArticuloAdapter(Context context, List<Articulo> articulos, DatabaseHelper databaseHelper) {
        this.context = context;
        this.articulos = articulos;
        this.dbHelper = databaseHelper;
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
            String resourceName = path.substring("drawable/".length());
            int resourceId = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
            if (resourceId != 0) {
                holder.ivArticulo.setImageResource(resourceId);  //Establecer la imagen
            } else {
                holder.ivArticulo.setImageResource(R.drawable.default_image);  //Imagen por defecto si no se encuentra el recurso
            }
        } else{
            holder.ivArticulo.setImageURI(Uri.parse(path));
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("user_id", -1);
        // Acción de agregar al carrito
        holder.btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = dbHelper.agregarAlCarrito(userId, articulo.getId());

                // Mostrar un Toast si el artículo fue añadido con éxito
                if (success) {
                    Toast.makeText(context, R.string.toastItemAddedCart, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, R.string.toastItemNotAddedCart, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return articulos.size();
    }

    public static class ArticuloViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvDescripcion, tvPrecio, tvTipo, btnComprar;
        ImageView ivArticulo;

        public ArticuloViewHolder(View itemView) {
            super(itemView);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            ivArticulo = itemView.findViewById(R.id.ivArticulo);
            btnComprar = itemView.findViewById(R.id.btnComprar);
        }
    }
}
