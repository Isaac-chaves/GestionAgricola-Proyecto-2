package Modelo;

import java.time.LocalDate;

/**
 *
 * @author UTN
 */
public class GestionAlmacen extends Almacenamiento {

    // **CORRECCIÓN:** Se añade el parámetro 'tipo' al constructor.
    // Llama al constructor de Almacenamiento con 4 parámetros.
    public GestionAlmacen(int id, String nombre, double capacidadKg, String tipo) {
        super(id, nombre, capacidadKg, tipo);
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