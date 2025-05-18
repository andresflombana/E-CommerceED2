package umariana.ventas1.dao;

import umariana.ventas1.modelo.Categoria;
import umariana.ventas1.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    
    public List<Categoria> listarCategorias(int pagina, int registrosPorPagina) throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id_categoria, nombre_categoria, descripcion_categoria FROM categoria LIMIT ? OFFSET ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, registrosPorPagina);
            stmt.setInt(2, (pagina - 1) * registrosPorPagina);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Categoria cat = new Categoria();
                    cat.setIdCategoria(rs.getInt("id_categoria"));
                    cat.setNombreCategoria(rs.getString("nombre_categoria"));
                    cat.setDescripcionCategoria(rs.getString("descripcion_categoria"));
                    categorias.add(cat);
                }
            }
        }
        return categorias;
    }
    
    public boolean registrarCategoria(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categoria(nombre_categoria, descripcion_categoria) VALUES (?, ?)";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, categoria.getNombreCategoria());
            stmt.setString(2, categoria.getDescripcionCategoria());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean actualizarCategoria(Categoria categoria) throws SQLException {
        String sql = "UPDATE categoria SET nombre_categoria = ?, descripcion_categoria = ? WHERE id_categoria = ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, categoria.getNombreCategoria());
            stmt.setString(2, categoria.getDescripcionCategoria());
            stmt.setInt(3, categoria.getIdCategoria());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean eliminarCategoria(int idCategoria) throws SQLException {
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idCategoria);
            return stmt.executeUpdate() > 0;
        }
    }
    
    public Categoria obtenerCategoriaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Categoria(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre_categoria"),
                        rs.getString("descripcion_categoria")
                    );
                }
            }
        }
        return null;
    }
}