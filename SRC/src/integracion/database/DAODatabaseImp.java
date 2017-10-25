package is.apptel.integracion.database;

import is.apptel.integracion.factoria.FactoriaDB;
import is.apptel.negocio.cliente.TCliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAODatabaseImp implements DAODatabase {

    @Override
    public void up() {
        String query;

        query = "CREATE TABLE IF NOT EXISTS CLIENTES" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NOMBRE TEXT NOT NULL, " +
                "APELLIDOS TEXT NOT NULL, " +
                "NIF TEXT NOT NULL, " +
                "DIRECCION TEXT NOT NULL, " +
                "TELEFONO INTEGER NOT NULL, " +
                "MAIL TEXT NOT NULL, " +
                "TIPO TEXT NOT NULL)";
        createTable(query);

        query = "CREATE TABLE IF NOT EXISTS HABITACIONES" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NUMERO INTEGER NOT NULL, " +
                "TIPO TEXT NOT NULL, " +
                "PRECIO DOUBLE NOT NULL)";
        createTable(query);
        
        query = "CREATE TABLE IF NOT EXISTS SERVICIOS" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NOMBRE TEXT NOT NULL, " +
                "PRECIO DOUBLE NOT NULL)";
        createTable(query);
        
        query = "CREATE TABLE IF NOT EXISTS RESERVAS (ID INTEGER PRIMARY KEY AUTOINCREMENT, INICIO TEXT NOT NULL, FIN TEXT NOT NULL, IMPORTE DOUBLE NOT NULL)";
        createTable(query);
        
        query = "CREATE TABLE IF NOT EXISTS RESERVA_CLIENTE (ID_RESERVA INTEGER NOT NULL, ID_CLIENTE INTEGER NOT NULL)";
        createTable(query);
        
        query = "CREATE TABLE IF NOT EXISTS RESERVA_HABITACION (ID_RESERVA INTEGER NOT NULL, ID_HABITACION INTEGER NOT NULL)";
        createTable(query);
        
        query = "CREATE TABLE IF NOT EXISTS RESERVA_SERVICIO (ID_RESERVA INTEGER NOT NULL, ID_SERVICIO INTEGER NOT NULL)";
        createTable(query);
    }

    @Override
    public ResultSet select(String query) {
        Connection connection = FactoriaDB.obtenerInstancia().getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        TCliente cliente = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            return resultSet;
        } catch (SQLException e) {
            System.err.println(e);
        }

        return resultSet;
    }

    @Override
    public void insert(String query) {
        Connection connection = FactoriaDB.obtenerInstancia().getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String query) {
        Connection connection = FactoriaDB.obtenerInstancia().getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String query) {
        Connection connection = FactoriaDB.obtenerInstancia().getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable(String query) {
        Connection connection = FactoriaDB.obtenerInstancia().getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLastID(String table) {
    	int lastID = -1;
    	String query = "SELECT MAX(ID) FROM " + table;
    	
    	ResultSet resultSet = select(query);
    	
    	try {
			lastID = resultSet.getInt(1);
		} catch (SQLException e) {
			System.err.println(e);
		}
    	
    	return lastID;
    }
    
}
