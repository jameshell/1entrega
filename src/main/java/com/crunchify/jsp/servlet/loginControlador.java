/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.co.sergio.mundo.dao.AdministrativoDAO;
import edu.co.sergio.mundo.vo.Administrativo;
import edu.co.sergio.mundo.vo.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author James Alonso
 */

@WebServlet(urlPatterns = {"/loginServlet"})
public class loginControlador extends HttpServlet {
    
    
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
      String idAdmin, contrasena;
      idAdmin=request.getParameter("idAdmin");
      contrasena=request.getParameter("contrasena");
      
      AdministrativoDAO dao= new AdministrativoDAO();
        List<Administrativo> administrativos = dao.findAdministrativoEntities();
       
      
      for (Administrativo administrativo : administrativos){
      
      if(idAdmin.equalsIgnoreCase(String.valueOf(administrativo.getIdadministrativo()))){
          if(contrasena.equalsIgnoreCase(administrativo.getContrasena())){
                
                administrativo.setIdadministrativo(Integer.parseInt(idAdmin));
                HttpSession session = request.getSession();
                session.setAttribute("administrador", administrativo);
                session.setAttribute("loginResult", false);
                request.getRequestDispatcher("home.jsp").forward(request, response);
         
              //Usuario correcto!
          }else{
              //Contrasena incorrecta!
              //Crear un jsp para el error!
              request.setAttribute("loginResult", true);
              request.getRequestDispatcher("login.jsp").forward(request, response);
          }
      }else{
          //Usuario incorrecto!
           request.setAttribute("loginResult", true);
           request.getRequestDispatcher("login.jsp").forward(request, response);
          
      }
     
      }
      
      
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

