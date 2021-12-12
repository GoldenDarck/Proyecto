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
public class userDAO {
     private Connection connection = null;
    
    public userDAO(){
        connection =Database.getConnection();
    }
    
    public void revUser(User user){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT usuario FROM users WHERE usuario = ?");
            ps.setString(1, user.getUsuario());
            ResultSet rs = ps.executeQuery();
             if (rs.next()) // found
             {
                 //metodos
                 modUSer(user);
             } else {
                //metodo
                 System.out.println("Error de Login");
             }
         } catch (Exception ex) {
              System.out.println("Error in check() -->" + ex.getMessage());
             }
    }
    
     public void modUSer(User user) {
     try {
         PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET nombre=?, contraseña=?, a_paterno=?, a_materno=? WHERE usuario = ?;");
        
        preparedStatement.setString(1, user.getNombre());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getA_paterno());
        preparedStatement.setString(4, user.getA_materno());
        preparedStatement.setString(5, user.getUsuario());
        preparedStatement.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
    }
     
    public User getUser(String userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE usuario = ?;");
            preparedStatement.setString(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setUrl_img(rs.getNString("url_img"));
                user.setUsuario(rs.getString("usuario"));
                user.setPassword(rs.getString("contraseña"));
                user.setNombre(rs.getString("nombre"));
                user.setA_paterno(rs.getString("a_paterno"));
                user.setA_materno(rs.getString("a_materno"));
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
       return user;
     }
    
    public boolean addUimguser(String archivo, String user) 
    {
        try{        
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET url_img=? WHERE usuario =?;");
            preparedStatement.setString(1, archivo);
            preparedStatement.setString(2,user);
            ;
            if(preparedStatement.executeUpdate() ==1)
            {
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
            return false;
    }
    
    public String getImagenId(int urlId) {        
        String urlImagen=null;//variable local temporal
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select url from prueba where id_img = ?;");
            preparedStatement.setInt(1, urlId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
               urlImagen = rs.getString("url");               
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
       return urlImagen;
     }
    
}
