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
import sistema.dao.chefDAO;

/**
 *
 * @author Hernan
 */
@WebServlet
public class chefservlet extends HttpServlet {
    private static String PPENDIENTES = "/jspchef/pendientes.jsp";
    private static String PACTIVOS = "/jspchef/pedidosactivos.jsp";
    private static String PFINALIZADOS = "/jspchef/pedidosfinalizados.jsp";
    //private static String LIST_PRODUCTS = "/jspadmin/productos.jsp";
    //private static String LIST_CLIENTS = "/jspadmin/clientes.jsp";
    private chefDAO dao;
    
    public chefservlet(){
        dao = new chefDAO();
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
        
        if (action.equalsIgnoreCase("pactivos")){
            forward = PACTIVOS;
            request.setAttribute("activos", dao.getAllActivos());            
        }else if (action.equalsIgnoreCase("ppendientes")){
            forward = PPENDIENTES;
            request.setAttribute("pend", dao.getAllActivos());            
        }else if (action.equalsIgnoreCase("pfinalizados")){
            forward = PFINALIZADOS;
            request.setAttribute("finaly", dao.getAllFinalizados());            
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
        processRequest(request, response);
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
