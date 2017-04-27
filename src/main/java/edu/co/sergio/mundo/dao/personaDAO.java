/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import edu.co.sergio.mundo.vo.Persona;
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


public class personaDAO implements IBaseDatos<Persona> {

	/**
	 * Funcion que permite obtener una lista de los departamentos existentes en la base de datos
	 * @return List<Departamento> Retorna la lista de Departamentos existentes en la base de datos
	 */
	public List<Persona> findAll() {
		List<Persona> personas= null;
	    String query = "SELECT * FROM Persona";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(personaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id =0;
	    String nombre = null;
            String apellido= null;
            int semestre= 0;
            String carrera= null;
            String cargo= null;
	
	    while (rs.next()){
	    	if(personas == null){
	    		personas= new ArrayList<Persona>();
	    	}
	      
	        Persona registro= new Persona();
	        id = rs.getInt("idPersona");
	        registro.setIdPersona(id);
	        
	        nombre = rs.getString("nombrePersona");
	        registro.setNombrePersona(nombre); 
                
                apellido = rs.getString("apellidoPersona");
                registro.setApellidoPersona(apellido);
                
                semestre = rs.getInt("semestrePersona");
                registro.setSemestrePersona(semestre);
                
                carrera = rs.getString("carreraPersona");
                registro.setCarreraPersona(carrera);
                
                cargo= rs.getString("cargoPersona");
                registro.setCargoPersona(cargo);
                
                
	        
	        personas.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Personas en el registro");
			e.printStackTrace();
		}
	    
	    return personas;
	}

	
	/**
	 * Funcion que permite realizar la insercion de un nuevo registro en la tabla Departamento
	 * @param Departamento recibe un objeto de tipo Departamento 
	 * @return boolean retorna true si la operacion de insercion es exitosa.
	 */
	public boolean insert(Persona t) {
		boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = " insert into Persona (idPersona,nombrePersona,apellidoPersona,semestrePersona,carreraPersona,cargoPersona)"  + " values (?,?,?,?,?,?)";
        PreparedStatement preparedStmt=null;
	    try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt (1, t.getIdPersona());
                        preparedStmt.setString (2, t.getNombrePersona());
                        preparedStmt.setString (3, t.getApellidoPersona());
                        preparedStmt.setInt (4, t.getSemestrePersona());
                        preparedStmt.setString (5, t.getCarreraPersona());
                        preparedStmt.setString (6, t.getCargoPersona());
                           
                        
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
	public boolean update(Persona t) {
		boolean result=false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "update Persona set nombrePersona = ? where idPersona = ?";
                /* Revisar bien el query que se esta ejecutando en MYSQL*/
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, t.getNombrePersona());
                    preparedStmt.setInt   (2, t.getIdPersona());
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
	public boolean delete(Persona t) {
	   boolean result=false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "DELETE from Persona WHERE idPersona = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		     preparedStmt = connection.prepareStatement(query);
		     preparedStmt.setInt(1, t.getIdPersona());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
	}
}
