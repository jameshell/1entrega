/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.vo;

import java.sql.Date;



/**
 *
 * @author Jaime Alonso
 */
public class Mantenimiento {
    private int idHerramienta;

    
    private int idMantenimiento;
    private String refFabricante;
    private String enServicio;
    private int nivelImportancia;
    private String tipoMantenimiento;
    private String entidadCargo;
    private String nombreTecnico;
    private Date fechaInicio;
    private Date fechaFinal;


    public int getIdHerramienta() {
        return idHerramienta;
    }

    public void setIdHerramienta(int idHerramienta) {
        this.idHerramienta = idHerramienta;
    }
    
    public int getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(int idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public String getRefFabricante() {
        return refFabricante;
    }

    public void setRefFabricante(String refFabricante) {
        this.refFabricante = refFabricante;
    }

    public String getEnServicio() {
        return enServicio;
    }

    public void setEnServicio(String enServicio) {
        this.enServicio = enServicio;
    }

    public int getNivelImportancia() {
        return nivelImportancia;
    }

    public void setNivelImportancia(int nivelImportancia) {
        this.nivelImportancia = nivelImportancia;
    }

    public String getTipoMantenimiento() {
        return tipoMantenimiento;
    }

    public void setTipoMantenimiento(String tipoMantenimiento) {
        this.tipoMantenimiento = tipoMantenimiento;
    }

    public String getEntidadCargo() {
        return entidadCargo;
    }

    public void setEntidadCargo(String entidadCargo) {
        this.entidadCargo = entidadCargo;
    }

    public String getNombreTecnico() {
        return nombreTecnico;
    }

    public void setNombreTecnico(String nombreTecnico) {
        this.nombreTecnico = nombreTecnico;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
  
    
}
