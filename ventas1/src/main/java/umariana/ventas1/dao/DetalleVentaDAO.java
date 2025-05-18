package umariana.ventas1.dao;

import umariana.ventas1.modelo.DetalleVenta;
import umariana.ventas1.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAO {
    
    public boolean registrarDetalle(DetalleVenta detalle) throws SQLException {
        String sql = "INSERT INTO detalle_venta(id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, detalle.getIdVenta());
            stmt.setInt(2, detalle.getIdProducto());
            stmt.setInt(3, detalle.getCantidad());
            stmt.setDouble(4, detalle.getPrecioUnitario());
            stmt.setDouble(5, detalle.getSubtotal());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    public List<DetalleVenta> listarPorVenta(int idVenta) throws SQLException {
        List<DetalleVenta> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalle_venta WHERE id_venta = ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idVenta);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    detalles.add(new DetalleVenta(
                        rs.getInt("id_detalle"),
                        rs.getInt("id_venta"),
                        rs.getInt("id_producto"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio_unitario"),
                        rs.getDouble("subtotal")
                    ));
                }
            }
        }
        return detalles;
    }
    
    public boolean eliminarDetallesPorVenta(int idVenta) throws SQLException {
    String sql = "DELETE FROM detalle_venta WHERE id_venta = ?";
    try (Connection conn = ConexionBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idVenta);
        return stmt.executeUpdate() > 0;
    }
}
}