package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.PersonaDAO;
import edu.co.sergio.mundo.vo.Persona;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class excelPersonas extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);  
    }

    public void destroy() {
    }

    /** Processes requests for both HTTP GET and POST methods.
     * @param request servlet request
     * @param response servlet response
     */

    protected void processRequest(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/vnd.ms-excel");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("new sheet");
 
        PersonaDAO dao= new PersonaDAO();
         List<Persona> personas = dao.findPersonaEntities();
       
        Map<String, Object[]> data = new HashMap<String, Object[]>();
        int count=1;
        int count2=0;
        data.put("1", new Object[] {"Nombre", "ID", "Cargo"});
              for (Persona persona : personas){
                  
                  
                  data.put(String.valueOf(count),new Object[] {persona.getNombrepersona(),persona.getIdpersona(),persona.getCargopersona()});
                  count++;
              }
        
		
		
		
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object [] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof Date) 
					cell.setCellValue((Date)obj);
				else if(obj instanceof Boolean)
					cell.setCellValue((Boolean)obj);
				else if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Double)
					cell.setCellValue((Double)obj);
			}
		}
        
        // Write the output 
        OutputStream out = response.getOutputStream();
        wb.write(out);
        out.close();
    }

    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */

    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /** Handles the HTTP POST method.
     * @param request servlet request
     * @param response servlet response
     */

    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /** Returns a short description of the servlet.
     */

    public String getServletInfo() {
       return "Example to create a workbook in a servlet using HSSF";
    }
}
