package umariana.ventas1.dao;

import umariana.ventas1.modelo.Producto;
import umariana.ventas1.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    
    public List<Producto> listarProductos(int pagina, int registrosPorPagina) throws SQLException {
        ArrayList<Producto> misProductos = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        int offset = (pagina - 1) * registrosPorPagina;
        
        try {
            conexion = ConexionBD.conectar();
            consulta = conexion.prepareStatement(
                "SELECT id_producto, nombre_producto, precio_actual, cantidad_inventario, "
                + "id_proveedor, id_categoria FROM producto LIMIT ? OFFSET ?"
            );
            consulta.setInt(1, registrosPorPagina);
            consulta.setInt(2, offset);
            resultado = consulta.executeQuery();
            
            while(resultado.next()) {
                Producto miProducto = new Producto();
                miProducto.setIdProducto(resultado.getInt("id_producto"));
                miProducto.setNombreProducto(resultado.getString("nombre_producto"));
                miProducto.setPrecioActual(resultado.getDouble("precio_actual"));
                miProducto.setCantidadInventario(resultado.getInt("cantidad_inventario"));
                miProducto.setIdProveedor(resultado.getInt("id_proveedor"));
                miProducto.setIdCategoria(resultado.getInt("id_categoria"));
                misProductos.add(miProducto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultado != null) resultado.close();
            if (consulta != null) consulta.close();
            if (conexion != null) conexion.close();
        }
        return misProductos;
    }
    
    public boolean registrarProducto(Producto producto) throws SQLException {
        Connection conexion = null;
        PreparedStatement consulta = null;
        boolean registrado = false;
        
        try {
            conexion = ConexionBD.conectar();
            String sql = "INSERT INTO producto(nombre_producto, precio_actual, cantidad_inventario, "
                        + "id_proveedor, id_categoria, created_at, updated_at) "
                        + "VALUES(?, ?, ?, ?, ?, NOW(), NOW())";
            consulta = conexion.prepareStatement(sql);
            consulta.setString(1, producto.getNombreProducto());
            consulta.setDouble(2, producto.getPrecioActual());
            consulta.setInt(3, producto.getCantidadInventario());
            consulta.setInt(4, producto.getIdProveedor());
            consulta.setInt(5, producto.getIdCategoria());
            
            int filasAfectadas = consulta.executeUpdate();
            registrado = filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (consulta != null) consulta.close();
            if (conexion != null) conexion.close();
        }
        return registrado;
    }
    
    public Producto obtenerProductoPorId(int id) throws SQLException {
    Connection conexion = null;
    PreparedStatement consulta = null;
    ResultSet resultado = null;
    Producto producto = null;
    
    try {
        conexion = ConexionBD.conectar();
        String sql = "SELECT * FROM producto WHERE id_producto = ?";
        consulta = conexion.prepareStatement(sql);
        consulta.setInt(1, id);
        resultado = consulta.executeQuery();
        
        if (resultado.next()) {
            producto = new Producto();
            producto.setIdProducto(resultado.getInt("id_producto"));
            producto.setNombreProducto(resultado.getString("nombre_producto"));
            producto.setPrecioActual(resultado.getDouble("precio_actual"));
            producto.setCantidadInventario(resultado.getInt("cantidad_inventario"));
            producto.setIdProveedor(resultado.getInt("id_proveedor"));
            producto.setIdCategoria(resultado.getInt("id_categoria"));
        }
    } finally {
        if (resultado != null) resultado.close();
        if (consulta != null) consulta.close();
        if (conexion != null) conexion.close();
    }
    return producto;
}
    
    public boolean actualizarProducto(Producto producto) throws SQLException {
        Connection conexion = null;
        PreparedStatement consulta = null;
        boolean actualizado = false;
        
        try {
            conexion = ConexionBD.conectar();
            String sql = "UPDATE producto SET nombre_producto = ?, precio_actual = ?, "
                        + "cantidad_inventario = ?, id_proveedor = ?, id_categoria = ?, "
                        + "updated_at = NOW() WHERE id_producto = ?";
            consulta = conexion.prepareStatement(sql);
            consulta.setString(1, producto.getNombreProducto());
            consulta.setDouble(2, producto.getPrecioActual());
            consulta.setInt(3, producto.getCantidadInventario());
            consulta.setInt(4, producto.getIdProveedor());
            consulta.setInt(5, producto.getIdCategoria());
            consulta.setInt(6, producto.getIdProducto());
            
            int filasAfectadas = consulta.executeUpdate();
            actualizado = filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (consulta != null) consulta.close();
            if (conexion != null) conexion.close();
        }
        return actualizado;
    }
    
    public boolean eliminarProducto(int idProducto) throws SQLException {
        Connection conexion = null;
        PreparedStatement consulta = null;
        boolean eliminado = false;
        
        try {
            conexion = ConexionBD.conectar();
            String sql = "DELETE FROM producto WHERE id_producto = ?";
            consulta = conexion.prepareStatement(sql);
            consulta.setInt(1, idProducto);
            
            int filasAfectadas = consulta.executeUpdate();
            eliminado = filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (consulta != null) consulta.close();
            if (conexion != null) conexion.close();
        }
        return eliminado;
    }
    
    public boolean actualizarStock(int idProducto, int cantidad) throws SQLException {
    String sql = "UPDATE producto SET cantidad_inventario = cantidad_inventario + ? WHERE id_producto = ?";
    try (Connection conn = ConexionBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, cantidad);
        stmt.setInt(2, idProducto);
        return stmt.executeUpdate() > 0;
    }
}
}