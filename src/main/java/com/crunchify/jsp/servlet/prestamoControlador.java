/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.HerramientaDAO;
import edu.co.sergio.mundo.dao.PrestamoDAO;
import edu.co.sergio.mundo.vo.Administrativo;
import edu.co.sergio.mundo.vo.Persona;
import edu.co.sergio.mundo.vo.Prestamo;
import edu.co.sergio.mundo.vo.Salon;
import edu.co.sergio.mundo.vo.Transaccion;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author james
 */

public class prestamoControlador extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        String idadmin = request.getParameter("idAdmin");//
        String idpersona = request.getParameter("idpersonaPrestamo");//
        String idsalon= request.getParameter("idsalonPrestamo");//
        String cod = request.getParameter("codPrestamo");//
        String tipopractica = request.getParameter("tipopracticaPrestamo");//
        String fechasalida= request.getParameter("fechasalidaPrestamo");//
        String fechaentrada= request.getParameter("fechaentradaPrestamo");//
        String estado= request.getParameter("estadoPrestamo");//
        String justificacion= request.getParameter("justificacionPrestamo");//
        String observaciones= request.getParameter("observaciones");//
        String tipo= request.getParameter("tipoPrestamo");//
        
        
        
 
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        PrestamoDAO dao = new PrestamoDAO();
        Prestamo prestamo = new Prestamo();
        Persona persona = new Persona();
        Salon salon= new Salon();
        Administrativo admin= new Administrativo();
        
        try{     
            Date date1 ,date2;
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechasalida);
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaentrada);
            admin.setIdadministrativo(Integer.parseInt(idadmin));
            persona.setIdpersona(Integer.parseInt(idpersona));
            salon.setIdsalon(Integer.parseInt(idsalon));  
            
        prestamo.setCodprestamo(cod);
        prestamo.setIdpersona(persona);
        prestamo.setTipopractica(tipopractica);
        prestamo.setIdadministrativo(admin);
        prestamo.setFechasalida(date1);
        prestamo.setEstado(estado);
        prestamo.setJustificacion(justificacion);
        prestamo.setObservaciones(observaciones);
        prestamo.setTipoprestamo(tipo);
        prestamo.setFechaentrada(date2);
        prestamo.setIdsalon(salon); 
        dao.create(prestamo);
        } catch (Exception ex) {
            Logger.getLogger(prestamoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Listando la informacion  
        List<Prestamo> prestamos =  dao.findPrestamoEntities();
        request.setAttribute("prestamos", prestamos);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("prestamo.jsp");
        redireccion.forward(request, response);
        
        
        }
}


