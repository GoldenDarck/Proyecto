/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.dao;

import java.sql.*;
import java.util.*;
import sistema.model.*;
import sistema.util.*;
/**
 *
 * @author Hernan
 */
public class VAdminDAO {
    private Connection connection;
    
    public VAdminDAO(){
    connection=Database.getConnection();
    }
    
    public void checkUser(User user){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT usuario FROM users WHERE usuario = ?");
            ps.setString(1, user.getUsuario());
            ResultSet rs = ps.executeQuery();
             if (rs.next()) // found
             {
                 //metodos
                 updateUser(user);
             } else {
                //metodo
                 addUser(user);
             }
         } catch (Exception ex) {
              System.out.println("Error in check() -->" + ex.getMessage());
             }
    } 
    
    public void checkProduct(productos pro){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT nombre FROM productos WHERE nombre = ?");
            ps.setString(1, pro.getNombre());
            ResultSet rs = ps.executeQuery();
             if (rs.next()) // found
             {
                 //metodos
                 updateProducto(pro);
             } else {
                //metodo
                 addProducto(pro);
             }
         } catch (Exception ex) {
              System.out.println("Error in check() -->" + ex.getMessage());
             }
    }
    
   public int addUser(User user) {
     int result=0;
        try {
            PreparedStatement pstm =null;
            ResultSet rs =null;
            String query = ("INSERT INTO users (usuario, contraseña, nombre, a_paterno, a_materno,rol,staatus) VALUES (?, ?, ?, ?,?,?,?)");
            pstm =connection.prepareStatement(query);
            //pstm.setInt(1, 1);
            pstm.setString(1, user.getUsuario());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getNombre());
            pstm.setString(4, user.getA_paterno());
            pstm.setString(5, user.getA_materno());
            pstm.setInt(6, user.getRol());
            pstm.setInt(7, 1);
            
            System.out.println(pstm);
            result=pstm.executeUpdate();
    
        }catch(SQLException ex)
        {
           ex.printStackTrace();
        }
        return result;  
   }
   public int addProducto(productos pro) {
     int result=0;
        try {
            PreparedStatement pstm =null;
            ResultSet rs =null;
            String query = ("INSERT INTO productos (nombre, precio, categoria, url_img, cocina, staatus) VALUES (?, ?, ?, ?,?,?)");
            pstm =connection.prepareStatement(query);
            //pstm.setInt(1, 1);
            pstm.setString(1, pro.getNombre());
            pstm.setInt(2, pro.getPrecio());
            pstm.setInt(3, pro.getCategoria());
            pstm.setString(4, pro.getUrl_img());
            pstm.setBoolean(5, pro.isCocina());
            pstm.setInt(6, 1);
            
            System.out.println(pstm);
            result=pstm.executeUpdate();
    
        }catch(SQLException ex)
        {
           ex.printStackTrace();
        }
        return result;  
   }
   
   public void deleteUser(String userId) {
     try {
       PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE usuario=?;");
       preparedStatement.setString(1, userId);
       preparedStatement.executeUpdate();
     }
     catch (SQLException e) {
        e.printStackTrace();
     }
    }
   
   public void deleteProducto(String proId) {
     try {
       PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM productos WHERE nombre=?;");
       preparedStatement.setString(1, proId);
       preparedStatement.executeUpdate();
     }
     catch (SQLException e) {
        e.printStackTrace();
     }
    }
   
   public void updateUser(User user) {
     try {
         PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET contraseña=?, nombre=?, a_paterno=?, a_materno=?, rol=?, staatus=? WHERE usuario = ?;");
         
         preparedStatement.setString(1, user.getPassword());
         preparedStatement.setString(2, user.getNombre());
         preparedStatement.setString(3, user.getA_paterno());
         preparedStatement.setString(4, user.getA_materno());
         preparedStatement.setInt(5, user.getRol());
         preparedStatement.setBoolean(6, user.isStaatus());
         preparedStatement.setString(7, user.getUsuario());
         preparedStatement.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
    }
   
   public void updateProducto(productos pro) {
     try {
         PreparedStatement preparedStatement = connection.prepareStatement("UPDATE productos SET precio=?, categoria=?, url_img=?, cocina=?, staatus=? WHERE nombre = ?;");
         
         preparedStatement.setInt(1, pro.getPrecio());
         preparedStatement.setInt(2, pro.getCategoria());
         preparedStatement.setString(3, pro.getUrl_img());
         preparedStatement.setBoolean(4, pro.isCocina());
         preparedStatement.setBoolean(5, pro.isStaatus());
         preparedStatement.setString(6, pro.getNombre());
         preparedStatement.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
    }
    
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE rol != 4;");
            while (rs.next()) {
            User user = new User();
            user.setId_user(rs.getInt("id_user"));
                user.setUsuario(rs.getString("usuario"));
                user.setPassword(rs.getString("contraseña"));
                user.setNombre(rs.getString("nombre"));
                user.setA_paterno(rs.getString("a_paterno"));
                user.setA_materno(rs.getString("a_materno"));
                user.setRol(rs.getInt("rol"));
                user.setStaatus(rs.getBoolean("staatus"));
            users.add(user);
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return users;
      }
    
    public List<User> getAllClientes() {
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE rol = 4 ;");
            while (rs.next()) {
            User user = new User();
            user.setId_user(rs.getInt("id_user"));
                user.setUsuario(rs.getString("usuario"));
                user.setPassword(rs.getString("contraseña"));
                user.setNombre(rs.getString("nombre"));
                user.setA_paterno(rs.getString("a_paterno"));
                user.setA_materno(rs.getString("a_materno"));
                user.setRol(rs.getInt("rol"));
            users.add(user);
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return users;
      }
    
    public List<productos> getAllproductos() {
        List<productos> products = new ArrayList<productos>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM productos ");
            while (rs.next()) {
            productos prod = new productos();
            prod.setId_producto(rs.getInt("id_producto"));
            prod.setNombre(rs.getString("nombre"));
            prod.setPrecio(rs.getByte("precio"));
            prod.setCategoria(rs.getInt("categoria"));
            prod.setUrl_img(rs.getString("url_img"));
            prod.setCocina(rs.getBoolean("cocina"));
            prod.setStaatus(rs.getBoolean("staatus"));
            products.add(prod);
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return products;
    }
    
    public User getUserById(String userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE usuario = ?;");
            preparedStatement.setString(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setUsuario(rs.getString("usuario"));
                user.setPassword(rs.getString("contraseña"));
                user.setNombre(rs.getString("nombre"));
                user.setA_paterno(rs.getString("a_paterno"));
                user.setA_materno(rs.getString("a_materno"));
                user.setRol(rs.getInt("rol"));
                user.setStaatus(rs.getBoolean("staatus"));
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
       return user;
     }
     
    
    public productos getProductoById(String proId) {
        productos prod = new productos();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM productos WHERE nombre = ?;");
            preparedStatement.setString(1, proId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                prod.setNombre(rs.getString("nombre"));
                prod.setPrecio(rs.getInt("precio"));
                prod.setCategoria(rs.getInt("categoria"));
                prod.setUrl_img(rs.getString("url_img"));
                prod.setCocina(rs.getBoolean("cocina"));
                prod.setStaatus(rs.getBoolean("staatus"));
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
       return prod;
     }
    
    public void addURLfromImageName(String archivo, String product) 
    {
     try{        
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE productos SET url_img=? WHERE nombre =?;");
        preparedStatement.setString(1, archivo);
        preparedStatement.setString(2,product);
        preparedStatement.executeUpdate();
     }catch(SQLException e){
        e.printStackTrace();
     }  
    }
    
    public String getImageById(int urlId) {        
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
