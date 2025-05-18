<%@page import="java.util.List"%>
<%@page import="umariana.ventas1.modelo.Venta"%>
<%@page import="umariana.ventas1.dao.VentaDAO"%>
<%@page import="umariana.ventas1.modelo.Producto"%>
<%@page import="umariana.ventas1.dao.ProductoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="Dashboard - Sistema de Ventas"/>
</jsp:include>

<div class="row mb-4">
    <div class="col-12">
        <div class="card">
            <div class="card-body p-4 text-center">
                <h1 class="display-5 fw-bold mb-4">Bienvenido al Sistema de Gestión E-Commerce</h1>
                <p class="lead mb-4">Gestiona tus ventas, productos, categorías, proveedores y clientes de manera eficiente.</p>
            </div>
        </div>
    </div>
</div>

<div class="row g-4">
    <!-- Widget de Ventas -->
    <div class="col-md-3">
        <div class="card h-100">
            <div class="card-body text-center p-4">
                <div class="mb-3">
                    <i class="fas fa-cash-register fa-3x text-primary"></i>
                </div>
                <h5 class="card-title">Ventas</h5>
                <p class="card-text">Gestiona tus ventas y consulta el historial.</p>
                <div class="mt-3">
                    <a href="nuevaVenta.jsp" class="btn btn-primary">
                        <i class="fas fa-plus-circle me-1"></i> Nueva Venta
                    </a>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Widget de Productos -->
    <div class="col-md-3">
        <div class="card h-100">
            <div class="card-body text-center p-4">
                <div class="mb-3">
                    <i class="fas fa-boxes fa-3x text-success"></i>
                </div>
                <h5 class="card-title">Productos</h5>
                <p class="card-text">Administra tu catálogo de productos.</p>
                <div class="mt-3">
                    <a href="listarProductos.jsp" class="btn btn-success">
                        <i class="fas fa-list me-1"></i> Ver Productos
                    </a>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Widget de Clientes -->
    <div class="col-md-3">
        <div class="card h-100">
            <div class="card-body text-center p-4">
                <div class="mb-3">
                    <i class="fas fa-users fa-3x text-warning"></i>
                </div>
                <h5 class="card-title">Clientes</h5>
                <p class="card-text">Gestiona la información de tus clientes.</p>
                <div class="mt-3">
                    <a href="listarClientes.jsp" class="btn btn-warning">
                        <i class="fas fa-list me-1"></i> Ver Clientes
                    </a>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Widget de Categorías -->
    <div class="col-md-3">
        <div class="card h-100">
            <div class="card-body text-center p-4">
                <div class="mb-3">
                    <i class="fas fa-tags fa-3x text-danger"></i>
                </div>
                <h5 class="card-title">Categorías</h5>
                <p class="card-text">Administra las categorías de productos.</p>
                <div class="mt-3">
                    <a href="listarCategorias.jsp" class="btn btn-danger">
                        <i class="fas fa-list me-1"></i> Ver Categorías
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row mt-4">
    <div class="col-md-6">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h5 class="mb-0"><i class="fas fa-chart-line me-2"></i>Últimas Ventas</h5>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Cliente</th>
                                <th>Fecha</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                VentaDAO ventaDao = new VentaDAO();
                                List<Venta> ultimasVentas = ventaDao.listarVentas(1, 5); // Usando el método listarVentas que tienes
                                
                                if (ultimasVentas.isEmpty()) {
                            %>
                                <tr>
                                    <td colspan="4" class="text-center py-4 text-muted">
                                        <i class="fas fa-database fa-2x mb-2"></i><br>
                                        No hay ventas registradas
                                    </td>
                                </tr>
                            <%
                                } else {
                                    for (Venta venta : ultimasVentas) {
                            %>
                                <tr>
                                    <td><%= venta.getIdVenta() %></td>
                                    <td>Cliente #<%= venta.getIdCliente() %></td>
                                    <td><%= venta.getFechaVenta() %></td>
                                    <td>$<%= String.format("%,.2f", venta.getMontoTotal()) %></td>
                                </tr>
                            <%
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    
    <div class="col-md-6">
        <div class="card">
            <div class="card-header bg-success text-white">
                <h5 class="mb-0"><i class="fas fa-box-open me-2"></i>Últimos Productos</h5>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Producto</th>
                                <th>Categoría</th>
                                <th>Precio</th>
                                <th>Stock</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ProductoDAO productoDao = new ProductoDAO();
                                List<Producto> ultimosProductos = productoDao.listarProductos(1, 5); // Usando el método listarProductos que tienes
                                
                                if (ultimosProductos.isEmpty()) {
                            %>
                                <tr>
                                    <td colspan="4" class="text-center py-4 text-muted">
                                        <i class="fas fa-database fa-2x mb-2"></i><br>
                                        No hay productos registrados
                                    </td>
                                </tr>
                            <%
                                } else {
                                    for (Producto producto : ultimosProductos) {
                            %>
                                <tr>
                                    <td><%= producto.getNombreProducto() %></td>
                                    <td>Categoría #<%= producto.getIdCategoria() %></td>
                                    <td>$<%= String.format("%,.2f", producto.getPrecioActual()) %></td>
                                    <td><%= producto.getCantidadInventario() %></td>
                                </tr>
                            <%
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/includes/footer.jsp"/>