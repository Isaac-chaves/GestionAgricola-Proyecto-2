package Modelo.Mapper;

import Modelo.Distribucion;
import Modelo.Dto.DistribucionDTO;
import java.util.List;
import java.util.stream.Collectors;

public class DistribucionMapper {
    
    // 1. MÉTODO PARA CONVERTIR ENTIDAD (CON ID) A DTO
    // Si usas setters, asegúrate siempre de hacer 'return dto;' al final.
    public static DistribucionDTO toDTO(Distribucion entidad) {
        if (entidad == null) {
            return null;
        }
        DistribucionDTO dto = new DistribucionDTO();
        dto.setIdDistribucion(entidad.getIdDistribucion());
        dto.setIdProductoAlmacenado(entidad.getIdProductoAlmacenado());
        dto.setCantidad(entidad.getCantidad());
        dto.setDestino(entidad.getDestino());
        dto.setFechaDistribucion(entidad.getFechaDistribucion());
        dto.setIdTrabajadorResponsable(entidad.getIdTrabajadorResponsable());
        
        return dto; 
    }
    
    public static List<DistribucionDTO> toDTOList(List<Distribucion> entidades) {
        return entidades.stream()
                .map(DistribucionMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 2. MÉTODO PARA CONVERTIR DTO A ENTIDAD PARA ACTUALIZACIÓN O CONSULTA (CON ID)
    public static Distribucion toEntidad(DistribucionDTO dto) {
        if (dto == null) {
            return null;
        }
        
        // Llama al constructor de 6 parámetros de Distribucion (Entidad)
        Distribucion entidad = new Distribucion(
            dto.getIdDistribucion(),
            dto.getIdProductoAlmacenado(),
            dto.getCantidad(),
            dto.getDestino(),
            dto.getFechaDistribucion(),
            dto.getIdTrabajadorResponsable()
        );
        return entidad;
    }

    // 3. MÉTODO PARA CONVERTIR DTO A ENTIDAD PARA INSERCIÓN (SIN ID)
    public static Distribucion toEntidadSinId(DistribucionDTO dto) {
        if (dto == null) {
            return null;
        }
        
        // Llama al constructor de 5 parámetros de Distribucion (Entidad)
        Distribucion entidad = new Distribucion(
            dto.getIdProductoAlmacenado(),
            dto.getCantidad(),
            dto.getDestino(),
            dto.getFechaDistribucion(),
            dto.getIdTrabajadorResponsable()
        );
        return entidad;
    }
}