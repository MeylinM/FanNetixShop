package com.example.fannetixshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuArtistActivity extends AppCompatActivity {

    private ImageButton btnBlack, btnAdele, btnEminem, btnBruno, btnHarry, btnFito, btnIZ, btnStray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_artist);

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
    }
}