<%-- 
    Document   : Pendientes
    Created on : 12/12/2021, 12:45:58 AM
    Author     : eduar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<% 
    String usernombre = (String)session.getAttribute("usuario");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/nuevosestilos.css"/>
        <title>Pedidos Activos</title>
    </head>
    <body>
        
        <nav class="navegacion">
        <ul class="menu">
                <li><a>FootInc</a></li>
                <li> <a  href="jspchef/pedidosactivos.jsp">Pedidos Activos</a></li>
                <li> <a  class="active" href="adminservlet?action=ClientList">Pedidos Pendientes</a></li>
                <li> <a href="adminservlet?action=listUser">Personal</a></li>
                <li> <a href="adminservlet?action=listproductos">Productos</a></li>
        
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
                <th>Id</th>
                <th>Usuario</th>
                <th>Contraseña</th>
                <th>Nombre</th>
                <th>A. Paterno</th>
                <th>A. Materno</th>
                <th>Rol</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.id_user}"/></td>
                    <td><c:out value="${user.usuario}" /></td>
                    <td><c:out value="${user.password}" /></td>
                    <td><c:out value="${user.nombre}" /></td>
                    <td><c:out value="${user.a_paterno}" /></td>
                    <td><c:out value="${user.a_materno}" /></td>
                    <td><c:out value="${user.rol}" /></td>
                    <td><a href="adminservlet?action=edit&userId=<c:out value="${user.usuario}"/>">Update</a> &nbsp;&nbsp;&nbsp;&nbsp; 
                    <a href="adminservlet?action=delete&userId=<c:out value="${user.usuario}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
        </table>
        <p><a href="adminservlet?action=insert">Add User</a></p>
    </body>
</html>
