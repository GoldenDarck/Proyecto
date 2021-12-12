/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

import sistema.model.User;
import sistema.model.productos;
import sistema.dao.VAdminDAO;

@WebServlet
public class adminservlet extends HttpServlet {

    //private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/jspadmin/modificaru.jsp";
    private static String INSERT_OR_EDIT_P = "/jspadmin/modificarp.jsp";
    private static String LIST_USER = "/jspadmin/personal.jsp";
    private static String LIST_PRODUCTS = "/jspadmin/productos.jsp";
    private static String LIST_CLIENTS = "/jspadmin/clientes.jsp";
    private VAdminDAO dao;
    
    
    public adminservlet()
    {
        dao = new VAdminDAO();
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String forward="";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")){
            String userId = request.getParameter("userId");
            dao.deleteUser(userId);
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());    
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            String userId = request.getParameter("userId");
            User user = dao.getUserById(userId);
            request.setAttribute("user", user);
            
        } else if (action.equalsIgnoreCase("deleteprod")){
            String proId = request.getParameter("proId");
            dao.deleteProducto(proId);
            forward = LIST_PRODUCTS;
            request.setAttribute("users", dao.getAllproductos());    
        } else if (action.equalsIgnoreCase("editprod")){
            forward = INSERT_OR_EDIT_P;
            String proId = request.getParameter("proId");
            productos pro = dao.getProductoById(proId);
            request.setAttribute("user", pro);
            
        }else if (action.equalsIgnoreCase("listUser")){
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());            
        } else if (action.equalsIgnoreCase("listproductos")){
            forward = LIST_PRODUCTS;
            request.setAttribute("users", dao.getAllproductos());
        }else if (action.equalsIgnoreCase("ClientList")){
            forward = LIST_CLIENTS;
            request.setAttribute("users", dao.getAllClientes());            
        } else if(action.equalsIgnoreCase("insertprod")){
            forward = INSERT_OR_EDIT_P;
        }else{
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        response.setContentType("text/plain; charset=UTF-8");//establece el tipo de contenido de la respuesta 'text' en unicode UTF8
        PrintWriter out = response.getWriter();
        
        if (action.equalsIgnoreCase("modp")){
            
            productos prod = new productos();
            int cash = Integer.parseInt(request.getParameter("precio"));
            int cate = Integer.parseInt(request.getParameter("cate"));
            boolean cocina= Boolean.parseBoolean(request.getParameter("cocina")); 
            boolean Stat= Boolean.parseBoolean(request.getParameter("status"));
            prod.setNombre(request.getParameter("name"));
            prod.setPrecio(cash);
            prod.setCategoria(cate);
            prod.setCocina(cocina);
            prod.setStaatus(Stat);
            String proid = request.getParameter("name");       
            if(proid == null || proid.isEmpty())
            {
                dao.addProducto(prod);
                
            }else{
                prod.setNombre(proid);
                dao.checkProduct(prod);
            }
            RequestDispatcher view = request.getRequestDispatcher(LIST_PRODUCTS);
            request.setAttribute("users", dao.getAllproductos());
            view.forward(request, response);
        
        
        }else if (action.equalsIgnoreCase("modu")){
            User user = new User();
        int Rol = Integer.parseInt(request.getParameter("rol")); 
        boolean Status= Boolean.parseBoolean(request.getParameter("status")); 
        user.setUsuario(request.getParameter("uname"));
        user.setPassword(request.getParameter("pass"));
        user.setNombre(request.getParameter("nombre"));
        user.setA_paterno(request.getParameter("apaterno"));
        user.setA_materno(request.getParameter("amaterno"));
        user.setRol(Rol);
        user.setStaatus(Status);
        String userid = request.getParameter("uname");
        if(userid == null || userid.isEmpty())
        {
            dao.addUser(user);
        }else{
            user.setUsuario(userid);
            dao.checkUser(user);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("users", dao.getAllUsers());
        view.forward(request, response);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
