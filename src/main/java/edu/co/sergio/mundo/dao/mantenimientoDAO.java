/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;


import edu.co.sergio.mundo.vo.Mantenimiento;
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
public class mantenimientoDAO implements IBaseDatos<Mantenimiento> {

	/**
	 * Funcion que permite obtener una lista de los departamentos existentes en la base de datos
	 * @return List<Departamento> Retorna la lista de Departamentos existentes en la base de datos
	 */
	public List<Mantenimiento> findAll() {
		List<Mantenimiento> mantenimientos= null;
	    String query = "SELECT * FROM Mantenimiento";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(mantenimientoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
            
            /* Atributos */
	    int idmant=0;
            int idherramienta=0;
            String ref=null;
            String enservicio=null;
            int nivelimportancia=0;
            String tipomantenimiento=null;
            String entidad=null;
            String nombretecnico=null;
            Date inicio=null;
            Date final1 = null;

	    while (rs.next()){
	    	if(mantenimientos == null){
	    		mantenimientos= new ArrayList<Mantenimiento>();
	    	}
	      
	        Mantenimiento registro= new Mantenimiento();
	        idmant = rs.getInt("idMantenimiento");
	        registro.setIdMantenimiento(idmant);
                
                idherramienta = rs.getInt("idHerramienta");
	        registro.setIdHerramienta(idherramienta);
	        
	        ref = rs.getString("refFabricante");
	        registro.setRefFabricante(ref);
                
                 enservicio = rs.getString("enServicio");
	        registro.setEnServicio(enservicio);
                
                 nivelimportancia = rs.getInt("nivelImportancia");
	        registro.setNivelImportancia(nivelimportancia);
                
                 tipomantenimiento = rs.getString("tipoMantenimiento");
	        registro.setTipoMantenimiento(tipomantenimiento);
                
                 entidad = rs.getString("entidadCargo");
	        registro.setEntidadCargo(entidad);
                
                 nombretecnico = rs.getString("nombreTecnico");
	        registro.setNombreTecnico(nombretecnico);
                
                 inicio = rs.getDate("fechaInicio");
	        registro.setFechaInicio(inicio);
                
                 final1 = rs.getDate("fechaFinal");
	        registro.setFechaFinal(final1);
	        
	        mantenimientos.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Mantenimientos");
			e.printStackTrace();
		}
	    
	    return mantenimientos;
	}

	
	/**
	 * Funcion que permite realizar la insercion de un nuevo registro en la tabla Departamento
	 * @param Departamento recibe un objeto de tipo Departamento 
	 * @return boolean retorna true si la operacion de insercion es exitosa.
	 */
	public boolean insert(Mantenimiento t) {
		boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(mantenimientoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = " INSERT INTO Mantenimiento (idMantenimiento, idHerramienta, refFabricante, enServicio, nivelImportancia, tipoMantenimiento, entidadCargo, nombreTecnico, fechaInicio, fechaFinal)"  + " values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStmt=null;
	    try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt (1, t.getIdMantenimiento());
                        preparedStmt.setInt (2, t.getIdHerramienta());
                        preparedStmt.setString (3, t.getRefFabricante());
                        preparedStmt.setString (4, t.getEnServicio());
                        preparedStmt.setInt (5, t.getNivelImportancia());
                        preparedStmt.setString (6, t.getTipoMantenimiento());
                        preparedStmt.setString (7, t.getEntidadCargo());
                        preparedStmt.setString (8, t.getNombreTecnico());
                        preparedStmt.setDate (9, t.getFechaInicio());
                        preparedStmt.setDate (10, t.getFechaFinal());
                        
                        
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
	public boolean update(Mantenimiento t) {
		boolean result=false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(personaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "UPDATE Mantenimiento set nombreTenico = ? WHERE idMantenimiento = ?";
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, t.getNombreTecnico());
                    preparedStmt.setInt   (2, t.getIdMantenimiento());
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
	public boolean delete(Mantenimiento t) {
	   boolean result=false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(mantenimientoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "DELETE from Mantenimiento WHERE idMantenimiento = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		     preparedStmt = connection.prepareStatement(query);
		     preparedStmt.setInt(1, t.getIdMantenimiento());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
	}
}

