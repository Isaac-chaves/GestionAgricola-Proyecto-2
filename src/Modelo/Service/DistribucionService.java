package Modelo.Service;


import Modelo.Distribucion;
import Modelo.Dao.DistribucionDAO; // Asume que esta clase existe
import Modelo.Dto.DistribucionDTO;
import Modelo.Mapper.DistribucionMapper;
import java.util.List;
 /**
 *
 * @author isaac
 */
public class DistribucionService {

    private DistribucionDAO distribucionDAO; // Se asume que DistribucionDAO existe e implementa los métodos CRUD

    // Constructor que inicializa el DAO. Puedes adaptarlo si usas inyección de dependencias
    public DistribucionService() {
        this.distribucionDAO = new DistribucionDAO(); 
    }

    public List<DistribucionDTO> obtenerTodasLasDistribuciones() {
        // Se asume que DistribucionDAO tiene un método seleccionarTodos()
        List<Distribucion> entidades = distribucionDAO.seleccionarTodos(); 
        return DistribucionMapper.toDTOList(entidades);
    }

    public DistribucionDTO obtenerDistribucionPorId(int id) {
        // Se asume que DistribucionDAO tiene un método seleccionarPorId(int id)
        Distribucion entidad = distribucionDAO.seleccionarPorId(id); 
        return DistribucionMapper.toDTO(entidad);
    }

    public boolean guardarDistribucion(DistribucionDTO distribucionDTO) {
        Distribucion nuevaDistribucion = DistribucionMapper.toEntidad(distribucionDTO);
        // Se asume que DistribucionDAO tiene un método insertar(Distribucion distribucion)
        return distribucionDAO.insertar(nuevaDistribucion);
    }

    public boolean actualizarDistribucion(DistribucionDTO distribucionDTO) {
        Distribucion distribucionActualizada = DistribucionMapper.toEntidad(distribucionDTO);
        // Se asume que DistribucionDAO tiene un método actualizar(Distribucion distribucion)
        return distribucionDAO.actualizar(distribucionActualizada);
    }

    public boolean eliminarDistribucion(int id) {
        // Se asume que DistribucionDAO tiene un método eliminar(int id)
        return distribucionDAO.eliminar(id); 
    }
}