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
    
    public int addCarrito(comanda prod) {
     int result=0;
        try {
            PreparedStatement pstm =null;
            ResultSet rs =null;
            String query = ("INSERT INTO comandas (id_producto, cantidad, mesa, subtotal,descuentp,total,cancelada,abierta,dom_service) VALUES (?, ?, ?, ?,?,?,?,?,?)");
            pstm =connection.prepareStatement(query);
            
            pstm.setInt(1, prod.getId_producto());
            pstm.setInt(2, prod.getCantidad());
            pstm.setString(3, prod.getMesa());
            pstm.setInt(4, prod.getSubtotal());
            pstm.setInt(5, prod.getDescuentp());
            pstm.setInt(6, prod.getTotal());
            pstm.setBoolean(7, prod.getCancelada());
            pstm.setBoolean(8, prod.getAbierta());
            pstm.setBoolean(9, prod.getDom_service());
            
            System.out.println(pstm);
            result=pstm.executeUpdate();
    
        }catch(SQLException ex)
        {
           ex.printStackTrace();
        }
        return result;  
   }
    
    
    public void getVenta (int tot, String user){
         try{
             PreparedStatement preparedStatement = connection.prepareStatement("insert into ventas (id_venta, id_cliente, total, usuario_registra, f_creacion, f_cierre, status_abierta, cancelada	) ");
             preparedStatement.setString(1, user);
             ResultSet rs =preparedStatement.executeQuery();
             if(rs.next()){
                 
             }    
         }catch (Exception ex)
         {
             ex.printStackTrace();
         }
    }

    
}
