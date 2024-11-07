package com.example.fannetixshop;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;


import java.util.List;

public class ArticuloViewHolder extends RecyclerView.ViewHolder {
    private TextView tipo,titulo,descripcion,precio;
    private ImageView image1, image2;
    private Context context;  // Definir contexto

    public ArticuloViewHolder(View view) {
        super(view);
        tipo = view.findViewById(R.id.tvTipo);
        titulo = view.findViewById(R.id.tvTitulo);
        descripcion = view.findViewById(R.id.tvDescripcion);
        precio = view.findViewById(R.id.tvPrecioArt);
        image1 = view.findViewById(R.id.ivArtImage1);
        image2 = view.findViewById(R.id.ivArtImage2);
    }

    public void render(Articulo articuloModel) {
        // Establecer los detalles del artículo
        tipo.setText(articuloModel.getTipo().name());
        titulo.setText(articuloModel.getTitulo());
        descripcion.setText(articuloModel.getDescripcion());
        precio.setText(String.valueOf(articuloModel.getPrecio()));
    }
    //public void renderMulti(Multimedia multiModel) {
   //    Glide.with(image1.getContext()).load(multiModel.getPath()).into(image1);
   // }
}