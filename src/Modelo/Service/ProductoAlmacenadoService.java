package Modelo.Service;

import Modelo.Cultivo; 
import Modelo.ProductoAlmacenado; 
import Modelo.Dao.ProductoAlmacenadoDAO; 
import Modelo.Dao.CultivoDAO; 
import Modelo.Dto.ProductoAlmacenadoDTO; 
import Modelo.Mapper.ProductoAlmacenadoMapper; 
import java.util.List;
import java.util.Collections; 




public class ProductoAlmacenadoService {

    private final ProductoAlmacenadoDAO productoAlmacenadoDAO = new ProductoAlmacenadoDAO(); 
    private final CultivoDAO cultivoDAO = new CultivoDAO(); 

    // --- NUEVO MÉTODO PARA DISTRIBUCIONSERVICE ---
    public String obtenerNombreProductoPorId(int idProductoAlmacenado) {
        ProductoAlmacenado entidad = productoAlmacenadoDAO.seleccionarPorId(idProductoAlmacenado); 

        if (entidad != null && entidad.getCultivo() != null) {
            if (entidad.getCultivo().getNombre() != null) {
                 return entidad.getCultivo().getNombre();
            } else {
                 Cultivo cultivoCompleto = cultivoDAO.seleccionarPorId(entidad.getCultivo().getId());
                 return cultivoCompleto != null ? cultivoCompleto.getNombre() : "Producto Desconocido (ID: " + idProductoAlmacenado + ")";
            }
        }
        return "Producto Desconocido (ID: " + idProductoAlmacenado + ")";
    }
    
    public List<ProductoAlmacenadoDTO> obtenerTodosLosProductosAlmacenados() {
         try {
             int idAlmacenPorDefecto = 1; 
             return obtenerProductosPorAlmacen(idAlmacenPorDefecto); 
         } catch (Exception e) {
             System.err.println("Error al obtener todos los productos almacenados: " + e.getMessage());
             return Collections.emptyList();
         }
    }
    
    
    public List<ProductoAlmacenadoDTO> obtenerProductosPorAlmacen(int idAlmacen) {
        List<ProductoAlmacenado> entidades = productoAlmacenadoDAO.seleccionarTodosPorAlmacen(idAlmacen); 
        
        for (ProductoAlmacenado entidad : entidades) {
            Cultivo cultivoParcial = entidad.getCultivo();
            if (cultivoParcial != null && cultivoParcial.getId() > 0) {
                Cultivo cultivoEntidadCompleta = cultivoDAO.seleccionarPorId(cultivoParcial.getId());
                
                if (cultivoEntidadCompleta != null) {
                    entidad.setCultivo(cultivoEntidadCompleta);
                } else {
                    entidad.setCultivo(null);
                }
            } else {
                entidad.setCultivo(null);
            }
        }

        return ProductoAlmacenadoMapper.toDTOList(entidades, idAlmacen); 
    }

    public ProductoAlmacenadoDTO obtenerProductoPorId(int idProductoAlmacenado, int idAlmacen) {
        ProductoAlmacenado entidad = productoAlmacenadoDAO.seleccionarPorId(idProductoAlmacenado); 

        if (entidad != null) {
            Cultivo cultivoParcial = entidad.getCultivo();
            if (cultivoParcial != null && cultivoParcial.getId() > 0) {
                 Cultivo cultivoEntidadCompleta = cultivoDAO.seleccionarPorId(cultivoParcial.getId());
                 if (cultivoEntidadCompleta != null) {
                    entidad.setCultivo(cultivoEntidadCompleta);
                 } else {
                    entidad.setCultivo(null);
                 }
            } else {
                entidad.setCultivo(null);
            }
        }
        
        return ProductoAlmacenadoMapper.toDTO(entidad, idAlmacen);
    }

    public boolean guardarProductoAlmacenado(ProductoAlmacenadoDTO productoDTO) {
        Cultivo cultivoEntidad = cultivoDAO.seleccionarPorId(productoDTO.getIdCultivo());
        
        if (cultivoEntidad == null) {
            return false;
        }

        ProductoAlmacenado nuevoProducto = ProductoAlmacenadoMapper.toEntidad(productoDTO, cultivoEntidad); 
        return productoAlmacenadoDAO.insertar(productoDTO.getIdAlmacen(), nuevoProducto); 
    }

    public boolean actualizarProductoAlmacenado(ProductoAlmacenadoDTO productoDTO) {
        Cultivo cultivoEntidad = cultivoDAO.seleccionarPorId(productoDTO.getIdCultivo());

        if (cultivoEntidad == null) {
            return false;
        }

        ProductoAlmacenado productoActualizado = ProductoAlmacenadoMapper.toEntidad(productoDTO, cultivoEntidad); 
        return productoAlmacenadoDAO.actualizar(productoActualizado, productoDTO.getIdAlmacen()); 
    }

    public boolean eliminarProductoAlmacenado(int id) {
        return productoAlmacenadoDAO.eliminar(id); 
    }
public int obtenerIdAlmacenParaProducto(int idProducto) {
        return -1; 
}

   
}