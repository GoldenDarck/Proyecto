<%-- 
    Document   : perfil
    Created on : 8/12/2021, 05:30:44 PM
    Author     : Hernan
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
                <li> <a href="servletmenu?action=postres" >Postres</a></li>
                <li> <a href="servletmenu?action=bebida" >Bebidas</a></li>
                <li> <a href="carritoservlet?action=carrito&userId=<%out.println(usernombre); %>"  > <img src="imagenes/carrito.jpg"  height="35px" width="35px" aria-haspopup="true" aria-expanded="false"></img></a>
                <% 
                    if (usernombre!=null){
                        
                %>
                <li class="posicion"><a class="active" href="userservlet?action=perfil&userId=<%out.println(usernombre); %>"> <% out.println(usernombre); %> </a> 
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
        <h2 > Mi perfil </h2>
        <a href="UploadServlet?action=imperfil&userId=<%out.println(usernombre); %>"> <img src=${user.url_img} alt=""  width="200" height="200"/></img></a>
            
        <form method="POST" action='userservlet' name="frmeditperfil">
                <input type="hidden" name="action" value="modificaru"/>
                <table border="0" width="500" cellpadding="3" aling="center">
                        <tr>
                            <th> Usuario </th>
                            <th> <input  type="text" name="uname" value="<c:out value="${user.usuario}" />" readonly="readonly"/></th>
                        </tr>
                        <tr>
                            <th>Contaseña</th>
                            <th><input type="password" name="pass" value="<c:out value="${user.password}" />"  /></th>
                        </tr>
                        <tr>
                            <th>Nombre</th>
                            <th><input type="text" name="nombre" value="<c:out value="${user.nombre}" />" /></th>
                        </tr>
                        <tr>
                            <th>Apellido Paterno</th>
                            <th><input type="text" name="apaterno" value="<c:out value="${user.a_paterno}" />" /></th>
                        </tr>
                        <tr>
                            <th>Apellido Materno</th>
                            <th><input type="text" name="amaterno" value="<c:out value="${user.a_materno}" />" /></th>
                        </tr>
                        <tr>                            
                            <th colspan="3"> <input type="submit" value="Guardar" /> </th>
                        </tr>      
                </table>         
        </form>
    </center> 
        <h3>${requestScope.message}</h3>
    </body>
</html>
