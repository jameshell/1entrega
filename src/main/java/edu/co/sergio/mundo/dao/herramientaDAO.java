/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import edu.co.sergio.mundo.vo.Herramienta;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jaime Alonso
 */
public class herramientaDAO implements IBaseDatos<Herramienta> {

	/**
	 * Funcion que permite obtener una lista de los departamentos existentes en la base de datos
	 * @return List<Departamento> Retorna la lista de Departamentos existentes en la base de datos
	 */
	public List<Herramienta> findAll() {
		List<Herramienta> herramientas= null;
	    String query = "SELECT * FROM Herramienta";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
            
	    int id =0;
            int idInv=0;
            String nombre=null;
            int serial=0;
            String descripcion=null;
            
            
	
	    while (rs.next()){
	    	if(herramientas == null){
	    		herramientas= new ArrayList<Herramienta>();
	    	}
	      
	        Herramienta registro= new Herramienta();
	        id = rs.getInt("idHerramienta");
	        registro.setIdHerramienta(id);
                
                 idInv = rs.getInt("idInventario");
	        registro.setIdInventario(idInv);
                
	        nombre = rs.getString("nombreHerramienta");
	        registro.setNombreHerramienta(nombre);
                
                serial = rs.getInt("noSerial");
	        registro.setNoSerial(serial);
                
                descripcion = rs.getString("descripcionHerramienta");
	        registro.setDescripcionHerramienta(descripcion);
                
	        
	        herramientas.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de herramientas");
			e.printStackTrace();
		}
	    
	    return herramientas;
	}

	
	/**
	 * Funcion que permite realizar la insercion de un nuevo registro en la tabla Departamento
	 * @param Departamento recibe un objeto de tipo Departamento 
	 * @return boolean retorna true si la operacion de insercion es exitosa.
	 */
	public boolean insert(Herramienta t) {
		boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(herramientaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = " INSERT into Herramienta (idHerramienta, idInventario, nombreHerramienta, noSerial, descripcionHerramienta)"  + " values (?,?,?,?,?)";
        PreparedStatement preparedStmt=null;
	    try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt (1, t.getIdHerramienta());
                        preparedStmt.setInt (2, t.getIdInventario());
                        preparedStmt.setString(3, t.getNombreHerramienta());
                        preparedStmt.setInt (4, t.getNoSerial());
                        preparedStmt.setString(5, t.getDescripcionHerramienta());
                        
                        
                        
			result= preparedStmt.execute();
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Funcion que permite realizar la actualizacion de un nuevo registro en la tabla Departamento
	 * @param Departamento recibe un objeto de tipo Departamento 
	 * @return boolean retorna true si la operacion de actualizacion es exitosa.
	 */
	public boolean update(Herramienta t) {
		boolean result=false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(herramientaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "UPDATE Herramienta set nombreHerramienta = ? WHERE idHerramienta = ?";
                
                /* Revisar bien el efecto de update en mysql*/
                
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, t.getNombreHerramienta());
                    preparedStmt.setInt   (2, t.getIdHerramienta());
                    
		    if (preparedStmt.executeUpdate() > 0){
		    	result=true;
		    }
			    
		} catch (SQLException e) {
				e.printStackTrace();
		}
			
		return result;
	}

	/**
	 * Funcion que permite realizar la eliminario de registro en la tabla Departamento
	 * @param Departamento recibe un objeto de tipo Departamento 
	 * @return boolean retorna true si la operacion de borrado es exitosa.
	 */
	public boolean delete(Herramienta t) {
	   boolean result=false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(herramientaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "DELETE * from Herramienta WHERE idHerramienta = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		     preparedStmt = connection.prepareStatement(query);
		     preparedStmt.setInt(1, t.getIdHerramienta());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
	}
}
