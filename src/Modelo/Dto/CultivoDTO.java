/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dto;


 /**
 *
 * @author isaac
 */
public class CultivoDTO {

    private int id;
    private String nombre;
    private String tipo;
    private int areaSembrada;
    private String estadoCrecimiento;
    private String fechaSiembra;
    private String fechaCosecha;

    public CultivoDTO() {
    }

    public CultivoDTO(int id, String nombre, String tipo, int areaSembrada, String estadoCrecimiento, String fechaSiembra, String fechaCosecha) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.areaSembrada = areaSembrada;
        this.estadoCrecimiento = estadoCrecimiento;
        this.fechaSiembra = fechaSiembra;
        this.fechaCosecha = fechaCosecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAreaSembrada() {
        return areaSembrada;
    }

    public void setAreaSembrada(int areaSembrada) {
        this.areaSembrada = areaSembrada;
    }

    public String getEstadoCrecimiento() {
        return estadoCrecimiento;
    }

    public void setEstadoCrecimiento(String estadoCrecimiento) {
        this.estadoCrecimiento = estadoCrecimiento;
    }

    public String getFechaSiembra() {
        return fechaSiembra;
    }

    public void setFechaSiembra(String fechaSiembra) {
        this.fechaSiembra = fechaSiembra;
    }

    public String getFechaCosecha() {
        return fechaCosecha;
    }

    public void setFechaCosecha(String fechaCosecha) {
        this.fechaCosecha = fechaCosecha;
    }
}