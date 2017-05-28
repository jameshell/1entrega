/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;


import edu.co.sergio.mundo.dao.SalonDAO;
import edu.co.sergio.mundo.vo.Salon;
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

public class salonControlador extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
         // reading the user input
        int id = Integer.parseInt(request.getParameter("idSalon"));
        String nombre = request.getParameter("nombreSalon");
 
        
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        SalonDAO dao = new SalonDAO();
        Salon salon = new Salon();
        
        salon.setIdsalon(id);
        salon.setNombresalon(nombre);

        
        try {
            dao.create(salon);
        } catch (Exception ex) {
            Logger.getLogger(personaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Listando la informacion  
        List<Salon> salones =  dao.findSalonEntities();
        request.setAttribute("salones", salones);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("salon.jsp");
        redireccion.forward(request, response);
        
}

}
