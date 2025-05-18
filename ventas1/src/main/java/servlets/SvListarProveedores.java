package servlets;

import umariana.ventas1.dao.ProveedorDAO;
import umariana.ventas1.modelo.Proveedor;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvListarProveedores", urlPatterns = {"/SvListarProveedores"})
public class SvListarProveedores extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Configurar paginación
            int pagina = request.getParameter("pagina") != null 
                       ? Integer.parseInt(request.getParameter("pagina")) : 1;
            int registrosPorPagina = 10;
            
            // Obtener proveedores
            ProveedorDAO dao = new ProveedorDAO();
            List<Proveedor> proveedores = dao.listarProveedores(pagina, registrosPorPagina);
            
            // Calcular total de páginas (requeriría método contarProveedores() en DAO)
            // int totalPaginas = (int) Math.ceil(dao.contarProveedores() / (double) registrosPorPagina);
            
            // Enviar atributos al JSP
            request.setAttribute("proveedores", proveedores);
            request.setAttribute("paginaActual", pagina);
            // request.setAttribute("totalPaginas", totalPaginas);
            
            // Redireccionar
            request.getRequestDispatcher("/listarProveedores.jsp").forward(request, response);
            
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp?mensaje=Error al listar proveedores");
        }
    }
}