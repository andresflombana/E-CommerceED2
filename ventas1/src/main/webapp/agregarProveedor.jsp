<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="Nuevo Proveedor"/>
</jsp:include>

<div class="card mx-auto" style="max-width: 600px;">
    <div class="card-header bg-primary text-white">
        <h3 class="mb-0"><i class="fas fa-truck me-2"></i>Nuevo Proveedor</h3>
    </div>
    
    <div class="card-body">
        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-danger alert-auto-close">
                <i class="fas fa-exclamation-circle me-2"></i>
                <%= request.getParameter("error") %>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        <% } %>
        
        <form action="SvRegistrarProveedor" method="POST" class="needs-validation" novalidate>
            <div class="mb-3">
                <label class="form-label">RUT:</label>
                <input type="text" name="rut" class="form-control" 
                       placeholder="12345678-9" required
                       pattern="[0-9]{7,8}-[0-9kK]{1}"
                       title="Formato: 12345678-9">
                <div class="invalid-feedback">
                    Ingrese un RUT válido (ej: 12345678-9)
                </div>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Nombre:</label>
                <input type="text" name="nombre" class="form-control" required
                       minlength="3" maxlength="100">
                <div class="invalid-feedback">
                    El nombre debe tener entre 3 y 100 caracteres
                </div>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Teléfono:</label>
                <input type="tel" name="telefono" class="form-control" 
                       placeholder="+56912345678" required
                       pattern="\+?[0-9\s]{9,15}">
                <div class="invalid-feedback">
                    Ingrese un número de teléfono válido
                </div>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Dirección:</label>
                <textarea name="direccion" class="form-control" rows="2"></textarea>
            </div>
            
            <div class="d-flex justify-content-between mt-4">
                <a href="listarProveedores.jsp" class="btn btn-outline-secondary">
                    <i class="fas fa-times me-1"></i> Cancelar
                </a>
                <button type="submit" class="btn btn-primary">
                    <i class="fas fa-save me-1"></i> Registrar
                </button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="/includes/footer.jsp"/>