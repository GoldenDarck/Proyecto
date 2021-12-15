<%-- 
    Document   : mensaje
    Created on : 14/12/2021, 08:06:17 PM
    Author     : Hernan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    String usernombre = (String)session.getAttribute("usuario");
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/nuevosestilos.css"/>
        <title>Mensaje</title>
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
        <h1>Gracias por comprar en nuestro restaurante!</h1>
        <br>
        <h3>En seguida se le entregaran sus productos.</h3>
        <br>
        <a href="servletmenu?action=desayunos" > <input type="button" value="Regresar" /></a>
    </center>
        
    </body>
</html>
