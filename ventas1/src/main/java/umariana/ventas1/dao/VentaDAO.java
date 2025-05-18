package umariana.ventas1.dao;

import umariana.ventas1.modelo.Venta;
import umariana.ventas1.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {
    
    public List<Venta> listarVentas(int pagina, int registrosPorPagina) throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT id_venta, fecha_venta, descuento, monto_total, id_cliente FROM venta LIMIT ? OFFSET ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, registrosPorPagina);
            stmt.setInt(2, (pagina - 1) * registrosPorPagina);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venta venta = new Venta();
                    venta.setIdVenta(rs.getInt("id_venta"));
                    venta.setFechaVenta(rs.getString("fecha_venta"));
                    venta.setDescuento(rs.getDouble("descuento"));
                    venta.setMontoTotal(rs.getDouble("monto_total"));
                    venta.setIdCliente(rs.getInt("id_cliente"));
                    ventas.add(venta);
                }
            }
        }
        return ventas;
    }
    
    public int registrarVenta(Venta venta) throws SQLException {
        String sql = "INSERT INTO venta(fecha_venta, descuento, monto_total, id_cliente) VALUES (?, ?, ?, ?)";
        int idGenerado = -1;
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, venta.getFechaVenta());
            stmt.setDouble(2, venta.getDescuento());
            stmt.setDouble(3, venta.getMontoTotal());
            stmt.setInt(4, venta.getIdCliente());
            
            stmt.executeUpdate();
            
            // Obtener el ID generado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                }
            }
        }
        return idGenerado; // Retorna el ID de la venta para usar en los detalles
    }
    
    public boolean actualizarVenta(Venta venta) throws SQLException {
        String sql = "UPDATE venta SET fecha_venta = ?, descuento = ?, monto_total = ?, id_cliente = ? WHERE id_venta = ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, venta.getFechaVenta());
            stmt.setDouble(2, venta.getDescuento());
            stmt.setDouble(3, venta.getMontoTotal());
            stmt.setInt(4, venta.getIdCliente());
            stmt.setInt(5, venta.getIdVenta());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean eliminarVenta(int idVenta) throws SQLException {
        String sql = "DELETE FROM venta WHERE id_venta = ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idVenta);
            return stmt.executeUpdate() > 0;
        }
    }
    
    public Venta obtenerVentaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM venta WHERE id_venta = ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Venta(
                        rs.getInt("id_venta"),
                        rs.getString("fecha_venta"),
                        rs.getDouble("descuento"),
                        rs.getDouble("monto_total"),
                        rs.getInt("id_cliente")
                    );
                }
            }
        }
        return null;
    }
    
    public int contarVentas() throws SQLException {
    String sql = "SELECT COUNT(*) AS total FROM venta";
    try (Connection conn = ConexionBD.conectar();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        if (rs.next()) {
            return rs.getInt("total");
        }
    }
    return 0;
}
}