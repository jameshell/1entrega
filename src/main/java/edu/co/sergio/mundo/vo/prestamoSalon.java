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
    private int idPrestamoSalon;
    private Persona persona;
    private Salon salon;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }
    
    
    
    
}
