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
import javax.servlet.annotation.WebServlet;
import java.text.ParseException;
import javax.servlet.RequestDispatcher;

import sistema.model.productos;
import sistema.model.comanda;
import sistema.dao.menuDAO;

/**
 *
 * @author Hernan
 */
@WebServlet
public class servletmenu extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String  DESAYUNOS = "carta/menu.jsp";
    private static String  PFUERTE = "carta/pfuerte.jsp";
    private static String  BEBIDAS = "carta/bebidas.jsp";
    private static String  POSTRES = "carta/postres.jsp";
    private menuDAO dao;
    
    public servletmenu()
    {
        dao = new menuDAO();
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
        if (action.equalsIgnoreCase("desayunos")){
            forward = DESAYUNOS;
            request.setAttribute("desayunos", dao.getAlldesayunos());            
        } else if(action.equalsIgnoreCase("pfuerte")){
            forward = PFUERTE;
            request.setAttribute("fuerte", dao.getAllpprincipal());
        }else if(action.equalsIgnoreCase("postres")){
            forward = POSTRES ;
            request.setAttribute("postre", dao.getAllpostres());
        }
        else if(action.equalsIgnoreCase("bebida")){
            forward = BEBIDAS;
            request.setAttribute("bebidas", dao.getAllbebidas());
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
