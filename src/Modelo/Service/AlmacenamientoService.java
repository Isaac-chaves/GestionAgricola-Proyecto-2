package Modelo.Service;

import Modelo.Almacenamiento; 
import Modelo.Dao.AlmacenamientoDAO; 
import Modelo.Dto.AlmacenamientoDTO; 
import Modelo.Mapper.AlmacenamientoMapper; 
import java.util.List;


public class AlmacenamientoService {

    private final AlmacenamientoDAO almacenamientoDAO = new AlmacenamientoDAO(); 

    public List<AlmacenamientoDTO> obtenerTodosLosAlmacenes() {
        List<Almacenamiento> entidades = almacenamientoDAO.seleccionarTodos(); 
        return AlmacenamientoMapper.toDTOList(entidades); 
    }
    public AlmacenamientoDTO obtenerAlmacenPorId(int id) {
        Almacenamiento entidad = almacenamientoDAO.seleccionarPorId(id); 
        return AlmacenamientoMapper.toDTO(entidad); 
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