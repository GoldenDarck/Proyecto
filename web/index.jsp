<%-- 
    Document   : index
    Created on : 28/10/2021, 11:18:39 PM
    Author     : Hernan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/nuevosestilos.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div id="header">
            <nav class="navegacion">
                <ul class="menu">
                    <li><a>FootInc</a></li>
                    <li><a href="comprar.jsp" > <img src="imagenes/carrito.jpg"  height="45px" width="45px" aria-haspopup="true" aria-expanded="false"></img></a>
                    </li>
                    <li class="posicion" ><a  href="inisesion.jsp"> Iniciar Sesion </a> </li>
                </ul>
            </nav>
        </div>
    <center>
        <div class="banner">
	    <div class="banner-body">
                <h2>Bienvenido a FOOT INC</h2>
	        <p>Los mejores platillos y la mejor calidad los encuentras aquí</p>
	        <a href="servletmenu?action=desayunos" >
                    <input type="button" value="Menu">
                </a>
	    </div>
	</div>
    </center>
    </body>
</html>
