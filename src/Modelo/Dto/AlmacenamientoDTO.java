package Modelo.Dto;

public class AlmacenamientoDTO {

    private int idAlmacen;
    private String nombre;
    private double capacidadKg;
    private String tipo; // AGREGADO

    public AlmacenamientoDTO() {
    }

    // CONSTRUCTOR PARA NUEVOS REGISTROS (SIN ID)
    public AlmacenamientoDTO(String nombre, double capacidadKg, String tipo) {
        this.nombre = nombre;
        this.capacidadKg = capacidadKg;
        this.tipo = tipo;
    }

    // CONSTRUCTOR COMPLETO (CON ID)
    public AlmacenamientoDTO(int idAlmacen, String nombre, double capacidadKg, String tipo) {
        this.idAlmacen = idAlmacen;
        this.nombre = nombre;
        this.capacidadKg = capacidadKg;
        this.tipo = tipo;
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
    
    // GETTER Y SETTER PARA TIPO (AGREGADO)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}