package com.example.fannetixshop;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShopActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper = new DatabaseHelper(this);
    private List<Articulo> articulos;
    private ImageView volver, carrito, btnPlay, btnig;
    private MediaPlayer mediaPlayer;
    private RecyclerView recyclerView;
    private ArticuloAdapter articuloAdapter;
    private String instagramUrl="";
    private VideoView videoView;
    private TextView textInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        btnig = findViewById(R.id.btnig);
        textInstagram = findViewById(R.id.btnigText);
        btnig.setVisibility(View.GONE);
        textInstagram.setVisibility(View.GONE);
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

        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    // Si el video está reproduciéndose, lo pausamos
                    videoView.pause();
                } else {
                    // Si el video está pausado, lo reproducimos
                    videoView.start();
                }
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

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // Comprobar si el usuario ha llegado al final de la lista
                if (!recyclerView.canScrollVertically(1)) { // Si no puede desplazarse más hacia abajo
                    btnig.setVisibility(View.VISIBLE); // Mostrar el botón de Instagram
                    textInstagram.setVisibility(View.VISIBLE);
                }

                // Comprobar si el usuario está desplazando hacia arriba
                if (dy < 0) { // Si está desplazando hacia arriba
                    btnig.setVisibility(View.GONE); // Ocultar el botón de Instagram
                    textInstagram.setVisibility(View.GONE);
                }
            }
        });

        //Poner las imagenes + musica por defecto correspondientes al artista
        if (artista != null) {
            switch (artista) {
                case "BlackPink": {
                    mediaPlayer = MediaPlayer.create(this, R.raw.blackpink);
                    ImageView encabezado = findViewById(R.id.encabezado);
                    encabezado.setImageDrawable(getDrawable(R.drawable.encabezado));
                    videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.blackpink_shut_down));
                    videoView.start();
                    instagramUrl = "https://www.instagram.com/blackpinkofficial/";
                    break;
                }
                case "Adele": {
                    mediaPlayer = MediaPlayer.create(this, R.raw.adele);
                    ImageView encabezado = findViewById(R.id.encabezado);
                    encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_adele));
                    videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.adele_easy_on_me));
                    videoView.start();
                    instagramUrl = "https://www.instagram.com/adele/";
                    break;
                }
                case "Eminem": {
                    mediaPlayer = MediaPlayer.create(this, R.raw.eminem);
                    ImageView encabezado = findViewById(R.id.encabezado);
                    encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_eminem));
                    videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.eminem_houdini));
                    videoView.start();
                    instagramUrl = "https://www.instagram.com/eminem/";
                    break;
                }
                case "Bruno Mars": {
                    mediaPlayer = MediaPlayer.create(this, R.raw.bruno_mars);
                    ImageView encabezado = findViewById(R.id.encabezado);
                    encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_bruno));
                    videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bruno_mars_die_with_a_smile));
                    videoView.start();
                    instagramUrl = "https://www.instagram.com/brunomars/";
                    break;
                }
                case "Harry Styles": {
                    mediaPlayer = MediaPlayer.create(this, R.raw.harry_styles);
                    ImageView encabezado = findViewById(R.id.encabezado);
                    encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_harry));
                    videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.harry_styles_satellite));
                    videoView.start();
                    instagramUrl = "https://www.instagram.com/harrystyles/";
                    break;
                }
                case "Fito": {
                    mediaPlayer = MediaPlayer.create(this, R.raw.fito);
                    ImageView encabezado = findViewById(R.id.encabezado);
                    encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_fito));
                    videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fito_fitipaldis_cielo_hermetico));
                    videoView.start();
                    instagramUrl = "https://www.instagram.com/fitoyfitipaldisoficial/";
                    break;
                }
                case "IZ*ONE": {
                    mediaPlayer = MediaPlayer.create(this, R.raw.iz_one);
                    ImageView encabezado = findViewById(R.id.encabezado);
                    encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_izone));
                    videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.iz_one_d_d_dance));
                    videoView.start();
                    instagramUrl = "https://www.instagram.com/official_izone/";
                    break;
                }
                case "StrayKids": {
                    mediaPlayer = MediaPlayer.create(this, R.raw.straykids);
                    ImageView encabezado = findViewById(R.id.encabezado);
                    encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_straykids));
                    videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.stray_kids_chk_chk_boom));
                    videoView.start();
                    instagramUrl = "https://www.instagram.com/realstraykids/";
                    break;
                }
                default:
                    Log.d("ArtistaCheck", "El artista no es existe");
                    break;
            }
            if (!instagramUrl.isEmpty()) {
                btnig.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Crear un Intent para abrir la URL de Instagram en el navegador
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl));
                        startActivity(intent);
                    }
                });
            }
        }

        // Configurar RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewArticulos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArticuloAdapter adapter = new ArticuloAdapter(this, articulos, dbHelper);
        recyclerView.setAdapter(adapter);
    }
}