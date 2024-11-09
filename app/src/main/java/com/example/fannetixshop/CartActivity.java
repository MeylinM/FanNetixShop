package com.example.fannetixshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ImageView volver;
    private RecyclerView recyclerViewCarrito;
    private TextView tvSuma;
    private Button btnPagar;
    private CarritoAdapter carritoAdapter;
    private List<Articulo> articulosCarrito; // Lista de artículos en el carrito
    private Carrito carrito;
    private DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        // Inicializa los componentes de la interfaz
        volver = findViewById(R.id.volver);
        recyclerViewCarrito = findViewById(R.id.recyclerViewCarrito);
        tvSuma = findViewById(R.id.tvSuma);
        btnPagar = findViewById(R.id.btnPagar);

        // Obtén el ID del usuario desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("user_id", -1);

        // Aquí debes cargar el carrito del usuario, por ejemplo desde una base de datos
        // Pero por ahora vamos a crear un carrito vacío (simulando que ya está cargado)
        carrito = new Carrito(); // Este carrito debe estar asociado con el usuario cargado

        // Cargar artículos en el carrito (esto sería de una base de datos o del estado del usuario)
        articulosCarrito = dbHelper.obtenerArticulosCarrito(userId);

        // Configura el RecyclerView
        recyclerViewCarrito.setLayoutManager(new LinearLayoutManager(this));
        carritoAdapter = new CarritoAdapter(articulosCarrito, carrito);
        recyclerViewCarrito.setAdapter(carritoAdapter);

        // Calcula y muestra el total
        actualizarTotal();

        // Configura el evento para el botón "volver"
        volver.setOnClickListener(v -> finish());

        // Configura el evento para el botón "pagar"
        btnPagar.setOnClickListener(v -> {
            if (!articulosCarrito.isEmpty()) {
                // Vaciar el carrito tras realizar la compra
                carrito.vaciarCarrito();
                carritoAdapter.notifyDataSetChanged(); // Actualiza el adaptador

                // Muestra el Toast de confirmación
                Toast.makeText(this, "Compra realizada. ¡Gracias por tu compra!", Toast.LENGTH_SHORT).show();

                // Actualiza el total a 0
                actualizarTotal();
            } else {
                Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actualizarTotal() {
        double sumaTotal = carrito.calcularTotal(); // Método en Carrito para calcular el total
        tvSuma.setText(String.format("%.2f €", sumaTotal));
    }
}
