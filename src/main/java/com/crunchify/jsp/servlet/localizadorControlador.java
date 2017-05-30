/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.PersonaDAO;
import edu.co.sergio.mundo.vo.Persona;
import edu.co.sergio.mundo.vo.localizador;
import java.io.IOException;
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

public class localizadorControlador extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
       
        //String ap2= request.getAttribute("");
       
        
        
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        
       
  try { 
       String salon = request.getParameter("salon");
       localizador localizador= new localizador();
      
        localizador.setNombresalon(salon);
        
        request.setAttribute("salon",localizador);
        //request.getAttribute("salon");
            
        } catch (Exception ex) {
            Logger.getLogger(localizadorControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        try {
            dao.create(persona);
        } catch (Exception ex) {
            Logger.getLogger(personaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Listando la informacion  
        List<Persona> personas =  dao.findPersonaEntities();
        request.setAttribute("personas", personas);
       */
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("localizador.jsp");
        redireccion.forward(request, response);
        
        
        }
}


