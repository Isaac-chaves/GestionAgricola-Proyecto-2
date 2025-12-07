/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Yefran
 */
public class Cultivo {

    private int id;
    private String nombre;
    private String tipo;
    private int areaSembrada;
    private String estadoCrecimiento;
    private String fechaSiembra;
    private String fechaCosecha;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public double getAreaSembrada() {
        return areaSembrada;
    }

    public String getEstadoCrecimiento() {
        return estadoCrecimiento;
    }

    public String getFechaSiembra() {
        return fechaSiembra;
    }

    public String getFechaCosecha() {
        return fechaCosecha;
    }

    public void setAreaSembrada(int areaSembrada) {
        this.areaSembrada = areaSembrada;//actualizar boton
    }

    public void setFechaCosecha(String fechaCosecha) {
        this.fechaCosecha = fechaCosecha;
    }
    //validacion lo que se meta en el editar sea un numero y en el otro una fecha 

    public Cultivo(int id, String nombre, String tipo, int areaSembrada,
            String estadoCrecimiento, String fechaSiembra, String fechaCosecha) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.areaSembrada = areaSembrada;
        this.estadoCrecimiento = estadoCrecimiento;
        this.fechaSiembra = fechaSiembra;
        this.fechaCosecha = fechaCosecha;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " (" + tipo + ")";
    }  
}  
