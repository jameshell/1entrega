/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.vo;

/**
 *
 * @author Jaime Alonso
 */
public class Mantenimiento {
    
    int IdMantenimiento;
    int IdDispositivo;
    String tituloMantenimiento;
    String descripcionMantenimiento;

    public Mantenimiento(int IdMantenimiento, int IdDispositivo, String tituloMantenimiento, String descripcionMantenimiento) {
        this.IdMantenimiento = IdMantenimiento;
        this.IdDispositivo = IdDispositivo;
        this.tituloMantenimiento = tituloMantenimiento;
        this.descripcionMantenimiento = descripcionMantenimiento;
    }

    public int getIdMantenimiento() {
        return IdMantenimiento;
    }

    public void setIdMantenimiento(int IdMantenimiento) {
        this.IdMantenimiento = IdMantenimiento;
    }

    public int getIdDispositivo() {
        return IdDispositivo;
    }

    public void setIdDispositivo(int IdDispositivo) {
        this.IdDispositivo = IdDispositivo;
    }

    public String getTituloMantenimiento() {
        return tituloMantenimiento;
    }

    public void setTituloMantenimiento(String tituloMantenimiento) {
        this.tituloMantenimiento = tituloMantenimiento;
    }

    public String getDescripcionMantenimiento() {
        return descripcionMantenimiento;
    }

    public void setDescripcionMantenimiento(String descripcionMantenimiento) {
        this.descripcionMantenimiento = descripcionMantenimiento;
    }

    @Override
    public String toString() {
        return "Mantenimientos{" + "IdMantenimiento=" + IdMantenimiento + ", IdDispositivo=" + IdDispositivo + ", tituloMantenimiento=" + String.valueOf(tituloMantenimiento) + ", descripcionMantenimiento=" + String.valueOf(descripcionMantenimiento) + '}';
    }
    
    
}
