/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author UTN
 */
public class GestionAlmacen extends Almacenamiento {

    public GestionAlmacen(int id, String nombre, double capacidadKg) {
        super(id, nombre, capacidadKg);
    }

    public boolean agregarProducto(ProductoAlmacenado producto) {
        double total = productos.stream().mapToDouble(ProductoAlmacenado::getCantidadKg).sum();
        if (total + producto.getCantidadKg() <= capacidadKg) {
            productos.add(producto);
            return true;
        }
        return false;
    }

    public boolean Alerta(ProductoAlmacenado producto, int diasMaximo) {
        return producto.getFechaIngreso().plusDays(diasMaximo).isBefore(LocalDate.now());
    }

} 
