/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.PersonaDAO;
import edu.co.sergio.mundo.dao.PrestamosalonDAO;
import edu.co.sergio.mundo.vo.Persona;
import edu.co.sergio.mundo.vo.Prestamosalon;
import edu.co.sergio.mundo.vo.Salon;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
 * @author james Alonso
 */

public class prestamoSalonControlador extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        String idprestamo = request.getParameter("idPrestamoSalon");
        String idsalon = request.getParameter("idSalon");
        String idpersona= request.getParameter("idPersona");
        String fechaentrada= request.getParameter("fechaentrada");
        String fechasalida= request.getParameter("fechasalida");

        
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        PrestamosalonDAO dao = new PrestamosalonDAO();
        
        Prestamosalon prestamo = new Prestamosalon();
        Persona  persona= new Persona();
        Salon salon= new Salon();
        
        //--------------
        
        persona.setIdpersona(Integer.parseInt(idpersona));
        salon.setIdsalon(Integer.parseInt(idsalon));
        
        //--------------
        
       
        
               
        try {
            
             Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaentrada);
             Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fechasalida);
             prestamo.setIdpersona(persona);
             prestamo.setIdprestamosalon(Integer.parseInt(idprestamo));
             prestamo.setIdsalon(salon);
             prestamo.setFechaentrada(date1);
             prestamo.setFechasalida(date2);
            
            
            dao.create(prestamo);
        } catch (Exception ex) {
            Logger.getLogger(personaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Listando la informacion  
        List<Prestamosalon> prestamos =  dao.findPrestamosalonEntities();
        request.setAttribute("prestamos", prestamos);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("prestamosSalon.jsp");
        redireccion.forward(request, response);
        
        
        }
}


