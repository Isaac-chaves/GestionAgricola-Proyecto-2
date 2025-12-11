package Modelo.Service;

import Modelo.Cultivo;
import Modelo.Dao.CultivoDAO;
import Modelo.Dto.CultivoDTO;
import Modelo.Mapper.CultivoMapper;
import java.util.List;
import Modelo.Dto.ProduccionDTO;
import Modelo.Dto.ProductoAlmacenadoDTO;
import java.util.Objects;

/**
 *
 * @author isaac
 */
public class CultivoService {

    private CultivoDAO cultivoDAO = new CultivoDAO();

    public List<CultivoDTO> obtenerTodosLosCultivos() {
        List<Cultivo> entidades = cultivoDAO.seleccionarTodos();
        return CultivoMapper.toDTOList(entidades);
    }

    public CultivoDTO obtenerCultivoPorId(int id) {
        Cultivo entidad = cultivoDAO.seleccionarPorId(id);
        return CultivoMapper.toDTO(entidad);
    }

    public boolean guardarCultivo(CultivoDTO cultivoDTO) {
        Cultivo nuevoCultivo = CultivoMapper.toEntidad(cultivoDTO);
        return cultivoDAO.insertar(nuevoCultivo);
    }

    public boolean actualizarCultivo(CultivoDTO cultivoDTO) {
        Cultivo cultivoActualizado = CultivoMapper.toEntidad(cultivoDTO);
        return cultivoDAO.actualizar(cultivoActualizado);
    }

    /**
     * Elimina un cultivo SOLO si no es referenciado por otras tablas críticas.
     * Comprueba:
     *  - datos_produccion (mediante ProduccionService)
     *  - productos_almacenados (mediante ProductoAlmacenadoService)
     *
     * Si hay dependencias, NO borra y devuelve false.
     */
    public boolean eliminarCultivo(int id) {
        try {
            // 1) Revisar producciones que referencien el cultivo
            ProduccionService produccionService = new ProduccionService();
            List<ProduccionDTO> producciones = produccionService.obtenerTodasLasProducciones();
            if (producciones != null) {
                for (ProduccionDTO p : producciones) {
                    if (p != null && p.getIdCultivo() == id) {
                        System.err.println("No se puede eliminar cultivo: existe referencia en datos_produccion (id_produccion=" + p.getIdProduccion() + ")");
                        return false;
                    }
                }
            }

            // 2) Revisar productos almacenados que referencien el cultivo
            ProductoAlmacenadoService productoService = new ProductoAlmacenadoService();
            List<ProductoAlmacenadoDTO> productos = productoService.obtenerTodosLosProductosAlmacenados();
            if (productos != null) {
                for (ProductoAlmacenadoDTO prod : productos) {
                    if (prod != null && prod.getIdCultivo() == id) {
                        System.err.println("No se puede eliminar cultivo: existe referencia en productos_almacenados (id_producto=" + prod.getIdProductoAlmacenado() + ")");
                        return false;
                    }
                }
            }

            // Si no se encontraron referencias, proceder a eliminar
            return cultivoDAO.eliminar(id);
        } catch (Exception e) {
            System.err.println("Error al eliminar cultivo: " + e.getMessage());
            return false;
        }
    }
}