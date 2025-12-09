/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao;


import Modelo.ConexionBD;
import Modelo.Cultivo;
import Modelo.DatosProduccion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author isaac
 */
public class ProduccionDAO {
    private CultivoDAO cultivoDAO = new CultivoDAO();

    public boolean insertar(DatosProduccion produccion) {
        String sql = "INSERT INTO produccion (id_cultivo, fecha_cosecha, cantidad_recolectada_kg, calidad_kg, destino) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, produccion.getCultivo().getId());
            ps.setDate(2, Date.valueOf(produccion.getFechaCosecha()));
            ps.setInt(3, produccion.getCantidadRecolectadaKg());
            ps.setInt(4, produccion.getCalidad());
            ps.setString(5, produccion.getDestino());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar producción: " + e.getMessage());
            return false;
        }
    }

    public List<DatosProduccion> seleccionarTodos() {
        List<DatosProduccion> producciones = new ArrayList<>();
        String sql = "SELECT id_produccion, id_cultivo, fecha_cosecha, cantidad_recolectada_kg, calidad_kg, destino FROM produccion";

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idCultivo = rs.getInt("id_cultivo");
                Cultivo cultivo = cultivoDAO.seleccionarPorId(idCultivo); 

                DatosProduccion produccion = new DatosProduccion(
                    rs.getInt("id_produccion"),
                    cultivo,
                    rs.getDate("fecha_cosecha").toLocalDate(),
                    rs.getInt("cantidad_recolectada_kg"),
                    rs.getInt("calidad_kg"),
                    rs.getString("destino")
                );
                producciones.add(produccion);
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar todas las producciones: " + e.getMessage());
        }
        return producciones;
    }

    public boolean actualizar(DatosProduccion produccion) {
        String sql = "UPDATE produccion SET id_cultivo = ?, fecha_cosecha = ?, cantidad_recolectada_kg = ?, calidad_kg = ?, destino = ? WHERE id_produccion = ?";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, produccion.getCultivo().getId());
            ps.setDate(2, Date.valueOf(produccion.getFechaCosecha()));
            ps.setInt(3, produccion.getCantidadRecolectadaKg());
            ps.setInt(4, produccion.getCalidad());
            ps.setString(5, produccion.getDestino());
            ps.setInt(6, produccion.getId()); 

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar producción: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM produccion WHERE id_produccion = ?";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar producción: " + e.getMessage());
            return false;
        }
    }
}