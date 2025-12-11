package Modelo.Service;

import Modelo.Usuarios.Trabajador; 
import Modelo.Dao.TrabajadorDAO; 
import Modelo.Dto.TrabajadorDTO; 
import Modelo.Mapper.TrabajadorMapper; 
import java.util.List;

public class TrabajadorService {

    private final TrabajadorDAO trabajadorDAO = new TrabajadorDAO(); 

    public List<TrabajadorDTO> obtenerTodosLosTrabajadores() {
        List<Trabajador> entidades = trabajadorDAO.seleccionarTodos(); 
        return TrabajadorMapper.toDTOList(entidades); 
    }

    // CORRECCIÓN: Ahora acepta String (VARCHAR de la BD)
    public String obtenerNombrePorCedula(String cedula) {
        try {
            // Asumiendo que trabajadorDAO.seleccionarPorCedula recibe String
            Trabajador entidad = trabajadorDAO.seleccionarPorCedula(cedula); 
            
            if (entidad != null) {
                return entidad.getNombre();
            } else {
                return "Responsable Desconocido (Cédula: " + cedula + ")";
            }
        } catch (Exception e) {
            System.err.println("Error en TrabajadorService.obtenerNombrePorCedula: " + e.getMessage());
            return "Error al buscar Responsable";
        }
    }

    public boolean guardarTrabajador(TrabajadorDTO trabajadorDTO) {
        if (trabajadorDTO.getSalario() < 0) {
            System.err.println("El salario no puede ser negativo.");
            return false;
        }
        
        // Se asume que el DAO también usa String para la cédula.
        Trabajador nuevoTrabajador = TrabajadorMapper.toEntidad(trabajadorDTO); 
        return trabajadorDAO.insertar(nuevoTrabajador); 
    }

    public boolean actualizarTrabajador(TrabajadorDTO trabajadorDTO) {
        Trabajador trabajadorActualizado = TrabajadorMapper.toEntidad(trabajadorDTO); 
        return trabajadorDAO.actualizar(trabajadorActualizado); 
    }

    // CORRECCIÓN: Ahora acepta String
    public boolean eliminarTrabajador(String cedula) {
        // Asumiendo que trabajadorDAO.eliminar recibe String
        return trabajadorDAO.eliminar(cedula); 
    }

    /**
     * Comprueba si ya existe un trabajador con la cédula dada.
     * @param cedula cédula a buscar
     * @return true si existe, false si no existe o en caso de error
     */
    public boolean existePorCedula(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            return false;
        }
        try {
            Trabajador entidad = trabajadorDAO.seleccionarPorCedula(cedula.trim());
            return entidad != null;
        } catch (Exception e) {
            System.err.println("Error en TrabajadorService.existePorCedula: " + e.getMessage());
            return false;
        }
    }
}