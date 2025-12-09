/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao;

import Modelo.ConexionBD;
import Modelo.Cultivo;
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
public class CultivoDAO {

    public boolean insertar(Cultivo cultivo) {
        String sql = "INSERT INTO cultivos (nombre, tipo, area_sembrada, estado_crecimiento, fecha_siembra, fecha_cosecha) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cultivo.getNombre());
            ps.setString(2, cultivo.getTipo());
            ps.setInt(3, (int) cultivo.getAreaSembrada());
            ps.setString(4, cultivo.getEstadoCrecimiento());
            ps.setString(5, cultivo.getFechaSiembra());
            ps.setString(6, cultivo.getFechaCosecha());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar cultivo: " + e.getMessage());
            return false;
        }
    }

    public Cultivo seleccionarPorId(int id) {
        String sql = "SELECT id_cultivo, nombre, tipo, area_sembrada, estado_crecimiento, fecha_siembra, fecha_cosecha FROM cultivos WHERE id_cultivo = ?";
        Cultivo cultivo = null;

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cultivo = new Cultivo(
                        rs.getInt("id_cultivo"),
                        rs.getString("nombre"),
                        rs.getString("tipo"),
                        rs.getInt("area_sembrada"),
                        rs.getString("estado_crecimiento"),
                        rs.getString("fecha_siembra"),
                        rs.getString("fecha_cosecha")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar cultivo por ID: " + e.getMessage());
        }
        return cultivo;
    }

    public List<Cultivo> seleccionarTodos() {
        List<Cultivo> cultivos = new ArrayList<>();
        String sql = "SELECT id_cultivo, nombre, tipo, area_sembrada, estado_crecimiento, fecha_siembra, fecha_cosecha FROM cultivos";

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cultivo cultivo = new Cultivo(
                    rs.getInt("id_cultivo"),
                    rs.getString("nombre"),
                    rs.getString("tipo"),
                    rs.getInt("area_sembrada"),
                    rs.getString("estado_crecimiento"),
                    rs.getString("fecha_siembra"),
                    rs.getString("fecha_cosecha")
                );
                cultivos.add(cultivo);
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar todos los cultivos: " + e.getMessage());
        }
        return cultivos;
    }
    public boolean actualizar(Cultivo cultivo) {
        String sql = "UPDATE cultivos SET nombre = ?, tipo = ?, area_sembrada = ?, estado_crecimiento = ?, fecha_siembra = ?, fecha_cosecha = ? WHERE id_cultivo = ?";
        
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cultivo.getNombre());
            ps.setString(2, cultivo.getTipo());
            ps.setInt(3, (int) cultivo.getAreaSembrada());
            ps.setString(4, cultivo.getEstadoCrecimiento());
            ps.setString(5, cultivo.getFechaSiembra());
            ps.setString(6, cultivo.getFechaCosecha());
            ps.setInt(7, cultivo.getId());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cultivo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM cultivos WHERE id_cultivo = ?";
        
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cultivo: " + e.getMessage());
            return false;
        }
    }
}