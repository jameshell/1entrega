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
public class Inventario {
    int idInventario;
    String nombreInventario;
    String descripcionInventario;
    int Cant_Disponible;
    int Cant_EnUso;
    int Cant_Danados;

    public Inventario(int idInventario, String nombreInventario, String descripcionInventario, int Cant_Disponible, int Cant_EnUso, int Cant_Danados) {
        this.idInventario = idInventario;
        this.nombreInventario = nombreInventario;
        this.descripcionInventario = descripcionInventario;
        this.Cant_Disponible = Cant_Disponible;
        this.Cant_EnUso = Cant_EnUso;
        this.Cant_Danados = Cant_Danados;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getNombreInventario() {
        return nombreInventario;
    }

    public void setNombreInventario(String nombreInventario) {
        this.nombreInventario = nombreInventario;
    }

    public String getDescripcionInventario() {
        return descripcionInventario;
    }

    public void setDescripcionInventario(String descripcionInventario) {
        this.descripcionInventario = descripcionInventario;
    }

    public int getCant_Disponible() {
        return Cant_Disponible;
    }

    public void setCant_Disponible(int Cant_Disponible) {
        this.Cant_Disponible = Cant_Disponible;
    }

    public int getCant_EnUso() {
        return Cant_EnUso;
    }

    public void setCant_EnUso(int Cant_EnUso) {
        this.Cant_EnUso = Cant_EnUso;
    }

    public int getCant_Danados() {
        return Cant_Danados;
    }

    public void setCant_Danados(int Cant_Danados) {
        this.Cant_Danados = Cant_Danados;
    }

    @Override
    public String toString() {
        return "Inventario{" + "idInventario=" + idInventario + ", nombreInventario=" + String.valueOf(nombreInventario) + ", descripcionInventario=" + String.valueOf(descripcionInventario) + ", Cant_Disponible=" + Cant_Disponible + ", Cant_EnUso=" + Cant_EnUso + ", Cant_Danados=" + Cant_Danados + '}';
    }
    
    
}
