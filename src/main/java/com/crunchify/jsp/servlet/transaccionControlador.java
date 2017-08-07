/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.HerramientaDAO;
import edu.co.sergio.mundo.dao.TransaccionDAO;

import edu.co.sergio.mundo.vo.Administrativo;
import edu.co.sergio.mundo.vo.Herramienta;
import edu.co.sergio.mundo.vo.Persona;
import edu.co.sergio.mundo.vo.Prestamo;

import edu.co.sergio.mundo.vo.Salon;
import edu.co.sergio.mundo.vo.Transaccion;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Date;
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

public class transaccionControlador extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        String idtrans = request.getParameter("idTransaccion");//
        String idprestamo = request.getParameter("idPrestamo");//
        String idherramienta= request.getParameter("idHerramienta");//
 
        
 
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        TransaccionDAO dao = new TransaccionDAO();
        Transaccion trans = new Transaccion();
        Prestamo prestamo= new Prestamo();
        Herramienta herramienta= new Herramienta();
        
        
        
      
        
        try{  
            herramienta.setIdherramienta(Integer.parseInt(idherramienta));
            prestamo.setCodprestamo(idprestamo);
            
            trans.setIdtransaccion(Integer.parseInt(idtrans));
            trans.setIdherramienta(herramienta);
            trans.setCodprestamo(prestamo);

        dao.create(trans);
        } catch (Exception ex) {
            Logger.getLogger(prestamoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Listando la informacion  
        List<Transaccion> transacciones =  dao.findTransaccionEntities();
        request.setAttribute("transacciones", transacciones);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("transaccion.jsp");
        redireccion.forward(request, response);
        
        
        }
}


