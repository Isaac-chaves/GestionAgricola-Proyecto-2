package Controlador;


import java.util.List;
public interface EntityService<T> {
    List<T> obtenerTodos();
    boolean eliminar(int id);
    Object[] convertirAObjectArray(T entidad);
}