package servlets;

import umariana.ventas1.dao.ProductoDAO;
import umariana.ventas1.modelo.Producto;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvRegistrarProducto", urlPatterns = {"/SvRegistrarProducto"})
public class SvRegistrarProducto extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Producto producto = new Producto();
            producto.setNombreProducto(request.getParameter("nombre"));
            producto.setPrecioActual(Double.parseDouble(request.getParameter("precio")));
            producto.setCantidadInventario(Integer.parseInt(request.getParameter("stock")));
            producto.setIdProveedor(Integer.parseInt(request.getParameter("proveedor")));
            producto.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
            
            ProductoDAO dao = new ProductoDAO();
            boolean exito = dao.registrarProducto(producto);
            
            if (exito) {
                response.sendRedirect(request.getContextPath() + "/listarProductos.jsp?exito=Producto registrado correctamente");
            } else {
                response.sendRedirect(request.getContextPath() + "/agregarProducto.jsp?error=Error al registrar producto");
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/agregarProducto.jsp?error=Error en el sistema: " + e.getMessage());
        }
    }
}
