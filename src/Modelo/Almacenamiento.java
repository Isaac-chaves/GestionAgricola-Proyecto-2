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
    // **CORRECCIÓN 1:** Agregar la variable 'tipo'.
    private String tipo; 
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
    
    // **CORRECCIÓN 2:** Agregar el getter para 'tipo'.
    public String getTipo() {
        return tipo;
    }

    public List<ProductoAlmacenado> getProductos() {
        return productos;
    }

    public void setCapacidadKg(double capacidadKg) {
        this.capacidadKg = capacidadKg;
    }
    
    // **CORRECCIÓN 3:** Agregar el setter para 'tipo'.
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setProductos(List<ProductoAlmacenado> productos) {
        this.productos = productos;
    }

    // **CORRECCIÓN 4:** Actualizar el constructor para incluir 'tipo'.
    // Notar que el constructor anterior era (id, nombre, capacidadKg).
    // Ahora es (id, nombre, capacidadKg, tipo).
    public Almacenamiento(int id, String nombre, double capacidadKg, String tipo) {
        this.idAlmacen = id;
        this.nombre = nombre;
        this.capacidadKg = capacidadKg;
        this.tipo = tipo; // Asignación del nuevo campo
        this.productos = new ArrayList<>();
    }
    
    // Si necesitas el constructor sin ID (para inserción inicial)
    public Almacenamiento(String nombre, double capacidadKg, String tipo) {
        this.nombre = nombre;
        this.capacidadKg = capacidadKg;
        this.tipo = tipo; 
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
        return nombre + " (" + tipo + ", " + productos.size() + " productos, capacidad " + capacidadKg + " kg)";
    }
}