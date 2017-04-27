/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.vo;

/**
 *
 * @author james
 */
public class Herramienta {
    
    private int idDispositivo;
    private int idMantenimiento;
    private int idInventario;
    private int noSerial;
    private String nombreHerramienta;
    private String descripcionHerramienta;
    private String statusHerramienta;
    private int cantArreglos;
    
    public Herramienta(int idDispositivo, int idMantenimiento, int idInventario,int noSerial, String nombreHerramienta, String descripcionHerramienta, String statusHerramienta, int cantArreglos){
    this.idDispositivo=idDispositivo;
    this.idMantenimiento=idMantenimiento;
    this.idInventario=idInventario;
    this.noSerial=noSerial;
    this.nombreHerramienta=nombreHerramienta;
    this.descripcionHerramienta=descripcionHerramienta;
    this.statusHerramienta=statusHerramienta;
    this.cantArreglos=cantArreglos;
    
    }
    

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public int getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(int idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getNoSerial() {
        return noSerial;
    }

    public void setNoSerial(int noSerial) {
        this.noSerial = noSerial;
    }
    
    public String getNombreHerramienta() {
        return nombreHerramienta;
    }

    public void setNombreHerramienta(String nombreHerramienta) {
        this.nombreHerramienta = nombreHerramienta;
    }

    public String getDescripcionHerramienta() {
        return descripcionHerramienta;
    }

    public void setDescripcionHerramienta(String descripcionHerramienta) {
        this.descripcionHerramienta = descripcionHerramienta;
    }

    public String getStatusHerramienta() {
        return statusHerramienta;
    }

    public void setStatusHerramienta(String statusHerramienta) {
        this.statusHerramienta = statusHerramienta;
    }

    public int getCantArreglos() {
        return cantArreglos;
    }

    public void setCantArreglos(int cantAegglos) {
        this.cantArreglos = cantAegglos;
    }
    
    
    
}
