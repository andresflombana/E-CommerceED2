/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import umariana.ventas1.dao.CategoriaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvEliminarCategoria", urlPatterns = {"/SvEliminarCategoria"})
public class SvEliminarCategoria extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Validar ID
            int idCategoria;
            try {
                idCategoria = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/listarCategorias.jsp?error=ID inválido");
                return;
            }
            
            // Eliminar categoría
            CategoriaDAO dao = new CategoriaDAO();
            boolean exito = dao.eliminarCategoria(idCategoria);
            
            // Redireccionar con mensaje
            String mensaje = exito 
                ? "exito=Categoría eliminada correctamente" 
                : "error=No se pudo eliminar la categoría";
            
            response.sendRedirect(request.getContextPath() + "/listarCategorias.jsp?" + mensaje);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarCategorias.jsp?error=Error de base de datos: " + e.getMessage());
        }
    }
}