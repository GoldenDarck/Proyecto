<%-- 
    Document   : inisesion
    Created on : 22/11/2021, 09:32:12 PM
    Author     : Hernan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form method="post" action="controlAdmin">
            <input type="hidden" name="action" value="login"/>
            <center>
                <table border="1" width="30%" cellpadding="3">
                    <thead>
                        <tr> 
                            <th colspan="2"> Iniciar Sesion</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td for="User"> Usuario </td>
                            <td> <input type="text" name="usuario" placeholder=" usuario" autofocus required/> </td>
                        </tr>
                        <tr>
                            <td for="passwd"> Contraseña </td>
                            <td> <input type="password" name="password" placeholder="contraseña" autofocus required/> </td>
                        </tr>
                        <tr>                            
                            <td><input type="submit" value="Login" /></td>
                            <td><input type="reset" value="Borrar" /></td>
                        </tr>
                        <tr>
                            <td colspan="2"> <a href="${pageContext.request.contextPath}/registrousuario.jsp"> Registrarse </a></td>
                        </tr>
                        </tbody>
                </table>
            </center>
        </form>
        
        <%
            String mensaje = ( String )session.getAttribute("mensaje");
            if (!(mensaje == null)){
                out.println(mensaje);
                session.setAttribute("mensaje", null);
            }
        %>
    </body>
</html>
