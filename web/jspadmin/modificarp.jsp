<%-- 
    Document   : modificarp
    Created on : 25/11/2021, 05:13:25 PM
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
                <li> <a href="jspadmin/menuadmin.jsp">Menu</a></li>
                <li> <a class="active" href="adminservlet?action=ClientList">Clientes</a></li>
                <li> <a href="adminservlet?action=listUser">Personal</a></li>
                <li> <a href="adminservlet?action=listproductos">Productos</a></li>
        
                    <li class="posicion"><a href="userservlet?action=perfil&userId=<%out.println(usernombre); %>"> <% out.println(usernombre); %> </a> 
                    <ul class="submenu">
                        <li> <a href="logout.jsp">Salir</a> </li>    
                    </ul>
                </li>
                </ul>
                </nav>
        
        <form method="POST" action='adminservlet' enctype="multipart/form-data" >
             <input type="hidden" name="action" value="modp"/>
            <% String action = request.getParameter("action");
                System.out.println(action);
            %>
            <center>
                <table border="1" width="30%" cellpadding="3">
                    <thead>
                        <% if (action.equalsIgnoreCase("editprod")) {%>
                        <tr> 
                            <th colspan="2">Editar Producto</th>
                        </tr>
                        <%} else {%>
                        <tr> 
                            <th colspan="2">Añadir Producto</th>
                        </tr>
                        <%}%>
                    </thead>
                    <tbody>
                        <% if (action.equalsIgnoreCase("editprod")) {%>
                        <tr>
                            <td>nombre</td>
                            <td><input  type="text" name="name" value="<c:out value="${user.nombre}" />" readonly="readonly"/> Solo lectura </td>
                        </tr>
                        <%} else {%>
                        <tr>
                            <td>nombre</td>
                            <td><input  type="text" name="name" value="<c:out value="${user.nombre}" />"/> </td>
                        </tr>
                        <%}%>
                        
                        <tr>
                            <td>Precio</td>
                            <td><input type="text" name="precio" value="<c:out value="${user.precio}" />"  /></td>
                        </tr>
                        <tr>
                            <td>Categoria</td>
                            <td><input type="text" name="cate" value="<c:out value="${user.categoria}" />" /></td>
                        </tr>
                        <tr>
                            <td>Cocina</td>
                            <td><input type="text" name="cocina" value="<c:out value="${user.cocina}" />" /></td>
                        </tr>
                        <% if (action.equalsIgnoreCase("editprod")) {%>
                        <tr>
                            <td>Status</td>
                            <td><input type="text" name="status" value="<c:out value="${user.staatus}" />" /></td>
                        </tr>
                        <%} %>
                        <tr>                            
                            <td><input type="submit" value="Guardar" /></td>
                        </tr>
                       
                    </tbody>
                </table>
             </center>
        </form>
    </body>
</html>

