<%-- 
    Document   : carrito
    Created on : 27/11/2021, 02:49:23 PM
    Author     : Hernan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<% 
    String usernombre = (String)session.getAttribute("usuario");
    int tventa = (int)session.getAttribute("totalv");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/nuevosestilos.css"/>
        <title>Carrito</title>
    </head>
    <body>
        <nav class="navegacion">
            <ul class="menu">
                <li><a>FootInc</a></li>
                <li> <a href="servletmenu?action=desayunos" >Desayunos</a></li>
                <li> <a href="servletmenu?action=pfuerte" >Plato fuerte </a></li>
                <li> <a href="servletmenu?action=postres" >Postres</a></li>
                <li> <a href="servletmenu?action=bebida" >Bebidas</a></li>
                <li> <a class="active" href="carritoservlet?action=carrito&userId=<%out.println(usernombre); %>"  > <img src="imagenes/carrito.jpg"  height="35px" width="35px" aria-haspopup="true" aria-expanded="false"></img></a>
                <% 
                    if (usernombre!=null){
                        
                %>
                <li class="posicion"><a href="userservlet?action=perfil&userId=<%out.println(usernombre); %>"> <% out.println(usernombre); %> </a> 
                    <ul class="submenu">
                        <li> <a href="logout.jsp"> Cerrar Sesion </a> </li>    
                    </ul>
                </li>   
                <% } else { %> 
                <li class="posicion"><a href="inisesion.jsp"> Iniciar Sesion </a> </li>
                <%}%>
            </ul>
        </nav>
        <center>
            <h1>Carrito</h1>    
            <table border="0" width="500" cellpadding="3" aling="center">
            <thead>
            <tr>
                    <th></th>
                    <th>Producto</th>
                    <th>Precio</th>
                    <th>Mesa</th>
                    <th>Cantidad</th>
                    <th>Total</th>
                    <th colspan=2> </th>
            </tr>
            
            </thead>
                <tbody>
            <c:forEach items="${comanda}" var="com">
                <tr>
                    <td><img src=${com.url_img} alt=""  width="150" height="150"/></td>
                    <td><c:out value="${com.nombre}" /></td>
                    <td><c:out value="${com.precio}" /></td>
                    <td><c:out value="${com.mesa}" /> </td>
                    <td><c:out value="${com.cantidad}" /></td>
                    <td><c:out value="${com.total}" />  </td>
                    <td> <a href="carritoservlet?action=cancel&comId=<c:out value="${com.id_comanda}"/>&userId=<%out.println(usernombre); %>">Eliminar</a></td>
                </tr>
            </c:forEach>
                <tr>
                    <td colspan=6 > Total de compra: <a ><%out.println(tventa); %></a></td>
                </tr> 
                </tbody>
                </table>
                <a href="carritoservlet?action=fcompra&userId=<%out.println(usernombre); %>"><input type="button" value="Finalizar Compra" /></a>
        </center>
    </body>
</html>
