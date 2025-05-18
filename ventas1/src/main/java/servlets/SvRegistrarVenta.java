package servlets;

import umariana.ventas1.dao.VentaDAO;
import umariana.ventas1.dao.DetalleVentaDAO;
import umariana.ventas1.dao.ProductoDAO;
import umariana.ventas1.modelo.Venta;
import umariana.ventas1.modelo.DetalleVenta;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvRegistrarVenta", urlPatterns = {"/SvRegistrarVenta"})
public class SvRegistrarVenta extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        List<DetalleVenta> carrito = (List<DetalleVenta>) session.getAttribute("carrito");
        if (carrito == null || carrito.isEmpty()) {
            response.sendRedirect("nuevaVenta.jsp?error=Carrito vacío");
            return;
        }

        try {
            // Validar cliente seleccionado
            if (request.getParameter("idCliente") == null || request.getParameter("idCliente").isEmpty()) {
                response.sendRedirect("nuevaVenta.jsp?error=Seleccione un cliente");
                return;
            }

            // 1. Obtener datos de la venta
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            double descuento = Double.parseDouble(request.getParameter("descuento"));

            // 2. Calcular total
            double subtotal = carrito.stream().mapToDouble(d -> d.getSubtotal()).sum();
            double montoTotal = subtotal - (subtotal * (descuento / 100));

            // 3. Registrar venta
            Venta venta = new Venta();
            venta.setFechaVenta(new java.util.Date().toString());
            venta.setDescuento(descuento);
            venta.setMontoTotal(montoTotal);
            venta.setIdCliente(idCliente);

            VentaDAO ventaDAO = new VentaDAO();
            int idVenta = ventaDAO.registrarVenta(venta);

            if (idVenta <= 0) {
                response.sendRedirect("nuevaVenta.jsp?error=Error al registrar venta");
                return;
            }

            // 4. Registrar detalles y actualizar stock
            DetalleVentaDAO detalleDAO = new DetalleVentaDAO();
            ProductoDAO productoDAO = new ProductoDAO();

            for (DetalleVenta detalle : carrito) {
                detalle.setIdVenta(idVenta);
                detalleDAO.registrarDetalle(detalle);
                productoDAO.actualizarStock(detalle.getIdProducto(), -detalle.getCantidad());
            }

            // 5. Limpiar carrito y redirigir
            session.removeAttribute("carrito");
            response.sendRedirect("detalleVenta.jsp?id=" + idVenta + "&exito=Venta registrada correctamente");

        } catch (SQLException | NumberFormatException e) {
            response.sendRedirect("nuevaVenta.jsp?error=" + e.getMessage());
        }
    }
}