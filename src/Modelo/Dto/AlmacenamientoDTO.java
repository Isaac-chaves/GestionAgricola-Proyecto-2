/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dto;

 

 
public class AlmacenamientoDTO {

    private int idAlmacen;
    private String nombre;
    private double capacidadKg;

    public AlmacenamientoDTO() {
    }

    public AlmacenamientoDTO(int idAlmacen, String nombre, double capacidadKg) {
        this.idAlmacen = idAlmacen;
        this.nombre = nombre;
        this.capacidadKg = capacidadKg;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCapacidadKg() {
        return capacidadKg;
    }

    public void setCapacidadKg(double capacidadKg) {
        this.capacidadKg = capacidadKg;
    }
}