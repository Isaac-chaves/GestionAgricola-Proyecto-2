package Modelo.Dao;


import Modelo.Almacenamiento;
import Modelo.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlmacenamientoDAO {
    
    public boolean insertar(Almacenamiento almacen) {
        String sql = "INSERT INTO almacenes (nombre, capacidad_kg) VALUES (?, ?)";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, almacen.getNombre());
            ps.setDouble(2, almacen.getCapacidadKg());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar almacén: " + e.getMessage());
            return false;
        }
    }

    public Almacenamiento seleccionarPorId(int id) {
        String sql = "SELECT id_almacen, nombre, capacidad_kg FROM almacenes WHERE id_almacen = ?";
        Almacenamiento almacen = null;

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    almacen = new Almacenamiento(
                        rs.getInt("id_almacen"),
                        rs.getString("nombre"),
                        rs.getDouble("capacidad_kg")
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
        String sql = "SELECT id_almacen, nombre, capacidad_kg FROM almacenes";

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Almacenamiento almacen = new Almacenamiento(
                    rs.getInt("id_almacen"),
                    rs.getString("nombre"),
                    rs.getDouble("capacidad_kg")
                );
                almacenes.add(almacen);
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar todos los almacenes: " + e.getMessage());
        }
        return almacenes;
    }
}