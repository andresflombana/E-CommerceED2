package servlets;

import umariana.ventas1.dao.ClienteDAO;
import umariana.ventas1.modelo.Cliente;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvListarClientes", urlPatterns = {"/SvListarClientes"})
public class SvListarClientes extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Configurar paginación
            int pagina = request.getParameter("pagina") != null 
                       ? Integer.parseInt(request.getParameter("pagina")) : 1;
            int registrosPorPagina = 10;
            
            // Obtener clientes
            ClienteDAO dao = new ClienteDAO();
            List<Cliente> clientes = dao.listarClientes(pagina, registrosPorPagina);
            
            // Calcular total de páginas (requeriría método contarClientes() en DAO)
            // int totalPaginas = (int) Math.ceil(dao.contarClientes() / (double) registrosPorPagina);
            
            // Enviar atributos al JSP
            request.setAttribute("clientes", clientes);
            request.setAttribute("paginaActual", pagina);
            // request.setAttribute("totalPaginas", totalPaginas);
            
            // Redireccionar
            request.getRequestDispatcher("/listarClientes.jsp").forward(request, response);
            
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp?mensaje=Error al listar clientes");
        }
    }
}