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
public class carritoDAO {
    private Connection connection;
    
    public carritoDAO(){
        
        connection=Database.getConnection();
    }
    
    public boolean checkVenta(User us){
        try{
            PreparedStatement pstm =null;
            ResultSet rs =null;
            String query ="SELECT * FROM ventas WHERE id_cliente = ? AND status_abierta = 1";
            pstm =connection.prepareStatement(query);
            
            pstm.setInt(1, us.getId_user());
            rs = pstm.executeQuery();
             if (rs.next()) // found
             {
                 return true;
                 //metodos
                //getVentaById(us); 
                //System.out.println("Error in A");
            } 
         } catch (Exception ex) {
              System.out.println("Error in check() -->" + ex.getMessage());
            return false;
         }
        return false;
    }
    
    
    public User getUserById(String userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE usuario = ?;");
            preparedStatement.setString(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setId_user(rs.getInt("id_user"));
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
    
    public comanda getComandaById(int idcom) {
        comanda com= new comanda();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM comandas WHERE id_comanda = ?;");
            preparedStatement.setInt(1, idcom);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                com.setId_comanda(rs.getInt("id_comanda"));
                com.setId_venta(rs.getInt("id_venta"));
                com.setId_producto(rs.getInt("id_producto"));
                com.setFecha_reg(rs.getDate("fecha_reg"));
                com.setCantidad(rs.getInt("cantidad"));
                com.setMesa(rs.getString("mesa"));
                com.setTotal(rs.getInt("total"));
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
       return com;
     }
    
    public int getVentaById(User userId) {
        int venta = 0;
        
        try {
            PreparedStatement pstm =null;
            ResultSet rs = null;
            String query = "SELECT id_venta FROM ventas WHERE id_cliente = ? AND status_abierta =1";
            pstm = connection.prepareStatement(query);
            pstm.setInt(1,  userId.getId_user());
            rs = pstm.executeQuery();
            if (rs.next())
            {
                venta =rs.getInt("id_venta");
            }
            
        } catch (SQLException e) {
           e.printStackTrace();
        }
       return venta;
     }
    
    public List<comanda> getAllcomandas(User us) {
        List<comanda> comands = new ArrayList<comanda>();
        try {
            PreparedStatement pstm =null;
            ResultSet rs =null;
            String query ="SELECT url_img,nombre,precio,mesa,cantidad,total,id_comanda FROM comandas as c INNER JOIN productos as p ON p.id_producto = c.id_producto INNER JOIN ventas as v ON v.id_venta = c.id_venta WHERE c.abierta = 1 AND v.id_cliente = ? AND v.status_abierta =1";
            pstm =connection.prepareStatement(query);
            pstm.setInt(1, us.getId_user());
            rs = pstm.executeQuery();
            while (rs.next()) {
            
            comanda com = new comanda();
            
            com.setUrl_img(rs.getString("url_img"));
            com.setNombre(rs.getString("nombre"));
            com.setPrecio(rs.getInt("precio"));
            com.setMesa(rs.getString("mesa"));
            com.setCantidad(rs.getInt("cantidad"));
            com.setTotal(rs.getInt("total"));
            com.setId_comanda(rs.getInt("id_comanda"));
            
            comands.add(com);
            
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return comands;
    }
    
    public productos getProductoById(String proId) {
        productos prod = new productos();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM productos WHERE nombre = ?;");
            preparedStatement.setString(1, proId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                prod.setId_producto(rs.getInt("id_producto"));
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
        
    public int addCarrito(int venta, comanda prodId) {
     int addcar=0;
     
        try {
            PreparedStatement pstm =null;
            String query = ("INSERT INTO comandas (id_venta,id_producto, fecha_reg, cantidad, mesa, total, cancelada,abierta,dom_service) VALUES (?,?, now(), ?, ?,?,?,?,?)");
            pstm =connection.prepareStatement(query);
            pstm.setInt(1, venta);
            pstm.setInt(2, prodId.getId_producto());
            pstm.setInt(3, prodId.getCantidad());
            pstm.setString(4, prodId.getMesa());
            pstm.setInt(5, prodId.getTotal());
            pstm.setInt(6, 0);
            pstm.setInt(7, 1);
            pstm.setInt(8, 0);
            
            System.out.println(pstm);
            addcar=pstm.executeUpdate();
            
    
        }catch(SQLException ex)
        {
           ex.printStackTrace();
        }
        return addcar;  
   }
    
    
    public int addVenta (User us){
        int result=0;
        try {
            PreparedStatement pstm =null;
            String query = ("INSERT INTO ventas (id_cliente, f_creacion, status_abierta, cancelada) VALUES (?,now(),1,0)");
            pstm =connection.prepareStatement(query);
            pstm.setInt(1, us.getId_user());
            
            if(pstm.executeUpdate() ==1)
            {
                result=1;
            }
            
         }catch(SQLException ex)
        {
           ex.printStackTrace();
           result = 0;
        }
        return result; 
    }
    
    public int totalventa(User us) {        
        int total=0;//variable local temporal
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT total_v From ventas WHERE id_cliente=? ");
            preparedStatement.setInt(1, us.getId_user());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
               total = rs.getInt("total_v");               
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
       return total;
     }
     public int totalcomanda(comanda com) {        
        int totalc=0;//variable local temporal
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT total From comandas WHERE id_comanda=? ");
            preparedStatement.setInt(1, com.getId_comanda());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
               totalc = rs.getInt("total");               
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
       return totalc;
     }
        
    public void updatetotal(venta total, User us) {
     try {
         PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ventas SET total_v=? WHERE id_cliente = ?;");
         
         preparedStatement.setInt(1, total.getTotal_v());
         preparedStatement.setInt(2, us.getId_user());
         
         preparedStatement.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
    }
    
    public void terminarv(User us) {
     try {
         PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ventas SET status_abierta=0 WHERE id_cliente = ?;");
         preparedStatement.setInt(1, us.getId_user());
         preparedStatement.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
    }
    
    public int cancelcomanda (comanda com){
        int rescancel=0;
        try {
            PreparedStatement pstm =null;
            String query = ("UPDATE comandas SET abierta = 0, cancelada=1 WHERE id_comanda = ?;");
            pstm =connection.prepareStatement(query);
            pstm.setInt(1, com.getId_comanda());
            
            if(pstm.executeUpdate() ==1)
            {
                rescancel=1;
            }
            
         }catch(SQLException ex)
        {
           ex.printStackTrace();
           rescancel = 0;
        }
        return rescancel; 
    }
}
