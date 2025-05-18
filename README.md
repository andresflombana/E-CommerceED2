<p align="center">
    <img src="https://images.icon-icons.com/2415/PNG/512/java_plain_wordmark_logo_icon_146457.png" alt="Logo Java">
</p>

<p align="center">
    <img src="https://img.shields.io/badge/GitHub-%23121011.svg?style=for-the-badge&logo=github&logoColor=white">
</p>

# Proyecto

**Sistema de Gestión de Ventas E-Commerce**

# Descripción del Proyecto

**Aplicación web Java EE para gestión completa de un e-commerce, con:**

* ✅ CRUD de productos, categorías, clientes y proveedores

* 🛒 Carrito de compras con gestión de stock

* 📊 Dashboard analítico

* 🔄 Relaciones N:M (Productos-Categorías)

* 📦 Persistencia en MySQL

**Tecnologías clave:**

* Java EE8 (Servlets, JSP, JDBC)

* Bootstrap 5 + FontAwesome

* Apache Tomcat 9 + Maven

* MySQL (Modelo relacional)

*El propósito principal es proporcionar una solución completa para administrar inventarios, procesar ventas y gestionar relaciones comerciales (proveedores-clientes) mediante un CRUD funcional y seguro.*

## Empezando

Para obtener una copia de este proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba, siga las instrucciones a continuación.

### Requisitos previos

* Para ejecutar este programa, necesita tener algún entorno que permita compilar paquetes de Java (Como NetBeans) instalado en su máquina.
* Para la administración de datos se requiere un modelo relacional para MySQL Workbench, si es necesario, pídaselo a los autores de este proyecto.

### Instalación

No se requiere instalación adicional para este programa.

### Ejecución

1. Ingresa a NetBeans (o tu entorno de desarrollo Java)
2. Solicita el archivo .zip que contiene el programa
3. Compile el programa

**Clonar Repositorio**

```
git clone https://github.com/tu-usuario/E-CommerceED2.git
```

## NOTA:
**La interfaz principal incluye:**

* Dashboard:
Resumen de ventas recientes.

Productos con bajo stock.

Accesos rápidos a módulos.


**Módulos Principales:**

* Productos:

Listado paginado con filtros.

Formularios para agregar/editar (precio, stock, categorías).

* Ventas:

Carrito de compras interactivo.

Registro con descuentos y cálculo automático de totales.

* Clientes/Proveedores:

Gestión de información comercial (RUT, contactos).

# Guía de Uso
1. Agregar Producto
Navegar a listarProductos.jsp.

Click en "Agregar Producto".

Completar formulario:

Nombre, precio, stock.

Seleccionar proveedor y categoría (dropdowns).

Confirmar con "Guardar".

2. Registrar Venta
Ir a nuevaVenta.jsp.

Seleccionar cliente.

Añadir productos al carrito (cantidad y validación de stock).

Aplicar descuento (opcional).

Finalizar con "Procesar Venta".

3. Eliminar Registros
Productos/Clientes/Proveedores:

Buscar en listado correspondiente.

Click en "Eliminar" (con confirmación).

## Ejecutando las pruebas

**Pruebas Manuales:**

Validar CRUD en cada módulo.

Probar relaciones (ej: no eliminar categorías con productos asociados).

## Despliegue (Deployment)

Este programa es de uso local y no requiere despliegue en un sistema en vivo.

## Construcción

**Tecnologías:**

* Java EE8 (Servlets, JSP, JDBC).

* Bootstrap 5 + FontAwesome (frontend).

* Maven (gestión de dependencias).
## Autor/es

**Andrés Lombana, Carlos Arcos y Sebastián Moreno** 

## Licencia

Todos los derechos protegidos por el/los autor/es anterior/es.
