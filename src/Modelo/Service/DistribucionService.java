package Modelo.Service;

import Modelo.Distribucion;
import Modelo.Dao.DistribucionDAO;
import Modelo.Dto.DistribucionDTO;
import Modelo.Dto.ProductoAlmacenadoDTO;
import Modelo.Mapper.DistribucionMapper;
import java.util.List;
import java.util.ArrayList;

/**
 * Servicio que maneja la lógica de negocio para las distribuciones.
 * - Valida existencia del trabajador responsable antes de insertar.
 * - Valida stock disponible en el producto almacenado antes de insertar.
 * - Inserta la distribución y luego actualiza el stock del producto almacenado.
 *
 * Nota: En la implementación actual no hay una transacción global que abarque
 * la inserción de la distribución y la actualización de stock. Si la actualización
 * de stock falla tras insertar la distribución, la distribución quedará registrada.
 * Recomendado: mejorar DistribucionDAO para devolver el id generado y/o usar transacciones.
 */
public class DistribucionService {

    private final DistribucionDAO distribucionDAO;
    private final ProductoAlmacenadoService productoAlmacenadoService;
    private final TrabajadorService trabajadorService;

    public DistribucionService() {
        this.distribucionDAO = new DistribucionDAO();
        this.productoAlmacenadoService = new ProductoAlmacenadoService();
        this.trabajadorService = new TrabajadorService();
    }

    /**
     * Guarda una nueva distribución y actualiza el stock del producto almacenado asociado.
     *
     * Estrategia:
     *  - Normaliza y valida que la cédula del trabajador responsable exista.
     *  - Localiza el ProductoAlmacenadoDTO correspondiente al idProductoAlmacenado.
     *  - Valida que la cantidad solicitada no supere el stock disponible.
     *  - Inserta la distribución (DAO).
     *  - Si la inserción tiene éxito, actualiza el producto almacenado con el nuevo stock.
     *
     * @param distribucionDTO DTO con los datos de la nueva distribución.
     * @return true si la distribución y la actualización de stock fueron exitosas, false en caso contrario.
     */
    public boolean guardarDistribucion(DistribucionDTO distribucionDTO) {
        if (distribucionDTO == null) return false;

        int idProducto = distribucionDTO.getIdProductoAlmacenado();
        double cantidad = distribucionDTO.getCantidad();
        String cedulaResponsable = distribucionDTO.getIdTrabajadorResponsable();

        // Normalizar cédula: trim y eliminar caracteres no alfanuméricos comunes
        if (cedulaResponsable != null) {
            cedulaResponsable = cedulaResponsable.trim().replaceAll("\\s+", "")
                                        .replaceAll("[^0-9A-Za-z]", "");
            distribucionDTO.setIdTrabajadorResponsable(cedulaResponsable);
        } else {
            System.err.println("DistribucionService: cedula responsable nula");
            return false;
        }

        try {
            // 1) Validar que el trabajador existe
            boolean trabajadorExiste = trabajadorService.existePorCedula(cedulaResponsable);
            if (!trabajadorExiste) {
                System.err.println("DistribucionService: trabajador responsable no encontrado (cedula=" + cedulaResponsable + ")");
                return false;
            }

            // 2) Localizar el ProductoAlmacenadoDTO (buscar en el listado por defecto)
            List<ProductoAlmacenadoDTO> posibles = productoAlmacenadoService.obtenerTodosLosProductosAlmacenados();
            ProductoAlmacenadoDTO encontrado = null;

            if (posibles != null) {
                for (ProductoAlmacenadoDTO p : posibles) {
                    if (p != null && p.getIdProductoAlmacenado() == idProducto) {
                        encontrado = p;
                        break;
                    }
                }
            }

            if (encontrado == null) {
                System.err.println("DistribucionService: producto almacenado no encontrado (id=" + idProducto + ")");
                return false;
            }

            // 3) Validar stock
            double stockActual = encontrado.getCantidadKg();
            if (cantidad > stockActual) {
                System.err.println("DistribucionService: stock insuficiente. Disponible=" + stockActual + ", solicitado=" + cantidad);
                return false;
            }

            // 4) Mapear DTO a entidad (sin id) e insertar la distribución
            Distribucion nuevaDistribucion = DistribucionMapper.toEntidadSinId(distribucionDTO);
            boolean insertOk = distribucionDAO.insertar(nuevaDistribucion);
            if (!insertOk) {
                System.err.println("DistribucionService: fallo al insertar distribucion en DAO.");
                return false;
            }

            // 5) Actualizar stock: restar cantidad y actualizar producto almacenado
            double nuevoStock = stockActual - cantidad;
            ProductoAlmacenadoDTO actualizado = new ProductoAlmacenadoDTO(
                    encontrado.getIdProductoAlmacenado(),
                    encontrado.getIdAlmacen(),
                    encontrado.getIdCultivo(),
                    encontrado.getNombreCultivo(),
                    nuevoStock,
                    encontrado.getFechaIngreso(),
                    encontrado.getFechaEgreso()
            );

            boolean updateOk = productoAlmacenadoService.actualizarProductoAlmacenado(actualizado);
            if (!updateOk) {
                System.err.println("DistribucionService: la distribución fue insertada pero falló la actualización de stock.");
                // Nota: sería ideal revertir la inserción si falla la actualización de stock.
                return false;
            }

            return true;
        } catch (Exception e) {
            System.err.println("DistribucionService.guardarDistribucion error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene una lista de todas las distribuciones.
     * @return Lista de DistribucionDTO.
     */
    public List<DistribucionDTO> obtenerTodasLasDistribuciones() {
        List<Distribucion> entidades = distribucionDAO.seleccionarTodos();
        if (entidades == null) return new ArrayList<>();
        return DistribucionMapper.toDTOList(entidades);
    }

    /**
     * Obtiene una distribución específica por su ID.
     * @param id ID de la distribución.
     * @return DistribucionDTO.
     */
    public DistribucionDTO obtenerDistribucionPorId(int id) {
        Distribucion entidad = distribucionDAO.seleccionarPorId(id);
        return DistribucionMapper.toDTO(entidad);
    }

    /**
     * Actualiza los datos de una distribución existente.
     * @param distribucionDTO DTO con los datos actualizados.
     * @return true si la actualización fue exitosa.
     */
    public boolean actualizarDistribucion(DistribucionDTO distribucionDTO) {
        Distribucion distribucionActualizada = DistribucionMapper.toEntidad(distribucionDTO);
        return distribucionDAO.actualizar(distribucionActualizada);
    }

    /**
     * Elimina una distribución por su ID.
     * @param id ID de la distribución a eliminar.
     * @return true si la eliminación fue exitosa.
     */
    public boolean eliminarDistribucion(int id) {
        return distribucionDAO.eliminar(id);
    }
}