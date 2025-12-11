package Modelo.Dao;

import Modelo.Almacenamiento;
import Modelo.ConexionBD;
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
public class AlmacenamientoDAO {
    
    public boolean insertar(Almacenamiento almacen) {
        String sql = "INSERT INTO almacenes (nombre, capacidad_kg, tipo) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Parámetro 1
            ps.setString(1, almacen.getNombre());
            // Parámetro 2
            ps.setDouble(2, almacen.getCapacidadKg());
            // Parámetro 3 (Ya estaba bien)
            ps.setString(3, almacen.getTipo()); 

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar almacén: " + e.getMessage());
            return false;
        }
    }

    public Almacenamiento seleccionarPorId(int id) {
        // La sentencia SQL incluye 'tipo'
        String sql = "SELECT id_almacen, nombre, capacidad_kg, tipo FROM almacenes WHERE id_almacen = ?";
        Almacenamiento almacen = null;
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    almacen = new Almacenamiento(
                        rs.getInt("id_almacen"),
                        rs.getString("nombre"),
                        rs.getDouble("capacidad_kg"),
                        // **CORRECCIÓN:** Se añade el campo 'tipo' al constructor
                        rs.getString("tipo") 
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar almacén por ID: " + e.getMessage());
        }
        return almacen;
    }

    public List<Almacenamiento> seleccionarTodos() {
        List<Almacenamiento> almacenes = new ArrayList<>();
        // La sentencia SQL incluye 'tipo'
        String sql = "SELECT id_almacen, nombre, capacidad_kg, tipo FROM almacenes";

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Almacenamiento almacen = new Almacenamiento(
                    rs.getInt("id_almacen"),
                    rs.getString("nombre"),
                    rs.getDouble("capacidad_kg"),
                    // **CORRECCIÓN:** Se añade el campo 'tipo' al constructor
                    rs.getString("tipo") 
                );
                almacenes.add(almacen);
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar todos los almacenes: " + e.getMessage());
        }
        return almacenes;
    }

    public boolean actualizar(Almacenamiento almacen) {
        // La sentencia SQL incluye 'tipo'
        String sql = "UPDATE almacenes SET nombre = ?, capacidad_kg = ?, tipo = ? WHERE id_almacen = ?";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, almacen.getNombre());
            ps.setDouble(2, almacen.getCapacidadKg());
            // **CORRECCIÓN:** Se agrega el Parámetro 3 (tipo)
            ps.setString(3, almacen.getTipo());
            ps.setInt(4, almacen.getIdAlmacen());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar almacén: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM almacenes WHERE id_almacen = ?";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar almacén: " + e.getMessage());
            return false;
        }
    }
}