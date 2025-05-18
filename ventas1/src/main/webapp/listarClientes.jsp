<%@page import="java.util.List"%>
<%@page import="umariana.ventas1.dao.ClienteDAO"%>
<%@page import="umariana.ventas1.modelo.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="Listado de Clientes"/>
</jsp:include>

<h1 class="text-center mb-4">Clientes Registrados</h1>

<% 
    String exito = request.getParameter("exito");
    String error = request.getParameter("error");
%>
<% if ("eliminacion".equals(exito)) { %>
    <div class="alert alert-success alert-auto-close">
        <i class="fas fa-check-circle me-2"></i>Cliente eliminado correctamente
    </div>
<% } else if ("registro".equals(exito)) { %>
    <div class="alert alert-success alert-auto-close">
        <i class="fas fa-check-circle me-2"></i>Cliente registrado correctamente
    </div>
<% } else if (error != null) { %>
    <div class="alert alert-danger alert-auto-close">
        <i class="fas fa-exclamation-circle me-2"></i><%= error %>
    </div>
<% } %>

<div class="d-flex justify-content-between mb-4">
    <a href="index.jsp" class="btn btn-outline-secondary">
        <i class="fas fa-arrow-left me-1"></i> Inicio
    </a>
    <a href="agregarCliente.jsp" class="btn btn-success">
        <i class="fas fa-user-plus me-1"></i> Nuevo Cliente
    </a>
</div>

<div class="table-responsive">
    <table class="table table-hover">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>RUT</th>
                <th>Nombre</th>
                <th>Teléfono</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                int pagina = request.getParameter("pagina") != null 
                           ? Integer.parseInt(request.getParameter("pagina")) : 1;
                int registrosPorPagina = 8;
                
                ClienteDAO dao = new ClienteDAO();
                List<Cliente> clientes = dao.listarClientes(pagina, registrosPorPagina);
                
                if (clientes.isEmpty()) {
            %>
            <tr>
                <td colspan="5" class="text-center py-4 text-muted">
                    <i class="fas fa-users-slash fa-2x mb-2"></i><br>
                    No hay clientes registrados
                </td>
            </tr>
            <% } else {
                for (Cliente c : clientes) {
            %>
            <tr>
                <td><%= c.getIdCliente() %></td>
                <td><%= c.getRutCliente() %></td>
                <td><%= c.getNombreCliente() %></td>
                <td><%= c.getTelefonosCliente() %></td>
                <td>
                    <div class="d-flex gap-2">
                        <a href="editarCliente.jsp?id=<%= c.getIdCliente() %>" 
                           class="btn btn-warning btn-sm" title="Editar">
                            <i class="fas fa-edit"></i>
                        </a>
                        <a href="SvEliminarCliente?id=<%= c.getIdCliente() %>" 
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('¿Eliminar este cliente?')" title="Eliminar">
                            <i class="fas fa-trash-alt"></i>
                        </a>
                    </div>
                </td>
            </tr>
            <% } } %>
        </tbody>
    </table>
</div>

<nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
        <li class="page-item <%= pagina == 1 ? "disabled" : "" %>">
            <a class="page-link" href="listarClientes.jsp?pagina=<%= pagina - 1 %>">
                <i class="fas fa-chevron-left"></i>
            </a>
        </li>
        <li class="page-item active">
            <span class="page-link"><%= pagina %></span>
        </li>
        <li class="page-item <%= clientes.size() < registrosPorPagina ? "disabled" : "" %>">
            <a class="page-link" href="listarClientes.jsp?pagina=<%= pagina + 1 %>">
                <i class="fas fa-chevron-right"></i>
            </a>
        </li>
    </ul>
</nav>

<jsp:include page="/includes/footer.jsp"/>