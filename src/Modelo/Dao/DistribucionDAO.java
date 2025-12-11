package Modelo.Dao;

import Modelo.ConexionBD;
import Modelo.Distribucion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistribucionDAO {

    public DistribucionDAO() {
    }

    /**
     * CORREGIDO: Se elimina la referencia a id_cultivo.
     */
    public boolean insertar(Distribucion distribucion) {
        String sql = "INSERT INTO distribuciones (id_producto_almacenado, cantidad, destino, fecha_distribucion, id_trabajador_responsable) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, distribucion.getIdProductoAlmacenado());
            ps.setDouble(2, distribucion.getCantidad());
            ps.setString(3, distribucion.getDestino());
            
            try {
                ps.setDate(4, Date.valueOf(distribucion.getFechaDistribucion()));
            } catch (IllegalArgumentException e) {
                ps.setDate(4, new Date(System.currentTimeMillis()));
            }

            ps.setString(5, distribucion.getIdTrabajadorResponsable());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar distribución: " + e.getMessage());
            return false;
        }
    }

    // Se asume que actualizar es correcto
    public boolean actualizar(Distribucion distribucion) {
        String sql = "UPDATE distribuciones SET id_producto_almacenado = ?, cantidad = ?, destino = ?, fecha_distribucion = ?, id_trabajador_responsable = ? WHERE id_distribucion = ?";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, distribucion.getIdProductoAlmacenado());
            ps.setDouble(2, distribucion.getCantidad());
            ps.setString(3, distribucion.getDestino());
            ps.setDate(4, Date.valueOf(distribucion.getFechaDistribucion()));
            ps.setString(5, distribucion.getIdTrabajadorResponsable());
            ps.setInt(6, distribucion.getIdDistribucion());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar distribución: " + e.getMessage());
            return false;
        }
    }
    
    // Se asume que eliminar es correcto
    public boolean eliminar(int id) {
        String sql = "DELETE FROM distribuciones WHERE id_distribucion = ?";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar distribución: " + e.getMessage());
            return false;
        }
    }

    public Distribucion seleccionarPorId(int id) {
        // **CORRECCIÓN: Se elimina id_cultivo del SELECT**
        String sql = "SELECT id_distribucion, id_producto_almacenado, cantidad, destino, fecha_distribucion, id_trabajador_responsable FROM distribuciones WHERE id_distribucion = ?";
        Distribucion distribucion = null;

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String fechaStr = (rs.getDate("fecha_distribucion") != null) 
                        ? rs.getDate("fecha_distribucion").toString() 
                        : "";

                    distribucion = new Distribucion(
                        rs.getInt("id_distribucion"),
                        rs.getInt("id_producto_almacenado"),
                        rs.getDouble("cantidad"),
                        rs.getString("destino"),
                        fechaStr,
                        rs.getString("id_trabajador_responsable")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar distribución por ID: " + e.getMessage());
        }
        return distribucion;
    }
    
    public List<Distribucion> seleccionarTodos() {
        List<Distribucion> lista = new ArrayList<>();
        // **CORRECCIÓN: Se elimina id_cultivo del SELECT**
        String sql = "SELECT id_distribucion, id_producto_almacenado, cantidad, destino, fecha_distribucion, id_trabajador_responsable FROM distribuciones";

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String fechaStr = (rs.getDate("fecha_distribucion") != null) 
                        ? rs.getDate("fecha_distribucion").toString() 
                        : "";

                Distribucion distribucion = new Distribucion(
                    rs.getInt("id_distribucion"),
                    rs.getInt("id_producto_almacenado"),
                    rs.getDouble("cantidad"),
                    rs.getString("destino"),
                    fechaStr,
                    rs.getString("id_trabajador_responsable")
                );
                lista.add(distribucion);
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar todas las distribuciones: " + e.getMessage());
        }
        return lista;
    }
}