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

@WebServlet(name = "SvActualizarProveedor", urlPatterns = {"/SvActualizarProveedor"})
public class SvActualizarProveedor extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Validar ID
            int idProveedor;
            try {
                idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/listarProveedores.jsp?error=ID inválido");
                return;
            }
            
            // Validar campos obligatorios
            String rut = request.getParameter("rut");
            String nombre = request.getParameter("nombre");
            
            if (rut == null || rut.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/editarProveedor.jsp?id=" + idProveedor + "&error=RUT y Nombre son obligatorios");
                return;
            }
            
            // Crear y actualizar proveedor
            Proveedor proveedor = new Proveedor();
            proveedor.setIdProveedor(idProveedor);
            proveedor.setRutProveedor(rut);
            proveedor.setNombreProveedor(nombre);
            proveedor.setTelefonoProveedor(request.getParameter("telefono"));
            proveedor.setDireccionProveedor(request.getParameter("direccion"));
            
            ProveedorDAO dao = new ProveedorDAO();
            boolean exito = dao.actualizarProveedor(proveedor);
            
            // Redireccionar
            String redirect = exito 
                ? request.getContextPath() + "/listarProveedores.jsp?exito=Proveedor actualizado correctamente"
                : request.getContextPath() + "/editarProveedor.jsp?id=" + idProveedor + "&error=Error al actualizar Proveedor";
            
            response.sendRedirect(redirect);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarProveedores.jsp?error=Error de base de datos");
        }
    }
}