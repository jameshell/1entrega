/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.personaDAO;
import edu.co.sergio.mundo.vo.Persona;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author james
 */

public class personaControlador extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        String id = request.getParameter("idPersona");
        String nombre = request.getParameter("nombrePersona");
        String apellido= request.getParameter("apellidoPersona");
        String semestre= request.getParameter("semestrePersona");
        String carrera= request.getParameter("carreraPersona");
        String cargo= request.getParameter("cargoPersona");
        
        
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        personaDAO dao = new personaDAO();
        
        Persona persona = new Persona();
        persona.setIdPersona(Integer.parseInt(id));
        persona.setNombrePersona(nombre);
        persona.setApellidoPersona(apellido);
        persona.setSemestrePersona(Integer.parseInt(semestre));
        persona.setCarreraPersona(carrera);
        persona.setCargoPersona(cargo);
        
        
        dao.insert(persona);
        
        //Listando la informacion  
        List<Persona> personas =  dao.findAll();
        request.setAttribute("personas", personas);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("personaRegistro.jsp");
        redireccion.forward(request, response);
        
        
        }
}


