package umariana.ventas1.dao;

import umariana.ventas1.modelo.Cliente;
import umariana.ventas1.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    
    public List<Cliente> listarClientes(int pagina, int registrosPorPagina) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT id_cliente, rut_cliente, nombre_cliente, telefonos_cliente FROM cliente LIMIT ? OFFSET ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, registrosPorPagina);
            stmt.setInt(2, (pagina - 1) * registrosPorPagina);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cliente cli = new Cliente();
                    cli.setIdCliente(rs.getInt("id_cliente"));
                    cli.setRutCliente(rs.getString("rut_cliente"));
                    cli.setNombreCliente(rs.getString("nombre_cliente"));
                    cli.setTelefonosCliente(rs.getString("telefonos_cliente"));
                    clientes.add(cli);
                }
            }
        }
        return clientes;
    }
    
    public boolean registrarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente(rut_cliente, nombre_cliente, telefonos_cliente) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getRutCliente());
            stmt.setString(2, cliente.getNombreCliente());
            stmt.setString(3, cliente.getTelefonosCliente());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean actualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET rut_cliente = ?, nombre_cliente = ?, telefonos_cliente = ? WHERE id_cliente = ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getRutCliente());
            stmt.setString(2, cliente.getNombreCliente());
            stmt.setString(3, cliente.getTelefonosCliente());
            stmt.setInt(4, cliente.getIdCliente());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean eliminarCliente(int idCliente) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idCliente);
            return stmt.executeUpdate() > 0;
        }
    }
    
    public Cliente obtenerClientePorId(int id) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("rut_cliente"),
                        rs.getString("nombre_cliente"),
                        rs.getString("telefonos_cliente")
                    );
                }
            }
        }
        return null;
    }
}
