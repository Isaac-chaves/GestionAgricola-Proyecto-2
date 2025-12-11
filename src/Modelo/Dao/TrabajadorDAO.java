package Modelo.Dao;

import Modelo.ConexionBD;
import Modelo.Usuarios.Trabajador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class TrabajadorDAO {

       public boolean insertar(Trabajador trabajador) {
        // CORRECCIÓN: Añadir campo contrasena al INSERT
        String sql = "INSERT INTO trabajadores (cedula, nombre, correo, telefono, puesto, horario, salario, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // CORRECCIÓN: Hashear la contraseña antes de guardar
            String hashContrasena = BCrypt.hashpw(trabajador.getContrasena(), BCrypt.gensalt());
            
            ps.setString(1, trabajador.getCedula()); 
            ps.setString(2, trabajador.getNombre());
            ps.setString(3, trabajador.getCorreo());
            ps.setString(4, trabajador.getTelefono());
            ps.setString(5, trabajador.getPuesto());
            ps.setString(6, trabajador.getHorario());
            ps.setDouble(7, trabajador.getSalario());
            ps.setString(8, hashContrasena); // Guardar el hash, no texto plano

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar trabajador: " + e.getMessage());
            return false;
        }
    }

    public Trabajador seleccionarPorCedula(String cedula) {
        String sql = "SELECT cedula, nombre, correo, telefono, puesto, horario, salario FROM trabajadores WHERE cedula = ?";
        Trabajador trabajador = null;

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    trabajador = new Trabajador(
                        rs.getString("cedula"), 
                        rs.getString("puesto"),
                        rs.getString("horario"),
                        rs.getDouble("salario"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("telefono")
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
                    rs.getString("cedula"), 
                    rs.getString("puesto"),
                    rs.getString("horario"),
                    rs.getDouble("salario"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("telefono")
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
            ps.setString(3, trabajador.getTelefono());
            ps.setString(4, trabajador.getPuesto());
            ps.setString(5, trabajador.getHorario());
            ps.setDouble(6, trabajador.getSalario());
            ps.setString(7, trabajador.getCedula());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar trabajador: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminar(String cedula) {
        String sql = "DELETE FROM trabajadores WHERE cedula = ?";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedula);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar trabajador: " + e.getMessage());
            return false;
        }
    }
    
    // MÉTODO QUE YA EXISTÍA EN VERSIONES ANTERIORES - LO MANTENGO
    public Trabajador autenticar(String correo, String contrasena) {
        String sql = "SELECT cedula, nombre, correo, telefono, puesto, horario, salario, contrasena "
                   + "FROM trabajadores WHERE correo = ?";

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hashBD = rs.getString("contrasena");

                // CORRECCIÓN: Verificar con BCrypt
                if (BCrypt.checkpw(contrasena, hashBD)) {
                    return new Trabajador(
                        rs.getString("cedula"),
                        rs.getString("puesto"),
                        rs.getString("horario"),
                        rs.getDouble("salario"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                        // No pasar la contraseña al constructor
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al autenticar trabajador: " + e.getMessage());
        }

        return null;
    }
    
    // MÉTODO ADICIONAL: Para actualizar contraseña cuando sea necesario
    public boolean actualizarContrasena(String cedula, String nuevaContrasena) {
        String sql = "UPDATE trabajadores SET contrasena = ? WHERE cedula = ?";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Hashear la nueva contraseña
            String hashNuevaContrasena = BCrypt.hashpw(nuevaContrasena, BCrypt.gensalt());
            
            ps.setString(1, hashNuevaContrasena);
            ps.setString(2, cedula);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar contraseña: " + e.getMessage());
            return false;
        }
    }
    

    public boolean existeTrabajador(String cedula) {
        String sql = "SELECT COUNT(*) FROM trabajadores WHERE cedula = ?";
        
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar trabajador: " + e.getMessage());
        }
        return false;
    }
    

    public Trabajador seleccionarPorCorreo(String correo) {
        String sql = "SELECT cedula, nombre, correo, telefono, puesto, horario, salario FROM trabajadores WHERE correo = ?";
        Trabajador trabajador = null;

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    trabajador = new Trabajador(
                        rs.getString("cedula"), 
                        rs.getString("puesto"),
                        rs.getString("horario"),
                        rs.getDouble("salario"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar trabajador por correo: " + e.getMessage());
        }
        return trabajador;
    }
}