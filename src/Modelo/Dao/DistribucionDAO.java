package Modelo.Dao;


import Modelo.ConexionBD;
import Modelo.Distribucion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author isaac
 */
public class DistribucionDAO {

    /**
     * Inserta un nuevo registro de distribución en la base de datos.
     * @param distribucion El objeto Distribucion a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertar(Distribucion distribucion) {
        String sql = "INSERT INTO distribuciones (id_producto_almacenado, cantidad, destino, fecha_distribucion, id_trabajador_responsable) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, distribucion.getIdProductoAlmacenado());
            ps.setDouble(2, distribucion.getCantidad());
            ps.setString(3, distribucion.getDestino());
            ps.setString(4, distribucion.getFechaDistribucion());
            ps.setInt(5, distribucion.getIdTrabajadorResponsable());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar distribución: " + e.getMessage());
            return false;
        }
    }

    /**
     * Selecciona un registro de distribución por su ID.
     * @param id El ID de la distribución a buscar.
     * @return El objeto Distribucion encontrado o null si no existe.
     */
    public Distribucion seleccionarPorId(int id) {
        String sql = "SELECT id_distribucion, id_producto_almacenado, cantidad, destino, fecha_distribucion, id_trabajador_responsable FROM distribuciones WHERE id_distribucion = ?";
        Distribucion distribucion = null;

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    distribucion = new Distribucion(
                        rs.getInt("id_distribucion"),
                        rs.getInt("id_producto_almacenado"),
                        rs.getDouble("cantidad"),
                        rs.getString("destino"),
                        rs.getString("fecha_distribucion"),
                        rs.getInt("id_trabajador_responsable")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar distribución por ID: " + e.getMessage());
        }
        return distribucion;
    }

    /**
     * Selecciona todos los registros de distribuciones.
     * @return Una lista de objetos Distribucion.
     */
    public List<Distribucion> seleccionarTodos() {
        List<Distribucion> distribuciones = new ArrayList<>();
        String sql = "SELECT id_distribucion, id_producto_almacenado, cantidad, destino, fecha_distribucion, id_trabajador_responsable FROM distribuciones";

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Distribucion distribucion = new Distribucion(
                    rs.getInt("id_distribucion"),
                    rs.getInt("id_producto_almacenado"),
                    rs.getDouble("cantidad"),
                    rs.getString("destino"),
                    rs.getString("fecha_distribucion"),
                    rs.getInt("id_trabajador_responsable")
                );
                distribuciones.add(distribucion);
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar todas las distribuciones: " + e.getMessage());
        }
        return distribuciones;
    }
    
    /**
     * Actualiza un registro de distribución existente.
     * @param distribucion El objeto Distribucion con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizar(Distribucion distribucion) {
        String sql = "UPDATE distribuciones SET id_producto_almacenado = ?, cantidad = ?, destino = ?, fecha_distribucion = ?, id_trabajador_responsable = ? WHERE id_distribucion = ?";
        
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, distribucion.getIdProductoAlmacenado());
            ps.setDouble(2, distribucion.getCantidad());
            ps.setString(3, distribucion.getDestino());
            ps.setString(4, distribucion.getFechaDistribucion());
            ps.setInt(5, distribucion.getIdTrabajadorResponsable());
            ps.setInt(6, distribucion.getIdDistribucion());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar distribución: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un registro de distribución por su ID.
     * @param id El ID de la distribución a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
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
}