package Modelo.Dao;


import Modelo.ConexionBD;
import Modelo.Usuarios.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {

    private Connection connection;
    public UsuariosDAO() {
        
    }

    public boolean insertar(Usuarios usuario) {
        String sql = "INSERT INTO usuarios (nombre_usuario, contrasena, rol, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContraseña());
            ps.setString(3, usuario.getRol());
            ps.setString(4, usuario.getEstado());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Usuarios usuario) {
        String sql = "UPDATE usuarios SET nombre_usuario = ?, contrasena = ?, rol = ?, estado = ? WHERE id_usuario = ?";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContraseña());
            ps.setString(3, usuario.getRol());
            ps.setString(4, usuario.getEstado());
            ps.setInt(5, usuario.getIdUsuario());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    public Usuarios seleccionarPorId(int id) {
        String sql = "SELECT id_usuario, nombre_usuario, contrasena, rol, estado FROM usuarios WHERE id_usuario = ?";
        Usuarios usuario = null;

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuarios(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre_usuario"),
                        rs.getString("contrasena"),
                        rs.getString("rol"),
                        rs.getString("estado")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar usuario por ID: " + e.getMessage());
        }
        return usuario;
    }

    public List<Usuarios> seleccionarTodos() {
        List<Usuarios> usuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, nombre_usuario, contrasena, rol, estado FROM usuarios";

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuarios usuario = new Usuarios(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre_usuario"),
                    rs.getString("contrasena"),
                    rs.getString("rol"),
                    rs.getString("estado")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar todos los usuarios: " + e.getMessage());
        }
        return usuarios;
    }
}