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
public class comanda {
    int id_comanda,id_venta, id_producto,usuario_reg, cantidad, subtotal, descuentp,total;
    Date fecha_reg;	
    String mesa;							
    Boolean cancelada,abierta,dom_service;
    
    public comanda(){}

    public int getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(int id_comanda) {
        this.id_comanda = id_comanda;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getUsuario_reg() {
        return usuario_reg;
    }

    public void setUsuario_reg(int usuario_reg) {
        this.usuario_reg = usuario_reg;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getDescuentp() {
        return descuentp;
    }

    public void setDescuentp(int descuentp) {
        this.descuentp = descuentp;
    }

   

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(Date fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public Boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }

    public Boolean getAbierta() {
        return abierta;
    }

    public void setAbierta(Boolean abierta) {
        this.abierta = abierta;
    }

    public Boolean getDom_service() {
        return dom_service;
    }

    public void setDom_service(Boolean dom_service) {
        this.dom_service = dom_service;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
