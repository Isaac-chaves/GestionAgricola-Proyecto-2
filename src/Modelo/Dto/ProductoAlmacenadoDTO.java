/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dto;

 /**
 *
 * @author isaac
 */
public class ProductoAlmacenadoDTO {

    private int idProductoAlmacenado;
    private int idAlmacen;
    private int idCultivo;
    private String nombreCultivo; 
    private double cantidadKg;
    private String fechaIngreso;
    private String fechaEgreso; 

    public ProductoAlmacenadoDTO() {
    }

    public ProductoAlmacenadoDTO(int idProductoAlmacenado, int idAlmacen, int idCultivo, String nombreCultivo, double cantidadKg, String fechaIngreso, String fechaEgreso) {
        this.idProductoAlmacenado = idProductoAlmacenado;
        this.idAlmacen = idAlmacen;
        this.idCultivo = idCultivo;
        this.nombreCultivo = nombreCultivo;
        this.cantidadKg = cantidadKg;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
    }


    public int getIdProductoAlmacenado() {
        return idProductoAlmacenado;
    }

    public void setIdProductoAlmacenado(int idProductoAlmacenado) {
        this.idProductoAlmacenado = idProductoAlmacenado;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public int getIdCultivo() {
        return idCultivo;
    }

    public void setIdCultivo(int idCultivo) {
        this.idCultivo = idCultivo;
    }

    public String getNombreCultivo() {
        return nombreCultivo;
    }

    public void setNombreCultivo(String nombreCultivo) {
        this.nombreCultivo = nombreCultivo;
    }

    public double getCantidadKg() {
        return cantidadKg;
    }

    public void setCantidadKg(double cantidadKg) {
        this.cantidadKg = cantidadKg;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(String fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }
}