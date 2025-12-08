package Modelo.Mapper;

import Modelo.Dto.ProduccionDTO;
import Modelo.DatosProduccion; // Paquete corregido
import Modelo.Cultivo;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ProduccionMapper {
    public static ProduccionDTO toDTO(DatosProduccion entidad) {
        if (entidad == null) return null;
        
        ProduccionDTO dto = new ProduccionDTO();
        dto.setIdProduccion(entidad.getId());
        
        Cultivo cultivo = entidad.getCultivo();
        if (cultivo != null) {
            dto.setIdCultivo(cultivo.getId());
            dto.setNombreCultivo(cultivo.getNombre());
        }
        
        dto.setFechaCosecha(entidad.getFechaCosecha().toString()); 
        dto.setCantidadRecolectadaKg(entidad.getCantidadRecolectadaKg());
        dto.setCalidad(entidad.getCalidad());
        dto.setDestino(entidad.getDestino());
        
        return dto;
    }
    public static List<ProduccionDTO> toDTOList(List<DatosProduccion> entidades) {
        return entidades.stream()
                .map(ProduccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static DatosProduccion toEntidad(ProduccionDTO dto, Cultivo cultivoEntidad) {
        if (dto == null) return null;
        LocalDate fecha = LocalDate.parse(dto.getFechaCosecha()); 

        DatosProduccion entidad = new DatosProduccion(
            dto.getIdProduccion(),
            cultivoEntidad, 
            fecha,
            dto.getCantidadRecolectadaKg(),
            dto.getCalidad(),
            dto.getDestino()
        );

        return entidad;
    }
}