/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author isaac
 */
public class TablaControlador {
    
        public static <T> void EditarSelecionada(JInternalFrame vista, JTable tabla, EntityService<T> servicio) {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, "Por favor, seleccione un registro para eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int idRegistro = Integer.parseInt(tabla.getValueAt(filaSeleccionada, 0).toString());
        
        int confirmacion = JOptionPane.showConfirmDialog(vista,
                "¿Está seguro de que desea eliminar el registro con ID: " + idRegistro + "?",
                "Confirmar Edicion",
                JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean eliminado = servicio.eliminar(idRegistro);
            if (eliminado) {
                JOptionPane.showMessageDialog(vista, "Registro Edito correctamente.");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al Edito el registro. Verifique la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
     
    public static <T> void Eliminar(JInternalFrame vista, JTable tabla, EntityService<T> servicio) {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, "Por favor, seleccione un registro para eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int idRegistro = Integer.parseInt(tabla.getValueAt(filaSeleccionada, 0).toString());
        
        int confirmacion = JOptionPane.showConfirmDialog(vista,
                "¿Está seguro de que desea eliminar el registro con ID: " + idRegistro + "?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean eliminado = servicio.eliminar(idRegistro);
            if (eliminado) {
                JOptionPane.showMessageDialog(vista, "Registro eliminado correctamente.");
                // Asumo que la vista tiene un método para recargar datos
                // En un diseño real, esto forzaría a recargar la tabla en la vista.
                // Como workaround, puedes usar el controlador para llamar a ese método si lo haces NO estático.
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar el registro. Verifique la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}