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
public class Administrativo {
    
    private int idAdministrativo;
    private String Contrasena;
    private String nombreAdmin;
    private int tipoPermiso;
    private ArrayList<Herramienta> listaHerramientas;
    private ArrayList<Prestamo> listaPrestamo;



    public int getIdAdministrativo() {
        return idAdministrativo;
    }

    public void setIdAdministrativo(int idAdministrativo) {
        this.idAdministrativo = idAdministrativo;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    public int getTipoPermiso() {
        return tipoPermiso;
    }

    public void setTipoPermiso(int tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }

    public ArrayList<Herramienta> getListaHerramientas() {
        return listaHerramientas;
    }

    public void setListaHerramientas(ArrayList<Herramienta> listaHerramientas) {
        this.listaHerramientas = listaHerramientas;
    }

    public ArrayList<Prestamo> getListaPrestamo() {
        return listaPrestamo;
    }

    public void setListaPrestamo(ArrayList<Prestamo> listaPrestamo) {
        this.listaPrestamo = listaPrestamo;
    }
    
}
