package Modelo.Dto;


/**
 *
 * @author isaac
 */
public class DistribucionDTO {

    private int idDistribucion;
    private int idProductoAlmacenado; 
    private double cantidad;
    private String destino;
    private String fechaDistribucion;
    private int idTrabajadorResponsable; 

    public DistribucionDTO() {
    }

    public DistribucionDTO(int idDistribucion, int idProductoAlmacenado, double cantidad, String destino, String fechaDistribucion, int idTrabajadorResponsable) {
        this.idDistribucion = idDistribucion;
        this.idProductoAlmacenado = idProductoAlmacenado;
        this.cantidad = cantidad;
        this.destino = destino;
        this.fechaDistribucion = fechaDistribucion;
        this.idTrabajadorResponsable = idTrabajadorResponsable;
    }

    // --- Getters y Setters ---

    public int getIdDistribucion() {
        return idDistribucion;
    }

    public void setIdDistribucion(int idDistribucion) {
        this.idDistribucion = idDistribucion;
    }

    public int getIdProductoAlmacenado() {
        return idProductoAlmacenado;
    }

    public void setIdProductoAlmacenado(int idProductoAlmacenado) {
        this.idProductoAlmacenado = idProductoAlmacenado;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFechaDistribucion() {
        return fechaDistribucion;
    }

    public void setFechaDistribucion(String fechaDistribucion) {
        this.fechaDistribucion = fechaDistribucion;
    }

    public int getIdTrabajadorResponsable() {
        return idTrabajadorResponsable;
    }

    public void setIdTrabajadorResponsable(int idTrabajadorResponsable) {
        this.idTrabajadorResponsable = idTrabajadorResponsable;
    }
}