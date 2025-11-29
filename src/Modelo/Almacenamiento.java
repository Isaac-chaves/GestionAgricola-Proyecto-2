/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author UTN
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Almacenamiento {

    private int idAlmacen;
    private String nombre;
    protected double capacidadKg;
    protected List<ProductoAlmacenado> productos;

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCapacidadKg() {
        return capacidadKg;
    }

    public List<ProductoAlmacenado> getProductos() {
        return productos;
    }

    public void setCapacidadKg(double capacidadKg) {
        this.capacidadKg = capacidadKg;
    }

    public void setProductos(List<ProductoAlmacenado> productos) {
        this.productos = productos;
    }

    public Almacenamiento(int id, String nombre, double capacidadKg) {
        this.idAlmacen = id;
        this.nombre = nombre;
        this.capacidadKg = capacidadKg;
        this.productos = new ArrayList<>();
    }

    public boolean agregarProducto(ProductoAlmacenado producto) {
        double total = productos.stream().mapToDouble(ProductoAlmacenado::getCantidadKg).sum();
        if (total + producto.getCantidadKg() <= capacidadKg) {
            productos.add(producto);
            return true;
        }
        return false;
    }

    public boolean necesitaAlerta(ProductoAlmacenado producto, int diasMaximo) {
        return producto.getFechaIngreso().plusDays(diasMaximo).isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return nombre + " (" + productos.size() + " productos, capacidad " + capacidadKg + " kg)";
    }
} 
