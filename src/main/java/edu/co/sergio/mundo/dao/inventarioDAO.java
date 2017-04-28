/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import edu.co.sergio.mundo.vo.Inventario;
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
public class inventarioDAO implements IBaseDatos<Inventario> {

	/**
	 * Funcion que permite obtener una lista de los departamentos existentes en la base de datos
	 * @return List<Departamento> Retorna la lista de Departamentos existentes en la base de datos
	 */
	public List<Inventario> findAll() {
		List<Inventario> inventarios= null;
	    String query = "SELECT * FROM Inventario";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
            
            /*  Inventarios   */
	    int id =0;
	    String nombre = null;
            String descripcion=null;
	
	    while (rs.next()){
	    	if(inventarios == null){
	    		inventarios= new ArrayList<Inventario>();
	    	}
	      
	        Inventario registro= new Inventario();
	        id = rs.getInt("idInventario");
	        registro.setIdInventario(id);
	        
	        nombre = rs.getString("nombreInventario");
	        registro.setNombreInventario(nombre);
                
                 descripcion = rs.getString("descripcionInventario");
	        registro.setNombreInventario(nombre);
                
	        
	        inventarios.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Inventarios");
			e.printStackTrace();
		}
	    
	    return inventarios;
	}

	
	/**
	 * Funcion que permite realizar la insercion de un nuevo registro en la tabla Departamento
	 * @param Departamento recibe un objeto de tipo Departamento 
	 * @return boolean retorna true si la operacion de insercion es exitosa.
	 */
	public boolean insert(Inventario t) {
		boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(inventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = " INSERT into Inventario (idInventario, nombreInventario, descripcionInventario)"  + " values (?,?,?)";
        PreparedStatement preparedStmt=null;
	    try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt (1, t.getIdInventario());
                        preparedStmt.setString (2, t.getNombreInventario());
                        preparedStmt.setString (3, t.getDescripcionInventario());
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
	public boolean update(Inventario t) {
		boolean result=false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(inventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "UPDATE Inventario set nombreInventario = ? WHERE idInventario = ?";
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, t.getNombreInventario());
                    preparedStmt.setInt   (2, t.getIdInventario());
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
	public boolean delete(Inventario t) {
	   boolean result=false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(inventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "DELETE from Inventario WHERE idInventario = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		     preparedStmt = connection.prepareStatement(query);
		     preparedStmt.setInt(1, t.getIdInventario());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
	}
}
