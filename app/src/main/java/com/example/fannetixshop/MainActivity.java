package com.example.fannetixshop;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar campos y botón
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextEmail2);
        ImageButton imageBtnSignIn = findViewById(R.id.imageBtnSignIn);

        // Inicializar la base de datos
        databaseHelper = new DatabaseHelper(this);

        // Configurar el listener para el botón de inicio de sesión
        imageBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();


                // Verificar que el usuario exista y que la contraseña sea correcta
                if (databaseHelper.validarUsuario(email, password)) {
                    // Usuario válido, pasar a la siguiente actividad
                    Intent intent = new Intent(MainActivity.this, MenuArtistActivity.class);
                    startActivity(intent);
                } else {
                    // Mostrar mensaje de error si el usuario no es válido
                    Toast.makeText(MainActivity.this, "Error, usuario no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}