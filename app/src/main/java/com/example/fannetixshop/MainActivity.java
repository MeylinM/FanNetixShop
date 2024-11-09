package com.example.fannetixshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Habilitar Edge to Edge
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextEmail2);
        ImageButton imageBtnSignIn = findViewById(R.id.imageBtnSignIn);

        databaseHelper = new DatabaseHelper(this);

        // Configurar el listener para el botón de inicio de sesión
        imageBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();


                // Verificar si el usuario existe
                if (databaseHelper.validarUsuario(email, password)) {
                    int userId = databaseHelper.obtenerIdUsuarioPorEmail(email);
                    // Guardar el ID del usuario en SharedPreferences
                    SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("user_id", userId);
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this, MenuArtistActivity.class);
                    startActivity(intent);
                } else {
                    // Mostrar mensaje de error
                    Toast.makeText(MainActivity.this, "Error, usuario no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}