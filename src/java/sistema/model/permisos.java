/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.model;

/**
 *
 * @author Hernan
 */
public class permisos {
    
    private boolean ver, modificar, eliminar,escribir;
    private boolean admin;
    
    public permisos(){}    
    
     public boolean getVer() {
        return ver;
     }
   
     public void setVer(boolean select) {
        this.ver = select;
     }
     
     public boolean getModificar() {
        return modificar;
     }
   
     public void setModificar(boolean update) {
        this.modificar = update;
     }
     
     public boolean getEliminar (){
        return eliminar;
     }
     
     public void setEliminar (boolean delete){
         this.eliminar = delete;
     }
   
     public boolean getEscribir (){
         return escribir;
     }
   
     public void setEscribir (boolean insert){
         this.escribir = insert;
         
     }
     
     public boolean getAdmin (){
         return admin;
     }
     
     public void setAdmin (boolean isadmin){
         this.admin = isadmin;
     }
}
