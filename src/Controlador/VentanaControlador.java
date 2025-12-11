package Controlador;

import Modelo.Dto.*;
import Modelo.Service.*;
import Vista.MenuPrincipal;
import Vista.GestionProduccion;
import Vista.GestionAlmacenamiento;
import Vista.GestionEmpleados;
import Vista.GestionCultivo;
import Vista.GestionDistribucion;
import Vista.GestionProductoAlmacenado;
import Vista.AgregarJdialog.AgregarAlmacen;
import Vista.AgregarJdialog.AgregarCultivo;
import Vista.AgregarJdialog.AgregarDistribucion;
import Vista.AgregarJdialog.AgregarEmpleados;
import Vista.AgregarJdialog.AgregarProductoAlmacenado;
import Vista.EditarJdialog.EditarAlmacen;
import Vista.EditarJdialog.EditarCultivo;
import Vista.EditarJdialog.EditarEmpleados;
import Vista.EditarJdialog.EditarProductoAlmacenado;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JDialog;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Collections;

public class VentanaControlador {

    private final MenuPrincipal menuPrincipal;
    private final JDesktopPane desktopPane;

    private GestionProduccion ventanaProduccion;
    private GestionAlmacenamiento ventanaAlmacen;
    private GestionEmpleados ventanaEmpleados;
    private GestionCultivo ventanaCultivo;
    private GestionDistribucion ventanaDistribucion;
    private GestionProductoAlmacenado ventanaProductoAlmacen;

    private final CultivoService cultivoService = new CultivoService();
    private final TrabajadorService trabajadorService = new TrabajadorService();
    private final ProduccionService produccionService = new ProduccionService();
    private final ProductoAlmacenadoService productoAlmacenadoService = new ProductoAlmacenadoService();
    private final AlmacenamientoService almacenamientoService = new AlmacenamientoService();
    private final DistribucionService distribucionService = new DistribucionService();

    public VentanaControlador(MenuPrincipal menuPrincipal, JDesktopPane desktopPane) {
        this.menuPrincipal = menuPrincipal;
        this.desktopPane = desktopPane;
        inicializarVentanasInternas();
    }

    private void inicializarVentanasInternas() {
        ventanaProduccion = new GestionProduccion(this);
        ventanaAlmacen = new GestionAlmacenamiento(this);
        ventanaEmpleados = new GestionEmpleados(this);
        ventanaCultivo = new GestionCultivo(this);
        ventanaDistribucion = new GestionDistribucion(this);
        ventanaProductoAlmacen = new GestionProductoAlmacenado(this);
    }

    // --- MÉTODOS DE OBTENCIÓN DE DATOS (delegan a services) ---
    public List<CultivoDTO> obtenerDatosCultivos() {
        try {
            return cultivoService.obtenerTodosLosCultivos();
        } catch (Exception e) {
            System.err.println("Error Cntrl.Cultivo: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<TrabajadorDTO> obtenerDatosTrabajadores() {
        try {
            return trabajadorService.obtenerTodosLosTrabajadores();
        } catch (Exception e) {
            System.err.println("Error Cntrl.Trabajador: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<ProduccionDTO> obtenerDatosProduccion() {
        try {
            return produccionService.obtenerTodasLasProducciones();
        } catch (Exception e) {
            System.err.println("Error Cntrl.Produccion: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<DistribucionDTO> obtenerDatosDistribucion() {
        try {
            return distribucionService.obtenerTodasLasDistribuciones();
        } catch (Exception e) {
            System.err.println("Error Cntrl.Distribucion: " + e.getMessage());
            return Collections.emptyList();
        }
    }
  public List<ProduccionDTO> obtenerTodasLasProducciones() {
        List<ProduccionDTO> lista = produccionService.obtenerTodasLasProducciones();
        return lista != null ? lista : Collections.emptyList();
    }
    public List<AlmacenamientoDTO> obtenerDatosAlmacenes() {
        try {
            return almacenamientoService.obtenerTodosLosAlmacenes();
        } catch (Exception e) {
            System.err.println("Error Cntrl.Almacenes: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<ProductoAlmacenadoDTO> obtenerTodosDatosProductosAlmacenados() {
        try {
            return productoAlmacenadoService.obtenerTodosLosProductosAlmacenados();
        } catch (Exception e) {
            System.err.println("Error Cntrl.ProductoAlmacenado: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // --- Métodos auxiliares para obtener nombres/ids ---
    public String obtenerNombreProducto(int idProductoAlmacenado) {
        try {
            return productoAlmacenadoService.obtenerNombreProductoPorId(idProductoAlmacenado);
        } catch (Exception e) {
            System.err.println("Error Cntrl.NombreProducto: " + e.getMessage());
            return "Producto Desconocido";
        }
    }

    public String obtenerNombreTrabajador(String cedulaTrabajador) {
        try {
            return trabajadorService.obtenerNombrePorCedula(cedulaTrabajador);
        } catch (Exception e) {
            System.err.println("Error Cntrl.NombreTrabajador: " + e.getMessage());
            return "Trabajador Desconocido";
        }
    }

    // --- Mostrar ventanas internas / diálogos ---
    private void mostrarVentanaInterna(JInternalFrame ventana) {
        if (!ventana.isVisible()) {
            desktopPane.add(ventana);
            int x = (desktopPane.getWidth() - ventana.getWidth()) / 2;
            int y = (desktopPane.getHeight() - ventana.getHeight()) / 2;
            ventana.setLocation(x, y);
            ventana.setVisible(true);
            try {
                ventana.setSelected(true);
                ventana.toFront();
            } catch (PropertyVetoException pve) {
                pve.printStackTrace();
            }
        } else {
            try {
                ventana.setSelected(true);
                ventana.toFront();
            } catch (PropertyVetoException pve) {
                pve.printStackTrace();
            }
        }
    }

    public void abrirGestionProduccion() { mostrarVentanaInterna(ventanaProduccion); }
    public void abrirGestionAlmacenamiento() { mostrarVentanaInterna(ventanaAlmacen); }
    public void abrirGestionEmpleados() { mostrarVentanaInterna(ventanaEmpleados); }
    public void abrirGestionCultivo() { mostrarVentanaInterna(ventanaCultivo); }
    public void abrirGestionDistribucion() { mostrarVentanaInterna(ventanaDistribucion); }
    public void abrirGestionProductoAlmacenado() { mostrarVentanaInterna(ventanaProductoAlmacen); }

    // --- Abrir diálogos de creación ---
    public void abrirAgregarCultivo() { mostrarDialogoModal(new AgregarCultivo(menuPrincipal, true)); }
    public void abrirAgregarDistribucion() { mostrarDialogoModal(new AgregarDistribucion(menuPrincipal, true)); }
    public void abrirAgregarAlmacen() { mostrarDialogoModal(new AgregarAlmacen(menuPrincipal, true)); }
    public void abrirAgregarEmpleado() { mostrarDialogoModal(new AgregarEmpleados(menuPrincipal, true)); }
    public void abrirAgregarProductoAlmacenado() { mostrarDialogoModal(new AgregarProductoAlmacenado(menuPrincipal, true)); }

    private void mostrarDialogoModal(JDialog dialogo) {
        if (dialogo == null) return;
        dialogo.setLocationRelativeTo(menuPrincipal);
        dialogo.setVisible(true);
    }

    // --- Abrir diálogos de edición (sobrecargas válidas, una sola definición por firma) ---
    public void abrirEditarCultivo() { mostrarDialogoModal(new EditarCultivo(menuPrincipal, true)); }
    public void abrirEditarEmpleado() { mostrarDialogoModal(new EditarEmpleados(menuPrincipal, true)); }
    public void abrirEditarAlmacen() { mostrarDialogoModal(new EditarAlmacen(menuPrincipal, true)); }
    public void abrirEditarProductoAlmacenado() { mostrarDialogoModal(new EditarProductoAlmacenado(menuPrincipal, true)); }

    public void abrirEditarCultivo(CultivoDTO cultivo) {
        if (cultivo == null) abrirEditarCultivo();
        else mostrarDialogoModal(new EditarCultivo(menuPrincipal, true, cultivo));
    }

    public void abrirEditarEmpleado(TrabajadorDTO trabajador) {
        if (trabajador == null) abrirEditarEmpleado();
        else mostrarDialogoModal(new EditarEmpleados(menuPrincipal, true, trabajador));
    }

    public void abrirEditarProductoAlmacenado(ProductoAlmacenadoDTO productoSeleccionado) {
        if (productoSeleccionado == null) abrirEditarProductoAlmacenado();
        else mostrarDialogoModal(new EditarProductoAlmacenado(menuPrincipal, true, productoSeleccionado));
    }

    // --- Métodos getById / getByCedula útiles para ventanas internas ---
    public CultivoDTO getCultivoById(int id) {
        List<CultivoDTO> lista = obtenerDatosCultivos();
        if (lista == null) return null;
        for (CultivoDTO c : lista) if (c != null && c.getId() == id) return c;
        return null;
    }

    public TrabajadorDTO getTrabajadorByCedula(String cedula) {
        List<TrabajadorDTO> lista = obtenerDatosTrabajadores();
        if (lista == null) return null;
        for (TrabajadorDTO t : lista) if (t != null && cedula != null && cedula.equals(t.getCedula())) return t;
        return null;
    }

    public AlmacenamientoDTO getAlmacenById(int id) {
        try { return almacenamientoService.obtenerAlmacenPorId(id); }
        catch (Exception e) { System.err.println("Error al obtener almacen por id: " + e.getMessage()); return null; }
    }

    // --- Eliminaciones delegadas a servicios (notifican Observadores) ---
    public boolean eliminarProductoAlmacenado(int idProducto) {
        try { return productoAlmacenadoService.eliminarProductoAlmacenado(idProducto); }
        catch (Exception e) { System.err.println("Error al eliminar producto almacenado (id=" + idProducto + "): " + e.getMessage()); return false; }
    }

    public boolean eliminarCultivo(int id) {
        try {
            boolean ok = cultivoService.eliminarCultivo(id);
            if (ok) ObservadorManager.getInstancia().notificarCambio("CULTIVOS");
            return ok;
        } catch (Exception e) { System.err.println("Error eliminando cultivo: " + e.getMessage()); return false; }
    }

    public boolean eliminarTrabajador(String cedula) {
        try {
            boolean ok = trabajadorService.eliminarTrabajador(cedula);
            if (ok) ObservadorManager.getInstancia().notificarCambio("TRABAJADORES");
            return ok;
        } catch (Exception e) { System.err.println("Error eliminando trabajador: " + e.getMessage()); return false; }
    }

    public boolean eliminarAlmacen(int id) {
        try {
            boolean ok = almacenamientoService.eliminarAlmacen(id);
            if (ok) ObservadorManager.getInstancia().notificarCambio("ALMACENAMIENTO");
            return ok;
        } catch (Exception e) { System.err.println("Error eliminando almacen: " + e.getMessage()); return false; }
    }

    // --- Otras delegaciones simples ---
    public boolean guardarAlmacen(AlmacenamientoDTO almacenDTO) { return almacenamientoService.guardarAlmacen(almacenDTO); }
    public boolean actualizarAlmacen(AlmacenamientoDTO almacenDTO) { return almacenamientoService.actualizarAlmacen(almacenDTO); }
    public boolean guardarProductoAlmacenado(ProductoAlmacenadoDTO productoDTO) { return productoAlmacenadoService.guardarProductoAlmacenado(productoDTO); }
    public boolean actualizarProductoAlmacenado(ProductoAlmacenadoDTO productoDTO) { return productoAlmacenadoService.actualizarProductoAlmacenado(productoDTO); }
    public boolean guardarDistribucion(DistribucionDTO distribucionDTO) { return distribucionService.guardarDistribucion(distribucionDTO); }

    public void cerrarTodasLasVentanasInternas() {
        desktopPane.removeAll();
        desktopPane.repaint();
        inicializarVentanasInternas();
    }

    public void abrirDialogoAyuda() { menuPrincipal.abrirDialogoAyuda(); }
    public void abrirLogin() { menuPrincipal.mostrarVentanaLogin(); }
}