package Modelo.Mapper;


import Modelo.Distribucion;
import Modelo.Dto.DistribucionDTO;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author isaac
 */
public class DistribucionMapper {
    
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

    public static Distribucion toEntidad(DistribucionDTO dto) {
        if (dto == null) {
            return null;
        }
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
}