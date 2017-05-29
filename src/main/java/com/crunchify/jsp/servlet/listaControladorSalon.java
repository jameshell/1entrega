package com.crunchify.jsp.servlet;
 

import edu.co.sergio.mundo.dao.PersonaDAO;
import edu.co.sergio.mundo.dao.SalonDAO;
import edu.co.sergio.mundo.vo.Persona;
import edu.co.sergio.mundo.vo.Salon;

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
 
public class listaControladorSalon extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        SalonDAO dao = new SalonDAO();

        
        //Listando la informacion  
        List<Salon> salones =  dao.findSalonEntities();
        request.setAttribute("salones", salones);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("listaSalon.jsp");
        redireccion.forward(request, response);
        
        
        }
}
