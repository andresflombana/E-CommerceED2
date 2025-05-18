<%@page import="java.util.List"%>
<%@page import="umariana.ventas1.modelo.DetalleVenta"%>
<%@page import="umariana.ventas1.dao.DetalleVentaDAO"%>
<%@page import="umariana.ventas1.modelo.Venta"%>
<%@page import="umariana.ventas1.dao.VentaDAO"%>
<%@page import="umariana.ventas1.dao.ProductoDAO"%>
<%@page import="umariana.ventas1.modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="Detalle de Venta"/>
</jsp:include>

<%
    int idVenta = Integer.parseInt(request.getParameter("id"));
    VentaDAO ventaDAO = new VentaDAO();
    DetalleVentaDAO detalleDAO = new DetalleVentaDAO();
    ProductoDAO productoDAO = new ProductoDAO();
    
    Venta venta = ventaDAO.obtenerVentaPorId(idVenta);
    List<DetalleVenta> detalles = detalleDAO.listarPorVenta(idVenta);
    
    if (venta == null) {
        response.sendRedirect("listarVenta.jsp?error=Venta no encontrada");
        return;
    }
%>

<div class="card mx-auto" style="max-width: 900px;">
    <div class="card-header bg-primary text-white">
        <h3 class="mb-0">
            <i class="fas fa-receipt me-2"></i>Detalle de Venta #<%= venta.getIdVenta() %>
        </h3>
    </div>
    
    <div class="card-body">
        <!-- Resumen de venta -->
        <div class="row mb-4">
            <div class="col-md-4">
                <strong>Fecha:</strong> <%= venta.getFechaVenta() %>
            </div>
            <div class="col-md-4">
                <strong>Cliente:</strong> #<%= venta.getIdCliente() %>
            </div>
            <div class="col-md-4">
                <strong>Total:</strong> $<%= String.format("%,.2f", venta.getMontoTotal()) %>
            </div>
        </div>
        
        <!-- Lista de productos -->
        <h5 class="mb-3">Productos:</h5>
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead class="table-light">
                    <tr>
                        <th>Producto</th>
                        <th>Cantidad</th>
                        <th>Precio Unitario</th>
                        <th>Subtotal</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (DetalleVenta d : detalles) { 
                        Producto p = productoDAO.obtenerProductoPorId(d.getIdProducto());
                        String nombreProducto = (p != null) ? p.getNombreProducto() : "Producto #" + d.getIdProducto();
                    %>
                    <tr>
                        <td><%= nombreProducto %></td>
                        <td><%= d.getCantidad() %></td>
                        <td>$<%= String.format("%,.2f", d.getPrecioUnitario()) %></td>
                        <td>$<%= String.format("%,.2f", d.getSubtotal()) %></td>
                    </tr>
                    <% } %>
                </tbody>
                <tfoot class="table-light">
                    <tr>
                        <th colspan="3" class="text-end">Subtotal:</th>
                        <td>$<%= String.format("%,.2f", venta.getMontoTotal() / (1 - venta.getDescuento()/100)) %></td>
                    </tr>
                    <tr>
                        <th colspan="3" class="text-end">Descuento (<%= venta.getDescuento() %>%):</th>
                        <td>-$<%= String.format("%,.2f", venta.getMontoTotal() * venta.getDescuento()/100) %></td>
                    </tr>
                    <tr>
                        <th colspan="3" class="text-end">Total:</th>
                        <th>$<%= String.format("%,.2f", venta.getMontoTotal()) %></th>
                    </tr>
                </tfoot>
            </table>
        </div>
        
        <div class="d-flex justify-content-between mt-4">
            <a href="listarVenta.jsp" class="btn btn-outline-secondary">
                <i class="fas fa-arrow-left me-1"></i> Volver al historial
            </a>
            <button class="btn btn-primary" onclick="window.print()">
                <i class="fas fa-print me-1"></i> Imprimir
            </button>
        </div>
    </div>
</div>

<jsp:include page="/includes/footer.jsp"/>