package servlets;

import umariana.ventas1.dao.ClienteDAO;
import umariana.ventas1.modelo.Cliente;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvActualizarCliente", urlPatterns = {"/SvActualizarCliente"})
public class SvActualizarCliente extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Validar ID
            int idCliente;
            try {
                idCliente = Integer.parseInt(request.getParameter("idCliente"));
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/listarClientes.jsp?error=ID inválido");
                return;
            }
            
            // Validar campos obligatorios
            String rut = request.getParameter("rut");
            String nombre = request.getParameter("nombre");
            
            if (rut == null || rut.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/editarCliente.jsp?id=" + idCliente + "&error=RUT y Nombre son obligatorios");
                return;
            }
            
            // Crear y actualizar cliente
            Cliente cliente = new Cliente();
            cliente.setIdCliente(idCliente);
            cliente.setRutCliente(rut);
            cliente.setNombreCliente(nombre);
            cliente.setTelefonosCliente(request.getParameter("telefono"));
            
            ClienteDAO dao = new ClienteDAO();
            boolean exito = dao.actualizarCliente(cliente);
            
            // Redireccionar
            String redirect = exito 
                ? request.getContextPath() + "/listarClientes.jsp?exito=Cliente actualizado correctamente"
                : request.getContextPath() + "/editarCliente.jsp?id=" + idCliente + "&error=Error al actualizar Cliente";
            
            response.sendRedirect(redirect);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarClientes.jsp?error=Error de base de datos");
        }
    }
}