/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao;


import Modelo.ConexionBD;
import Modelo.Usuarios.Trabajador;
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
public class TrabajadorDAO {

    public boolean insertar(Trabajador trabajador) {
        String sql = "INSERT INTO trabajadores (cedula, nombre, correo, telefono, puesto, horario, salario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, trabajador.getCedula());
            ps.setString(2, trabajador.getNombre());
            ps.setString(3, trabajador.getCorreo());
            ps.setString(4, String.valueOf(trabajador.getTelefono())); 
            ps.setString(5, trabajador.getPuesto());
            ps.setString(6, trabajador.getHorario());
            ps.setDouble(7, trabajador.getSalario());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar trabajador: " + e.getMessage());
            return false;
        }
    }

    public Trabajador seleccionarPorCedula(int cedula) {
        String sql = "SELECT cedula, nombre, correo, telefono, puesto, horario, salario FROM trabajadores WHERE cedula = ?";
        Trabajador trabajador = null;

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    trabajador = new Trabajador(
                        rs.getInt("cedula"),
                        rs.getString("puesto"),
                        rs.getString("horario"),
                        rs.getDouble("salario"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        Integer.parseInt(rs.getString("telefono"))
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar trabajador por cédula: " + e.getMessage());
        }
        return trabajador;
    }

    public List<Trabajador> seleccionarTodos() {
        List<Trabajador> trabajadores = new ArrayList<>();
        String sql = "SELECT cedula, nombre, correo, telefono, puesto, horario, salario FROM trabajadores";

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Trabajador trabajador = new Trabajador(
                    rs.getInt("cedula"),
                    rs.getString("puesto"),
                    rs.getString("horario"),
                    rs.getDouble("salario"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    Integer.parseInt(rs.getString("telefono"))
                );
                trabajadores.add(trabajador);
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar todos los trabajadores: " + e.getMessage());
        }
        return trabajadores;
    }
    public boolean actualizar(Trabajador trabajador) {
        String sql = "UPDATE trabajadores SET nombre = ?, correo = ?, telefono = ?, puesto = ?, horario = ?, salario = ? WHERE cedula = ?";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trabajador.getNombre());
            ps.setString(2, trabajador.getCorreo());
            ps.setString(3, String.valueOf(trabajador.getTelefono()));
            ps.setString(4, trabajador.getPuesto());
            ps.setString(5, trabajador.getHorario());
            ps.setDouble(6, trabajador.getSalario());
            ps.setInt(7, trabajador.getCedula()); 

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar trabajador: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar un trabajador por cédula
    public boolean eliminar(int cedula) {
        String sql = "DELETE FROM trabajadores WHERE cedula = ?";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cedula);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar trabajador: " + e.getMessage());
            return false;
        }
    }
}
