package com.example.fannetixshop;

import java.util.List;

public class Articulo {
    private int idArticulo;
    private String titulo;
    private String descripcion;
    private Tipo tipo; // 'fanmade' o 'original'
    private double precio; // Precio del artículo
    private int idArtista; // ID del artista
    private List<Multimedia> multimedia;

    // Constructor actualizado con idArtista
    public Articulo(String titulo, String descripcion, Tipo tipo, double precio, int idArtista) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.precio = precio; // Precio del artículo
        this.idArtista = idArtista; // Asignar ID del artista
    }
    //con el id del Articulo para usarlo al enlazar el contenido multimedia con el articulo en concreto
    public Articulo(int idArticulo,String titulo, String descripcion, Tipo tipo, double precio, int idArtista) {
        this.idArticulo = idArticulo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.precio = precio; // Precio del artículo
        this.idArtista = idArtista; // Asignar ID del artista
    }

    public Articulo(String titulo, String descripcion, Tipo tipo, double precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.precio = precio; // Precio del artículo
    }


    // Getters
    public int getIdArticulo() {
        return idArticulo; // Getter para el ID del articulo
    }

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

    // Setter
    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }
}
