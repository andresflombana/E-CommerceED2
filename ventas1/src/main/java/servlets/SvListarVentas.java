/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import umariana.ventas1.dao.VentaDAO;
import umariana.ventas1.modelo.Venta;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvListarVentas", urlPatterns = {"/SvListarVentas"})
public class SvListarVentas extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // Configurar paginación
            int pagina = request.getParameter("pagina") != null 
                       ? Integer.parseInt(request.getParameter("pagina")) : 1;
            int registrosPorPagina = 10;

            // Obtener ventas
            VentaDAO dao = new VentaDAO();
            List<Venta> ventas = dao.listarVentas(pagina, registrosPorPagina);

            // Enviar datos al JSP
            request.setAttribute("ventas", ventas);
            request.setAttribute("paginaActual", pagina);
            request.setAttribute("totalPaginas", (dao.contarVentas() / registrosPorPagina) + 1);
            
            request.getRequestDispatcher("listarVentas.jsp").forward(request, response);

        } catch (SQLException | NumberFormatException e) {
            response.sendRedirect("listarVentas.jsp?mensaje=Error al listar ventas: " + e.getMessage());
        }
    }
}