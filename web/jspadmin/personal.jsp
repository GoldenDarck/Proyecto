<%-- 
    Document   : personal
    Created on : 15/11/2021, 12:07:55 AM
    Author     : Hernan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/estilosadmin.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <ul>
                <li><a href="jspadmin/menuadmin.jsp">Menu</a></li>
                <li><a href="adminservlet?action=ClientList">Clientes</a></li>
                <li><a class="active" href="adminservlet?action=listUser">Personal</a></li>
                <li><a href="adminservlet?action=listproductos">Productos</a></li>
                <li class="posicion"><a href="logout.jsp"> Salir </a> </li>
            </ul>
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
                <th>Status</th>
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
                    <td><c:out value="${user.staatus}" /></td>
                    <td><a href="adminservlet?action=edit&userId=<c:out value="${user.usuario}"/>"> Editar </a> &nbsp;&nbsp;&nbsp;&nbsp; 
                    <a href="adminservlet?action=delete&userId=<c:out value="${user.usuario}"/>"> Eliminar</a></td>
                </tr>
            </c:forEach>
        </tbody>
        </table>
        <p><a href="adminservlet?action=insert">Añadir Usuario</a></p>
    </body>
</html>
