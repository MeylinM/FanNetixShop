package com.example.fannetixshop;

public class Multimedia {
    private int idArchivo;
    private int idArticulo;
    private String tipo;
    private String pathM;

    public Multimedia(int idArchivo, int idArticulo, String tipo, String pathM) {
        this.idArchivo = idArchivo;
        this.idArticulo = idArticulo;
        this.tipo = tipo;
        this.pathM = pathM;
    }

    // Getters y Setters
    public int getIdArchivo() { return idArchivo; }
    public int getIdArticulo() { return idArticulo; }
    public String getTipo() { return tipo; }
    public String getPath() { return pathM; }
}

