package is.apptel.integracion.servicio;

import is.apptel.integracion.database.DAODatabase;
import is.apptel.integracion.factoria.FactoriaDB;
import is.apptel.negocio.excepciones.ServicioExistsException;
import is.apptel.negocio.excepciones.ServicioNotExists;
import is.apptel.negocio.servicio.TServicio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOServicioImp implements DAOServicio {
    public void create(TServicio servicio) {
    	DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

        try {
            checkExists(servicio.getNombre());
            String query = "INSERT INTO SERVICIOS " +
                    "(NOMBRE, PRECIO) VALUES (" +
                    "'" + servicio.getNombre() + "', " +
                    "" + servicio.getPrecio() + " )";

            daoDatabase.insert(query);
        } catch (ServicioExistsException e) {
            System.err.println(e);
        }
    }
    public void delete(int id) {
    	DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

        String query = "DELETE FROM SERVICIOS WHERE ID=" + id;

        daoDatabase.delete(query);
    }
    
    public TServicio read(int id) {
    	DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

        String query = "SELECT * FROM SERVICIOS WHERE ID=" + id;

       TServicio servicio = null;

        try {
            ResultSet resultSet = daoDatabase.select(query);

            if (!resultSet.next()) {
                throw new ServicioNotExists();
            }
            
            servicio = new TServicio();
            servicio.setId(resultSet.getInt("ID"));
            servicio.setNombre(resultSet.getString("NOMBRE"));
            servicio.setPrecio(resultSet.getDouble("PRECIO"));
        } catch (SQLException e) {
            System.err.println(e);
        } catch (ServicioNotExists e) {
            System.err.println(e);
        }
        return servicio;
    }
    
    public ArrayList<TServicio> readAll() {
    	 DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

         String query = "SELECT * FROM SERVICIOS";

         TServicio servicio = null;
         ArrayList<TServicio> servicios = new ArrayList<TServicio>();

         try {
             ResultSet resultSet = daoDatabase.select(query);
             
             while (resultSet.next()) {
            	 servicio = new TServicio();
                 servicio.setId(resultSet.getInt("ID"));
                 servicio.setNombre(resultSet.getString("NOMBRE"));
                 servicio.setPrecio(resultSet.getDouble("PRECIO"));

                 servicios.add(servicio);
             }
         } catch (SQLException e) {
             System.err.println(e);
         } 

         return servicios;
    }
    public void update(TServicio servicio) {
    	DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

        String query = "UPDATE SERVICIOS SET " +
                "NOMBRE='" + servicio.getNombre() + "', " +
                "PRECIO=" + servicio.getPrecio() + " " +
                "WHERE ID=" + servicio.getId();

        daoDatabase.update(query);
    }
    
	public TServicio readByNombre(String nombre) {
		DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

        String query = "SELECT * FROM SERVICIOS WHERE NOMBRE='" +  nombre+"'";
       TServicio servicio = null;

        try {
            ResultSet resultSet = daoDatabase.select(query);
            if (!resultSet.next()) {
                throw new ServicioNotExists();
            }
            
            servicio = new TServicio();
            servicio.setId(resultSet.getInt("ID"));
            servicio.setNombre(resultSet.getString("NOMBRE"));
            servicio.setPrecio(resultSet.getDouble("PRECIO"));
        } catch (SQLException e) {
        
        } catch (ServicioNotExists e) {
        	System.err.println(e);
		} 

        return servicio;
	}
	
	public void checkExists(String nombre) throws ServicioExistsException {
		 TServicio servicio= readByNombre(nombre);

	        if(servicio != null) {
	            throw new ServicioExistsException(nombre);
	        }
	}
  
}
