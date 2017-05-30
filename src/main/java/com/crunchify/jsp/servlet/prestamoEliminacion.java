/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.PersonaDAO;
import edu.co.sergio.mundo.dao.exceptions.IllegalOrphanException;
import edu.co.sergio.mundo.dao.exceptions.NonexistentEntityException;
import edu.co.sergio.mundo.vo.Persona;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
 
/**
 * @author Crunchify.com
 */
 
public class prestamoEliminacion extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        String id = request.getParameter("idPersona");
     
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        PersonaDAO dao = new PersonaDAO();
        Persona persona = new Persona();
        persona.setIdpersona(Integer.parseInt(id));
      
        try {
            dao.destroy(Integer.parseInt(id));
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(salonEliminacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(salonEliminacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("persona.jsp");
        redireccion.forward(request, response);
        
        
        }
}
