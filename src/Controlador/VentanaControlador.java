package Controlador;

import Modelo.Dto.*; 
import Modelo.Service.*; 
import Vista.MenuPrincipal;
import Vista.GestionProduccion;
import Vista.GestionAlmacenamiento;
import Vista.GestionEmpleados;
import Vista.GestionCultivo;
import Vista.GestionDistribucion;
import Vista.AgregarJdialog.AgregarAlmacen;
import Vista.AgregarJdialog.AgregarCultivo;
import Vista.AgregarJdialog.AgregarDistribucion;
import Vista.AgregarJdialog.AgregarEmpleados;
import Vista.EditarJdialog.EditarAlmacen;
import Vista.EditarJdialog.EditarCultivo;
import Vista.EditarJdialog.EditarEmpleados;

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
    }
    
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

    // CORRECCIÓN DEL ERROR DE 'idAlmacenActivo': Obtener todos los productos almacenados
    public List<ProductoAlmacenadoDTO> obtenerTodosDatosProductosAlmacenados() { 
         try {
             return productoAlmacenadoService.obtenerTodosLosProductosAlmacenados(); 
         } catch (Exception e) {
             System.err.println("Error Cntrl.ProductoAlmacenado: " + e.getMessage());
             return Collections.emptyList();
         }
    }

    public List<AlmacenamientoDTO> obtenerDatosAlmacenes() {
         try {
             return almacenamientoService.obtenerTodosLosAlmacenes(); 
         } catch (Exception e) {
             System.err.println("Error Cntrl.Almacenes: " + e.getMessage());
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
    
    public String obtenerNombreProducto(int idProductoAlmacenado) {
        try {
            // Llama al método que ya existe en ProductoAlmacenadoService.java
            return productoAlmacenadoService.obtenerNombreProductoPorId(idProductoAlmacenado);
        } catch (Exception e) {
            System.err.println("Error Cntrl.NombreProducto: " + e.getMessage());
            return "Producto Desconocido"; // Retorna un valor por defecto en caso de error
        }
    }

    public String obtenerNombreTrabajador(int cedulaTrabajador) {
        try {
            // Asumiendo que TrabajadorService tiene este método
            return trabajadorService.obtenerNombrePorCedula(cedulaTrabajador);
        } catch (Exception e) {
            System.err.println("Error Cntrl.NombreTrabajador: " + e.getMessage());
            return "Trabajador Desconocido"; // Retorna un valor por defecto en caso de error
        }
    }

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
    

    private void mostrarDialogoModal(JDialog dialogo) {
        if (dialogo == null) return;
        dialogo.setLocationRelativeTo(menuPrincipal); 
        dialogo.setVisible(true);
    }
    
    public void abrirAgregarCultivo() { mostrarDialogoModal(new AgregarCultivo(menuPrincipal, true)); }
    public void abrirAgregarDistribucion() { mostrarDialogoModal(new AgregarDistribucion(menuPrincipal, true)); }
    public void abrirAgregarAlmacen() { mostrarDialogoModal(new AgregarAlmacen(menuPrincipal, true)); }
    public void abrirAgregarEmpleado() { mostrarDialogoModal(new AgregarEmpleados(menuPrincipal, true)); }

    public void abrirEditarCultivo() { 
        mostrarDialogoModal(new EditarCultivo(menuPrincipal, true)); 
    }
    public void abrirEditarAlmacen() { mostrarDialogoModal(new EditarAlmacen(menuPrincipal, true)); }
    public void abrirEditarEmpleado() { mostrarDialogoModal(new EditarEmpleados(menuPrincipal, true)); }

    public void cerrarTodasLasVentanasInternas() {
        desktopPane.removeAll();
        desktopPane.repaint();
        inicializarVentanasInternas();
    }
    
    public void abrirDialogoAyuda() {
        menuPrincipal.abrirDialogoAyuda();
    }
    
    public void abrirLogin() {
        menuPrincipal.mostrarVentanaLogin();
    }
}