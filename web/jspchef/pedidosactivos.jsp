<%-- 
    Document   : pedidosactivos
    Created on : 12/12/2021, 12:11:41 AM
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
                <li> <a class="active" href="chefservlet?action=pactivos">Pedidos Activos</a></li>
                <li> <a href="chefservlet?action=pfinalizados">Pedidos finalizados</a></li>
                <li> <a href="chefservlet?action=ppendientes">Pedidos Pendientes</a></li>
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
                <th>Id_comanda</th>
                <th>Id_producto</th>
                <th>Mesa</th>
                <th>Cantidad</th>
                <th>Total</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${activos}" var="com">
                <tr>
                    <td><c:out value="${com.id_comanda}"/></td>
                    <td><c:out value="${com.id_producto}" /></td>
                    <td><c:out value="${com.mesa}" /></td>
                    <td><c:out value="${com.cantidad}" /></td>
                    <td><c:out value="${com.total}" /></td>
                    <td><a href="adminservlet?action=edit&userId=<c:out value="${user.usuario}"/>">Finalizar</a> &nbsp;&nbsp;&nbsp;&nbsp; 
                    <a href="adminservlet?action=delete&userId=<c:out value="${user.usuario}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
        </table>
    </body>
</html>
