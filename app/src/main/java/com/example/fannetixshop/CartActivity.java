package com.example.fannetixshop;

import static androidx.camera.core.CameraXThreads.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCarrito;
    private TextView tvSuma;
    private Button btnPagar;
    private ImageView btnVolver;
    private ImageView btnEliminar;
    private CartAdapter cartAdapter;
    private List<Articulo> articulosCarrito;
    private DatabaseHelper databaseHelper;
    private SharedPreferences sharedPreferences;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        // Inicializar vistas
        recyclerViewCarrito = findViewById(R.id.recyclerViewCarrito);
        tvSuma = findViewById(R.id.tvSuma);
        btnPagar = findViewById(R.id.btnPagar);
        btnVolver = findViewById(R.id.volver);
        btnEliminar = findViewById(R.id.btnEliminar);

        // Configurar RecyclerView
        recyclerViewCarrito.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", -1);

        databaseHelper = new DatabaseHelper(this);

        // Obtener artículos del carrito
        articulosCarrito = databaseHelper.obtenerArticulosCarrito(userId);

        // Verificar si la lista de artículos está vacía o no
        if (articulosCarrito == null || articulosCarrito.isEmpty()) {
            Log.w(TAG, "El carrito está vacío o no se obtuvieron artículos.");
            Toast.makeText(this, "Carrito vacío", Toast.LENGTH_SHORT).show();
        } else {
            // Configurar el adaptador
            cartAdapter = new CartAdapter(articulosCarrito, this);
            recyclerViewCarrito.setAdapter(cartAdapter);

        }


        // Configurar acción del botón de pagar
        btnPagar.setOnClickListener(v -> {
            comprarArticulosSeleccionados();
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(v -> {
            eliminarArticulosSeleccionados();
        });
    }

    private void comprarArticulosSeleccionados() {
        List<Articulo> seleccionados = cartAdapter.getArticulosSeleccionados();
        if (seleccionados.isEmpty()) {
            Toast.makeText(this, "No hay artículos seleccionados para comprar.", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", -1);

        // Eliminar los artículos seleccionados de la base de datos
        for (Articulo articulo : seleccionados) {
            boolean eliminado = databaseHelper.eliminarArticuloDelCarrito(userId, articulo.getId());
            if (eliminado) {
                articulosCarrito.remove(articulo);
            }
        }
        // Actualizar el RecyclerView
        cartAdapter.notifyDataSetChanged();

        // Mostrar mensaje de éxito
        Toast.makeText(this, "Pago procesado", Toast.LENGTH_SHORT).show();
    }

    private void eliminarArticulosSeleccionados() {
        List<Articulo> seleccionados = cartAdapter.getArticulosSeleccionados();
        if (seleccionados.isEmpty()) {
            Toast.makeText(this, "No hay artículos seleccionados para eliminar.", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", -1);

        // Eliminar los artículos seleccionados de la base de datos
        for (Articulo articulo : seleccionados) {
            boolean eliminado = databaseHelper.eliminarArticuloDelCarrito(userId, articulo.getId());
            if (eliminado) {
                articulosCarrito.remove(articulo);
            }
        }

        // Actualizar el RecyclerView
        cartAdapter.notifyDataSetChanged();

        // Mostrar mensaje de éxito
        Toast.makeText(this, "Artículos eliminados", Toast.LENGTH_SHORT).show();
    }

    public void actualizarTotal(double total) {
        tvSuma.setText(String.format("$%.2f", total));
    }
}



