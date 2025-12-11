package Modelo.Service;

import Modelo.Almacenamiento; 
import Modelo.Dao.AlmacenamientoDAO; 
import Modelo.Dto.AlmacenamientoDTO; 
import Modelo.Mapper.AlmacenamientoMapper; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AlmacenamientoService {

    private final AlmacenamientoDAO almacenamientoDAO = new AlmacenamientoDAO(); 


   
    public List<AlmacenamientoDTO> obtenerTodosLosAlmacenes() {
        List<Almacenamiento> entidades = almacenamientoDAO.seleccionarTodos(); 
        return AlmacenamientoMapper.toDTOList(entidades); 
    }
    public Map<String, Integer> obtenerMapaAlmacenesParaCombo() {
        // 1. Obtener la lista de DTOs con la información completa
        List<AlmacenamientoDTO> almacenesDTO = obtenerTodosLosAlmacenes();
        
        // 2. Crear el mapa Nombre -> ID
        Map<String, Integer> mapaAlmacenes = new HashMap<>();
        
        for (AlmacenamientoDTO dto : almacenesDTO) {
            // Se usa el nombre como clave y el ID como valor
            mapaAlmacenes.put(dto.getNombre(), dto.getIdAlmacen());
        }
        
        return mapaAlmacenes;
    }
    public AlmacenamientoDTO obtenerAlmacenPorId(int id) {
        Almacenamiento entidad = almacenamientoDAO.seleccionarPorId(id); 
        return AlmacenamientoMapper.toDTO(entidad); 
    }
public int obtenerIdAlmacenPorNombre(String nombre) {
    Map<String, Integer> mapa = obtenerMapaAlmacenesParaCombo();
    return mapa.getOrDefault(nombre, -1); 
}
    public boolean guardarAlmacen(AlmacenamientoDTO almacenDTO) {
        if (almacenDTO.getCapacidadKg() <= 0) {
             System.err.println("La capacidad del almacén debe ser positiva.");
             return false;
        }
        Almacenamiento nuevoAlmacen = AlmacenamientoMapper.toEntidad(almacenDTO); 
        return almacenamientoDAO.insertar(nuevoAlmacen); 
    }

    public boolean actualizarAlmacen(AlmacenamientoDTO almacenDTO) {
        Almacenamiento almacenActualizado = AlmacenamientoMapper.toEntidad(almacenDTO);
        return almacenamientoDAO.actualizar(almacenActualizado);
    }

    public boolean eliminarAlmacen(int id) {
        return almacenamientoDAO.eliminar(id); //
    }
}