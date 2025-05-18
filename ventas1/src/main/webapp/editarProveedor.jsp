<%@page import="umariana.ventas1.dao.ProveedorDAO"%>
<%@page import="umariana.ventas1.modelo.Proveedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="Editar Proveedor"/>
</jsp:include>

<%
    Proveedor proveedor = null;
    if (request.getParameter("id") != null) {
        try {
            ProveedorDAO dao = new ProveedorDAO();
            proveedor = dao.obtenerProveedorPorId(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            response.sendRedirect("listarProveedores.jsp?error=Error al cargar proveedor");
            return;
        }
    }
    
    if (proveedor == null) {
        response.sendRedirect("listarProveedores.jsp?error=Proveedor no encontrado");
        return;
    }
%>

<div class="card mx-auto" style="max-width: 600px;">
    <div class="card-header bg-primary text-white">
        <h3 class="mb-0"><i class="fas fa-edit me-2"></i>Editar Proveedor</h3>
    </div>
    
    <div class="card-body">
        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-warning alert-auto-close">
                <i class="fas fa-exclamation-triangle me-2"></i>
                <%= request.getParameter("error") %>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        <% } %>
        
        <form action="SvActualizarProveedor" method="POST" class="needs-validation" novalidate>
            <input type="hidden" name="idProveedor" value="<%= proveedor.getIdProveedor() %>">
            
            <div class="mb-3">
                <label class="form-label">RUT:</label>
                <input type="text" name="rut" class="form-control" 
                       value="<%= proveedor.getRutProveedor() %>" readonly
                       style="background-color:#f0f0f0;">
            </div>
            
            <div class="mb-3">
                <label class="form-label">Nombre:</label>
                <input type="text" name="nombre" class="form-control" 
                       value="<%= proveedor.getNombreProveedor() %>" required>
                <div class="invalid-feedback">
                    Por favor ingrese el nombre del proveedor
                </div>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Teléfono:</label>
                <input type="tel" name="telefono" class="form-control" 
                       value="<%= proveedor.getTelefonoProveedor() %>" required
                       pattern="[0-9]{7,15}">
                <div class="invalid-feedback">
                    Ingrese un número de teléfono válido
                </div>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Dirección:</label>
                <textarea name="direccion" class="form-control" rows="2"><%= 
                    proveedor.getDireccionProveedor() != null ? proveedor.getDireccionProveedor() : "" 
                %></textarea>
            </div>
            
            <div class="d-flex justify-content-between mt-4">
                <a href="listarProveedores.jsp" class="btn btn-outline-secondary">
                    <i class="fas fa-arrow-left me-1"></i> Volver
                </a>
                <div>
                    <button type="submit" class="btn btn-primary me-2">
                        <i class="fas fa-save me-1"></i> Guardar
                    </button>
                    <a href="eliminarProveedor.jsp?id=<%= proveedor.getIdProveedor() %>" 
                       class="btn btn-outline-danger">
                        <i class="fas fa-trash-alt me-1"></i> Eliminar
                    </a>
                </div>
            </div>
        </form>
    </div>
</div>

<jsp:include page="/includes/footer.jsp"/>