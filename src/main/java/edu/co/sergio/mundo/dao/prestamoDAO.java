/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;


import edu.co.sergio.mundo.vo.Administrativo;
import edu.co.sergio.mundo.vo.Persona;
import edu.co.sergio.mundo.vo.Prestamo;
import edu.co.sergio.mundo.vo.Salon;
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
 * @author james
 */

public class prestamoDAO implements IBaseDatos<Prestamo> {

	/**
	 * Funcion que permite obtener una lista de los departamentos existentes en la base de datos
	 * @return List<Departamento> Retorna la lista de Departamentos existentes en la base de datos
	 */
	public List<Prestamo> findAll() {
		List<Prestamo> prestamos= null;
	    String query = "SELECT * FROM Prestamo";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(prestamoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
            
            /* Atributos */
            String codprestamos=null;
	    int idpersona =0;
            int idadministrativo=0;
            int idsalon= 0;
            String tipopractica=null;
            Date entrada=null;
            Date salida=null;
            String estado=null;
            String justificacion=null;
            String observaciones=null;
            String tipoprestamo=null;
            

	    while (rs.next()){
	    	if(prestamos == null){
	    		prestamos = new ArrayList<Prestamo>();
	    	}
	      
	        Prestamo registro= new Prestamo();
                Persona persona= new Persona();
                Administrativo admin= new Administrativo();
                Salon salon=new Salon();
                
	        codprestamos = rs.getString("codPrestamo");
	        registro.setCodPrestamo(codprestamos);
	        
	        idpersona = rs.getInt("idPersona");
                persona.setIdPersona(idpersona);
	        registro.setPersona(persona);
                
                idadministrativo = rs.getInt("idAdministrativo");
                admin.setIdAdministrativo(idadministrativo);
	        registro.setAdministrativo(admin);
                
                idsalon = rs.getInt("idSalon");
                salon.setIdSalon(idsalon);
	        registro.setSalon(salon);
                
                tipopractica = rs.getString("tipoPractica");
	        registro.setTipoPractica(tipopractica);
                
                entrada = rs.getDate("fechaEntrada");
	        registro.setFechaEntrada(entrada);
                
                salida = rs.getDate("fechaSalida");
	        registro.setFechaSalida(salida);
	        
	        prestamos.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Prestamos");
			e.printStackTrace();
		}
	    
	    return prestamos;
	}

	
	/**
	 * Funcion que permite realizar la insercion de un nuevo registro en la tabla Departamento
	 * @param Departamento recibe un objeto de tipo Departamento 
	 * @return boolean retorna true si la operacion de insercion es exitosa.
	 */
	public boolean insert(Prestamo t) {
		boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(prestamoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = 
        " INSERT into Prestamo (codPrestamo, idPersona, idAdministrativo, idSalon, tipoPractica, fechaEntrada, fechaSalida, estado, justificacion, observaciones, tipoPrestamo)"  + " values (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStmt=null;
	    try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString (1, t.getCodPrestamo());
                        preparedStmt.setInt (2, t.getPersona().getIdPersona());
                        preparedStmt.setInt (3, t.getAdministrativo().getIdAdministrativo());
                        preparedStmt.setInt (4, t.getSalon().getIdSalon());
                        preparedStmt.setString (5, t.getTipoPractica());
                        preparedStmt.setDate(6, t.getFechaEntrada());
                        preparedStmt.setDate(7, t.getFechaSalida());
                        preparedStmt.setString (8, t.getEstado());
                        preparedStmt.setString (9, t.getJustificacion());
                        preparedStmt.setString (10, t.getObservaciones());
                        preparedStmt.setString (11, t.getTipoPrestamo());
                        
                         
                         
                        
                        
                        
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
	public boolean update(Prestamo t) {
		boolean result=false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(prestamoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "UPDATE Prestamo tipoPractica = ? where codPrestamo = ?";
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, t.getTipoPractica());
                    preparedStmt.setString   (2, t.getCodPrestamo());
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
	public boolean delete(Prestamo t) {
	   boolean result=false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(prestamoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "DELETE from Prestamo where codPrestamo = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		     preparedStmt = connection.prepareStatement(query);
		     preparedStmt.setString(1, t.getCodPrestamo());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
	}
}
