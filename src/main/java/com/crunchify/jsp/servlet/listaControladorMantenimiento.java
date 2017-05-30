package com.crunchify.jsp.servlet;
 

import edu.co.sergio.mundo.dao.AdministrativoDAO;
import edu.co.sergio.mundo.dao.MantenimientoDAO;
import edu.co.sergio.mundo.vo.Administrativo;
import edu.co.sergio.mundo.vo.Mantenimiento;
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
 
public class listaControladorMantenimiento extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        MantenimientoDAO dao = new MantenimientoDAO();

        
        //Listando la informacion  
        List<Mantenimiento> mants =  dao.findMantenimientoEntities();
        request.setAttribute("mants", mants);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("listaMantenimiento.jsp");
        redireccion.forward(request, response);
        
        
        }
}
