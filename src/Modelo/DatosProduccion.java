/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author isaac
 */
public class DatosProduccion {

    private int id;
    private Cultivo cultivo;
    private LocalDate fechaCosecha;
    private int cantidadRecolectadaKg;
    private int Calidad;
    private String destino;

    public DatosProduccion(int id, Cultivo cultivo, LocalDate fechaCosecha, int cantidadRecolectadaKg, int calidadProductoFinalKg, String destino) {
        this.id = id;
        this.cultivo = cultivo;
        this.fechaCosecha = fechaCosecha;
        this.cantidadRecolectadaKg = cantidadRecolectadaKg;
        this.Calidad = calidadProductoFinalKg;
        this.destino = destino;
    }
    
    public int getId() {return id;}
    public Cultivo getCultivo() {return cultivo;}
    public LocalDate getFechaCosecha() {return fechaCosecha;}
    public int getCantidadRecolectadaKg() {return cantidadRecolectadaKg;}
    public int getCalidad() {return Calidad;}
    public String getDestino() {return destino;}
    public void setId(int id) {this.id = id;}
    public void setCultivo(Cultivo cultivo) {this.cultivo = cultivo;}
    public void setFechaCosecha(LocalDate fechaCosecha) {this.fechaCosecha = fechaCosecha;}
    public void setCantidadRecolectadaKg(int cantidadRecolectadaKg) {this.cantidadRecolectadaKg = cantidadRecolectadaKg;}
    public void setCalidad(int Calidad) {this.Calidad = Calidad;}
    public void setDestino(String destino) {this.destino = destino;}

    public double PorcentajeProductividad() {
        if (cantidadRecolectadaKg > 0) {
         return ((double) Calidad / cantidadRecolectadaKg) * 100.0;    
        }
        return 0;
    }
}
