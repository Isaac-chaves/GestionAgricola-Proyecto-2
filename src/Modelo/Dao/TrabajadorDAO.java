package Modelo.Dao;


import Modelo.ConexionBD;
import Modelo.Usuarios.Trabajador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrabajadorDAO {

    // Método para insertar un nuevo trabajador
    public boolean insertar(Trabajador trabajador) {
        String sql = "INSERT INTO trabajadores (cedula, nombre, correo, telefono, puesto, horario, salario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, trabajador.getCedula());
            ps.setString(2, trabajador.getNombre());
            ps.setString(3, trabajador.getCorreo());
            ps.setString(4, String.valueOf(trabajador.getTelefono())); // Asumiendo que el campo 'telefono' en DB es VARCHAR
            ps.setString(5, trabajador.getPuesto());
            ps.setString(6, trabajador.getHorario());
            ps.setDouble(7, trabajador.getSalario());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar trabajador: " + e.getMessage());
            return false;
        }
    }

    // Método para buscar un trabajador por cédula
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
                        Integer.parseInt(rs.getString("telefono")) // Convierte de nuevo a int
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar trabajador por cédula: " + e.getMessage());
        }
        return trabajador;
    }

    // Método para seleccionar todos los trabajadores
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
}