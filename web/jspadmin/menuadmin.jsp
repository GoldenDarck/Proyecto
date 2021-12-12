<%-- 
    Document   : menuadmin
    Created on : 15/11/2021, 12:19:35 AM
    Author     : Hernan
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



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
        <link rel="stylesheet" type="text/css" href="css/estilosadmin.css"/>
        <title>JSP Page</title>
    </head>
    <body>
         <nav> 
            <ul>
                <li><a class="active" href="jspadmin/menuadmin.jsp">Menu</a></li>
                <li><a href="adminservlet?action=ClientList">Clientes</a></li>
                <li><a href="adminservlet?action=listUser">Personal</a></li>
                <li><a href="adminservlet?action=listproductos">Productos</a></li>
                <li class="posicion"><a href="logout.jsp"> Salir </a> </li>
            </ul>
        </nav>
     
        <h1>Datos relacionada al usuario</h1><br/>
        <%
            int id_per = (int)session.getAttribute("id_persona");
            int id_us = (int)session.getAttribute("id_user");
            String nom = (String)session.getAttribute("Nombre");
            String apep = (String)session.getAttribute("a_paterno");
            String  apem =(String)session.getAttribute("a_materno");
            
           
            
        %> 
        <br/>
       <h2> username: <% out.println(usernombre); %> </h2>
        <h2> Id Persona: <% out.println(id_per); %>  </h2>
        <h2> Id Usuario: <% out.println(id_us); %>  </h2>
        <h2> Nombre: <% out.println(nom); %>  </h2>
        <h2> Apellido Paterno: <% out.println(apep); %>  </h2>
        <h2> Apellido Materno: <% out.println(apem); %>  </h2>
        <h1> Permsos del usuario</h1>
        <%
            boolean isadmin = (boolean)session.getAttribute("admin");
            boolean select = (boolean)session.getAttribute("ver");
            boolean update = (boolean)session.getAttribute("modificar");
            boolean insert = (boolean)session.getAttribute("escribir");
            boolean delete = (boolean)session.getAttribute("eliminar");
            
        if (isadmin == true)
        {
            out.println("El usuario es administrador");
            
        
        %>
        
        <script> 
            
            <%out.println(id_us);%>
                
         </script> 
         
                 
         <br/>
         Menu Admin: <a href="controlAdmin?action=buscar_usuario&buscar=<% out.println(usernombre);%>"> Ver persona con otro jsp</a>
         <br/>
         <a href="${pageContext.request.contextPath}/logout.jsp"> Salir </a>
         <%
             }else{

             
         %>
         
         <br/>
        <h2> Permiso de select: <% out.println(select); %> </h2>
        <h2> Permiso de insert: <% out.println(insert); %>  </h2>
        <h2> Permiso de update: <% out.println(update); %>  </h2>
        <h2> Permiso de delete: <% out.println(delete); %>  </h2>
        <%
            out.println("<a href= '../moduloLogin/logout.jsp'> Salir </a>");
            }
        %>
    </body>
</html>
