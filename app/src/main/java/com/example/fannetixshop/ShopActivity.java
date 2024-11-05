package com.example.fannetixshop;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ShopActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private List<Articulo> articulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_blackpink);

        // Obtener los elementos del layout
        TextView tvTipo = findViewById(R.id.tvType);
        TextView tvTitulo = findViewById(R.id.tvDescripCamiseta);
        TextView tvDescripcion = findViewById(R.id.tvTitulo);
        TextView tvPrecioCamiseta = findViewById(R.id.tvPrecioCami);
/*
        // Obtener los artículos de Blackpink CASCA AQUI
        articulos = dbHelper.obtenerArticulosPorArtista("Blackpink");

        // El primer artículo es una camiseta
        if (!articulos.isEmpty()) {
            Articulo camiseta = articulos.get(0); // Suponiendo que el primer artículo es la camiseta
            tvTipo.setText(camiseta.getTipo().name());
            tvTitulo.setText(camiseta.getTitulo());
            tvDescripcion.setText(camiseta.getDescripcion());
            tvPrecioCamiseta.setText(String.valueOf(camiseta.getPrecio()));
        }

 */
    }
}