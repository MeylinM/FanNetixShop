package com.example.fannetixshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FanNetixShop.db";
    private static final int DATABASE_VERSION = 1;

    // SQL para crear las tablas
    private static final String CREATE_TABLE_USUARIOS = "CREATE TABLE Usuarios ("
            + "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "email TEXT NOT NULL UNIQUE,"
            + "passwd TEXT NOT NULL);";

    private static final String CREATE_TABLE_ARTISTAS = "CREATE TABLE Artistas ("
            + "id_artista INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nombre TEXT NOT NULL);";

    private static final String CREATE_TABLE_ARTICULOS = "CREATE TABLE Articulos ("
            + "id_articulo INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "id_artista INTEGER,"
            + "titulo TEXT NOT NULL,"
            + "descripcion TEXT,"
            + "tipo TEXT,"
            + "precio REAL NOT NULL,"
            + "FOREIGN KEY (id_artista) REFERENCES Artistas(id_artista));";

    private static final String CREATE_TABLE_ARCHIVOS_MULTIMEDIA = "CREATE TABLE ArchivosMultimedia ("
            + "id_archivo INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "id_articulo INTEGER,"
            + "tipo TEXT," // 'imagen' o 'video'
            + "path TEXT NOT NULL,"
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
        insertarUsuario(db);
        insertarArtistas(db);
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

    private void insertarUsuario(SQLiteDatabase db){
        db.execSQL("INSERT INTO Usuarios (email, passwd) VALUES ('admin@gmail.com', 'abcd*1234');");
        db.execSQL("INSERT INTO Usuarios (email, passwd) VALUES ('elbire@gmail.com', 'abcd*1234');");
        db.execSQL("INSERT INTO Usuarios (email, passwd) VALUES ('irati@gmail.com', 'abcd*1234');");
        db.execSQL("INSERT INTO Usuarios (email, passwd) VALUES ('olaia@gmail.com', 'abcd*1234');");
        db.execSQL("INSERT INTO Usuarios (email, passwd) VALUES ('meylin@gmail.com', 'abcd*1234');");
    }

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

    private void insertarArticulos(SQLiteDatabase db) {
        //ARTICULOS BRUNO MARS
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, precio) VALUES (1, 'Hey Don’t Make This Weird Tee', 'Camiseta nueva, sin usar.', 'Original', 65.95);");
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, precio) VALUES (1, 'Bruno Mars Hat', 'Poco usado, como nuevo.', 'Original', 25.00);");
        //ARTICULOS BLACK PINK
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, precio) VALUES (2, 'Born Pink Hear Globe Black T-Shirt', 'Camiseta nueva, sin usar.', 'Original', 45.00);");
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, precio) VALUES (2, 'Funko Pop! BLACKPINK Pin Set', 'Sin abrir', 'Original', 30.50);");
        //ARTICULOS EMINEM
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, precio) VALUES (3, 'TDOSS In Loving Memory Hoodie', 'Sin usar', 'Original', 65.00);");
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, precio) VALUES (3, 'Paul Skit iPhone Case', 'Nuevo', 'Original', 27.00);");
        //ARTICULOS STRAYKIDS
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, precio) VALUES (4, 'MAXIDENT (T-CRUSH ver.)', 'Sin abrir', 'Original', 45.00);");
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, precio) VALUES (4, 'Stray Kids Plushie', 'Peluche de Bang Chan', 'Original', 20.00);");
        //ARTICULOS HARRY STYLES
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, precio) VALUES (5, 'Rainbow Event Tee', 'Tour de Munich', 'Original', 30.00);");
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, precio) VALUES (5, 'TPWK Logo Keychain', 'Llavero nuevo', 'Fanmade', 25.00);");
        //ARTICULOS IZ*ONE
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, precio) VALUES (6, 'T-Shirt Iz*One EYES ON ME', 'Camiseta original', 'Original', 29.99);");
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, precio) VALUES (6, 'Iz*One  Keychain', 'Llavero echo de resina con accesorios', 'Fanmade', 10.50);");
        //ARTICULOS ADELE
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, precio) VALUES (7, '30 (Double Vinyl)', 'Vinilo de Adele sin usar', 'Original', 30.00);");
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, precio) VALUES (7, 'Adele poster', 'Poster oficial de Adele', 'Fanmade', 7.00);");
        //ARTICULOS FITO
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, precio) VALUES (8, '“Huesos” Luminiscent T-shirt ', 'Camiseta sin usar del último concierto', 'Original', 24.95);");
        db.execSQL("INSERT INTO Articulos (id_artista, titulo, descripcion, tipo, precio) VALUES (8, '\"Cada vez cadáver\" Bracelet ', 'Pulsera de plata', 'Original', 40.00);");
    }
    private void insertarMultimediaPorDefecto(SQLiteDatabase db) {
        //ARCHIVOS BRUNO MARS
        db.execSQL("INSERT INTO ArchivosMultimedia (id_articulo,tipo, path) VALUES (1,'Imagen','camiseta_bruno');");
        db.execSQL("INSERT INTO ArchivosMultimedia (id_articulo,tipo, path) VALUES (2,'Imagen','gorra_bruno');");
        //ARCHIVOS BLACK PINK
        db.execSQL("INSERT INTO ArchivosMultimedia (id_articulo,tipo, path) VALUES (3,'Imagen','camiseta');");
        db.execSQL("INSERT INTO ArchivosMultimedia (id_articulo,tipo, path) VALUES (4,'Imagen','funkos');");
        //ARCHIVOS EMINEM
        db.execSQL("INSERT INTO ArchivosMultimedia (id_articulo,tipo, path) VALUES (5,'Imagen','sudadera_eminem');");
        db.execSQL("INSERT INTO ArchivosMultimedia (id_articulo,tipo, path) VALUES (6,'Imagen','funda_eminem');");
        //ARCHIVOS STRAYKIDS
        db.execSQL("INSERT INTO ArchivosMultimedia (id_articulo,tipo, path) VALUES (7,'Imagen','album_straykids');");
        db.execSQL("INSERT INTO ArchivosMultimedia (id_articulo,tipo, path) VALUES (8,'Imagen','peluche_straykids');");
        //ARCHIVOS HARRY STYLES
        db.execSQL("INSERT INTO ArchivosMultimedia (id_articulo,tipo, path) VALUES (9,'Imagen','camiseta_harry');");
        db.execSQL("INSERT INTO ArchivosMultimedia (id_articulo,tipo, path) VALUES (10,'Imagen','llavero_harry');");
        //ARCHIVOS IZ*ONE
        db.execSQL("INSERT INTO ArchivosMultimedia (id_articulo,tipo, path) VALUES (11,'Imagen','camiseta_izone');");
        db.execSQL("INSERT INTO ArchivosMultimedia (id_articulo,tipo, path) VALUES (12,'Imagen','llavero_izone');");
        //ARCHIVOS ADELE
        db.execSQL("INSERT INTO ArchivosMultimedia (id_articulo,tipo, path) VALUES (13,'Imagen','vinilo_adele');");
        db.execSQL("INSERT INTO ArchivosMultimedia (id_articulo,tipo, path) VALUES (14,'Imagen','poster_adele');");
        //ARCHIVOS FITO
        db.execSQL("INSERT INTO ArchivosMultimedia (id_articulo,tipo, path) VALUES (15,'Imagen','camiseta_fito');");
        db.execSQL("INSERT INTO ArchivosMultimedia (id_articulo,tipo, path) VALUES (16,'Imagen','pulsera_fito');");
    }

    public List<Articulo> obtenerArticulosPorArtista(String nombreArtista) {
        List<Articulo> articulos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Asegúrate de que 'nombreArtista' no esté vacío o nulo
        if (nombreArtista == null || nombreArtista.isEmpty()) {
            return articulos; // Devuelve una lista vacía si el nombre del artista es inválido
        }

        // Realizar la consulta
        Cursor cursor = db.rawQuery("SELECT a.titulo, a.descripcion, a.tipo, a.precio " +
                        "FROM Articulos a INNER JOIN Artistas ar ON a.id_artista = ar.id_artista WHERE ar.nombre = ?",
                new String[]{nombreArtista});

        // Revisar si hay resultados
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                    String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));
                    String tipoStr = cursor.getString(cursor.getColumnIndexOrThrow("tipo"));

                    // Asegúrate de que el tipo se pueda convertir
                    Tipo tipo;
                    try {
                        tipo = Tipo.valueOf(tipoStr.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        // Manejo de error si el tipo no es válido
                        tipo = null; // O asigna un valor por defecto
                    }

                    double precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio"));

                    articulos.add(new Articulo(titulo, descripcion, tipo, precio));
                } while (cursor.moveToNext());
            } else {
                Log.d("DatabaseHelper", "No se encontraron artículos para el artista: " + nombreArtista);
            }
            cursor.close();
        } else {
            Log.e("DatabaseHelper", "Cursor es nulo, error en la consulta");
        }
        return articulos;
    }


    public boolean validarUsuario(String email, String passwd) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean usuarioValido = false;

        // Consulta SQL para verificar el email y la contraseña
        Cursor cursor = db.rawQuery("SELECT * FROM Usuarios WHERE email = ? AND passwd = ?",
                new String[]{email, passwd});

        if (cursor.moveToFirst()) {
            usuarioValido = true; // Usuario encontrado
        }

        cursor.close();
        return usuarioValido;
    }

    public List<Artista> obtenerArtistas() {
        List<Artista> artistas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT id_artista, nombre FROM Artistas", null);

        if (cursor.moveToFirst()) {
            do {
                int idArtista = cursor.getInt(cursor.getColumnIndexOrThrow("id_artista"));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                artistas.add(new Artista(idArtista, nombre));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return artistas;
    }

    public boolean crearArticulo(Articulo articulo) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Crear un ContentValues para almacenar los valores del artículo
        ContentValues values = new ContentValues();
        values.put("id_artista", articulo.getIdArtista()); // Asegúrate de que el método getIdArtista() exista en la clase Articulo
        values.put("titulo", articulo.getTitulo());
        values.put("descripcion", articulo.getDescripcion());
        values.put("tipo", articulo.getTipo().toString()); // Convierte el tipo a String
        values.put("precio", articulo.getPrecio());

        // Insertar el artículo en la tabla y obtener el ID del artículo creado
        long newRowId = db.insert("Articulos", null, values);
        //ver si se agrega
        Log.d("DatabaseHelper", "ID del artículo insertado: " + newRowId);
        // Cerrar la base de datos
        db.close();

        // Retornar true si la inserción fue exitosa, false de lo contrario
        return newRowId != -1;
    }
    public List<Multimedia> obtenerMultimediaPorArticulo(String nombreArtista) {
        List<Multimedia> multimedia = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Asegúrate de que 'nombreArtista' no esté vacío o nulo
        if (nombreArtista == null || nombreArtista.isEmpty()) {
            return multimedia; // Devuelve una lista vacía si el nombre del artista es inválido
        }

        // Realizar la consulta
        Cursor cursor = db.rawQuery("SELECT m.id_archivo, m.id_articulo, m.tipo, m.path " +
                        "FROM ArchivosMultimedia m INNER JOIN Articulo ar ON m.id_articulo = ar.id_articulo INNER JOIN Artistas a ON ar.id_artista = a.id_artista WHERE a.nombre = ?",
                new String[]{nombreArtista});

        // Revisar si hay resultados
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int id_archivo = cursor.getInt(cursor.getColumnIndexOrThrow("id_archivo"));
                    int id_articulo = cursor.getInt(cursor.getColumnIndexOrThrow("id_articulo"));
                    String tipo = cursor.getString(cursor.getColumnIndexOrThrow("tipo"));
                    String path = cursor.getString(cursor.getColumnIndexOrThrow("path"));

                    multimedia.add(new Multimedia(id_archivo,id_articulo, tipo, path));
                } while (cursor.moveToNext());
            } else {
                Log.d("DatabaseHelper", "No se encontraron archivo multimedia para el artista: " + nombreArtista);
            }
            cursor.close();
        } else {
            Log.e("DatabaseHelper", "Cursor es nulo, error en la consulta");
        }
        return multimedia;
    }



}
