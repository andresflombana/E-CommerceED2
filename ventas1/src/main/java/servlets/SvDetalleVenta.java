/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import umariana.ventas1.dao.DetalleVentaDAO;
import umariana.ventas1.dao.VentaDAO;
import umariana.ventas1.modelo.DetalleVenta;
import umariana.ventas1.modelo.Venta;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvDetalleVenta", urlPatterns = {"/SvDetalleVenta"})
public class SvDetalleVenta extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int idVenta = Integer.parseInt(request.getParameter("id"));

            VentaDAO ventaDAO = new VentaDAO();
            DetalleVentaDAO detalleDAO = new DetalleVentaDAO();

            Venta venta = ventaDAO.obtenerVentaPorId(idVenta);
            List<DetalleVenta> detalles = detalleDAO.listarPorVenta(idVenta);

            if (venta != null) {
                request.setAttribute("venta", venta);
                request.setAttribute("detalles", detalles);
                request.getRequestDispatcher("detalleVenta.jsp").forward(request, response);
            } else {
                response.sendRedirect("listarVentas.jsp?error=Venta no encontrada");
            }

        } catch (SQLException | NumberFormatException e) {
            response.sendRedirect("listarVentas.jsp?error=" + e.getMessage());
        }
    }
}