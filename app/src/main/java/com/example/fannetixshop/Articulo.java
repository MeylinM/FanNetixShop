package com.example.fannetixshop;

public class Articulo {
    private int id;
    private String titulo;
    private String descripcion;
    private Tipo tipo; // 'fanmade' o 'original'
    private double precio; // Precio del artículo
    private int idArtista; // ID del artista
    private String path;
    private boolean seleccionado;

    // Constructor actualizado con idArtista
    public Articulo(String titulo, String descripcion, Tipo tipo, double precio, int idArtista, String path) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.precio = precio; // Precio del artículo
        this.idArtista = idArtista; // Asignar ID del artista
        this.path = path;
    }

    public Articulo(String titulo, String descripcion, Tipo tipo, double precio, String path) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.precio = precio; // Precio del artículo
        this.path = path;
    }

    public Articulo(int id, String titulo, String descripcion, Tipo tipo, double precio, String path) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.precio = precio; // Precio del artículo
        this.path = path;
        this.seleccionado = false;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio; // Getter para el precio
    }

    public int getIdArtista() {
        return idArtista; // Getter para el ID del artista
    }

    public String getPath(){
        return path;
    }

    public void setImagePath(String imagePath) {
        this.path = imagePath;
    }
    // Setter
    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}
