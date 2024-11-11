package com.example.fannetixshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MenuArtistActivity extends AppCompatActivity {

    private ImageButton btnBlack, btnAdele, btnEminem, btnBruno, btnHarry, btnFito, btnIZ, btnStray;
    private Button btnSubirProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_artist);

        //Habilitar Edge to Edge
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );

        btnBlack = (ImageButton) findViewById(R.id.btnBlack);
        btnBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuArtistActivity.this, ShopActivity.class);
                intent.putExtra("Nombre", "BlackPink");
                startActivity(intent);
            }
        });
        btnAdele = (ImageButton) findViewById(R.id.btnAdele);
        btnAdele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuArtistActivity.this, ShopActivity.class);
                intent.putExtra("Nombre", "Adele");
                startActivity(intent);
            }
        });
        btnEminem = (ImageButton) findViewById(R.id.btnEminem);
        btnEminem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuArtistActivity.this, ShopActivity.class);
                intent.putExtra("Nombre", "Eminem");
                startActivity(intent);
            }
        });
        btnBruno = (ImageButton) findViewById(R.id.btnBruno);
        btnBruno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuArtistActivity.this, ShopActivity.class);
                intent.putExtra("Nombre", "Bruno Mars");
                startActivity(intent);
            }
        });
        btnHarry = (ImageButton) findViewById(R.id.btnHarry);
        btnHarry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuArtistActivity.this, ShopActivity.class);
                intent.putExtra("Nombre", "Harry Styles");
                startActivity(intent);
            }
        });
        btnFito = (ImageButton) findViewById(R.id.btnFito);
        btnFito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuArtistActivity.this, ShopActivity.class);
                intent.putExtra("Nombre", "Fito");
                startActivity(intent);
            }
        });
        btnIZ = (ImageButton) findViewById(R.id.btnIZ);
        btnIZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuArtistActivity.this, ShopActivity.class);
                intent.putExtra("Nombre", "IZ*ONE");
                startActivity(intent);
            }
        });
        btnStray = (ImageButton) findViewById(R.id.btnStray);
        btnStray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuArtistActivity.this, ShopActivity.class);
                intent.putExtra("Nombre", "StrayKids");
                startActivity(intent);
            }
        });
        btnSubirProducto = (Button) findViewById(R.id.btnSubirProducto);
        btnSubirProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuArtistActivity.this, UploadProductActivity.class);
                startActivity(intent);
            }
        });
    }
}