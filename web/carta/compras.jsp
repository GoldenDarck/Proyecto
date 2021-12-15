<%-- 
    Document   : compras
    Created on : 27/11/2021, 04:10:10 PM
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
    <center>
        <h2 aling="center"> Añadir Producto al Carrito </h2>
        
        <form method="POST" action='carritoservlet' name="frmComProduct">
             <input type="hidden" name="action" value="addcarrito"/>
             <input type="hidden" name="id" value="<c:out value="${prod.id_producto}"/>"/>
             <input type="hidden" name="user" value="<c:out value="${user.id_user}"/>"/>
                <table border="0" width="500" cellpadding="3" aling="center">
                        <tr>
                            <th rowspan="5"><img src=${prod.url_img} alt=""  width="200" height="200"/> </th>
                            <th> Producto </th>
                            <th> <input  type="text" name="name" value="<c:out value="${prod.nombre}" />" readonly="readonly" /> </th>
                        </tr>
                        <tr>
                            <th>Precio</th>
                            <th><input type="text" name="precio" value="<c:out value="${prod.precio}" />" readonly="readonly"/></th>
                        </tr>
                        <tr>
                            <th>Cantidad</th>
                            <th><input type="number" main="0" value="0" name="cantidad"/>   </th>
                        </tr>
                        <tr>
                            <th>Numero de Mesa</th>
                            <th>
				<select class="form-select" id="selectmesa" name="mesa" onchange="myFunction()" required>
				<option selected value="">Elige...</option>
				<option value="1">Mesa 1</option>
                                <option value="2">Mesa 2</option>
                                <option value="3">Mesa 3</option>
                                <option value="4">Mesa 4</option>
                                <option value="5">Mesa 5</option>
				</select>
                            </th>
                        </tr>
                        <tr>                            
                            <th colspan="3"> <input type="submit"  value="Añadir al Carrito" /> </th>
                        </tr>
                </table>
        </form>
    </center>                    
    </body>
</html>

