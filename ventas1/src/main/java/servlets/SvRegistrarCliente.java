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

@WebServlet(name = "SvRegistrarCliente", urlPatterns = {"/SvRegistrarCliente"})
public class SvRegistrarCliente extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Validar campos obligatorios
            String rut = request.getParameter("rut");
            String nombre = request.getParameter("nombre");
            
            if (rut == null || rut.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/registrarCliente.jsp?error=RUT y Nombre son obligatorios");
                return;
            }
            
            // Crear objeto cliente
            Cliente cliente = new Cliente();
            cliente.setRutCliente(rut);
            cliente.setNombreCliente(nombre);
            cliente.setTelefonosCliente(request.getParameter("telefono"));
            
            // Registrar en BD
            ClienteDAO dao = new ClienteDAO();
            boolean exito = dao.registrarCliente(cliente);
            
            // Redireccionar con mensaje
            String redirectPath = exito 
                ? request.getContextPath() + "/listarClientes.jsp?exito=Cliente registrado exitosamente"
                : request.getContextPath() + "/registrarCliente.jsp?error=Error al registrar cliente";
            
            response.sendRedirect(redirectPath);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/registrarCliente.jsp?error=Error de base de datos: " + e.getMessage());
        }
    }
}