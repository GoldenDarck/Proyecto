<%-- 
    Document   : uploads
    Created on : Nov 23, 2020, 10:22:48 AM
    Author     : pmoreno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<% 
    String usernombre = (String)session.getAttribute("usuario");
    
    
    if (usernombre==null){
        response.sendRedirect("index.jsp");
    
    }
    
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Upload JSP Page</title>
    </head>
    <body>
         <h1>File Upload</h1>
        <form method="post" action='UploadServlet' enctype="multipart/form-data" name="action">
            Seleccionar: <input type="file" name="file" size="60"/> 
            <br /> <input type="submit" value="Upload" />
        </form>

    </body>
</html>
