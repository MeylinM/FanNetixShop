package com.example.fannetixshop;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShopActivity extends AppCompatActivity {
    private final DatabaseHelper dbHelper = new DatabaseHelper(this);
    private ImageView btnPlay;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shop);

        //Iniciar el recycleView
        RecyclerView recyclerView = findViewById(R.id.recyclerArticulo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener los artículos del artista
        Bundle extras = getIntent().getExtras();
        String artista = null;
        if (extras != null) {
            artista = extras.getString("Nombre");
        }
        List<Articulo> articulos = dbHelper.obtenerArticulosPorArtista(artista);

        // Configurar el adaptador con la lista de artículos obtenida
        ArticuloAdapter adapter = new ArticuloAdapter(articulos);
        recyclerView.setAdapter(adapter);

        //Configurar listeners
        ImageView volver = findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this, MenuArtistActivity.class);
                startActivity(intent);
                mediaPlayer.pause();
            }
        });
        ImageView carrito = findViewById(R.id.carrito);
        carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this, CartActivity.class);
                startActivity(intent);
                mediaPlayer.pause();
            }
        });
        Button btnSubirProducto = findViewById(R.id.btnSubirProducto);
        btnSubirProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this, UploadProductActivity.class);
                startActivity(intent);
                mediaPlayer.pause();
            }
        });
        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    btnPlay.setImageDrawable(getDrawable(R.drawable.pause));
                } else {
                    mediaPlayer.pause();
                    btnPlay.setImageDrawable(getDrawable(R.drawable.play));
                }
            }
        });

        //Poner las imagenes por defecto correspondientes al artista
        if (artista != null) {
            switch (artista) {
                case "BlackPink": {
                    mediaPlayer = MediaPlayer.create(this, R.raw.blackpink);

                    ImageView encabezado = findViewById(R.id.encabezado);
                    encabezado.setImageDrawable(getDrawable(R.drawable.encabezado));

                    break;
                }
                case "Adele": {
                    mediaPlayer = MediaPlayer.create(this, R.raw.adele);

                    ImageView encabezado = findViewById(R.id.encabezado);
                    encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_adele));

                    break;
                }
                case "Eminem": {
                    mediaPlayer = MediaPlayer.create(this, R.raw.eminem);

                    ImageView encabezado = findViewById(R.id.encabezado);
                    encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_eminem));

                    break;
                }
                case "Bruno Mars": {
                    mediaPlayer = MediaPlayer.create(this, R.raw.bruno_mars);

                    ImageView encabezado = findViewById(R.id.encabezado);
                    encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_bruno));

                    break;
                }
                case "Harry Styles": {
                    mediaPlayer = MediaPlayer.create(this, R.raw.harry_styles);

                    ImageView encabezado = findViewById(R.id.encabezado);
                    encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_harry));

                    break;
                }
                case "Fito": {
                    mediaPlayer = MediaPlayer.create(this, R.raw.fito);

                    ImageView encabezado = findViewById(R.id.encabezado);
                    encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_fito));

                    break;
                }
                case "IZ*ONE": {
                    mediaPlayer = MediaPlayer.create(this, R.raw.iz_one);

                    ImageView encabezado = findViewById(R.id.encabezado);
                    encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_izone));

                    break;
                }
                case "StrayKids": {
                    mediaPlayer = MediaPlayer.create(this, R.raw.straykids);

                    ImageView encabezado = findViewById(R.id.encabezado);
                    encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_straykids));
                    break;
                }
                default:
                    Log.d("ArtistaCheck", "El artista no es existe");
                    break;
            }
        }
    }
}