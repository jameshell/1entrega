/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.MantenimientoDAO;

import edu.co.sergio.mundo.dao.exceptions.NonexistentEntityException;
import edu.co.sergio.mundo.vo.Mantenimiento;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
 
/**
 * @author Crunchify.com
 */
 
public class mantenimientoEliminacion extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        String id = request.getParameter("idMantenimiento");
     
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        MantenimientoDAO dao = new MantenimientoDAO();
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setIdmantenimiento(Integer.parseInt(id));
     
      
        try {
            dao.destroy(Integer.parseInt(id));
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(salonEliminacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("mantenimiento.jsp");
        redireccion.forward(request, response);
        
        
        }
}

