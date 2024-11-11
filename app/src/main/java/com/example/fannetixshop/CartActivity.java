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
            Log.w(TAG, getString(R.string.toastEmptyCart));
            Toast.makeText(this, getString(R.string.toastEmptyCart), Toast.LENGTH_SHORT).show();
        } else {
            // Configurar el adaptador
            cartAdapter = new CartAdapter(articulosCarrito, this);
            recyclerViewCarrito.setAdapter(cartAdapter);

        }
        // Configurar acción del botón de pagar
        btnPagar.setOnClickListener(v -> {
            if (articulosCarrito.isEmpty()) {
                Toast.makeText(this, getString(R.string.toastProductNotSelected), Toast.LENGTH_SHORT).show();
            }else{
                comprarArticulosSeleccionados();
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, MenuArtistActivity.class);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(v -> {
            if (articulosCarrito.isEmpty()) {
                Toast.makeText(this, getString(R.string.toastItemNotSelectedPay), Toast.LENGTH_SHORT).show();
            }else{
                eliminarArticulosSeleccionados();
            }
        });
    }

    private void comprarArticulosSeleccionados() {
        List<Articulo> seleccionados = cartAdapter.getArticulosSeleccionados();
        if (seleccionados.isEmpty()) {
            Toast.makeText(this, getString(R.string.toastProductNotSelected), Toast.LENGTH_SHORT).show();
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
        actualizarTotal(0.00);
        // Mostrar mensaje de éxito
        Toast.makeText(this, getString(R.string.toastPayment), Toast.LENGTH_SHORT).show();
    }

    private void eliminarArticulosSeleccionados() {
        List<Articulo> seleccionados = cartAdapter.getArticulosSeleccionados();
        if (seleccionados.isEmpty()) {
            Toast.makeText(this, getString(R.string.toastItemNotSelectedPay), Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", -1);

        // Eliminar los artículos seleccionados de la base de datos
        for (Articulo articulo : seleccionados) {
            boolean eliminado = databaseHelper.eliminarArticuloDelCarrito(userId, articulo.getId());
            if (eliminado) {
                articulosCarrito.remove(articulo);
                actualizarTotal(0.00);
            }
        }

        // Actualizar el RecyclerView
        cartAdapter.notifyDataSetChanged();

        // Mostrar mensaje de éxito
        Toast.makeText(this, getString(R.string.toastDeletedItems), Toast.LENGTH_SHORT).show();
    }

    public void actualizarTotal(double total) {
        tvSuma.setText(String.format("$%.2f", total));
    }
}



