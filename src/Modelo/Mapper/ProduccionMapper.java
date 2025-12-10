/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Mapper;

import Modelo.Cultivo;
import Modelo.DatosProduccion;
import Modelo.Dto.ProduccionDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

 /**
 *
 * @author isaac
 */
public class ProduccionMapper {
    public static ProduccionDTO toDTO(DatosProduccion entidad) {
        if (entidad == null) return null;
        
        ProduccionDTO dto = new ProduccionDTO();
        dto.setIdProduccion(entidad.getId());
        
        Cultivo cultivo = entidad.getCultivo();
        // El Service se encarga de que 'cultivo' esté completo o sea 'null'
        if (cultivo != null) {
            dto.setIdCultivo(cultivo.getId());
            dto.setNombreCultivo(cultivo.getNombre());
        } else {
            // Manejo de caso donde la entidad Cultivo es NULL (ej: idCultivo inválido)
            dto.setIdCultivo(0); // o un valor por defecto que indique ausencia
            dto.setNombreCultivo("Cultivo no encontrado"); 
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
        // Asumiendo que getFechaCosecha() devuelve un String en formato ISO (YYYY-MM-DD)
        LocalDate fecha = LocalDate.parse(dto.getFechaCosecha()); 

        DatosProduccion entidad = new DatosProduccion(
            dto.getIdProduccion(),
            cultivoEntidad, // Aquí se utiliza la entidad Cultivo completa buscada por el Service
            fecha,
            dto.getCantidadRecolectadaKg(),
            dto.getCalidad(),
            dto.getDestino()
        );

        return entidad;
    }
}