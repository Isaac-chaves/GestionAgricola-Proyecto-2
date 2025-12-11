package Modelo.Dto;

/**
 *
 * @author isaac
 */
public class CultivoDTO {

    private int id;
    private String nombre;
    private String tipo;
    private double areaSembrada; // CORREGIDO: De int a double
    private String estadoCrecimiento;
    private String fechaSiembra;
    private String fechaCosecha;

    public CultivoDTO() {
    }

    // CONSTRUCTOR PARA NUEVOS REGISTROS (SIN ID)
    // Orden de campos (ajustado para ser más lógico): nombre, tipo, areaSembrada, estadoCrecimiento, fechaSiembra, fechaCosecha
    public CultivoDTO(String nombre, String tipo, double areaSembrada, String estadoCrecimiento, String fechaSiembra, String fechaCosecha) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.areaSembrada = areaSembrada;
        this.estadoCrecimiento = estadoCrecimiento;
        this.fechaSiembra = fechaSiembra;
        this.fechaCosecha = fechaCosecha;
    }

    // CONSTRUCTOR COMPLETO (CON ID)
    public CultivoDTO(int id, String nombre, String tipo, double areaSembrada, String estadoCrecimiento, String fechaSiembra, String fechaCosecha) {
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

    public double getAreaSembrada() { // CORREGIDO: Tipo de retorno a double
        return areaSembrada;
    }

    public void setAreaSembrada(double areaSembrada) { // CORREGIDO: Tipo de parámetro a double
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