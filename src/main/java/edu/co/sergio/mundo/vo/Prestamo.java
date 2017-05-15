/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.vo;

import java.sql.Date;
import java.util.ArrayList;



/**
 *
 * @author Jaime Alonso
 */
public class Prestamo {
    private Persona Persona;
    private Herramienta Herramienta;
    private Salon Salon;
    private Administrativo Administrativo;
    private String codPrestamo;
    private String tipoPractica;
    private Date fechaEntrada;
    private Date fechaSalida;
    private String estado;
    private String justificacion;
    private String observaciones;
    private String tipoPrestamo;
    private ArrayList<Transaccion> transacciones; 
    
    public String getCodPrestamo() {
        return codPrestamo;
    }

    public void setCodPrestamo(String codPrestamo) {
        this.codPrestamo = codPrestamo;
    }
    public String getTipoPractica() {
        return tipoPractica;
    }

    public void setTipoPractica(String tipoPractica) {
        this.tipoPractica = tipoPractica;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTipoPrestamo() {
        return tipoPrestamo;
    }

    public void setTipoPrestamo(String tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    public Persona getPersona() {
        return Persona;
    }

    public void setPersona(Persona Persona) {
        this.Persona = Persona;
    }

    public Herramienta getHerramienta() {
        return Herramienta;
    }

    public void setHerramienta(Herramienta Herramienta) {
        this.Herramienta = Herramienta;
    }

    public Salon getSalon() {
        return Salon;
    }

    public void setSalon(Salon Salon) {
        this.Salon = Salon;
    }

    public Administrativo getAdministrativo() {
        return Administrativo;
    }

    public void setAdministrativo(Administrativo Administrativo) {
        this.Administrativo = Administrativo;
    }

    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(ArrayList<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

}
