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
public class chefDAO {
     private Connection connection;
     
     public chefDAO(){
         connection=Database.getConnection();
     }
     
     public List<comanda> getAllActivos() {
        List<comanda> comand = new ArrayList<comanda>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM comandas WHERE abierta = 1;");
            while (rs.next()) {
            comanda com = new comanda();
            com.setId_comanda(rs.getInt("id_comanda"));
            com.setId_producto(rs.getInt("id_producto"));
            com.setMesa(rs.getString("mesa"));
            com.setCantidad(rs.getInt("cantidad"));
            com.setTotal(rs.getInt("total"));
            
            comand.add(com);
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return comand;
      }
     
     public List<comanda> getAllFinalizados() {
        List<comanda> comand = new ArrayList<comanda>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM comandas WHERE abierta = 0;");
            while (rs.next()) {
            comanda com = new comanda();
            com.setId_comanda(rs.getInt("id_comanda"));
            com.setId_producto(rs.getInt("id_producto"));
            com.setMesa(rs.getString("mesa"));
            com.setCantidad(rs.getInt("cantidad"));
            com.setTotal(rs.getInt("total"));
            
            comand.add(com);
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return comand;
      }
}
