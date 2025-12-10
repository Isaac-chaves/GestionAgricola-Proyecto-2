package Modelo.Mapper;

import Modelo.Dto.ProductoAlmacenadoDTO;
import Modelo.ProductoAlmacenado; 
import Modelo.Cultivo;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author isaac
 */
public class ProductoAlmacenadoMapper {

    public static ProductoAlmacenadoDTO toDTO(ProductoAlmacenado entidad, int idAlmacen) {
        if (entidad == null) return null;
        
        ProductoAlmacenadoDTO dto = new ProductoAlmacenadoDTO();
        dto.setIdProductoAlmacenado(entidad.getId()); 
        dto.setIdAlmacen(idAlmacen); 

        Cultivo cultivo = entidad.getCultivo();
        // El Service se encarga de que 'cultivo' esté completo o sea 'null'
        if (cultivo != null) {
            dto.setIdCultivo(cultivo.getId());
            dto.setNombreCultivo(cultivo.getNombre());
        } else {
            // Manejo de caso donde la entidad Cultivo es NULL
            dto.setIdCultivo(0); 
            dto.setNombreCultivo("Cultivo no encontrado");
        }
        
        dto.setCantidadKg(entidad.getCantidadKg());
        dto.setFechaIngreso(entidad.getFechaIngreso().toString());
        dto.setFechaEgreso(entidad.getFechaEgreso() != null ? entidad.getFechaEgreso().toString() : null);
        
        return dto;
    }

    public static List<ProductoAlmacenadoDTO> toDTOList(List<ProductoAlmacenado> entidades, int idAlmacen) {
        return entidades.stream()
                .map(entidad -> toDTO(entidad, idAlmacen))
                .collect(Collectors.toList());
    }

    public static ProductoAlmacenado toEntidad(ProductoAlmacenadoDTO dto, Cultivo cultivoEntidad) {
        if (dto == null) return null;

        LocalDate fechaIngreso = LocalDate.parse(dto.getFechaIngreso());
        // Manejar el posible valor nulo de fechaEgreso
        LocalDate fechaEgreso = dto.getFechaEgreso() != null ? LocalDate.parse(dto.getFechaEgreso()) : null;

        ProductoAlmacenado entidad = new ProductoAlmacenado(
            dto.getIdProductoAlmacenado(),
            cultivoEntidad, // Aquí se utiliza la entidad Cultivo completa buscada por el Service
            dto.getCantidadKg(),
            fechaIngreso,
            fechaEgreso
        );
        
        return entidad;
    }
}