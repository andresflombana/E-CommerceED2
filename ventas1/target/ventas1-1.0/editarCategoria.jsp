<%@page import="umariana.ventas1.dao.CategoriaDAO"%>
<%@page import="umariana.ventas1.modelo.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="Editar Categoría"/>
</jsp:include>

<%
    Categoria categoria = null;
    if (request.getParameter("id") != null) {
        try {
            CategoriaDAO dao = new CategoriaDAO();
            categoria = dao.obtenerCategoriaPorId(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            response.sendRedirect("listarCategorias.jsp?error=Error al cargar categoría");
            return;
        }
    }
    
    if (categoria == null) {
        response.sendRedirect("listarCategorias.jsp?error=Categoría no encontrada");
        return;
    }
%>

<div class="card mx-auto" style="max-width: 600px;">
    <div class="card-header bg-primary text-white">
        <h3 class="mb-0"><i class="fas fa-tags me-2"></i>Editar Categoría</h3>
    </div>
    
    <div class="card-body">
        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-warning alert-auto-close">
                <i class="fas fa-exclamation-triangle me-2"></i>
                <%= request.getParameter("error") %>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        <% } %>
        
        <form action="SvActualizarCategoria" method="POST" class="needs-validation" novalidate>
            <input type="hidden" name="idCategoria" value="<%= categoria.getIdCategoria() %>">
            
            <div class="mb-3">
                <label class="form-label">ID:</label>
                <input type="text" class="form-control" 
                       value="<%= categoria.getIdCategoria() %>" readonly
                       style="background-color:#f0f0f0;">
            </div>
            
            <div class="mb-3">
                <label class="form-label">Nombre:</label>
                <input type="text" name="nombre" class="form-control" 
                       value="<%= categoria.getNombreCategoria() %>" required>
                <div class="invalid-feedback">
                    Por favor ingrese el nombre de la categoría
                </div>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Descripción:</label>
                <textarea name="descripcion" class="form-control" rows="3"><%= 
                    categoria.getDescripcionCategoria() != null ? categoria.getDescripcionCategoria() : "" 
                %></textarea>
            </div>
            
            <div class="d-flex justify-content-between mt-4">
                <a href="listarCategorias.jsp" class="btn btn-outline-secondary">
                    <i class="fas fa-arrow-left me-1"></i> Volver
                </a>
                <div>
                    <button type="submit" class="btn btn-primary me-2">
                        <i class="fas fa-save me-1"></i> Guardar
                    </button>
                    <a href="eliminarCategoria.jsp?id=<%= categoria.getIdCategoria() %>" 
                       class="btn btn-outline-danger">
                        <i class="fas fa-trash-alt me-1"></i> Eliminar
                    </a>
                </div>
            </div>
        </form>
    </div>
</div>

<jsp:include page="/includes/footer.jsp"/>