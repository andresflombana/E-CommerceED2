package umariana.ventas1.dao;

import umariana.ventas1.modelo.Proveedor;
import umariana.ventas1.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    
    public List<Proveedor> listarProveedores(int pagina, int registrosPorPagina) throws SQLException {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT id_proveedor, rut_proveedor, nombre_proveedor, telefono_proveedor, direccion_proveedor FROM proveedor LIMIT ? OFFSET ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, registrosPorPagina);
            stmt.setInt(2, (pagina - 1) * registrosPorPagina);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Proveedor prov = new Proveedor();
                    prov.setIdProveedor(rs.getInt("id_proveedor"));
                    prov.setRutProveedor(rs.getString("rut_proveedor"));
                    prov.setNombreProveedor(rs.getString("nombre_proveedor"));
                    prov.setTelefonoProveedor(rs.getString("telefono_proveedor"));
                    prov.setDireccionProveedor(rs.getString("direccion_proveedor"));
                    proveedores.add(prov);
                }
            }
        }
        return proveedores;
    }
    
    public boolean registrarProveedor(Proveedor proveedor) throws SQLException {
        String sql = "INSERT INTO proveedor(rut_proveedor, nombre_proveedor, telefono_proveedor, direccion_proveedor) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, proveedor.getRutProveedor());
            stmt.setString(2, proveedor.getNombreProveedor());
            stmt.setString(3, proveedor.getTelefonoProveedor());
            stmt.setString(4, proveedor.getDireccionProveedor());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean actualizarProveedor(Proveedor proveedor) throws SQLException {
        String sql = "UPDATE proveedor SET rut_proveedor = ?, nombre_proveedor = ?, telefono_proveedor = ?, direccion_proveedor = ? WHERE id_proveedor = ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, proveedor.getRutProveedor());
            stmt.setString(2, proveedor.getNombreProveedor());
            stmt.setString(3, proveedor.getTelefonoProveedor());
            stmt.setString(4, proveedor.getDireccionProveedor());
            stmt.setInt(5, proveedor.getIdProveedor());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean eliminarProveedor(int idProveedor) throws SQLException {
        String sql = "DELETE FROM proveedor WHERE id_proveedor = ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idProveedor);
            return stmt.executeUpdate() > 0;
        }
    }
    
    public Proveedor obtenerProveedorPorId(int id) throws SQLException {
        String sql = "SELECT * FROM proveedor WHERE id_proveedor = ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Proveedor(
                        rs.getInt("id_proveedor"),
                        rs.getString("rut_proveedor"),
                        rs.getString("nombre_proveedor"),
                        rs.getString("telefono_proveedor"),
                        rs.getString("direccion_proveedor")
                    );
                }
            }
        }
        return null;
    }
}