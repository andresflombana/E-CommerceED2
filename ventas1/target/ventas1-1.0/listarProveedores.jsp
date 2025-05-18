<%@page import="java.util.List"%>
<%@page import="umariana.ventas1.dao.ProveedorDAO"%>
<%@page import="umariana.ventas1.modelo.Proveedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="Listado de Proveedores"/>
</jsp:include>

<h1 class="text-center mb-4">Proveedores</h1>

<% 
    String exito = request.getParameter("exito");
    String error = request.getParameter("error");
%>
<% if (exito != null) { %>
    <div class="alert alert-success alert-auto-close">
        <i class="fas fa-check-circle me-2"></i><%= exito %>
    </div>
<% } %>
<% if (error != null) { %>
    <div class="alert alert-danger alert-auto-close">
        <i class="fas fa-exclamation-circle me-2"></i><%= error %>
    </div>
<% } %>

<div class="d-flex justify-content-between mb-4">
    <a href="index.jsp" class="btn btn-outline-secondary">
        <i class="fas fa-arrow-left me-1"></i> Inicio
    </a>
    <a href="agregarProveedor.jsp" class="btn btn-success">
        <i class="fas fa-truck-loading me-1"></i> Nuevo Proveedor
    </a>
</div>

<div class="table-responsive">
    <table class="table table-striped">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>RUT</th>
                <th>Nombre</th>
                <th>Teléfono</th>
                <th>Dirección</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                int pagina = request.getParameter("pagina") != null 
                           ? Integer.parseInt(request.getParameter("pagina")) : 1;
                int registrosPorPagina = 10;
                
                ProveedorDAO dao = new ProveedorDAO();
                List<Proveedor> proveedores = dao.listarProveedores(pagina, registrosPorPagina);
                
                if (proveedores.isEmpty()) {
            %>
            <tr>
                <td colspan="6" class="text-center py-4 text-muted">
                    <i class="fas fa-truck-moving fa-2x mb-2"></i><br>
                    No hay proveedores registrados
                </td>
            </tr>
            <% } else {
                for (Proveedor p : proveedores) {
            %>
            <tr>
                <td><%= p.getIdProveedor() %></td>
                <td><%= p.getRutProveedor() %></td>
                <td><%= p.getNombreProveedor() %></td>
                <td><%= p.getTelefonoProveedor() %></td>
                <td><%= p.getDireccionProveedor() != null ? p.getDireccionProveedor() : "N/A" %></td>
                <td>
                    <div class="d-flex gap-2">
                        <a href="editarProveedor.jsp?id=<%= p.getIdProveedor() %>" 
                           class="btn btn-warning btn-sm" title="Editar">
                            <i class="fas fa-edit"></i>
                        </a>
                        <a href="SvEliminarProveedor?id=<%= p.getIdProveedor() %>" 
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('¿Eliminar este proveedor?')" title="Eliminar">
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
            <a class="page-link" href="listarProveedores.jsp?pagina=<%= pagina - 1 %>">
                <i class="fas fa-chevron-left"></i>
            </a>
        </li>
        <li class="page-item active">
            <span class="page-link"><%= pagina %></span>
        </li>
        <li class="page-item <%= proveedores.size() < registrosPorPagina ? "disabled" : "" %>">
            <a class="page-link" href="listarProveedores.jsp?pagina=<%= pagina + 1 %>">
                <i class="fas fa-chevron-right"></i>
            </a>
        </li>
    </ul>
</nav>

<jsp:include page="/includes/footer.jsp"/>