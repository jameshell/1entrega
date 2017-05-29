/*
 * To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.co.sergio.mundo.dao.HerramientaDAO;
import edu.co.sergio.mundo.vo.Administrativo;
import edu.co.sergio.mundo.vo.Herramienta;
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
        int idadministrativo = Integer.parseInt(request.getParameter("idAdministrativo"));
        int idherramienta = Integer.parseInt(request.getParameter("idHerramienta"));
        String nombreherramienta = request.getParameter("nombreHerramienta");
        String noserial= request.getParameter("noserialHerramienta");
        String descripcionherramienta= request.getParameter("descripcionHerramienta");
    
        
        
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        
        HerramientaDAO dao = new HerramientaDAO();
        Herramienta herramienta = new Herramienta();
        Administrativo admin = new Administrativo();
        
        admin.setIdadministrativo(idadministrativo);
        
        herramienta.setIdadministrativo(admin);
        herramienta.setNombreherramienta(nombreherramienta);
        herramienta.setIdherramienta(idadministrativo);
        herramienta.setNoserial(noserial);
        herramienta.setDescripcionherramienta(descripcionherramienta);
        
        try {
            dao.create(herramienta);
            //Existe un problema con el DAO, probablemente es porque no se hace mencion de administrativo.
        } catch (Exception ex) {
            Logger.getLogger(herramientaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Listando la informacion  
        List<Herramienta> herramientas =  dao.findHerramientaEntities();
        request.setAttribute("herramientas", herramientas);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("herramienta.jsp");
        redireccion.forward(request, response);
        
        
        }
}


