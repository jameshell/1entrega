/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import edu.co.sergio.mundo.vo.Persona;
import edu.co.sergio.mundo.vo.Salon;
import edu.co.sergio.mundo.vo.prestamoSalon;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Date;
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
public class prestamoSalonDAO implements IBaseDatos<prestamoSalon> {

	/**
	 * Funcion que permite obtener una lista de los departamentos existentes en la base de datos
	 * @return List<Departamento> Retorna la lista de Departamentos existentes en la base de datos
	 */
	public List<prestamoSalon> findAll() {
		List<prestamoSalon> prestamosSalones= null;
	    String query = "SELECT * FROM prestamoSalon";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(prestamoSalonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
            
            /*Atributos*/
            
	    int idprestamosalon =0;
            int idsalon=0;
            int idpersona=0;
            
            Date entrada=null;
	    Date salida=null;
	
	    while (rs.next()){
	    	if(prestamosSalones == null){
	    		prestamosSalones= new ArrayList<prestamoSalon>();
	    	}
	      
	        prestamoSalon registro= new prestamoSalon();
                Salon salon= new Salon();
                Persona persona= new Persona();
                
	        idprestamosalon = rs.getInt("idPrestamoSalon");
	        registro.setIdPrestamoSalon(idprestamosalon);
                
                idsalon = rs.getInt("idSalon");
                salon.setIdSalon(idsalon);
	        registro.setSalon(salon);
                
                 idpersona = rs.getInt("idPersona");
                 persona.setIdPersona(idpersona);
	        registro.setPersona(persona);
	        
	        entrada = rs.getDate("fechaEntrada");
	        registro.setFechaEntrada(entrada);
                
                salida = rs.getDate("fechaSalida");
	        registro.setFechaSalida(salida);
	        
	        prestamosSalones.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de prestamos de salon");
			e.printStackTrace();
		}
	    
	    return prestamosSalones;
	}

	
	/**
	 * Funcion que permite realizar la insercion de un nuevo registro en la tabla Departamento
	 * @param Departamento recibe un objeto de tipo Departamento 
	 * @return boolean retorna true si la operacion de insercion es exitosa.
	 */
	public boolean insert(prestamoSalon t) {
		boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(prestamoSalonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = " INSERT INTO prestamoSalon (idPrestamoSalon, idSalon, idPersona, fechaEntrada, fechaSalida)"  + " values (?,?,?,?,?)";
        PreparedStatement preparedStmt=null;
	    try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt (1, t.getIdPrestamoSalon());
                        preparedStmt.setInt (2, t.getSalon().getIdSalon());
                        preparedStmt.setInt (3, t.getPersona().getIdPersona());
                        preparedStmt.setDate (4, t.getFechaEntrada());
                        preparedStmt.setDate (5, t.getFechaSalida());
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
	public boolean update(prestamoSalon t) {
		boolean result=false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(prestamoSalonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "UPDATE prestamoSalon set fechaEntrada = ? WHERE idPrestamoSalon = ?";
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setDate(1, t.getFechaEntrada());
                    preparedStmt.setInt   (2, t.getIdPrestamoSalon());
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
	public boolean delete(prestamoSalon t) {
	   boolean result=false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(prestamoSalonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "DELETE from prestamoSalon WHERE idPrestamoSalon = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		     preparedStmt = connection.prepareStatement(query);
		     preparedStmt.setInt(1, t.getIdPrestamoSalon());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
	}
}

