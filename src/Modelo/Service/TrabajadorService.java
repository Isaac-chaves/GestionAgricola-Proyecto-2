package Modelo.Service;


import Modelo.Usuarios.Trabajador; //
import Modelo.Dao.TrabajadorDAO; //
import Modelo.Dto.TrabajadorDTO; //
import Modelo.Mapper.TrabajadorMapper; //
import java.util.List;

public class TrabajadorService {

    private final TrabajadorDAO trabajadorDAO = new TrabajadorDAO(); 

    public List<TrabajadorDTO> obtenerTodosLosTrabajadores() {
        List<Trabajador> entidades = trabajadorDAO.seleccionarTodos(); 
        return TrabajadorMapper.toDTOList(entidades); 
    }

  public String obtenerNombrePorCedula(int cedula) {
        try {
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
        
        Trabajador nuevoTrabajador = TrabajadorMapper.toEntidad(trabajadorDTO); 
        return trabajadorDAO.insertar(nuevoTrabajador); 
    }

    public boolean actualizarTrabajador(TrabajadorDTO trabajadorDTO) {
        Trabajador trabajadorActualizado = TrabajadorMapper.toEntidad(trabajadorDTO); 
        return trabajadorDAO.actualizar(trabajadorActualizado); 
    }

    public boolean eliminarTrabajador(int cedula) {
        return trabajadorDAO.eliminar(cedula); 
    }
}