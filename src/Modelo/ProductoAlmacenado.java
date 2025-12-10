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

public class ProductoAlmacenado {

    private int id;
    private Cultivo cultivo;
    private double cantidadKg;
    private LocalDate fechaIngreso;
    private LocalDate fechaEgreso;

    public int getId() {
        return id;
    }

    public Cultivo getCultivo() {
        return cultivo;
    }

    public double getCantidadKg() {
        return cantidadKg;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public LocalDate getFechaEgreso() {
        return fechaEgreso;
    }

    public void setCantidadKg(double cantidadKg) {
        this.cantidadKg = cantidadKg;
    }

    public void setCultivo(Cultivo cultivo) {
        this.cultivo = cultivo;
    }

    
    public ProductoAlmacenado(int id, Cultivo cultivo, double cantidadKg, LocalDate fechaIngreso, LocalDate fechaEgreso) {
        this.id = id;
        this.cultivo = cultivo;
        this.cantidadKg = cantidadKg;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
    }  
}
