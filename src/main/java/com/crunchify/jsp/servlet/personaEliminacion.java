/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.personaDAO;
import edu.co.sergio.mundo.vo.Persona;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.RequestDispatcher;
 
/**
 * @author Crunchify.com
 */
 
public class personaEliminacion extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        String id = request.getParameter("idPersona");
     
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        personaDAO dao = new personaDAO();
        Persona persona = new Persona();
        persona.setIdPersona(Integer.parseInt(id));
      
        dao.delete(persona);
        
      
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("persona.jsp");
        redireccion.forward(request, response);
        
        
        }
}
