<%-- 
    Document   : registrousuario
    Created on : 9/11/2021, 07:07:53 PM
    Author     : Hernan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <form method="post" action="controlAdmin">
             <input type="hidden" name="action" value="registroUsuario"/>
             <center>
                <table border="1" width="30%" cellpadding="3">
                    <thead>
                        <tr> 
                            <th colspan="2">Registro</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Nombre</td>
                            <td><input type="text" name="name" value="" /></td>
                        </tr>
                        <tr>
                            <td>Apellido Paterno</td>
                            <td><input type="text" name="apepaterno" value="" /></td>
                        </tr>
                        <tr>
                            <td>Apellido Materno</td>
                            <td><input type="text" name="apematerno" value="" /></td>
                        </tr>
                        <tr>
                            <td>Nombre de usuario</td>
                            <td><input type="text" name="username" value="" /></td>
                        </tr>
                        <tr>
                            <td>Contraseña</td>
                            <td><input type="password" name="password" value="" /></td>
                        </tr>
                        <tr>                            
                            <td><input type="submit" value="Registrarse" /></td>
                            <td><input type="reset" value="Borrar" /></td>
                        </tr>
                       
                    </tbody>
                </table>
             </center>            
        </form>         
    </body>
</html>
