/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import umariana.ventas1.dao.CategoriaDAO;
import umariana.ventas1.modelo.Categoria;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvActualizarCategoria", urlPatterns = {"/SvActualizarCategoria"})
public class SvActualizarCategoria extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Validar ID
            int idCategoria;
            try {
                idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/listarCategorias.jsp?error=ID inválido");
                return;
            }
            
            // Obtener parámetros
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            
            // Validar nombre
            if (nombre == null || nombre.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/editarCategoria.jsp?id=" + idCategoria + "&error=Nombre requerido");
                return;
            }
            
            // Crear y actualizar categoría
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(idCategoria);
            categoria.setNombreCategoria(nombre);
            categoria.setDescripcionCategoria(descripcion != null ? descripcion : "");
            
            CategoriaDAO dao = new CategoriaDAO();
            boolean exito = dao.actualizarCategoria(categoria);
            
            // Redireccionar
            String redirect = exito 
                ? request.getContextPath() + "/listarCategorias.jsp?exito=Categoría actualizada"
                : request.getContextPath() + "/editarCategoria.jsp?id=" + idCategoria + "&error=Error al actualizar Categoria";
            
            response.sendRedirect(redirect);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listarCategorias.jsp?error=Error de base de datos");
        }
    }
}
