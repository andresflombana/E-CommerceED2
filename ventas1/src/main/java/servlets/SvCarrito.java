package servlets;

import umariana.ventas1.dao.ProductoDAO;
import umariana.ventas1.modelo.DetalleVenta;
import umariana.ventas1.modelo.Producto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvCarrito", urlPatterns = {"/SvCarrito"})
public class SvCarrito extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        List<DetalleVenta> carrito = (List<DetalleVenta>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        String accion = request.getParameter("accion");
        ProductoDAO productoDAO = new ProductoDAO();

        try {
            switch (accion) {
                case "agregar":
                    int idProducto = Integer.parseInt(request.getParameter("idProducto"));
                    int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                    
                    Producto producto = productoDAO.obtenerProductoPorId(idProducto);
                    if (producto == null || producto.getCantidadInventario() < cantidad) {
                        response.sendRedirect("nuevaVenta.jsp?error=Stock insuficiente o producto no existe");
                        return;
                    }
                    
                    DetalleVenta item = new DetalleVenta();
                    item.setIdProducto(idProducto);
                    item.setCantidad(cantidad);
                    item.setPrecioUnitario(producto.getPrecioActual());
                    item.setSubtotal(producto.getPrecioActual() * cantidad);
                    carrito.add(item);
                    break;

                case "eliminar":
                    int indexEliminar = Integer.parseInt(request.getParameter("index"));
                    if (indexEliminar >= 0 && indexEliminar < carrito.size()) {
                        carrito.remove(indexEliminar);
                    }
                    break;

                case "actualizar":
                    int indexActualizar = Integer.parseInt(request.getParameter("index"));
                    int nuevaCantidad = Integer.parseInt(request.getParameter("cantidad"));
                    if (indexActualizar >= 0 && indexActualizar < carrito.size()) {
                        DetalleVenta detalle = carrito.get(indexActualizar);
                        Producto p = productoDAO.obtenerProductoPorId(detalle.getIdProducto());
                        if (p != null && p.getCantidadInventario() >= nuevaCantidad) {
                            detalle.setCantidad(nuevaCantidad);
                            detalle.setSubtotal(detalle.getPrecioUnitario() * nuevaCantidad);
                        }
                    }
                    break;
            }

            session.setAttribute("carrito", carrito);
            response.sendRedirect("nuevaVenta.jsp");

        } catch (NumberFormatException | SQLException e) {
            response.sendRedirect("nuevaVenta.jsp?error=Error en el carrito: " + e.getMessage());
        }
    }
}