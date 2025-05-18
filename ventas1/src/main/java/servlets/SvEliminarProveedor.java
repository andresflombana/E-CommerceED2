package servlets;

import umariana.ventas1.dao.ProveedorDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvEliminarProveedor", urlPatterns = {"/SvEliminarProveedor"})
public class SvEliminarProveedor extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Validar ID
            int idProveedor;
            try {
                idProveedor = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/listarProveedores.jsp?error=ID inválido");
                return;
            }
            
            // Eliminar proveedor
            ProveedorDAO dao = new ProveedorDAO();
            boolean exito = dao.eliminarProveedor(idProveedor);
            
            // Redireccionar con mensaje
            String mensaje = exito 
                ? "exito=Proveedor eliminado correctamente" 
                : "error=No se pudo eliminar el proveedor";
            
            response.sendRedirect(request.getContextPath() + "/listarProveedores.jsp?" + mensaje);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarProveedores.jsp?error=Error de base de datos: " + e.getMessage());
        }
    }
}