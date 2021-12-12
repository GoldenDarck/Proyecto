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
public class menuDAO {
    private Connection connection;
    
    public menuDAO(){
        
        connection=Database.getConnection();
    }
    
    public List<productos> getAlldesayunos() {
        List<productos> products = new ArrayList<productos>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT nombre, precio, url_img FROM productos where categoria = 1;");
            while (rs.next()) {
            productos prod = new productos();
            prod.setNombre(rs.getString("nombre"));
            prod.setPrecio(rs.getByte("precio"));
            prod.setUrl_img(rs.getString("url_img"));
            products.add(prod);
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return products;
    }
    
    public List<productos> getAllpprincipal() {
        List<productos> products = new ArrayList<productos>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT nombre, precio, url_img FROM productos where categoria = 2;");
            while (rs.next()) {
            productos prod = new productos();
            prod.setNombre(rs.getString("nombre"));
            prod.setPrecio(rs.getByte("precio"));
            prod.setUrl_img(rs.getString("url_img"));
            products.add(prod);
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return products;
      }
    public List<productos> getAllpostres() {
        List<productos> products = new ArrayList<productos>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT nombre, precio, url_img FROM productos where categoria = 3;");
            while (rs.next()) {
            productos prod = new productos();
            prod.setNombre(rs.getString("nombre"));
            prod.setPrecio(rs.getByte("precio"));
            prod.setUrl_img(rs.getString("url_img"));
            products.add(prod);
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return products;
      }
    public List<productos> getAllbebidas() {
        List<productos> products = new ArrayList<productos>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT nombre, precio,url_img FROM productos where categoria = 4;");
            while (rs.next()) {
            productos prod = new productos();
            prod.setNombre(rs.getString("nombre"));
            prod.setPrecio(rs.getByte("precio"));
            prod.setUrl_img(rs.getString("url_img"));
            products.add(prod);
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return products;
      }
}
