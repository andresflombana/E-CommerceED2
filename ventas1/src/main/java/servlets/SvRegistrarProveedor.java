package servlets;

import umariana.ventas1.dao.ProveedorDAO;
import umariana.ventas1.modelo.Proveedor;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvRegistrarProveedor", urlPatterns = {"/SvRegistrarProveedor"})
public class SvRegistrarProveedor extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Validar campos obligatorios
            String rut = request.getParameter("rut");
            String nombre = request.getParameter("nombre");
            
            if (rut == null || rut.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/agregarProveedor.jsp?error=RUT y Nombre son obligatorios");
                return;
            }
            
            // Crear objeto proveedor
            Proveedor proveedor = new Proveedor();
            proveedor.setRutProveedor(rut);
            proveedor.setNombreProveedor(nombre);
            proveedor.setTelefonoProveedor(request.getParameter("telefono"));
            proveedor.setDireccionProveedor(request.getParameter("direccion"));
            
            // Registrar en BD
            ProveedorDAO dao = new ProveedorDAO();
            boolean exito = dao.registrarProveedor(proveedor);
            
            // Redireccionar con mensaje
            String redirectPath = exito 
                ? request.getContextPath() + "/listarProveedores.jsp?exito=Proveedor registrado exitosamente"
                : request.getContextPath() + "/agregarProveedor.jsp?error=Error al registrar proveedor";
            
            response.sendRedirect(redirectPath);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/agregarProveedor.jsp?error=Error de base de datos: " + e.getMessage());
        }
    }
}