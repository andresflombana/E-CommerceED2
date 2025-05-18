<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="Nuevo Producto"/>
</jsp:include>

<div class="card mx-auto" style="max-width: 800px;">
    <div class="card-header bg-primary text-white">
        <h3 class="mb-0"><i class="fas fa-box me-2"></i>Nuevo Producto</h3>
    </div>
    
    <div class="card-body">
        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-danger alert-auto-close">
                <i class="fas fa-exclamation-circle me-2"></i>
                <%= request.getParameter("error") %>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        <% } %>
        
        <form action="${pageContext.request.contextPath}/SvRegistrarProducto" method="POST" class="needs-validation" novalidate>
            <div class="row g-3">
                <div class="col-md-6">
                    <label class="form-label">Nombre del Producto:</label>
                    <input type="text" class="form-control" name="nombre" required
                           minlength="3" maxlength="100">
                    <div class="invalid-feedback">
                        El nombre debe tener entre 3 y 100 caracteres
                    </div>
                </div>
                
                <div class="col-md-6">
                    <label class="form-label">Precio:</label>
                    <div class="input-group">
                        <span class="input-group-text">$</span>
                        <input type="number" class="form-control" name="precio" 
                               step="0.01" min="0" required>
                    </div>
                    <div class="invalid-feedback">
                        Ingrese un precio válido (ej: 19.99)
                    </div>
                </div>
                
                <div class="col-md-6">
                    <label class="form-label">Stock:</label>
                    <input type="number" class="form-control" name="stock" 
                           min="0" required>
                    <div class="invalid-feedback">
                        Ingrese una cantidad válida
                    </div>
                </div>
                
                <div class="col-md-6">
                    <label class="form-label">ID Proveedor:</label>
                    <input type="number" class="form-control" name="proveedor" 
                           min="1" required>
                    <div class="invalid-feedback">
                        Ingrese un ID de proveedor válido
                    </div>
                </div>
                
                <div class="col-md-6">
                    <label class="form-label">ID Categoría:</label>
                    <input type="number" class="form-control" name="categoria" 
                           min="1" required>
                    <div class="invalid-feedback">
                        Ingrese un ID de categoría válido
                    </div>
                </div>
            </div>
            
            <div class="d-flex justify-content-between mt-4">
                <a href="listarProductos.jsp" class="btn btn-outline-secondary">
                    <i class="fas fa-times me-1"></i> Cancelar
                </a>
                <button type="submit" class="btn btn-primary">
                    <i class="fas fa-save me-1"></i> Guardar Producto
                </button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="/includes/footer.jsp"/>