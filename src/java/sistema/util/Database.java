/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;
/**
 *
 * @author Hernan
 */
public class Database {
    
    private static Connection con=null; 
    //metodo getConnection que ya existe dentro de la clase Connection
    public static Connection getConnection() {
       try {
           
           if (con == null)
           {
               Runtime.getRuntime().addShutdownHook(new MiShDwnHook());
               Class.forName("com.mysql.jdbc.Driver");
               con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tiendaonline?serverTimezone=UTC", "root", "");
           }
           return con;
       }catch (Exception ex){
           ex.printStackTrace();
           throw new RuntimeException("Error al ABRIR la conexion", ex);
       }
    }
    
    static class MiShDwnHook extends Thread
    {
        
        @Override
        public void run()
        {
            try {
                Connection con = Database.getConnection();
                con.close();
            }catch (Exception ex){
              ex.printStackTrace(); 
              throw new RuntimeException(ex);
            }
        }
    }

    
    public static void close(Connection con) {
       try {
         con.close();
        }
       catch(Exception ex) {
           ex.printStackTrace();
           throw new RuntimeException("Error al CERRAR la coneñion", ex);
        }
    }
}

