<%@page import="java.util.List"%>
<%@page import="umariana.ventas1.modelo.Venta"%>
<%@page import="umariana.ventas1.dao.VentaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="Historial de Ventas"/>
</jsp:include>

<h1 class="text-center mb-4">Historial de Ventas</h1>

<!-- Mensajes de éxito/error -->
<% if (request.getParameter("exito") != null) { %>
    <div class="alert alert-success alert-dismissible fade show alert-auto-close">
        <i class="fas fa-check-circle me-2"></i>
        <%= request.getParameter("exito") %>
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<% } %>
<% if (request.getParameter("error") != null) { %>
    <div class="alert alert-danger alert-dismissible fade show alert-auto-close">
        <i class="fas fa-exclamation-circle me-2"></i>
        <%= request.getParameter("error") %>
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<% } %>

<div class="d-flex justify-content-end mb-3">
    <a href="nuevaVenta.jsp" class="btn btn-success">
        <i class="fas fa-cash-register me-1"></i> Nueva Venta
    </a>
</div>

<div class="table-responsive">
    <table class="table table-striped table-hover">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Fecha</th>
                <th>Cliente</th>
                <th>Descuento</th>
                <th>Total</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                int pagina = request.getParameter("pagina") != null 
                           ? Integer.parseInt(request.getParameter("pagina")) : 1;
                int registrosPorPagina = 10;
                
                VentaDAO dao = new VentaDAO();
                List<Venta> ventas = dao.listarVentas(pagina, registrosPorPagina);
                
                for (Venta v : ventas) {
            %>
            <tr>
                <td><%= v.getIdVenta() %></td>
                <td><%= v.getFechaVenta() %></td>
                <td>Cliente #<%= v.getIdCliente() %></td>
                <td><%= v.getDescuento() %>%</td>
                <td>$<%= String.format("%,.2f", v.getMontoTotal()) %></td>
                <td>
                    <div class="d-flex gap-2">
                        <a href="detalleVenta.jsp?id=<%= v.getIdVenta() %>" 
                           class="btn btn-info btn-sm" title="Ver detalles">
                            <i class="fas fa-eye"></i>
                        </a>
                        <a href="SvEliminarVenta?id=<%= v.getIdVenta() %>" 
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('¿Eliminar esta venta?')" title="Eliminar">
                            <i class="fas fa-trash-alt"></i>
                        </a>
                    </div>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>

<!-- Paginación -->
<nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
        <li class="page-item <%= pagina == 1 ? "disabled" : "" %>">
            <a class="page-link" href="listarVentas.jsp?pagina=<%= pagina - 1 %>">
                <i class="fas fa-chevron-left"></i>
            </a>
        </li>
        <li class="page-item active">
            <span class="page-link"><%= pagina %></span>
        </li>
        <li class="page-item <%= ventas.size() < registrosPorPagina ? "disabled" : "" %>">
            <a class="page-link" href="listarVentas.jsp?pagina=<%= pagina + 1 %>">
                <i class="fas fa-chevron-right"></i>
            </a>
        </li>
    </ul>
</nav>

<jsp:include page="/includes/footer.jsp"/>