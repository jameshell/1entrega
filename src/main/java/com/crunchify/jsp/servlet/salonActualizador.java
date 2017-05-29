/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;
import edu.co.sergio.mundo.dao.SalonDAO;
import edu.co.sergio.mundo.dao.exceptions.NonexistentEntityException;
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

public class salonActualizador extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        
        String idsalon = request.getParameter("idSalon");
        String nombresalon = request.getParameter("nombreSalon");

        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        SalonDAO dao1 = new SalonDAO();
        Salon salon = new Salon();  
        
        salon.setIdsalon(Integer.parseInt(idsalon));
        salon.setNombresalon(nombresalon);

        try {
            dao1.edit(salon);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(salonActualizador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(salonActualizador.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        //Redireccionando la informacion
        
        RequestDispatcher redireccion = request.getRequestDispatcher("salon.jsp");
        redireccion.forward(request, response);
        
        
        }
}
