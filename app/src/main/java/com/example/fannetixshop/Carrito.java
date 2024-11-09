package com.example.fannetixshop;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    // Lista que contiene los artículos del carrito
    private List<Articulo> articulos;

    // Constructor
    public Carrito() {
        this.articulos = new ArrayList<>();
    }

    // Método para añadir un artículo al carrito
    public void añadirArticulo(Articulo articulo) {
        articulos.add(articulo);
    }

    // Método para calcular el total del carrito
    public double calcularTotal() {
        double total = 0;
        for (Articulo articulo : articulos) {
            total += articulo.getPrecio(); // Sumar el precio de cada artículo
        }
        return total;
    }

    // Obtener la lista de artículos
    public List<Articulo> getArticulos() {
        return articulos;
    }

    // Método para eliminar un artículo del carrito (por ejemplo, cuando el usuario lo elimina)
    public void eliminarArticulo(Articulo articulo) {
        articulos.remove(articulo);
    }

    // Método para limpiar el carrito (por ejemplo, después de una compra)
    public void vaciarCarrito() {
        articulos.clear();
    }
}
