
package sistema.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import sistema.dao.Admindao;
import sistema.model.User;
import sistema.model.persona;
import sistema.model.permisos;

 
public class controlAdmin extends HttpServlet {
    
    private Admindao admin;

    public controlAdmin()
    {
    super ();
    admin = new Admindao();
    }
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
        String action =request.getParameter ("action");
        response.setContentType("text/plain; charset=UTF-8");//establece el tipo de contenido de la respuesta 'text' en unicode UTF8
        PrintWriter out = response.getWriter();
        
        if (action.equalsIgnoreCase("login"))
        {
            String user = request.getParameter("usuario");
            String passwd = request.getParameter("password");
            if(admin.login(user, passwd))
            {
                
                    forward =admin.getURLMenu(user);
                    
                    int id_usuario =admin.getusuarioPorNombre(user);
                    int id_rol = admin.getROlByUsuario(id_usuario);
                    
                    request.getSession().setAttribute("usuario", user);
                    request.getSession().setAttribute("userid", id_usuario);
                    request.getSession().setAttribute("rol", id_rol);
                    
                    permisos pp = admin.obtenerPermisos(user);
                    
                    this.agregaPermiso(pp, request);
                    
                    admin.registroEnBitacora(id_usuario, id_rol);
                
                }else
                    {
                        forward="inisesion.jsp";
                        request.getSession().setAttribute("mensaje", "Error de Login");
                    }
                    persona x = new persona();
                    
                    x = admin.getPersona(user);
                    
                    request.getSession().setAttribute("id_persona", x.getIdPersona());
                    request.getSession().setAttribute("id_user", x.getIdUsuario());
                    request.getSession().setAttribute("Nombre", x.getNombre());
                    request.getSession().setAttribute("a_paterno", x.getAPaterno());
                    request.getSession().setAttribute("a_materno", x.getAMaterno());
            
                    this.redireccionar(forward, request, response);
                    
            }else if (action.equalsIgnoreCase("buscar_usuario"))
                {
                    String buscar =request.getParameter("buscar");
                    persona y = new persona();
                    y = admin.getPersona(buscar);
                    request.getSession().setAttribute("id_persona", y.getIdPersona());
                    request.getSession().setAttribute("id_user", y.getIdUsuario());
                    request.getSession().setAttribute("Nombre", y.getNombre());
                    request.getSession().setAttribute("a_paterno", y.getAPaterno());
                    request.getSession().setAttribute("a_materno", y.getAMaterno());
                    forward = "verpersona.jsp";
                    RequestDispatcher view =request.getRequestDispatcher(forward);
                    view.forward(request, response);
                    
                }else if (action.equalsIgnoreCase("registroUsuario"))
                    {
                        String Nombre = request.getParameter("name");
                        String apellidop = request.getParameter("apepaterno");
                        String apellidom = request.getParameter("apematerno");
                        String usuario = request.getParameter("username");
                        String contraseña = request.getParameter("password");
                        
                        User u = new User();
                        u.setNombre(Nombre);
                        u.setA_paterno(apellidop);
                        u.setA_materno(apellidom);
                        u.setUsuario(usuario);
                        u.setPassword(contraseña);
                        
                     try{
                         admin.registrarUser(u);
                     }catch (ClassNotFoundException ex ){
                            ex.printStackTrace();
                        }
                     request.getRequestDispatcher("index.jsp").forward(request, response);
                    } 
    
    }
    
    public void agregaPermiso(permisos p, HttpServletRequest req)
    {
        req.getSession().setAttribute("escribir", p.getEscribir());
        req.getSession().setAttribute("modificar", p.getModificar());
        req.getSession().setAttribute("ver", p.getVer());
        req.getSession().setAttribute("eliminar", p.getEliminar());
        req.getSession().setAttribute("admin", p.getAdmin());
    }
    
    public void redireccionar(String forward, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher view =request.getRequestDispatcher(forward);
        view.forward(request, response);
        
        
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
