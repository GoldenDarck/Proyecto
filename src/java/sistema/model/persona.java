/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.model;

import java.util.Date;

/**
 *
 * @author Hernan
 */
public class persona {
    int id_persona, id_user,Visitas,Observaciones;
    String usuario, Nombre, Correo, Contraseña, a_paterno, a_materno, Telefono;
    Date  f_nacimiento;
    boolean c_frecuente, lista_negra;
     
    
    public persona(){}
    
    public int getIdPersona(){
        return id_persona;
    }
    public void setIdPersona(int id){
        this.id_persona=id;
    }
    
    public int getIdUsuario(){
        return id_user;
    }
    public void setIdUsuario(int idu){
        this.id_user=idu;
    }
    
    public String getUsuario() {
     return usuario;
   }
   public void setUsuario(String unamex) {
     this.usuario = unamex;
   }
    
   public String getNombre() {
     return Nombre;
   }
   public void setNombre(String name) {
     this.Nombre = name;
   }
   
   public String getAPaterno() {
     return a_paterno;
   }
   public void setAPaterno(String ap) {
     this.a_paterno = ap;
   }
   
    public String getAMaterno() {
     return a_materno;
   }
   public void setAMaterno(String am) {
     this.a_materno = am;
   }
}
