package servlets;

import umariana.ventas1.dao.ClienteDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvEliminarCliente", urlPatterns = {"/SvEliminarCliente"})
public class SvEliminarCliente extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Validar ID
            int idCliente;
            try {
                idCliente = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/listarClientes.jsp?error=ID inválido");
                return;
            }
            
            // Eliminar cliente
            ClienteDAO dao = new ClienteDAO();
            boolean exito = dao.eliminarCliente(idCliente);
            
            // Redireccionar con mensaje
            String mensaje = exito 
                ? "exito=Cliente eliminado correctamente" 
                : "error=No se pudo eliminar el cliente";
            
            response.sendRedirect(request.getContextPath() + "/listarClientes.jsp?" + mensaje);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarClientes.jsp?error=Error de base de datos: " + e.getMessage());
        }
    }
}