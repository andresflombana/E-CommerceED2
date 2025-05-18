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

@WebServlet(name = "SvRegistrarCategoria", urlPatterns = {"/SvRegistrarCategoria"})
public class SvRegistrarCategoria extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 1. Obtener parámetros del formulario
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            
            // 2. Validar parámetros
            if (nombre == null || nombre.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/registrarCategoria.jsp?error=Nombre requerido");
                return;
            }
            
            // 3. Crear objeto y registrar
            Categoria categoria = new Categoria();
            categoria.setNombreCategoria(nombre);
            categoria.setDescripcionCategoria(descripcion != null ? descripcion : "");
            
            CategoriaDAO dao = new CategoriaDAO();
            boolean exito = dao.registrarCategoria(categoria);
            
            // 4. Redireccionar con mensaje
            String redirect = exito 
                ? request.getContextPath() + "/listarCategorias.jsp?exito=Categoría registrada correctamente"
                : request.getContextPath() + "/registrarCategoria.jsp?error=Error al registrar Categoria";
            
            response.sendRedirect(redirect);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/registrarCategoria.jsp?error=Error de base de datos: " + e.getMessage());
        }
    }
}