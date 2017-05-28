/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;
 

import edu.co.sergio.mundo.dao.HerramientaDAO;
import edu.co.sergio.mundo.dao.PersonaDAO;
import edu.co.sergio.mundo.vo.Herramienta;
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
 
public class listaHerramienta extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        HerramientaDAO dao = new HerramientaDAO();

        
        //Listando la informacion  
        List<Herramienta> herramientas =  dao.findHerramientaEntities();
        request.setAttribute("herramientas", herramientas);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("listaHerramienta.jsp");
        redireccion.forward(request, response);
        
        
        }
}
