package Modelo.Dto;

public class DistribucionDTO {

    // CAMPOS DE LA CLASE
    private int idDistribucion;
    private int idProductoAlmacenado; 
    private double cantidad;
    private String destino;
    private String fechaDistribucion;
    private String idTrabajadorResponsable; 
    
    // CONSTRUCTOR VACÍO
    public DistribucionDTO() {
    }

    // CONSTRUCTOR PARA INSERCIÓN (SIN ID) - 5 PARÁMETROS
    // Este constructor lo usa DistribucionMapper.toEntidadSinId
    public DistribucionDTO(int idProductoAlmacenado, double cantidad, String destino, String fechaDistribucion, String idTrabajadorResponsable) {
        this.idProductoAlmacenado = idProductoAlmacenado;
        this.cantidad = cantidad;
        this.destino = destino;
        this.fechaDistribucion = fechaDistribucion;
        this.idTrabajadorResponsable = idTrabajadorResponsable;
    }
    
    // CONSTRUCTOR COMPLETO (CON ID) - 6 PARÁMETROS
    // Este constructor lo usa DistribucionMapper.toDTO y DistribucionMapper.toEntidad
    public DistribucionDTO(int idDistribucion, int idProductoAlmacenado, double cantidad, String destino, String fechaDistribucion, String idTrabajadorResponsable) {
        this.idDistribucion = idDistribucion;
        this.idProductoAlmacenado = idProductoAlmacenado;
        this.cantidad = cantidad;
        this.destino = destino;
        this.fechaDistribucion = fechaDistribucion;
        this.idTrabajadorResponsable = idTrabajadorResponsable;
    }

    // GETTERS Y SETTERS
    
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

    public String getIdTrabajadorResponsable() {
        return idTrabajadorResponsable;
    }

    public void setIdTrabajadorResponsable(String idTrabajadorResponsable) {
        this.idTrabajadorResponsable = idTrabajadorResponsable;
    }
}