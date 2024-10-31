package com.example.fannetixshop;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FanNetixShop.db";
    private static final int DATABASE_VERSION = 1;

    // SQL para crear las tablas
    private static final String CREATE_TABLE_USUARIOS = "CREATE TABLE Usuarios ("
            + "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nombre TEXT NOT NULL,"
            + "email TEXT NOT NULL UNIQUE,"
            + "contrasena TEXT NOT NULL);";

    private static final String CREATE_TABLE_ARTISTAS = "CREATE TABLE Artistas ("
            + "id_artista INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nombre TEXT NOT NULL);";

    private static final String CREATE_TABLE_ARTICULOS = "CREATE TABLE Articulos ("
            + "id_articulo INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "id_artista INTEGER,"
            + "titulo TEXT NOT NULL,"
            + "descripcion TEXT,"
            + "tipo TEXT,"
            + "observaciones TEXT,"
            + "precio REAL NOT NULL,"
            + "FOREIGN KEY (id_artista) REFERENCES Artistas(id_artista));";

    private static final String CREATE_TABLE_ARCHIVOS_MULTIMEDIA = "CREATE TABLE ArchivosMultimedia ("
            + "id_archivo INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "id_articulo INTEGER,"
            + "tipo TEXT," // 'imagen' o 'video'
            + "url TEXT NOT NULL,"
            + "FOREIGN KEY (id_articulo) REFERENCES Articulos(id_articulo));";

    private static final String CREATE_TABLE_ARTICULOS_CARRITO = "CREATE TABLE ArticulosCarrito ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "id_usuario INTEGER,"
            + "id_articulo INTEGER,"
            + "FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),"
            + "FOREIGN KEY (id_articulo) REFERENCES Articulos(id_articulo),"
            + "UNIQUE (id_usuario, id_articulo));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIOS);
        db.execSQL(CREATE_TABLE_ARTISTAS);
        db.execSQL(CREATE_TABLE_ARTICULOS);
        db.execSQL(CREATE_TABLE_ARCHIVOS_MULTIMEDIA);
        db.execSQL(CREATE_TABLE_ARTICULOS_CARRITO);
        insertarArtistas(db); // Llamamos al método para insertar artistas iniciales
        insertarArticulos(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ArticulosCarrito");
        db.execSQL("DROP TABLE IF EXISTS ArchivosMultimedia");
        db.execSQL("DROP TABLE IF EXISTS Articulos");
        db.execSQL("DROP TABLE IF EXISTS Artistas");
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        onCreate(db);
    }

    // Método para insertar algunos artistas
    private void insertarArtistas(SQLiteDatabase db) {
        db.execSQL("INSERT INTO Artistas (nombre) VALUES ('Bruno Mars');");
        db.execSQL("INSERT INTO Artistas (nombre) VALUES ('BlackPink');");
        db.execSQL("INSERT INTO Artistas (nombre) VALUES ('Eminem');");
        db.execSQL("INSERT INTO Artistas (nombre) VALUES ('StrayKids');");
        db.execSQL("INSERT INTO Artistas (nombre) VALUES ('Harry Styles');");
        db.execSQL("INSERT INTO Artistas (nombre) VALUES ('IZ*ONE');");
        db.execSQL("INSERT INTO Artistas (nombre) VALUES ('Adele');");
        db.execSQL("INSERT INTO Artistas (nombre) VALUES ('Fito');");
    }

    // Método para insertar algunos artículos
    private void insertarArticulos(SQLiteDatabase db) {
        // Artículos de Bruno Mars
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, observaciones, precio) VALUES (1, 'Album 1', 'Descripción del álbum 1 de Blackpink', 'Original', 'Lanzamiento en 2020', 15.99);");
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, observaciones, precio) VALUES (1, 'Merchandising 1', 'Camiseta oficial de Blackpink', 'Original', 'Talla M', 25.00);");

        // Artículos de BlackPink
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, observaciones, precio) VALUES (2, 'Album 1', 'Descripción del álbum 1 de Mamamoo', 'Original', 'Lanzamiento en 2021', 14.99);");
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, observaciones, precio) VALUES (2, 'Merchandising 1', 'Taza oficial de Mamamoo', 'Fanmade', 'Capacidad 350ml', 10.50);");

        // Artículos de Eminem
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, observaciones, precio) VALUES (3, 'Album 1', 'Descripción del álbum 1 de IZ*ONE', 'Original', 'Lanzamiento en 2020', 16.99);");
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, observaciones, precio) VALUES (3, 'Merchandising 1', 'Poster oficial de IZ*ONE', 'Fanmade', 'Tamaño A3', 7.00);");

        // Artículos de StrayKids
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, observaciones, precio) VALUES (4, 'Album 1', 'Descripción del álbum 1 de StrayKids', 'Original', 'Lanzamiento en 2022', 18.99);");
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, observaciones, precio) VALUES (4, 'Merchandising 1', 'Gorra oficial de StrayKids', 'Fanmade', 'Color negro', 20.00);");
    }

    public List<Articulo> obtenerArticulosPorArtista(String nombreArtista) {
        List<Articulo> articulos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT a.titulo, a.descripcion, a.tipo, a.observaciones, a.precio " +
                        "FROM Articulos a INNER JOIN Artistas ar ON a.id_artista = ar.id_artista WHERE ar.nombre = ?",
                new String[]{nombreArtista});

        while (cursor.moveToNext()) {
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
            String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));
            String tipoStr = cursor.getString(cursor.getColumnIndexOrThrow("tipo"));
            Tipo tipo = Tipo.valueOf(tipoStr.toUpperCase()); // Convierte a Tipo enum
            String observaciones = cursor.getString(cursor.getColumnIndexOrThrow("observaciones"));
            double precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio"));

            articulos.add(new Articulo(titulo, descripcion, tipo, observaciones, precio));
        }

        cursor.close();
        return articulos;
    }


}

