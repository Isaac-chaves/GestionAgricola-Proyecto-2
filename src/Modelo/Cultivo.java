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

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setAreaSembrada(int areaSembrada) {
        this.areaSembrada = areaSembrada;
    }

    public void setEstadoCrecimiento(String estadoCrecimiento) {
        this.estadoCrecimiento = estadoCrecimiento;
    }

    public void setFechaSiembra(String fechaSiembra) {
        this.fechaSiembra = fechaSiembra;
    }

    public void setFechaCosecha(String fechaCosecha) {
        this.fechaCosecha = fechaCosecha;
    }

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
