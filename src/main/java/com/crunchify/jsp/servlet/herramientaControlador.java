/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.PersonaDAO;
import edu.co.sergio.mundo.vo.Persona;
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
 * @author james
 */

public class herramientaControlador extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        String id = request.getParameter("idPersona");
        String nombre = request.getParameter("nombrePersona");
        String apellido= request.getParameter("apellidoPersona");
        String semestre= request.getParameter("semestrePersona");
        String carrera= request.getParameter("carreraPersona");

        
        
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        PersonaDAO dao = new PersonaDAO();
        
        Persona persona = new Persona();
        
        persona.setIdpersona(Integer.parseInt(id));
        persona.setNombrepersona(nombre);
        persona.setApellidopersona(apellido);
        persona.setSemestrepersona(Integer.parseInt(semestre));
        persona.setCarrerapersona(carrera);
        String cargo = null;
        persona.setCargopersona(cargo);
        
        
        try {
            dao.create(persona);
        } catch (Exception ex) {
            Logger.getLogger(personaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Listando la informacion  
        List<Persona> personas =  dao.findPersonaEntities();
        request.setAttribute("personas", personas);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("persona.jsp");
        redireccion.forward(request, response);
        
        
        }
}


