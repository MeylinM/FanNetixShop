package com.example.fannetixshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.logging.Logger;

public class ShopActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper = new DatabaseHelper(this);
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
        Button btnUpload = findViewById(R.id.btnUpload);
        // Obtener los artículos de Blackpink CASCA AQUI
        articulos = dbHelper.obtenerArticulosPorArtista("Blackpink");

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un Intent para abrir UploadProductActivity
                Intent intent = new Intent(ShopActivity.this, UploadProductActivity.class);
                startActivity(intent);
            }
        });
        // El primer artículo es una camiseta
        if (!articulos.isEmpty()) {
            System.out.println("La lista de artículos está llena.");
            Articulo camiseta = articulos.get(0); // Suponiendo que el primer artículo es la camiseta
            tvTipo.setText(camiseta.getTipo().name());
            tvTitulo.setText(camiseta.getTitulo());
            tvDescripcion.setText(camiseta.getDescripcion());
            tvPrecioCamiseta.setText(String.valueOf(camiseta.getPrecio()));
        }else {
            System.out.println("La lista de artículos está vacía.");
        }

    }
}