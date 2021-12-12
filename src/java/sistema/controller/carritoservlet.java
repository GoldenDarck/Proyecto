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
import sistema.dao.carritoDAO;

/**
 *
 * @author Hernan
 */
@WebServlet
public class carritoservlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String VERPROD = "carta/compras.jsp";
    private carritoDAO cmt;
    
    public carritoservlet()
    {
        cmt =new carritoDAO();
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
        
        if (action.equalsIgnoreCase("comprar")){
            forward = VERPROD;
            String proId = request.getParameter("proId");
            productos pro = cmt.getProductoById(proId);
            request.setAttribute("prod", pro);
            
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
        
        if (action.equalsIgnoreCase("carrito")){
            comanda com = new comanda();
            int id = Integer.parseInt(request.getParameter("idprod"));
            int cant = Integer.parseInt(request.getParameter("cantidad"));
            int total = Integer.parseInt(request.getParameter("total"));
            com.setId_producto(id);
            com.setCantidad(cant);
            com.setSubtotal(0);
            com.setSubtotal(total);
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
