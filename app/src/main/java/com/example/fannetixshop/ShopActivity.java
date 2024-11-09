package com.example.fannetixshop;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShopActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper = new DatabaseHelper(this);
    private List<Articulo> articulos;
    private ImageView volver, carrito, btnPlay;
    private MediaPlayer mediaPlayer;
    private RecyclerView recyclerView;
    private ArticuloAdapter articuloAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        //Listeners
        volver = (ImageView) findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this, MenuArtistActivity.class);
                startActivity(intent);
                mediaPlayer.pause();
            }
        });
        carrito = (ImageView) findViewById(R.id.carrito);
        carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this, CartActivity.class);
                startActivity(intent);
                mediaPlayer.pause();
            }
        });

        btnPlay = (ImageView) findViewById(R.id.btnPlay);
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

        // Obtener los artículos del artista
        Bundle extras = getIntent().getExtras();
        String artista = null;
        if (extras != null) {
            artista = extras.getString("Nombre");
        }
        articulos = dbHelper.obtenerArticulosPorArtista(artista);

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewArticulos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        articuloAdapter = new ArticuloAdapter(this, articulos.subList(2, articulos.size()), dbHelper); // Excluimos los dos primeros artículos
        recyclerView.setAdapter(articuloAdapter);

        //Poner las imagenes + musica por defecto correspondientes al artista
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

        // Configurar RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewArticulos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArticuloAdapter adapter = new ArticuloAdapter(this, articulos, dbHelper);
        recyclerView.setAdapter(adapter);
    }
}