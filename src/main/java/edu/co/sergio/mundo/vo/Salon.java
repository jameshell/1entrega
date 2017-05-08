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
public class Salon {
    
    private int idSalon;
    private String nombreSalon;
    private ArrayList<Prestamo> listaPrestamo;
    private ArrayList<prestamoSalon> listaprestamoSalon;



    public ArrayList<Prestamo> getListaPrestamo() {
        return listaPrestamo;
    }

    public void setListaPrestamo(ArrayList<Prestamo> listaPrestamo) {
        this.listaPrestamo = listaPrestamo;
    }

    public ArrayList<prestamoSalon> getListaprestamoSalon() {
        return listaprestamoSalon;
    }

    public void setListaprestamoSalon(ArrayList<prestamoSalon> listaprestamoSalon) {
        this.listaprestamoSalon = listaprestamoSalon;
    }

    public int getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(int idSalon) {
        this.idSalon = idSalon;
    }

    public String getNombreSalon() {
        return nombreSalon;
    }

    public void setNombreSalon(String nombreSalon) {
        this.nombreSalon = nombreSalon;
    }
    
}
