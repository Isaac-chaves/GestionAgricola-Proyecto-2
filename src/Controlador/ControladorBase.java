package Controlador;

import Modelo.Dto.AlmacenamientoDTO;
import Modelo.Dto.CultivoDTO;
import Modelo.Dto.DistribucionDTO;
import Modelo.Dto.ProduccionDTO;
import Modelo.Dto.ProductoAlmacenadoDTO;
import Modelo.Dto.TrabajadorDTO;
import Modelo.Service.AlmacenamientoService;
import Modelo.Service.CultivoService;
import Modelo.Service.DistribucionService;
import Modelo.Service.ProduccionService;
import Modelo.Service.ProductoAlmacenadoService;
import Modelo.Service.TrabajadorService;
import java.util.List;
import java.util.Map;

public class ControladorBase {

    private final AlmacenamientoService almacenamientoService;
    private final CultivoService cultivoService;
    private final DistribucionService distribucionService;
    private final ProduccionService produccionService;
    private final ProductoAlmacenadoService productoAlmacenadoService;
    private final TrabajadorService trabajadorService;

    public ControladorBase() {
        this.almacenamientoService = new AlmacenamientoService();
        this.cultivoService = new CultivoService();
        this.distribucionService = new DistribucionService();
        this.produccionService = new ProduccionService();
        this.productoAlmacenadoService = new ProductoAlmacenadoService();
        this.trabajadorService = new TrabajadorService();
    }

    public List<CultivoDTO> obtenerTodosLosCultivos() {
        return cultivoService.obtenerTodosLosCultivos();
    }

    public List<TrabajadorDTO> obtenerTodosLosTrabajadores() {
        return trabajadorService.obtenerTodosLosTrabajadores();
    }

    public List<ProduccionDTO> obtenerTodasLasProducciones() {
        return produccionService.obtenerTodasLasProducciones();
    }

    public List<AlmacenamientoDTO> obtenerDatosAlmacenes() {
        return almacenamientoService.obtenerTodosLosAlmacenes();
    }

    public boolean guardarAlmacen(AlmacenamientoDTO almacenDTO) {
        return almacenamientoService.guardarAlmacen(almacenDTO);
    }

    public boolean actualizarAlmacen(AlmacenamientoDTO almacenDTO) {
        return almacenamientoService.actualizarAlmacen(almacenDTO);
    }

    public boolean eliminarAlmacen(int id) {
        return almacenamientoService.eliminarAlmacen(id);
    }

    public AlmacenamientoDTO obtenerAlmacenPorId(int id) {
        return almacenamientoService.obtenerAlmacenPorId(id);
    }

    public Map<String, Integer> obtenerMapaAlmacenesParaCombo() {
        return almacenamientoService.obtenerMapaAlmacenesParaCombo();
    }

    public String obtenerNombreProductoPorId(int idProductoAlmacenado) {
        return productoAlmacenadoService.obtenerNombreProductoPorId(idProductoAlmacenado);
    }
    
    public List<ProductoAlmacenadoDTO> obtenerTodosDatosProductosAlmacenados() {
        return productoAlmacenadoService.obtenerTodosLosProductosAlmacenados();
    }

    public boolean guardarProductoAlmacenado(ProductoAlmacenadoDTO productoDTO) {
        return productoAlmacenadoService.guardarProductoAlmacenado(productoDTO);
    }

    public boolean actualizarProductoAlmacenado(ProductoAlmacenadoDTO productoDTO) {
        return productoAlmacenadoService.actualizarProductoAlmacenado(productoDTO);
    }

    public boolean eliminarProductoAlmacenado(int id) {
        return productoAlmacenadoService.eliminarProductoAlmacenado(id);
    }

    public boolean guardarDistribucion(DistribucionDTO distribucionDTO) {
        return distribucionService.guardarDistribucion(distribucionDTO);
    }
    
    public List<DistribucionDTO> obtenerTodasLasDistribuciones() {
        return distribucionService.obtenerTodasLasDistribuciones();
    }

    public DistribucionDTO obtenerDistribucionPorId(int id) {
        return distribucionService.obtenerDistribucionPorId(id);
    }

    public boolean actualizarDistribucion(DistribucionDTO distribucionDTO) {
        return distribucionService.actualizarDistribucion(distribucionDTO);
    }

    public boolean eliminarDistribucion(int id) {
        return distribucionService.eliminarDistribucion(id);
    }
     
    // --- MÉTODOS DE APOYO ADICIONALES PARA PRODUCTO ALMACENADO ---

    public int obtenerIdAlmacenPorNombre(String nombre) {
        return almacenamientoService.obtenerIdAlmacenPorNombre(nombre); 
    }

    // Delegaciones para cultivo y trabajador
    public boolean guardarTrabajador(TrabajadorDTO trabajadorDTO) {
        return trabajadorService.guardarTrabajador(trabajadorDTO);
    }

    public boolean guardarCultivo(CultivoDTO cultivoDTO) {
        return cultivoService.guardarCultivo(cultivoDTO);
    }

    /**
     * Comprueba si existe un trabajador con la cédula indicada.
     */
    public boolean existeTrabajadorPorCedula(String cedula) {
        return trabajadorService.existePorCedula(cedula);
    }

    // NUEVOS MÉTODOS: eliminar cultivo y trabajador
    public boolean eliminarCultivo(int id) {
        return cultivoService.eliminarCultivo(id);
    }

    public boolean eliminarTrabajador(String cedula) {
        return trabajadorService.eliminarTrabajador(cedula);
    }

    // IMPLEMENTACIÓN CORRECTA: delegar actualización de trabajador
    public boolean actualizarTrabajador(TrabajadorDTO actualizado) {
        return trabajadorService.actualizarTrabajador(actualizado);
    }
}