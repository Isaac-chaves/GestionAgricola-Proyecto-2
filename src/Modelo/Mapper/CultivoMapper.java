package Modelo.Mapper;

import Modelo.Dto.CultivoDTO;
import Modelo.Cultivo;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author isaac
 */
public class CultivoMapper {

    public static Cultivo toEntidad(CultivoDTO dto) {
        if (dto == null) {
            return null;
        }
        Cultivo entidad = new Cultivo(
            dto.getId(),
            dto.getNombre(),
            dto.getTipo(), 
            dto.getAreaSembrada(),
            dto.getEstadoCrecimiento(),
            dto.getFechaSiembra(),
            dto.getFechaCosecha()
        );
        return entidad;
    }

    public static CultivoDTO toDTO(Cultivo entidad) {
        if (entidad == null) return null;

        CultivoDTO dto = new CultivoDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setTipo(entidad.getTipo());
        dto.setAreaSembrada(entidad.getAreaSembrada());
        dto.setEstadoCrecimiento(entidad.getEstadoCrecimiento());
        dto.setFechaSiembra(entidad.getFechaSiembra());
        dto.setFechaCosecha(entidad.getFechaCosecha());
        return dto;
    }

    public static List<CultivoDTO> toDTOList(List<Cultivo> entidades) {
        if (entidades == null) return java.util.Collections.emptyList();
        return entidades.stream()
                .map(CultivoMapper::toDTO)
                .collect(Collectors.toList());
    }
}