/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.dao;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;

import sistema.util.Database;
import sistema.model.permisos;
import sistema.model.persona;
import sistema.model.User;
/**
 *
 * @author Hernan
 */
public class Admindao {
    private Connection con = null;
    
    public Admindao(){
        con =Database.getConnection();
    }
    
    public boolean login (String Usuario, String Password)
    {
        try
        {
            PreparedStatement pstm =null;
            ResultSet rs =null;
            String query ="SELECT id_user FROM users WHERE usuario =? AND contraseña =? AND staatus =?";
            pstm =con.prepareStatement(query);
            pstm.setString(1, Usuario);
            pstm.setString(2, Password);
            pstm.setBoolean(3, true);
            rs = pstm.executeQuery();
            if (rs.next()){
                return true;
            }else{
                return false;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public permisos obtenerPermisos (String user)
    {
        try {
            PreparedStatement pstm =null;
            ResultSet rs = null;
            String query = "SELECT * FROM permisos WHERE id_permisos IN (SELECT id_permisos FROM roles as r INNER JOIN users as u ON r.id_rol = u.rol WHERE u.usuario =?)";
            pstm =con.prepareStatement(query);
            pstm.setString (1, user);
            rs = pstm.executeQuery();
            if(rs.next()){
                permisos p = new permisos();
                p.setVer(rs.getBoolean("ver"));
                p.setModificar(rs.getBoolean("modificar"));
                p.setEscribir(rs.getBoolean("escribir"));
                p.setEliminar(rs.getBoolean("eliminar"));
                p.setAdmin(rs.getBoolean("admin"));
                return p;                
            }else {
                return null;
            }           
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public String getURLMenu (String usuario){
        String url ="";
         try{
             PreparedStatement preparedStatement = con.prepareStatement("SELECT url FROM roles as r INNER JOIN users as u ON u.rol = r.id_rol WHERE u.usuario =?");
             preparedStatement.setString(1, usuario);
             ResultSet rs =preparedStatement.executeQuery();
             if(rs.next()){
                 url=rs.getString("url"); 
             }    
         }catch (Exception ex)
         {
             ex.printStackTrace();
         }
         return url;
    }
    
    public int getusuarioPorNombre (String nombre)
    {
        int usuario =0;
        try{
            PreparedStatement pstm =null;
            ResultSet rs = null;
            String query ="SELECT id_user from users WHERE usuario = ? ";
            pstm =con.prepareStatement(query);
            pstm.setString(1, nombre);
            rs = pstm.executeQuery();
            if(rs.next())
            {
                usuario = rs.getInt("id_user");
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return usuario;
    }
    
    public int getROlByUsuario(int id_usuario)
    {
        int id_rol = 0;
        try {
            PreparedStatement pstm =null;
            ResultSet rs = null;
            String query = "SELECT rol FROM users WHERE id_user =?";
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id_usuario);
            rs = pstm.executeQuery();
            if (rs.next())
            {
                id_rol =rs.getInt("rol");
            }
        }catch (Exception ex ){
            ex.printStackTrace();
        }
        return id_rol;
    }
    
    public boolean registroEnBitacora (int id_usuario, int id_rol)
    {
        try {
            PreparedStatement pstm =null;
            ResultSet rs =null;
            String query = ("INSERT INTO bitacora_ingresos (id_user, id_rol, fecha_reg) VALUES (?,?, now())");
            pstm =con.prepareStatement(query);
            pstm.setInt(1, id_usuario);
            pstm.setInt(2, id_rol);
            if(pstm.executeUpdate() ==1)
            {
                return true;
            }
        }catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
        return false;
    }
    
    public int registrarUser (User user) throws ClassNotFoundException
    {
        int regus=0;
        try {
            PreparedStatement pstm =null;
            ResultSet rs =null;
            String query = ("INSERT INTO users (usuario, contraseña, nombre, a_paterno, a_materno,url_img,rol,staatus) VALUES (?, ?, ?, ?,?,?,?,?)");
            pstm =con.prepareStatement(query);
            //pstm.setInt(1, 1);
            pstm.setString(1, user.getUsuario());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getNombre());
            pstm.setString(4, user.getA_paterno());
            pstm.setString(5, user.getA_materno());
            pstm.setString(6, "");
            pstm.setInt(7, 4);
            pstm.setInt(8, 1);
            
            System.out.println(pstm);
            regus=pstm.executeUpdate();
    
        }catch(SQLException ex)
        {
           ex.printStackTrace();
        }
        return regus;
    }
    
    public persona getPersona(String nombre)
    {
        persona p = new persona();
        try{
            PreparedStatement pstm = null;
            ResultSet rs=null;
            String query ="SELECT * FROM persona WHERE id_user IN (SELECT id_user FROM users WHERE usuario = ? )";
            pstm = con.prepareStatement(query);
            pstm.setString(1, nombre);
            rs = pstm.executeQuery();
            while(rs.next())
            {
                p.setIdPersona(rs.getInt("id_persona"));
                p.setIdUsuario(rs.getInt("id_user"));
                p.setNombre(rs.getString("Nombre"));
                p.setAPaterno(rs.getString("a_paterno"));
                p.setAMaterno(rs.getString("a_materno"));
                
            }
        }catch (SQLException ex ){
            ex.printStackTrace();
        }
        return p;
    }
}
