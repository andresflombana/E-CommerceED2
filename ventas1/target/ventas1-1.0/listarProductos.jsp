<%@page import="java.util.List"%>
<%@page import="umariana.ventas1.modelo.Producto"%>
<%@page import="umariana.ventas1.dao.ProductoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="Listado de Productos"/>
</jsp:include>

<h1 class="text-center mb-4">Productos</h1>

<!-- Mensajes de éxito/error -->
<% if (request.getParameter("exito") != null) { %>
    <div class="alert alert-success alert-dismissible fade show alert-auto-close">
        <%= request.getParameter("exito") %>
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<% } %>
<% if (request.getParameter("error") != null) { %>
    <div class="alert alert-danger alert-dismissible fade show alert-auto-close">
        <%= request.getParameter("error") %>
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<% } %>

<div class="d-flex justify-content-between mb-4">
    <a href="index.jsp" class="btn btn-outline-secondary">
        <i class="fas fa-arrow-left me-1"></i> Inicio
    </a>
    <a href="agregarProducto.jsp" class="btn btn-success">
        <i class="fas fa-box me-1"></i> Nuevo Producto
    </a>
</div>

<div class="table-responsive">
    <table class="table table-striped table-hover">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Stock</th>
                <th>Proveedor</th>
                <th>Categoría</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                int pagina = request.getParameter("pagina") != null 
                           ? Integer.parseInt(request.getParameter("pagina")) : 1;
                int registrosPorPagina = 10;
                
                ProductoDAO dao = new ProductoDAO();
                List<Producto> productos = dao.listarProductos(pagina, registrosPorPagina);
                
                for (Producto p : productos) {
            %>
            <tr>
                <td><%= p.getIdProducto() %></td>
                <td><%= p.getNombreProducto() %></td>
                <td>$<%= String.format("%,.2f", p.getPrecioActual()) %></td>
                <td><%= p.getCantidadInventario() %></td>
                <td><%= p.getIdProveedor() %></td>
                <td><%= p.getIdCategoria() %></td>
                <td>
                    <div class="d-flex">
                        <a href="editarProducto.jsp?id=<%= p.getIdProducto() %>" 
                           class="btn btn-warning btn-sm btn-action">
                            <i class="fas fa-edit"></i>
                        </a>
                        <a href="SvEliminarProducto?id=<%= p.getIdProducto() %>" 
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('¿Estás seguro de eliminar este producto?')">
                            <i class="fas fa-trash"></i>
                        </a>
                    </div>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>

<nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
        <li class="page-item <%= pagina == 1 ? "disabled" : "" %>">
            <a class="page-link" href="listarProductos.jsp?pagina=<%= pagina - 1 %>">
                <i class="fas fa-chevron-left"></i>
            </a>
        </li>
        <li class="page-item active">
            <span class="page-link"><%= pagina %></span>
        </li>
        <li class="page-item <%= productos.size() < registrosPorPagina ? "disabled" : "" %>">
            <a class="page-link" href="listarProductos.jsp?pagina=<%= pagina + 1 %>">
                <i class="fas fa-chevron-right"></i>
            </a>
        </li>
    </ul>
</nav>
<jsp:include page="/includes/footer.jsp"/>