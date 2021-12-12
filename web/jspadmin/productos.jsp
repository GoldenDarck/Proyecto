<%-- 
    Document   : productos
    Created on : 24/11/2021, 04:48:54 PM
    Author     : josue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
    String usernombre = (String)session.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/nuevosestilos.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navegacion">
        <ul class="menu">
                <li><a>FootInc</a></li>
                <li> <a href="jspadmin/menuadmin.jsp">Menu</a></li>
                <li> <a  href="adminservlet?action=ClientList">Clientes</a></li>
                <li> <a  href="adminservlet?action=listUser">Personal</a></li>
                <li> <a class="active" href="adminservlet?action=listproductos">Productos</a></li>
        
                    <li class="posicion"><a href="userservlet?action=perfil&userId=<%out.println(usernombre); %>"> <% out.println(usernombre); %> </a> 
                    <ul class="submenu">
                        <li> <a href="logout.jsp">Salir</a> </li>    
                    </ul>
                </li>
                </ul>
                </nav>
        <table border=1>
        <thead>
            <tr>
                <th>Id_producto</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Categoria</th>
                <th>URL_img</th>
                <th>Cocina</th>
                <th>Staatus</th>
                <th colspan=3>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.id_producto}"/></td>
                    <td><c:out value="${user.nombre}" /></td>
                    <td><c:out value="${user.precio}" /></td>
                    <td><c:out value="${user.categoria}" /></td>
                    <td><c:out value="${user.url_img}" /></td>
                    <td><c:out value="${user.cocina}" /></td>
                    <td><c:out value="${user.staatus}" /></td>
                    <td><a href="adminservlet?action=editprod&proId=<c:out value="${user.nombre}"/>">Editar</a> &nbsp;&nbsp;&nbsp;&nbsp; 
                    <a href="adminservlet?action=deleteprod&proId=<c:out value="${user.nombre}"/>">Eliminar</a>
                    <a href="UploadServlet?action=subirimg&prodId=<c:out value="${user.nombre}"/>">Subir una Imagen</a>
                    </td>
                    
                </tr>
            </c:forEach>
        </tbody>
        </table>
        <p><a href="adminservlet?action=insertprod">Añadir Producto</a></p>
        
        
        <h2>${requestScope.message}</h2>
         
    </body>
</html>

