package Modelo.Dao;


import Modelo.ConexionBD;
import Modelo.Cultivo;
import Modelo.ProductoAlmacenado;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoAlmacenadoDAO {

    private CultivoDAO cultivoDAO = new CultivoDAO();

    public boolean insertar(int idAlmacen, ProductoAlmacenado producto) {
        String sql = "INSERT INTO productos_almacenados (id_almacen, id_cultivo, cantidad_kg, fecha_ingreso, fecha_egreso) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAlmacen);
            ps.setInt(2, producto.getCultivo().getId());
            ps.setDouble(3, producto.getCantidadKg());
            ps.setDate(4, Date.valueOf(producto.getFechaIngreso()));
            ps.setDate(5, producto.getFechaEgreso() != null ? Date.valueOf(producto.getFechaEgreso()) : null);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar producto almacenado: " + e.getMessage());
            return false;
        }
    }

    public List<ProductoAlmacenado> seleccionarPorAlmacenId(int idAlmacen) {
        List<ProductoAlmacenado> productos = new ArrayList<>();
        String sql = "SELECT id_producto_almacenado, id_cultivo, cantidad_kg, fecha_ingreso, fecha_egreso FROM productos_almacenados WHERE id_almacen = ?";

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAlmacen);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idCultivo = rs.getInt("id_cultivo");
                    Cultivo cultivo = cultivoDAO.seleccionarPorId(idCultivo); // Obtener el objeto Cultivo

                    ProductoAlmacenado producto = new ProductoAlmacenado(
                        rs.getInt("id_producto_almacenado"),
                        cultivo,
                        rs.getDouble("cantidad_kg"),
                        rs.getDate("fecha_ingreso").toLocalDate(),
                        rs.getDate("fecha_egreso") != null ? rs.getDate("fecha_egreso").toLocalDate() : null
                    );
                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar productos por ID de almacén: " + e.getMessage());
        }
        return productos;
    }
}