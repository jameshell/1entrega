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
 
public class listaControlador extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        personaDAO dao = new personaDAO();

        
        //Listando la informacion  
        List<Persona> personas =  dao.findAll();
        request.setAttribute("personas", personas);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("listaPersona.jsp");
        redireccion.forward(request, response);
        
        
        }
}
