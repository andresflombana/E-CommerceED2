<%@page import="umariana.ventas1.dao.ClienteDAO"%>
<%@page import="umariana.ventas1.modelo.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="Editar Cliente"/>
</jsp:include>

<%
    Cliente cliente = null;
    if (request.getParameter("id") != null) {
        try {
            ClienteDAO dao = new ClienteDAO();
            cliente = dao.obtenerClientePorId(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            response.sendRedirect("listarClientes.jsp?error=Error al cargar cliente");
            return;
        }
    }
    
    if (cliente == null) {
        response.sendRedirect("listarClientes.jsp?error=Cliente no encontrado");
        return;
    }
%>

<div class="card mx-auto" style="max-width: 600px;">
    <div class="card-header bg-primary text-white">
        <h3 class="mb-0"><i class="fas fa-user-edit me-2"></i>Editar Cliente</h3>
    </div>
    
    <div class="card-body">
        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-warning alert-auto-close">
                <i class="fas fa-exclamation-triangle me-2"></i>
                <%= request.getParameter("error") %>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        <% } %>
        
        <form action="SvActualizarCliente" method="POST" class="needs-validation" novalidate>
            <input type="hidden" name="idCliente" value="<%= cliente.getIdCliente() %>">
            
            <div class="mb-3">
                <label class="form-label">RUT:</label>
                <input type="text" name="rut" class="form-control" 
                       value="<%= cliente.getRutCliente() %>" readonly
                       style="background-color: #e9ecef;">
            </div>
            
            <div class="mb-3">
                <label class="form-label">Nombre:</label>
                <input type="text" name="nombre" class="form-control" 
                       value="<%= cliente.getNombreCliente() %>" required>
                <div class="invalid-feedback">
                    Por favor ingrese el nombre del cliente
                </div>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Teléfono:</label>
                <input type="tel" name="telefono" class="form-control" 
                       value="<%= cliente.getTelefonosCliente() %>" required
                       pattern="[0-9]{7,15}">
                <div class="invalid-feedback">
                    Ingrese un número de teléfono válido
                </div>
            </div>
            
            <div class="d-flex justify-content-between mt-4">
                <a href="listarClientes.jsp" class="btn btn-outline-secondary">
                    <i class="fas fa-arrow-left me-1"></i> Volver al listado
                </a>
                <div>
                    <button type="submit" class="btn btn-primary me-2">
                        <i class="fas fa-save me-1"></i> Guardar Cambios
                    </button>
                    <a href="eliminarCliente.jsp?id=<%= cliente.getIdCliente() %>" 
                       class="btn btn-outline-danger">
                        <i class="fas fa-trash-alt me-1"></i> Eliminar
                    </a>
                </div>
            </div>
        </form>
    </div>
</div>

<jsp:include page="/includes/footer.jsp"/>