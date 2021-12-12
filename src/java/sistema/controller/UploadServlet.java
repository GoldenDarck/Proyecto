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

//importar las siguientes clases
import java.io.File;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Part;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import sistema.dao.VAdminDAO;
import sistema.model.productos;
/**
 * Sintaxis de la annotation de webservlet
 * @WebServlet(
 *   attribute1=value1,
 *   attribute2=value2,
 *   ...
 * )
 * 
 * Sintaxis de la annotation de MultipartConfig
 * @MultipartConfig(
 *       fileSizeThreshold   = <size in bytes>,
 *       maxFileSize         = <size in bytes>,
 *       maxRequestSize      = <size in bytes>,
 *       location            = <save location>
 * )
 */
@WebServlet("/Upload") //se pone /Upload por el campo value=Upload en el form(JSP)
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB

public class UploadServlet extends HttpServlet {

    //almacenar el nombre del archivo 
    String fileNameUploaded = null; 
    String prodname = null;
    private static String LIST_PRODUCTS = "/jspadmin/productos.jsp";
    private static String upImg = "/uploads.jsp";
    private static final String SAVE_DIR = "imagenes"; //carpeta donde se guardaran los archivos
    //crear un objeto DAO para ejecutar querys del UPLOAD
    private VAdminDAO upObj;//crear un objetos de tipo DAO para realizar algunos querys
    public UploadServlet()
    {
        super();//invocar métodos de la clase padre
        upObj = new VAdminDAO();//crear objetos de tipo AdminDAO
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String forward="";//almacena la ruta a la que debe redireccionar al usuario
        String action = request.getParameter("action");//obtiene el valor del campo 'action' del JSP
        response.setContentType("text/plain; charset=UTF-8");//establece el tipo de contenido de la respuesta 'text' en unicode UTF8
        PrintWriter out = response.getWriter();//devuelve un objeto PrintWriter para imprimir (enviar) texto en la página del navegador (cliente)
        
        //if(action.equalsIgnoreCase("Upload"))//si el campo value de 'action' que viene del JSP es = login 
        //{         
            //obtener la ruta absoluta de la AppWeb
            String appPath = request.getServletContext().getRealPath("");
            //construir la ruta donde se almacenara el archivo
            String savePath = appPath + File.separator + SAVE_DIR;               
        
            /****** crear el directorio images en caso de que no exista *******/
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
        
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                // refines the fileName in case it is an absolute path
                fileName = new File(fileName).getName();
                part.write(savePath + File.separator + fileName);
                fileNameUploaded = fileName;
            }
        
            //fusionar el nombre de la carpeta + nombre-archivo
            String nombreArchivo = SAVE_DIR + "/" + fileNameUploaded;
            //String proid = request.getParameter("name");  
            upObj.addURLfromImageName(nombreArchivo,prodname);
        
            request.setAttribute("message", "Upload has been done successfully!  " + savePath + "/" +fileNameUploaded);
            RequestDispatcher view = request.getRequestDispatcher(LIST_PRODUCTS);
            request.setAttribute("users", upObj.getAllproductos());
            view.forward(request, response);
        //}else if (action.equalsIgnoreCase("buscarimg"))        
        //{
/*           int  imgID = Integer.parseInt(request.getParameter("imageid"));            
           String url = null;
           url = upObj.getImageById(imgID);
           request.getSession().setAttribute("mensaje", url);    
*/
        //}else{                
        //    forward = "index.jsp";
        //    request.getSession().setAttribute("mensaje", "Imagen no encontrada");
        //}
    }        
    
    /*** metodo que extrae el nombre del archivo del header HTTP content-disposition ***/
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
      
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        
        if(action.equalsIgnoreCase("buscarimg"))
        {
           int  imgID = Integer.parseInt(request.getParameter("imageid"));            
           String url = null;
           url = upObj.getImageById(imgID);
           
           request.getSession().setAttribute("urlimg", url);
           request.setAttribute("message", "Path imagen:  " + url);
            getServletContext().getRequestDispatcher(LIST_PRODUCTS).forward(
                request, response);
        }else if(action.equalsIgnoreCase("subirimg")){ 
            
            String proId = request.getParameter("prodId");
            prodname =proId;
            productos pro = upObj.getProductoById(proId);
            forward = upImg;
            request.setAttribute("producto", pro);
        }else{
            forward = LIST_PRODUCTS;
            
        } 
        RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
            //request.getSession().setAttribute("mensaje", "Imagen no encontrada");
    }
    
    @Override
    public String getServletInfo() {
        return "Servlet para subir archivos";
    }// </editor-fold>

}
