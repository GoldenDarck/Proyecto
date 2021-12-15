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
import sistema.model.User;
import sistema.model.venta;
/**
 *
 * @author Hernan
 */
@WebServlet
public class carritoservlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String VERPROD = "carta/compras.jsp";
    private static String  DESAYUNOS = "carta/menu.jsp";
    private static String  CARRITO = "carta/carrito.jsp";
    private static String  MENSAJE= "jspusers/mensaje.jsp";
    private carritoDAO cmt;
    private menuDAO dao;
    
    
    
    public carritoservlet()
    {
       cmt =new carritoDAO();
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
        
        if (action.equalsIgnoreCase("comprar")){
            forward = VERPROD;
            String proId = request.getParameter("proId");
            productos pro = cmt.getProductoById(proId);
            request.setAttribute("prod", pro);
            
            String userId = request.getParameter("userId");
            User user = cmt.getUserById(userId);
            request.setAttribute("user", user);
            
        } else if (action.equalsIgnoreCase("carrito")){
            forward = CARRITO;
            String userId = request.getParameter("userId");
            User user = cmt.getUserById(userId);
            int totalv = cmt.totalventa(user);
            request.getSession().setAttribute("totalv", totalv);
            request.setAttribute("comanda", cmt.getAllcomandas(user));
        }else if (action.equalsIgnoreCase("cancel")){
            forward = CARRITO;
            int comId = Integer.parseInt(request.getParameter("comId"));
            comanda comanda = cmt.getComandaById(comId);
            String userId = request.getParameter("userId");
            User user = cmt.getUserById(userId);
            
            int status = cmt.cancelcomanda(comanda);
            
            if(status == 1){
                
                int subtotal = cmt.totalcomanda(comanda);
                int total = cmt.totalventa(user);
                venta t = new venta();
                total = total - subtotal;
                t.setTotal_v(total);
                cmt.updatetotal(t,user);
                
                int totalv = cmt.totalventa(user);
                
                request.getSession().setAttribute("totalv", totalv);
                request.setAttribute("comanda", cmt.getAllcomandas(user));
                
            }else{
                request.setAttribute("message", "No se pudo eliminar el registro ");
                RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            }
        }else if (action.equalsIgnoreCase("fcompra")){
                forward = MENSAJE; 
                String userId = request.getParameter("userId");
                User user = cmt.getUserById(userId);
                cmt.terminarv(user);
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
        
        if (action.equalsIgnoreCase("addcarrito")){
            User user = new User();
            int iduser = Integer.parseInt(request.getParameter("user"));
            user.setId_user(iduser);
            String userid = request.getParameter("user");
            
            int id_prod = Integer.parseInt(request.getParameter("id"));
            int cant = Integer.parseInt(request.getParameter("cantidad"));
            int precio = Integer.parseInt(request.getParameter("precio"));
            
            int total=0;
            total = cant * precio;
            
            comanda com = new comanda();
            com.setId_producto(id_prod);
            com.setCantidad(cant);
            com.setMesa(request.getParameter("mesa"));
            com.setTotal(total);
            
            
            if(cmt.checkVenta(user)){
                int idventa = cmt.getVentaById(user);
                cmt.addCarrito(idventa,com);
                int subtotal = cmt.totalventa(user);
                venta t = new venta();
                total = total + subtotal;
                t.setTotal_v(total);
                cmt.updatetotal(t,user);
            }else {
                int crearvta = cmt.addVenta(user);
                if(crearvta == 1){
                    int idventa = cmt.getVentaById(user);
                    cmt.addCarrito(idventa,com);
                    int subtotal = cmt.totalventa(user);
                    venta t = new venta();
                    total = total + subtotal;
                    t.setTotal_v(total);
                    cmt.updatetotal(t,user);
                }
                
            }
            RequestDispatcher view = request.getRequestDispatcher(DESAYUNOS);
            request.setAttribute("desayunos", dao.getAlldesayunos());
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
