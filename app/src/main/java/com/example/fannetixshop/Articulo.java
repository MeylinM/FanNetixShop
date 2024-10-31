package com.example.fannetixshop;

public class Articulo {
    private String titulo;
    private String descripcion;
    private Tipo tipo; // 'fanmade' o 'original'
    private String observaciones; // Por ejemplo, un campo adicional
    private double precio; // Por ejemplo, el precio del artículo

    // Constructor actualizado
    public Articulo(String titulo, String descripcion, Tipo tipo, String otraInformacion, double precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.observaciones = otraInformacion; // Campo adicional
        this.precio = precio; // Precio del artículo
    }

    public Articulo(String titulo, String descripcion, String observaciones, double precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.observaciones = observaciones; // Campo adicional
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

    public String getOtraInformacion() {
        return observaciones; // Getter para el campo adicional
    }

    public double getPrecio() {
        return precio; // Getter para el precio
    }
}
