/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import umariana.ventas1.dao.CategoriaDAO;
import umariana.ventas1.modelo.Categoria;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvListarCategorias", urlPatterns = {"/SvListarCategorias"})
public class SvListarCategorias extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Configuración de paginación
            int pagina = request.getParameter("pagina") != null 
                       ? Integer.parseInt(request.getParameter("pagina")) : 1;
            int registrosPorPagina = 10;
            
            // Obtener datos
            CategoriaDAO dao = new CategoriaDAO();
            List<Categoria> categorias = dao.listarCategorias(pagina, registrosPorPagina);
            
            // Calcular total de páginas (requeriría método contarCategorias() en DAO)
            // int totalPaginas = (int) Math.ceil(dao.contarCategorias() / (double) registrosPorPagina);
            
            // Enviar atributos
            request.setAttribute("categorias", categorias);
            request.setAttribute("paginaActual", pagina);
            // request.setAttribute("totalPaginas", totalPaginas);
            
            // Redireccionar
            request.getRequestDispatcher("/listarCategorias.jsp").forward(request, response);
            
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp?mensaje=Error al listar categorías");
        }
    }
}
