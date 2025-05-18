<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="Nueva Categoría"/>
</jsp:include>

<div class="card mx-auto" style="max-width: 600px;">
    <div class="card-header bg-primary text-white">
        <h3 class="mb-0"><i class="fas fa-tag me-2"></i>Nueva Categoría</h3>
    </div>
    
    <div class="card-body">
        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-danger alert-auto-close">
                <i class="fas fa-exclamation-circle me-2"></i>
                <%= request.getParameter("error") %>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        <% } %>
        
        <form action="SvRegistrarCategoria" method="POST" class="needs-validation" novalidate>
            <div class="mb-3">
                <label class="form-label">Nombre:</label>
                <input type="text" name="nombre" class="form-control" 
                       placeholder="Ej: Electrónicos" required
                       minlength="3" maxlength="50">
                <div class="invalid-feedback">
                    El nombre debe tener entre 3 y 50 caracteres
                </div>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Descripción:</label>
                <textarea name="descripcion" class="form-control" rows="3"
                          placeholder="Descripción de la categoría..."></textarea>
            </div>
            
            <div class="d-flex justify-content-between mt-4">
                <a href="listarCategorias.jsp" class="btn btn-outline-secondary">
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