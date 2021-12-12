<%-- 
    Document   : modificaru
    Created on : 24/11/2021, 02:35:34 PM
    Author     : Hernan
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action='adminservlet' name="frmAddUser">
            <input type="hidden" name="action" value="modu"/>
            <% String action = request.getParameter("action");
                System.out.println(action);
            %>
            <center>
                <table border="1" width="30%" cellpadding="3">
                    <thead>
                        <% if (action.equalsIgnoreCase("edit")) {%>
                        <tr> 
                            <th colspan="2">Editar Usuario</th>
                        </tr>
                        <%} else {%>
                        <tr> 
                            <th colspan="2">Añadir Usuario</th>
                        </tr>
                        <%}%>
                    </thead>
                    <tbody>
                        <% if (action.equalsIgnoreCase("edit")) {%>
                        <tr>
                            <td>Usuario</td>
                            <td><input  type="text" name="uname" value="<c:out value="${user.usuario}" />" readonly="readonly"/> Solo lectura </td>
                        </tr>
                        <%} else {%>
                        <tr>
                            <td>Usuario</td>
                            <td><input  type="text" name="uname" value="<c:out value="${user.usuario}" />"/> </td>
                        </tr>
                        <%}%>
                        
                        <tr>
                            <td>Contraseña</td>
                            <td><input type="password" name="pass" value="<c:out value="${user.password}" />"  /></td>
                        </tr>
                        <tr>
                            <td>Nombre</td>
                            <td><input type="text" name="nombre" value="<c:out value="${user.nombre}" />" /></td>
                        </tr>
                        <tr>
                            <td>Apellido Paterno</td>
                            <td><input type="text" name="apaterno" value="<c:out value="${user.a_paterno}" />" /></td>
                        </tr>
                        <tr>
                            <td>Apellido Materno</td>
                            <td><input type="text" name="amaterno" value="<c:out value="${user.a_materno}" />" /></td>
                        </tr>
                        <tr>
                            <td>Rol</td>
                            <td><input type="text" name="rol" value="<c:out value="${user.rol}" />" /></td>
                        </tr>
                        <% if (action.equalsIgnoreCase("edit")) {%>
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
