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
public class Herramienta {
    
  private int idHerramienta;
  private int noSerial;
  private String nombreHerramienta;
  private String descripcionHerramienta;
  private ArrayList<Mantenimiento> listaMantenimientos;
  private ArrayList<Prestamo> listaPrestamo;


    public int getIdHerramienta() {
        return idHerramienta;
    }

    public void setIdHerramienta(int idHerramienta) {
        this.idHerramienta = idHerramienta;
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

    public ArrayList<Mantenimiento> getListaMantenimientos() {
        return listaMantenimientos;
    }

    public void setListaMantenimientos(ArrayList<Mantenimiento> listaMantenimientos) {
        this.listaMantenimientos = listaMantenimientos;
    }

    public ArrayList<Prestamo> getListaPrestamo() {
        return listaPrestamo;
    }

    public void setListaPrestamo(ArrayList<Prestamo> listaPrestamo) {
        this.listaPrestamo = listaPrestamo;
    }
  
    
}
