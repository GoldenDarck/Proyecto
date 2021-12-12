<%-- 
    Document   : postres
    Created on : 24/11/2021, 12:10:57 AM
    Author     : Hernan
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
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navegacion">
            <ul class="menu">
                <li><a>FootInc</a></li>
                <li> <a href="servletmenu?action=desayunos" >Desayunos</a></li>
                <li> <a href="servletmenu?action=pfuerte" >Plato fuerte </a></li>
                <li> <a class="active" href="servletmenu?action=postres" >Postres</a></li>
                <li> <a href="servletmenu?action=bebida" >Bebidas</a></li>
                <li> <a href="comprar.jsp" > <img src="imagenes/carrito.jpg"  height="35px" width="35px" aria-haspopup="true" aria-expanded="false"></img></a>
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
            <h1 align="center"> Postres </h1> 
                
            <table border=0 align="center" width="1000">
                <%    
                    int salto=0; 
                %>
                <c:forEach items="${postre}" var="pro">
                <th><img src="<c:out value="${pro.url_img}" />"  height="140" width="140"> <p>              
                    <c:out value="${pro.nombre}"/><br> 
                    <c:out value="${pro.precio}"/><p>
                    <input type="hidden" name="action" />
                    <a href="carritoservlet?action=comprar&proId=<c:out value="${pro.nombre}"/>"> 
                        <input type="button" value="Comprar" />  
                    </a> 
                </th>
                <%
                    salto++;
                    if (salto==3){
                %>
                        <tr>
                    <%
                            salto=0;
                    }
                    
                    %>
                </c:forEach>
                </table>
                
    </body>
</html>
