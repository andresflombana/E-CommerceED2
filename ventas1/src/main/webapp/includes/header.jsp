<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= request.getParameter("title") != null ? request.getParameter("title") : "Sistema de Ventas" %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark mb-4">
        <div class="container">
            <a class="navbar-brand d-flex align-items-center" href="index.jsp">
                <i class="fas fa-store me-2"></i>
                <span class="fw-bold">E-Commerce</span>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <!-- Ítem de Inicio/Dashboard -->
                    <li class="nav-item">
                        <a class="nav-link ${pageContext.request.requestURI.endsWith('/index.jsp') ? 'active' : ''}" href="index.jsp">
                            <i class="fas fa-home me-1"></i>Inicio
                        </a>
                    </li>
                    
                    <!-- Menú desplegable de Ventas -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle ${pageContext.request.requestURI.contains('Venta') ? 'active' : ''}" href="#" id="ventasDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fas fa-cash-register me-1"></i>Ventas
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="ventasDropdown">
                            <li>
                                <a class="dropdown-item" href="nuevaVenta.jsp">
                                    <i class="fas fa-plus-circle me-1"></i>Nueva Venta
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="listarVenta.jsp">
                                    <i class="fas fa-list me-1"></i>Historial de Ventas
                                </a>
                            </li>
                        </ul>
                    </li>
                    
                    <!-- Menú desplegable de Catálogo -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle ${pageContext.request.requestURI.contains('Producto') || pageContext.request.requestURI.contains('Categoria') || pageContext.request.requestURI.contains('Proveedor') ? 'active' : ''}" href="#" id="catalogoDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fas fa-box-open me-1"></i>Catálogo
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="catalogoDropdown">
                            <li>
                                <a class="dropdown-item" href="listarProductos.jsp">
                                    <i class="fas fa-boxes me-1"></i>Productos
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="listarCategorias.jsp">
                                    <i class="fas fa-tags me-1"></i>Categorías
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="listarProveedores.jsp">
                                    <i class="fas fa-truck me-1"></i>Proveedores
                                </a>
                            </li>
                        </ul>
                    </li>
                    
                    <!-- Ítem de Clientes -->
                    <li class="nav-item">
                        <a class="nav-link ${pageContext.request.requestURI.endsWith('/listarClientes.jsp') ? 'active' : ''}" href="listarClientes.jsp">
                            <i class="fas fa-users me-1"></i>Clientes
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    
    <div class="container">
        <!-- Contenido de la página será insertado aquí -->