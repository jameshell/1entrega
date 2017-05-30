package com.crunchify.jsp.servlet;
 

import edu.co.sergio.mundo.dao.HerramientaDAO;
import edu.co.sergio.mundo.vo.Herramienta;


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
 
public class listaControladorHerramienta extends HttpServlet {
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
