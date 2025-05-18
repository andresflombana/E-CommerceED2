/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import umariana.ventas1.dao.ProductoDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvEliminarProducto", urlPatterns = {"/SvEliminarProducto"})
public class SvEliminarProducto extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idProducto = Integer.parseInt(request.getParameter("id"));
            ProductoDAO dao = new ProductoDAO();
            boolean exito = dao.eliminarProducto(idProducto);

            if (exito) {
                response.sendRedirect(request.getContextPath() + "/listarProductos.jsp?exito=Producto eliminado correctamente");
            } else {
                response.sendRedirect(request.getContextPath() + "/listarProductos.jsp?error=Error al eliminar producto");
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarProductos.jsp?error=Error en el sistema: " + e.getMessage());
        }
    }
}