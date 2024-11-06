package com.example.fannetixshop;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShopActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper = new DatabaseHelper(this);
    private List<Articulo> articulos;
    private ImageView volver, carrito, btnPlay;
    private Button btnSubirProducto;
    private MediaPlayer mediaPlayer;
    private RecyclerView recyclerView;
    private ArticuloAdapter articuloAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        // Obtener los elementos del layout
        TextView tvTipo = findViewById(R.id.tvTipo);
        TextView tvTitulo = findViewById(R.id.tvTitulo);
        TextView tvDescripcion = findViewById(R.id.tvDescrip);
        TextView tvPrecioPrimerArt = findViewById(R.id.tvPrecioPrimerArt);

        TextView tvTipo2 = findViewById(R.id.tvTipo2);
        TextView tvTitulo2 = findViewById(R.id.tvTitulo2);
        TextView tvDescripcion2 = findViewById(R.id.tvDescripcion2);
        TextView tvPrecioSegundoArt = findViewById(R.id.tvPrecioSegundoArt);

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
        btnSubirProducto = (Button) findViewById(R.id.btnSubirProducto);
        btnSubirProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this, UploadProductActivity.class);
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
        String artista = extras.getString("Nombre");
        articulos = dbHelper.obtenerArticulosPorArtista(artista);

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewArticulos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        articuloAdapter = new ArticuloAdapter(this, articulos.subList(2, articulos.size())); // Excluimos los dos primeros artículos
        recyclerView.setAdapter(articuloAdapter);

        //Poner las imagenes por defecto correspondientes al artista
        if (artista.equals("BlackPink")){
            mediaPlayer = MediaPlayer.create(this, R.raw.blackpink);

            ImageView encabezado = findViewById(R.id.encabezado);
            encabezado.setImageDrawable(getDrawable(R.drawable.encabezado));

            ImageView ivPrimerArtImage1 = findViewById(R.id.ivPrimerArtImage1);
            ivPrimerArtImage1.setImageDrawable(getDrawable(R.drawable.camiseta));

            ImageView ivPrimerArtImage2 = findViewById(R.id.ivPrimerArtImage2);
            ivPrimerArtImage2.setImageDrawable(getDrawable(R.drawable.camiseta_blackpink));

            ImageView ivSegundoArtImage1 = findViewById(R.id.ivSegundoArtImage1);
            ivSegundoArtImage1.setImageDrawable(getDrawable(R.drawable.funkos));

            ImageView ivSegundoArtImage2 = findViewById(R.id.ivSegundoArtImage2);
            ivSegundoArtImage2.setImageDrawable(getDrawable(R.drawable.funko1));

            ImageView ivSegundoArtImage3 = findViewById(R.id.ivSegundoArtImage3);
            ivSegundoArtImage3.setImageDrawable(getDrawable(R.drawable.funko2));

            ImageView ivSegundoArtImage4 = findViewById(R.id.ivSegundoArtImage4);
            ivSegundoArtImage4.setImageDrawable(getDrawable(R.drawable.funko3));

            ImageView ivSegundoArtImage5 = findViewById(R.id.ivSegundoArtImage5);
            ivSegundoArtImage5.setImageDrawable(getDrawable(R.drawable.funko4));

        } else if (artista.equals("Adele")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.adele);

            ImageView encabezado = findViewById(R.id.encabezado);
            encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_adele));

            ImageView ivPrimerArtImage1 = findViewById(R.id.ivPrimerArtImage1);
            ivPrimerArtImage1.setImageDrawable(getDrawable(R.drawable.vinilo_adele));

            ImageView ivSegundoArtImage1 = findViewById(R.id.ivSegundoArtImage1);
            ivSegundoArtImage1.setImageDrawable(getDrawable(R.drawable.poster_adele));

        } else if (artista.equals("Eminem")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.eminem);

            ImageView encabezado = findViewById(R.id.encabezado);
            encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_eminem));

            ImageView ivPrimerArtImage1 = findViewById(R.id.ivPrimerArtImage1);
            ivPrimerArtImage1.setImageDrawable(getDrawable(R.drawable.sudadera_eminem));

            ImageView ivSegundoArtImage1 = findViewById(R.id.ivSegundoArtImage1);
            ivSegundoArtImage1.setImageDrawable(getDrawable(R.drawable.funda_eminem));

        } else if (artista.equals("Bruno Mars")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.bruno_mars);

            ImageView encabezado = findViewById(R.id.encabezado);
            encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_bruno));

            ImageView ivPrimerArtImage1 = findViewById(R.id.ivPrimerArtImage1);
            ivPrimerArtImage1.setImageDrawable(getDrawable(R.drawable.camiseta_bruno));

            ImageView ivSegundoArtImage1 = findViewById(R.id.ivSegundoArtImage1);
            ivSegundoArtImage1.setImageDrawable(getDrawable(R.drawable.gorra_bruno));

        } else if (artista.equals("Harry Styles")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.harry_styles);

            ImageView encabezado = findViewById(R.id.encabezado);
            encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_harry));

            ImageView ivPrimerArtImage1 = findViewById(R.id.ivPrimerArtImage1);
            ivPrimerArtImage1.setImageDrawable(getDrawable(R.drawable.camiseta_harry));

            ImageView ivSegundoArtImage1 = findViewById(R.id.ivSegundoArtImage1);
            ivSegundoArtImage1.setImageDrawable(getDrawable(R.drawable.llavero_harry));

        } else if (artista.equals("Fito")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.fito);

            ImageView encabezado = findViewById(R.id.encabezado);
            encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_fito));

            ImageView ivPrimerArtImage1 = findViewById(R.id.ivPrimerArtImage1);
            ivPrimerArtImage1.setImageDrawable(getDrawable(R.drawable.camiseta_fito));

            ImageView ivSegundoArtImage1 = findViewById(R.id.ivSegundoArtImage1);
            ivSegundoArtImage1.setImageDrawable(getDrawable(R.drawable.pulsera_fito));

        } else if (artista.equals("IZ*ONE")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.iz_one);

            ImageView encabezado = findViewById(R.id.encabezado);
            encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_izone));

            ImageView ivPrimerArtImage1 = findViewById(R.id.ivPrimerArtImage1);
            ivPrimerArtImage1.setImageDrawable(getDrawable(R.drawable.camiseta_izone));

            ImageView ivSegundoArtImage1 = findViewById(R.id.ivSegundoArtImage1);
            ivSegundoArtImage1.setImageDrawable(getDrawable(R.drawable.llavero_izone));

        } else if (artista.equals("StrayKids")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.straykids);

            ImageView encabezado = findViewById(R.id.encabezado);
            encabezado.setImageDrawable(getDrawable(R.drawable.encabezado_straykids));

            ImageView ivPrimerArtImage1 = findViewById(R.id.ivPrimerArtImage1);
            ivPrimerArtImage1.setImageDrawable(getDrawable(R.drawable.album_straykids));

            ImageView ivSegundoArtImage1 = findViewById(R.id.ivSegundoArtImage1);
            ivSegundoArtImage1.setImageDrawable(getDrawable(R.drawable.peluche_straykids));
        } else {
            Log.d("ArtistaCheck", "El artista no es existe");
        }

        // Obetener los datos de la base de datos
        if (!articulos.isEmpty()) {
            Articulo primerArt = articulos.get(0);
            tvTipo.setText(primerArt.getTipo().name());
            tvTitulo.setText(primerArt.getTitulo());
            tvDescripcion.setText(primerArt.getDescripcion());
            tvPrecioPrimerArt.setText(String.valueOf(primerArt.getPrecio()));

            Articulo segundoArt = articulos.get(1);
            tvTipo2.setText(segundoArt.getTipo().name());
            tvTitulo2.setText(segundoArt.getTitulo());
            tvDescripcion2.setText(segundoArt.getDescripcion());
            tvPrecioSegundoArt.setText(String.valueOf(segundoArt.getPrecio()));
        }
    }
}