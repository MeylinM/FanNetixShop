package com.example.fannetixshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ShopActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper = new DatabaseHelper(this);
    private List<Articulo> articulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_blackpink);

        // Obtener los elementos del layout
        ImageView encabezado = findViewById(R.id.encabezado);

        TextView tvTipo = findViewById(R.id.tvType);
        TextView tvTitulo = findViewById(R.id.tvTitulo);
        TextView tvDescripcion = findViewById(R.id.tvDescripCamiseta);
        TextView tvPrecioCamiseta = findViewById(R.id.tvPrecioCami);

        TextView tvType2 = findViewById(R.id.tvType2);
        TextView tvTitulo2 = findViewById(R.id.tvTitulo2);
        TextView tvDescrip2 = findViewById(R.id.tvDescrip2);
        TextView tvPrice2 = findViewById(R.id.tvPrice2);

        Bundle extras = getIntent().getExtras();
        String artista = extras.getString("Nombre");

        encabezado.setImageDrawable(getDrawable(R.drawable.encabezado));


        // Obtener los artículos de Blackpink CASCA AQUI
        articulos = dbHelper.obtenerArticulosPorArtista(artista);

        // El primer artículo es una camiseta
        if (!articulos.isEmpty()) {
            Articulo camiseta = articulos.get(0);
            tvTipo.setText(camiseta.getTipo().name());
            tvTitulo.setText(camiseta.getTitulo());
            tvDescripcion.setText(camiseta.getDescripcion());
            tvPrecioCamiseta.setText(String.valueOf(camiseta.getPrecio()));

            Articulo funkos = articulos.get(1);
            tvType2.setText(funkos.getTipo().name());
            tvTitulo2.setText(funkos.getTitulo());
            tvDescrip2.setText(funkos.getDescripcion());
            tvPrice2.setText(String.valueOf(funkos.getPrecio()));
        }
    }
}