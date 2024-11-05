package com.example.fannetixshop;

public class Articulo {
    private String titulo;
    private String descripcion;
    private Tipo tipo; // 'fanmade' o 'original'
    private double precio; // Precio del artículo

    // Constructor actualizado sin observaciones
    public Articulo(String titulo, String descripcion, Tipo tipo, double precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.precio = precio; // Precio del artículo
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
}
