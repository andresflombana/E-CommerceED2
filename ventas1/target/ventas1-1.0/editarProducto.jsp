<%@page import="umariana.ventas1.dao.ProductoDAO"%>
<%@page import="umariana.ventas1.modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="Editar Producto"/>
</jsp:include>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    ProductoDAO dao = new ProductoDAO();
    Producto producto = dao.obtenerProductoPorId(id);
    
    if (producto == null) {
        response.sendRedirect("index.jsp?error=Producto no encontrado");
        return;
    }
%>

<div class="card mx-auto" style="max-width: 800px;">
    <div class="card-header bg-primary text-white">
        <h3 class="mb-0"><i class="fas fa-edit me-2"></i>Editar Producto</h3>
    </div>
    
    <div class="card-body">
        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-danger alert-auto-close">
                <i class="fas fa-exclamation-circle me-2"></i>
                <%= request.getParameter("error") %>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        <% } %>
        
        <form action="${pageContext.request.contextPath}/SvactualizarProducto" method="POST" class="needs-validation" novalidate>
            <input type="hidden" name="idProducto" value="<%= producto.getIdProducto() %>">
            
            <div class="row g-3">
                <div class="col-md-6">
                    <label class="form-label">Nombre del Producto:</label>
                    <input type="text" class="form-control" name="nombre" 
                           value="<%= producto.getNombreProducto() %>" required>
                    <div class="invalid-feedback">
                        Por favor ingrese el nombre del producto.
                    </div>
                </div>
                
                <div class="col-md-6">
                    <label class="form-label">Precio:</label>
                    <div class="input-group">
                        <span class="input-group-text">$</span>
                        <input type="number" class="form-control" name="precio" 
                               value="<%= producto.getPrecioActual() %>" step="0.01" min="0" required>
                    </div>
                    <div class="invalid-feedback">
                        Ingrese un precio válido.
                    </div>
                </div>
                
                <div class="col-md-6">
                    <label class="form-label">Stock:</label>
                    <input type="number" class="form-control" name="stock" 
                           value="<%= producto.getCantidadInventario() %>" min="0" required>
                    <div class="invalid-feedback">
                        Ingrese la cantidad en inventario.
                    </div>
                </div>
                
                <div class="col-md-6">
                    <label class="form-label">ID Proveedor:</label>
                    <input type="number" class="form-control" name="proveedor" 
                           value="<%= producto.getIdProveedor() %>" min="1" required>
                    <div class="invalid-feedback">
                        Ingrese el ID del proveedor.
                    </div>
                </div>
                
                <div class="col-md-6">
                    <label class="form-label">ID Categoría:</label>
                    <input type="number" class="form-control" name="categoria" 
                           value="<%= producto.getIdCategoria() %>" min="1" required>
                    <div class="invalid-feedback">
                        Ingrese el ID de la categoría.
                    </div>
                </div>
            </div>
            
            <div class="d-flex justify-content-between mt-4">
                <a href="listarProductos.jsp" class="btn btn-outline-secondary">
                    <i class="fas fa-arrow-left me-1"></i> Cancelar
                </a>
                <button type="submit" class="btn btn-primary">
                    <i class="fas fa-save me-1"></i> Actualizar Producto
                </button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="/includes/footer.jsp"/>