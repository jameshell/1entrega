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
public class prestamoSalon {
    private int idSalon;
    private int idPersona;

    public int getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(int idSalon) {
        this.idSalon = idSalon;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    private int idPrestamoSalon;
    private Date fechaEntrada;
    private Date fechaSalida;

    public int getIdPrestamoSalon() {
        return idPrestamoSalon;
    }

    public void setIdPrestamoSalon(int idPrestamoSalon) {
        this.idPrestamoSalon = idPrestamoSalon;
    }
    
    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    
    
    
    
}
