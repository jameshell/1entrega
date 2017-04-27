/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.vo;

/**
 *
 * @author james
 */
public class Formulario {
    
   private int idTransaccion;
   private int idFormulario;
   private int cedulaEstudiante;
   private int idDispositivo;
   private String fechaEntrada;
   private String fechaSalida;
   
   public Formulario(int idTransaccion, int idFormulario, int cedulaEstudiante, int idDispositivo, String fechaEntrada, String fechaSalida){
     this.idTransaccion=idTransaccion;
     this.idFormulario=idFormulario;
     this.cedulaEstudiante=cedulaEstudiante;
     this.idDispositivo=idDispositivo;
     this.fechaEntrada=fechaEntrada;
     this.fechaSalida=fechaSalida;
   }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    public int getCedulaEstudiante() {
        return cedulaEstudiante;
    }

    public void setCedulaEstudiante(int cedulaEstudiante) {
        this.cedulaEstudiante = cedulaEstudiante;
    }

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    
   
    
}
