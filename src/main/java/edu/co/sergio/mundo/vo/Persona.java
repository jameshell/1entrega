/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.vo;

import java.util.ArrayList;

/**
 *
 * @author Jaime Alonso
 */
public class Persona {
    
    private int idPersona;
    private String nombrePersona;
    private String apellidoPersona;
    private int semestrePersona;
    private String carreraPersona;
    private String cargoPersona;
    private ArrayList<Prestamo> listaPrestamo;
    private ArrayList<prestamoSalon> prestamoSalon;

 

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    public int getSemestrePersona() {
        return semestrePersona;
    }

    public void setSemestrePersona(int semestrePersona) {
        this.semestrePersona = semestrePersona;
    }

    public String getCarreraPersona() {
        return carreraPersona;
    }

    public void setCarreraPersona(String carreraPersona) {
        this.carreraPersona = carreraPersona;
    }

    public String getCargoPersona() {
        return cargoPersona;
    }

    public void setCargoPersona(String cargoPersona) {
        this.cargoPersona = cargoPersona;
    }

    
}
