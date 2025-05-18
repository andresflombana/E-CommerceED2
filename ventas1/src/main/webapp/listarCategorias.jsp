<%@page import="java.util.List"%>
<%@page import="umariana.ventas1.dao.CategoriaDAO"%>
<%@page import="umariana.ventas1.modelo.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="Listado de Categorias"/>
</jsp:include>

<h1 class="text-center mb-4">Categorías</h1>

<% 
    String exito = request.getParameter("exito");
    String error = request.getParameter("error");
    
    if (exito != null) { 
%>
    <div class="alert alert-success alert-auto-close">
        <i class="fas fa-check-circle me-2"></i><%= exito %>
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<% } 
   if (error != null) { 
%>
    <div class="alert alert-danger alert-auto-close">
        <i class="fas fa-exclamation-circle me-2"></i><%= error %>
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<% } %>

<div class="d-flex justify-content-between mb-4">
    <a href="index.jsp" class="btn btn-outline-secondary">
        <i class="fas fa-arrow-left me-1"></i> Inicio
    </a>
    <a href="agregarCategoria.jsp" class="btn btn-success">
        <i class="fas fa-folder-plus me-1"></i> Nueva Categoría
    </a>
</div>

<div class="table-responsive">
    <table class="table table-striped table-hover">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                int pagina = request.getParameter("pagina") != null 
                           ? Integer.parseInt(request.getParameter("pagina")) : 1;
                int registrosPorPagina = 10;
                
                CategoriaDAO dao = new CategoriaDAO();
                List<Categoria> categorias = dao.listarCategorias(pagina, registrosPorPagina);
                
                if (categorias.isEmpty()) {
            %>
            <tr>
                <td colspan="4" class="text-center py-4 text-muted">
                    <i class="fas fa-box-open fa-2x mb-2"></i><br>
                    No hay categorías registradas
                </td>
            </tr>
            <% } else {
                for (Categoria c : categorias) {
            %>
            <tr>
                <td><%= c.getIdCategoria() %></td>
                <td><%= c.getNombreCategoria() %></td>
                <td><%= c.getDescripcionCategoria() != null ? c.getDescripcionCategoria() : "N/A" %></td>
                <td>
                    <div class="d-flex gap-2">
                        <a href="editarCategoria.jsp?id=<%= c.getIdCategoria() %>" 
                           class="btn btn-warning btn-sm" title="Editar">
                            <i class="fas fa-edit"></i>
                        </a>
                        <a href="SvEliminarCategoria?id=<%= c.getIdCategoria() %>" 
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('¿Eliminar esta categoría?')" title="Eliminar">
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
            <a class="page-link" href="listarCategorias.jsp?pagina=<%= pagina - 1 %>">
                <i class="fas fa-chevron-left"></i>
            </a>
        </li>
        <li class="page-item active">
            <span class="page-link"><%= pagina %></span>
        </li>
        <li class="page-item <%= categorias.size() < registrosPorPagina ? "disabled" : "" %>">
            <a class="page-link" href="listarCategorias.jsp?pagina=<%= pagina + 1 %>">
                <i class="fas fa-chevron-right"></i>
            </a>
        </li>
    </ul>
</nav>

<jsp:include page="/includes/footer.jsp"/>