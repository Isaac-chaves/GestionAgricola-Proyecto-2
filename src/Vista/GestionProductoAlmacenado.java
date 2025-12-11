package Vista;

import Controlador.ObservadorManager;
import Controlador.VentanaControlador;
import Modelo.Dto.ProductoAlmacenadoDTO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author isaac
 */
public class GestionProductoAlmacenado extends javax.swing.JInternalFrame implements TablaObserver {
    private VentanaControlador controlador;
    private ProductoAlmacenadoDTO productoSeleccionado; // Para la edición
    
    public GestionProductoAlmacenado(VentanaControlador controlador) {
       initComponents();
       this.controlador = controlador;
       // Registrar observer y eliminarlo cuando se cierre la ventana
       ObservadorManager.getInstancia().registrarObservador(this); 
       this.addInternalFrameListener(new InternalFrameAdapter() {
           @Override
           public void internalFrameClosed(InternalFrameEvent e) {
               ObservadorManager.getInstancia().eliminarObservador(GestionProductoAlmacenado.this);
           }
       });
       cargarDatosTabla(); 
    }
    
    @Override
    public void actualizarTabla(String tipoEntidad) {
        if (tipoEntidad != null && tipoEntidad.equals("PRODUCTOALMACENADO")) {
            cargarDatosTabla();
        }
    }
     
    public void cargarDatosTabla() {
        DefaultTableModel modeloTabla = (DefaultTableModel) TablaAlmacenamiento.getModel(); 
        modeloTabla.setRowCount(0); 

        // Obtener datos desde el controlador
        List<ProductoAlmacenadoDTO> listaProductos = controlador.obtenerTodosDatosProductosAlmacenados(); 

        if (listaProductos == null) return;

        for (ProductoAlmacenadoDTO producto : listaProductos) { 
            Object[] fila = new Object[6]; 
            fila[0] = producto.getIdProductoAlmacenado();
            fila[1] = producto.getIdAlmacen();
            fila[2] = producto.getNombreCultivo(); 
            fila[3] = producto.getCantidadKg();
            fila[4] = producto.getFechaIngreso();
            fila[5] = producto.getFechaEgreso();

            modeloTabla.addRow(fila);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code if you plan to use NetBeans GUI builder.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaAlmacenamiento = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        btnEditar = new javax.swing.JButton();
        btnElimanar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();

        setClosable(true);
        setForeground(java.awt.Color.black);
        setTitle("Gestion Productos Almacenados");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        TablaAlmacenamiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Producto", "ID Almacen", "Nombre Cultivo", "Cantidad KG", "F. Ingreso", "F. Egreso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TablaAlmacenamiento.setColumnSelectionAllowed(true);
        TablaAlmacenamiento.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TablaAlmacenamiento);
        TablaAlmacenamiento.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (TablaAlmacenamiento.getColumnModel().getColumnCount() > 0) {
            TablaAlmacenamiento.getColumnModel().getColumn(0).setResizable(false);
            TablaAlmacenamiento.getColumnModel().getColumn(1).setResizable(false);
            TablaAlmacenamiento.getColumnModel().getColumn(2).setResizable(false);
            TablaAlmacenamiento.getColumnModel().getColumn(3).setResizable(false);
            TablaAlmacenamiento.getColumnModel().getColumn(4).setResizable(false);
            TablaAlmacenamiento.getColumnModel().getColumn(5).setResizable(false);
        }

        jTextField1.setText("Buscar");

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnElimanar.setText("Eliminar");
        btnElimanar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElimanarActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        // Horizontal: ensure addContainerGap used on SequentialGroup
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, 
                jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                        .addComponent(btnAgregar))
                )
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar)
                    .addComponent(btnElimanar))
                .addContainerGap()
            )
        );
        // Vertical
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnEditar)
                        .addGap(1, 1, 1)
                        .addComponent(btnElimanar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        // Correct grouping for content pane (SequentialGroup contains addContainerGap)
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()
            )
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            )
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnElimanarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElimanarActionPerformed
        int filaSeleccionada = TablaAlmacenamiento.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(this, "¿Confirma eliminar el producto seleccionado?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (opcion != JOptionPane.YES_OPTION) return;

        DefaultTableModel modelo = (DefaultTableModel) TablaAlmacenamiento.getModel();
        Object idObj = modelo.getValueAt(filaSeleccionada, 0);
        int idProducto;
        try {
            idProducto = Integer.parseInt(String.valueOf(idObj));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID de producto inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean ok = controlador.eliminarProductoAlmacenado(idProducto);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.");
            ObservadorManager.getInstancia().notificarCambio("PRODUCTOALMACENADO");
            cargarDatosTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnElimanarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int fila = TablaAlmacenamiento.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        DefaultTableModel modelo = (DefaultTableModel) TablaAlmacenamiento.getModel();
        Object idObj = modelo.getValueAt(fila, 0);
        int idProducto;
        try {
            idProducto = Integer.parseInt(String.valueOf(idObj));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID de producto inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<ProductoAlmacenadoDTO> lista = controlador.obtenerTodosDatosProductosAlmacenados();
        ProductoAlmacenadoDTO encontrado = null;
        if (lista != null) {
            for (ProductoAlmacenadoDTO p : lista) {
                if (p != null && p.getIdProductoAlmacenado() == idProducto) {
                    encontrado = p;
                    break;
                }
            }
        }

        if (encontrado == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el producto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        controlador.abrirEditarProductoAlmacenado(encontrado);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        controlador.abrirAgregarProductoAlmacenado();
    }//GEN-LAST:event_btnAgregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaAlmacenamiento;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnElimanar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}