package com.example.fannetixshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class UploadProductActivity extends AppCompatActivity {

    private EditText etNombreProducto, etDescripcion, etPrecio;
    private RadioGroup rgTipo;
    private Button btnSubir;
    private RadioButton btnOriginal, btnFanmade;
    private Spinner spinnerArtistas;
    private List<Artista> listaArtistas;
    private DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_upload_product);

        // Configuración de diseño edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_upload_product), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicialización de los elementos de la interfaz
        etNombreProducto = findViewById(R.id.etNombreProducto);
        etDescripcion = findViewById(R.id.etDescripcion);
        etPrecio = findViewById(R.id.etPrecio);
        rgTipo = findViewById(R.id.radioGroupTipo);
        btnSubir = findViewById(R.id.btnSubir);
        spinnerArtistas = findViewById(R.id.spinnerArtistas);
        btnOriginal = findViewById(R.id.radioButtonOriginal);
        btnFanmade = findViewById(R.id.radioButtonFanmade);

        // Configuración del listener para el botón de subir
        btnSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearArticulo();
            }
        });

        cargarArtistas();
    }

    private void cargarArtistas() {
        listaArtistas = databaseHelper.obtenerArtistas();
        ArrayAdapter<Artista> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaArtistas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArtistas.setAdapter(adapter);
    }

    // Método para obtener el id del artista seleccionado
    public int obtenerIdArtistaSeleccionado() {
        Artista artistaSeleccionado = (Artista) spinnerArtistas.getSelectedItem();
        return artistaSeleccionado != null ? artistaSeleccionado.getId() : -1; // Devuelve -1 si no hay selección
    }

    // Método para crear el artículo usando los datos ingresados en la interfaz
    private void crearArticulo() {
        String titulo = etNombreProducto.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();
        String precioText = etPrecio.getText().toString().trim();
        double precio;

        // Validación de los campos obligatorios
        if (titulo.isEmpty()) {
            Toast.makeText(this, "Introduce un nombre para el producto", Toast.LENGTH_SHORT).show();
            return;
        }

        if (descripcion.isEmpty()) {
            Toast.makeText(this, "Introduce una descripción para el producto", Toast.LENGTH_SHORT).show();
            return;
        }

        if (precioText.isEmpty()) {
            Toast.makeText(this, "Introduce un precio para el producto", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validación del campo de precio
        try {
            precio = Double.parseDouble(precioText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Introduce un precio válido", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedRadioButtonId = rgTipo.getCheckedRadioButtonId();
        Tipo tipo = Tipo.FANMADE; // Valor predeterminado

        if (selectedRadioButtonId == R.id.radioButtonOriginal) {
            tipo = Tipo.ORIGINAL;
        } else if (selectedRadioButtonId == R.id.radioButtonFanmade) {
            tipo = Tipo.FANMADE;
        } else {
            Toast.makeText(this, "Selecciona un tipo de producto", Toast.LENGTH_SHORT).show();
            return;
        }


        int idArtista = obtenerIdArtistaSeleccionado(); // Método que ya tienes en tu actividad

        Articulo articulo = new Articulo(titulo, descripcion, tipo, precio, idArtista);
        if(databaseHelper.crearArticulo(articulo)){
            // Mostrar un mensaje de confirmación
            Toast.makeText(this, "Artículo creado: " + articulo.getTitulo(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UploadProductActivity.this, ShopActivity.class);
            startActivity(intent);

        }else{
            // Mostrar un mensaje de confirmación
            Toast.makeText(this, "YA ME JODERIA ", Toast.LENGTH_SHORT).show();
        }

    }
}
