/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.model;
import java.util.Date;
import java.text.DateFormat;
/**
 *
 * @author Hernan
 */
public class venta {
    int id_venta, id_cliente, total, usuario_registra;
    Date f_creacion,f_cierre;
    Boolean status_abierta,cancelada;	
    
    public venta(){}

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUsuario_registra() {
        return usuario_registra;
    }

    public void setUsuario_registra(int usuario_registra) {
        this.usuario_registra = usuario_registra;
    }

    public Date getF_creacion() {
        return f_creacion;
    }

    public void setF_creacion(Date f_creacion) {
        this.f_creacion = f_creacion;
    }

    public Date getF_cierre() {
        return f_cierre;
    }

    public void setF_cierre(Date f_cierre) {
        this.f_cierre = f_cierre;
    }

    public Boolean getStatus_abierta() {
        return status_abierta;
    }

    public void setStatus_abierta(Boolean status_abierta) {
        this.status_abierta = status_abierta;
    }

    public Boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }
    
    
}
