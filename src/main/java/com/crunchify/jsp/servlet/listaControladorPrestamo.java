/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.PersonaDAO;
import edu.co.sergio.mundo.dao.PrestamoDAO;
import edu.co.sergio.mundo.vo.Persona;
import edu.co.sergio.mundo.vo.Prestamo;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author james
 */
public class listaControladorPrestamo {
    //oh gof why
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        PrestamoDAO dao = new PrestamoDAO();

        
        //Listando la informacion  
        List<Prestamo> prestamos =  dao.findPrestamoEntities();
        request.setAttribute("prestamos", prestamos);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("listaPrestamo.jsp");
        redireccion.forward(request, response);
        
        
        }
    
    
    
    
}
