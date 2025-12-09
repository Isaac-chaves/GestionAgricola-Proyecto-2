/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Mapper;


import Modelo.Dto.AlmacenamientoDTO;
import Modelo.Almacenamiento;
import java.util.List;
import java.util.stream.Collectors;
 /**
 *
 * @author isaac
 */
public class AlmacenamientoMapper {
    public static AlmacenamientoDTO toDTO(Almacenamiento entidad) {
        if (entidad == null) return null;
        
        AlmacenamientoDTO dto = new AlmacenamientoDTO();
        dto.setIdAlmacen(entidad.getIdAlmacen());
        dto.setNombre(entidad.getNombre());
        dto.setCapacidadKg(entidad.getCapacidadKg());
        return dto;
    }

    public static List<AlmacenamientoDTO> toDTOList(List<Almacenamiento> entidades) {
        return entidades.stream()
                .map(AlmacenamientoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static Almacenamiento toEntidad(AlmacenamientoDTO dto) {
        if (dto == null) return null;

        Almacenamiento entidad = new Almacenamiento(
            dto.getIdAlmacen(),
            dto.getNombre(),
            dto.getCapacidadKg()
        );
        return entidad;
    }
}