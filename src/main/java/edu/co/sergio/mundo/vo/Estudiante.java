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
public class Estudiante {
 private int cedulaEstudiante;
 private String nombreEstudiante;
 private String apellidoEstudiante;
 private int semestreEstudiante;
 private String carreraEstudiante;
 private String encargadoRegistro;
 
    public int getCedulaEstudiante() {
        return cedulaEstudiante;
    }

    public void setCedulaEstudiante(int cedulaEstudiante) {
        this.cedulaEstudiante = cedulaEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getApellidoEstudiante() {
        return apellidoEstudiante;
    }

    public void setApellidoEstudiante(String apellidoEstudiante) {
        this.apellidoEstudiante = apellidoEstudiante;
    }

    public int getSemestreEstudiante() {
        return semestreEstudiante;
    }

    public void setSemestreEstudiante(int semestreEstudiante) {
        this.semestreEstudiante = semestreEstudiante;
    }

    public String getCarreraEstudiante() {
        return carreraEstudiante;
    }

    public void setCarreraEstudiante(String carreraEstudiante) {
        this.carreraEstudiante = carreraEstudiante;
    }

    public String getEncargadoRegistro() {
        return encargadoRegistro;
    }

    public void setEncargadoRegistro(String encargadoRegistro) {
        this.encargadoRegistro = encargadoRegistro;
    }
 
}
