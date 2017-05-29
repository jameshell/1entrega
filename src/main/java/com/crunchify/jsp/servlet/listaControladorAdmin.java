package com.crunchify.jsp.servlet;
 

import edu.co.sergio.mundo.dao.AdministrativoDAO;
import edu.co.sergio.mundo.vo.Administrativo;
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
 
public class listaControladorAdmin extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        AdministrativoDAO dao = new AdministrativoDAO();

        
        //Listando la informacion  
        List<Administrativo> admins =  dao.findAdministrativoEntities();
        request.setAttribute("admins", admins);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("listaAdministrativo.jsp");
        redireccion.forward(request, response);
        
        
        }
}
