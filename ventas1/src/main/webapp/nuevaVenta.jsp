<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="umariana.ventas1.modelo.DetalleVenta"%>
<%@page import="umariana.ventas1.dao.ProductoDAO"%>
<%@page import="umariana.ventas1.modelo.Producto"%>
<%@page import="umariana.ventas1.dao.ClienteDAO"%>
<%@page import="umariana.ventas1.modelo.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="Nueva Venta"/>
</jsp:include>

<%
    List<DetalleVenta> carrito = (List<DetalleVenta>) request.getSession().getAttribute("carrito");
    if (carrito == null) {
        carrito = new ArrayList<>();
        request.getSession().setAttribute("carrito", carrito);
    }
    
    ProductoDAO productoDAO = new ProductoDAO();
    List<Producto> productos = productoDAO.listarProductos(1, 100); // Todos los productos
    
    ClienteDAO clienteDAO = new ClienteDAO();
    List<Cliente> clientes = clienteDAO.listarClientes(1, 100); // Todos los clientes
%>

<h1 class="text-center mb-4">Nueva Venta</h1>

<!-- Mensajes de error -->
<% if (request.getParameter("error") != null) { %>
    <div class="alert alert-danger alert-dismissible fade show">
        <i class="fas fa-exclamation-circle me-2"></i>
        <%= request.getParameter("error") %>
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<% } %>

<div class="row">
    <!-- Formulario para agregar productos -->
    <div class="col-md-4 mb-4">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h5 class="mb-0"><i class="fas fa-cart-plus me-2"></i>Agregar Producto</h5>
            </div>
            <div class="card-body">
                <form action="SvCarrito" method="POST">
                    <input type="hidden" name="accion" value="agregar">
                    
                    <div class="mb-3">
                        <label class="form-label">Producto:</label>
                        <select name="idProducto" class="form-select" required>
                            <option value="">Seleccione...</option>
                            <% for (Producto p : productos) { %>
                            <option value="<%= p.getIdProducto() %>">
                                <%= p.getNombreProducto() %> (Stock: <%= p.getCantidadInventario() %>)
                            </option>
                            <% } %>
                        </select>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Cantidad:</label>
                        <input type="number" name="cantidad" class="form-control" min="1" required>
                    </div>
                    
                    <button type="submit" class="btn btn-primary w-100">
                        <i class="fas fa-plus-circle me-1"></i> Agregar al carrito
                    </button>
                </form>
            </div>
        </div>
    </div>
    
    <!-- Carrito de compras -->
    <div class="col-md-8">
        <div class="card">
            <div class="card-header bg-success text-white">
                <h5 class="mb-0"><i class="fas fa-shopping-cart me-2"></i>Carrito de Compras</h5>
            </div>
            
            <div class="card-body">
                <% if (carrito.isEmpty()) { %>
                    <div class="text-center py-4 text-muted">
                        <i class="fas fa-cart-arrow-down fa-3x mb-3"></i>
                        <p>No hay productos en el carrito</p>
                    </div>
                <% } else { %>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Producto</th>
                                    <th>Cantidad</th>
                                    <th>Precio Unitario</th>
                                    <th>Subtotal</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (int i = 0; i < carrito.size(); i++) { 
                                    DetalleVenta item = carrito.get(i);
                                    Producto p = productoDAO.obtenerProductoPorId(item.getIdProducto());
                                %>
                                <tr>
                                    <td><%= p.getNombreProducto() %></td>
                                    <td>
                                        <form action="SvCarrito" method="POST" class="d-flex">
                                            <input type="hidden" name="accion" value="actualizar">
                                            <input type="hidden" name="index" value="<%= i %>">
                                            <input type="number" name="cantidad" value="<%= item.getCantidad() %>" 
                                                   min="1" max="<%= p.getCantidadInventario() %>" 
                                                   class="form-control form-control-sm" style="width: 70px;">
                                            <button type="submit" class="btn btn-sm btn-outline-primary ms-2">
                                                <i class="fas fa-sync-alt"></i>
                                            </button>
                                        </form>
                                    </td>
                                    <td>$<%= String.format("%,.2f", item.getPrecioUnitario()) %></td>
                                    <td>$<%= String.format("%,.2f", item.getSubtotal()) %></td>
                                    <td>
                                        <form action="SvCarrito" method="POST" class="d-inline">
                                            <input type="hidden" name="accion" value="eliminar">
                                            <input type="hidden" name="index" value="<%= i %>">
                                            <button type="submit" class="btn btn-sm btn-outline-danger" title="Eliminar">
                                                <i class="fas fa-trash-alt"></i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                    
                    <!-- Formulario finalizar venta -->
                    <form action="SvRegistrarVenta" method="POST" class="mt-4">
                        <div class="row g-3">
                            <div class="col-md-6">
                                <label class="form-label">Cliente:</label>
                                <select name="idCliente" class="form-select" required>
                                    <option value="">Seleccione un cliente...</option>
                                    <% for (Cliente c : clientes) { %>
                                    <option value="<%= c.getIdCliente() %>">
                                        <%= c.getNombreCliente() %> (RUT: <%= c.getRutCliente() %>)
                                    </option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Descuento (%):</label>
                                <input type="number" name="descuento" class="form-control" 
                                       min="0" max="100" value="0">
                            </div>
                        </div>
                        
                        <div class="d-flex justify-content-between mt-4">
                            <a href="listarVenta.jsp" class="btn btn-outline-secondary">
                                <i class="fas fa-times me-1"></i> Cancelar
                            </a>
                            <button type="submit" class="btn btn-success">
                                <i class="fas fa-check-circle me-1"></i> Finalizar Venta
                            </button>
                        </div>
                    </form>
                <% } %>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/includes/footer.jsp"/>