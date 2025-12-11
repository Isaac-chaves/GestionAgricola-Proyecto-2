package Controlador;


/**
 * Constantes centralizadas para notificaciones de ObservadorManager.
 * Usar estas constantes evita errores por cadenas literales inconsistentes.
 */
public final class ObservadorTipos {
    public static final String CULTIVOS = "CULTIVOS";
    public static final String TRABAJADORES = "TRABAJADORES";
    public static final String PRODUCTOALMACENADO = "PRODUCTOALMACENADO";
    public static final String ALMACENAMIENTO = "ALMACENAMIENTO";
    public static final String DISTRIBUCION = "DISTRIBUCION";

    private ObservadorTipos() {
        // No instanciable
    }
}