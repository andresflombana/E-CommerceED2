/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import umariana.ventas1.dao.VentaDAO;
import umariana.ventas1.dao.DetalleVentaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvEliminarVenta", urlPatterns = {"/SvEliminarVenta"})
public class SvEliminarVenta extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int idVenta = Integer.parseInt(request.getParameter("id"));

            VentaDAO ventaDAO = new VentaDAO();
            DetalleVentaDAO detalleDAO = new DetalleVentaDAO();

            // Eliminar detalles primero (por integridad referencial)
            detalleDAO.eliminarDetallesPorVenta(idVenta);
            boolean exito = ventaDAO.eliminarVenta(idVenta);

            if (exito) {
                response.sendRedirect("listarVentas.jsp?exito=Venta eliminada");
            } else {
                response.sendRedirect("listarVentas.jsp?error=Error al eliminar venta");
            }

        } catch (SQLException | NumberFormatException e) {
            response.sendRedirect("listarVentas.jsp?error=" + e.getMessage());
        }
    }
}