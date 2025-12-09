/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dto;

 /**
 *
 * @author isaac
 */
public class ProduccionDTO {

    private int idProduccion;
    private int idCultivo; 
    private String nombreCultivo; 
    private String fechaCosecha;
    private int cantidadRecolectadaKg;
    private int calidad;
    private String destino;

    public ProduccionDTO() {
    }

    public ProduccionDTO(int idProduccion, int idCultivo, String nombreCultivo, String fechaCosecha, int cantidadRecolectadaKg, int calidad, String destino) {
        this.idProduccion = idProduccion;
        this.idCultivo = idCultivo;
        this.nombreCultivo = nombreCultivo;
        this.fechaCosecha = fechaCosecha;
        this.cantidadRecolectadaKg = cantidadRecolectadaKg;
        this.calidad = calidad;
        this.destino = destino;
    }

    public int getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(int idProduccion) {
        this.idProduccion = idProduccion;
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

    public String getFechaCosecha() {
        return fechaCosecha;
    }

    public void setFechaCosecha(String fechaCosecha) {
        this.fechaCosecha = fechaCosecha;
    }

    public int getCantidadRecolectadaKg() {
        return cantidadRecolectadaKg;
    }

    public void setCantidadRecolectadaKg(int cantidadRecolectadaKg) {
        this.cantidadRecolectadaKg = cantidadRecolectadaKg;
    }

    public int getCalidad() {
        return calidad;
    }

    public void setCalidad(int calidad) {
        this.calidad = calidad;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}