/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.MantenimientoDAO;
import edu.co.sergio.mundo.dao.PersonaDAO;
import edu.co.sergio.mundo.vo.Herramienta;
import edu.co.sergio.mundo.vo.Mantenimiento;
import edu.co.sergio.mundo.vo.Persona;
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

public class mantenimientoControlador extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        
        
        String id = request.getParameter("idMantenimiento");
        String fabricante = request.getParameter("fabricanteMantenimiento");
        String enservicio= request.getParameter("enservicioMantenimiento");
        String importancia= request.getParameter("nivelImportanciaMantenimiento");
        String tipo= request.getParameter("tipoMantenimiento");
        String entidad= request.getParameter("entidadMantenimiento");
        String tecnico= request.getParameter("tecnicoMantenimiento");
        String idherramienta= request.getParameter("idHerramienta");
        String inicio1=request.getParameter("fechaInicio");
        String final1=request.getParameter("fechaFinal");
        
        
        
        
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        MantenimientoDAO dao = new MantenimientoDAO();
        Mantenimiento mant = new Mantenimiento();
        Herramienta herr= new Herramienta();
        herr.setIdherramienta(Integer.parseInt(idherramienta));
        
        
        mant.setIdmantenimiento(Integer.parseInt(id));
        mant.setReffabricante(fabricante);
        mant.setEnservicio(enservicio);
        mant.setNivelimportancia(Integer.parseInt(importancia));
        mant.setTipomantenimiento(tipo);
        mant.setEntidadcargo(entidad);
        mant.setMombretecnico(tecnico);
        mant.setIdherramienta(herr);
        
     
        
        try {
            dao.create(mant);
        } catch (Exception ex) {
            Logger.getLogger(mantenimientoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Listando la informacion  
        List<Mantenimiento> mantenimientos =  dao.findMantenimientoEntities();
        request.setAttribute("mantenimientos", mantenimientos);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("mantenimiento.jsp");
        redireccion.forward(request, response);
        
        
        }
}


