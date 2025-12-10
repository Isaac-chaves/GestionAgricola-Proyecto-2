package Modelo.Service;

import Modelo.Cultivo;
import Modelo.DatosProduccion; 
import Modelo.Dao.ProduccionDAO; 
import Modelo.Dao.CultivoDAO; 
import Modelo.Dto.ProduccionDTO;
import Modelo.Mapper.ProduccionMapper; 
import java.util.List;

/**
 * Capa de servicio para la gestión de datos de producción (cosechas).
 */
public class ProduccionService {

    private final ProduccionDAO produccionDAO = new ProduccionDAO();
    private final CultivoDAO cultivoDAO = new CultivoDAO(); 

    public List<ProduccionDTO> obtenerTodasLasProducciones() {
        // El DAO ya devuelve las entidades DatosProduccion con el Cultivo completamente cargado.
        List<DatosProduccion> entidades = produccionDAO.seleccionarTodos(); 
        
        // Se elimina la lógica de re-carga de Cultivo que era redundante.
        
        return ProduccionMapper.toDTOList(entidades);
    }

    public ProduccionDTO obtenerProduccionPorId(int id) {
        // El DAO ya devuelve la entidad DatosProduccion con el Cultivo completamente cargado.
        DatosProduccion entidad = produccionDAO.seleccionarPorId(id); 

        // Se elimina la lógica de re-carga de Cultivo que era redundante.
        
        return ProduccionMapper.toDTO(entidad);
    }

    public boolean guardarProduccion(ProduccionDTO produccionDTO) {
        // Es necesario cargar el Cultivo aquí para asegurar que el ID es válido
        // y pasarlo al Mapper que crea la entidad completa (DatosProduccion).
        Cultivo cultivoEntidad = cultivoDAO.seleccionarPorId(produccionDTO.getIdCultivo()); 
        
        if (cultivoEntidad == null) {
            System.err.println("Error: Cultivo asociado no encontrado al guardar.");
            return false;
        }
        
        DatosProduccion nuevaProduccion = ProduccionMapper.toEntidad(produccionDTO, cultivoEntidad); 
        return produccionDAO.insertar(nuevaProduccion); 
    }

    public boolean actualizarProduccion(ProduccionDTO produccionDTO) {
        // Es necesario cargar el Cultivo aquí para asegurar que el ID es válido
        // y pasarlo al Mapper que crea la entidad completa (DatosProduccion).
        Cultivo cultivoEntidad = cultivoDAO.seleccionarPorId(produccionDTO.getIdCultivo());
        
        if (cultivoEntidad == null) {
            System.err.println("Error: Cultivo asociado no encontrado al actualizar.");
            return false;
        }

        DatosProduccion produccionActualizada = ProduccionMapper.toEntidad(produccionDTO, cultivoEntidad); 
        return produccionDAO.actualizar(produccionActualizada); 
    }

    public boolean eliminarProduccion(int id) {
        return produccionDAO.eliminar(id);
    }
}