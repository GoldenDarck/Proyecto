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
import sistema.dao.userDAO;



/**
 *
 * @author Hernan
 */
@WebServlet
public class userservlet extends HttpServlet {
    private static String PERFIL = "/jspusers/perfil.jsp";
    private userDAO udao;
    
    public userservlet(){
        udao = new userDAO();
    }
    
    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        if (action.equalsIgnoreCase("perfil")){
            forward = PERFIL;
            String userId = request.getParameter("userId");
            User user = udao.getUser(userId);
            request.setAttribute("user", user);
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        response.setContentType("text/plain; charset=UTF-8");//establece el tipo de contenido de la respuesta 'text' en unicode UTF8
        PrintWriter out = response.getWriter();
        
        if (action.equalsIgnoreCase("modificaru")){
            User user = new User();
            user.setUsuario(request.getParameter("uname"));
            user.setPassword(request.getParameter("pass"));
            user.setNombre(request.getParameter("nombre"));
            user.setA_paterno(request.getParameter("apaterno"));
            user.setA_materno(request.getParameter("amaterno"));
        
            String userid = request.getParameter("uname");
            
            if(userid == null || userid.isEmpty())
            {
                request.setAttribute("message", "Error de Login");
            }else{
                user.setUsuario(userid);
                udao.revUser(user);
            }
            
            request.setAttribute("message", "Datos guardados ");
            RequestDispatcher view = request.getRequestDispatcher(PERFIL);
            request.setAttribute("user", udao.getUser(userid));
            view.forward(request, response);    
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
