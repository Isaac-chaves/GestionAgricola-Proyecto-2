package Modelo.Mapper;

import Modelo.Dto.TrabajadorDTO;
import Modelo.Usuarios.Trabajador; 
import java.util.List;
import java.util.stream.Collectors;

public class TrabajadorMapper {

    public static TrabajadorDTO toDTO(Trabajador entidad) {
        if (entidad == null) return null;
        
        TrabajadorDTO dto = new TrabajadorDTO();
        dto.setCedula(entidad.getCedula());
        dto.setNombre(entidad.getNombre());
        dto.setCorreo(entidad.getCorreo());
        dto.setTelefono(String.valueOf(entidad.getTelefono())); 
        dto.setPuesto(entidad.getPuesto());
        dto.setHorario(entidad.getHorario());
        dto.setSalario(entidad.getSalario());
        
        return dto;
    }

    public static List<TrabajadorDTO> toDTOList(List<Trabajador> entidades) {
        return entidades.stream()
                .map(TrabajadorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static Trabajador toEntidad(TrabajadorDTO dto) {
        if (dto == null) return null;

        Trabajador entidad = new Trabajador(
            dto.getCedula(),
            dto.getPuesto(),
            dto.getHorario(),
            dto.getSalario(),
            dto.getNombre(),
            dto.getCorreo(),
            Integer.parseInt(dto.getTelefono()) 
        );
        return entidad;
    }
}