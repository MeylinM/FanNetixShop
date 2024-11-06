package com.example.fannetixshop;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ArticuloViewHolder extends RecyclerView.ViewHolder {
    private TextView tipo,titulo,descripcion,precio;
    private ImageView image1, image2;

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
        tipo.setText(articuloModel.getTipo().name());
        titulo.setText(articuloModel.getTitulo());
        descripcion.setText(articuloModel.getDescripcion());
        precio.setText(String.valueOf(articuloModel.getPrecio()));
    }

}

