package is.apptel.integracion.factoria;

import is.apptel.integracion.database.DAODatabase;
import is.apptel.integracion.database.DAODatabaseImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoriaDBImp extends FactoriaDB {

	private final String DATABASE = "jdbc:sqlite:apptel.db";
	private Connection connection = null;

	private DAODatabase daoDatabase = null;
	
	public Connection getConnection() {
		try {
			if(this.connection != null) this.connection.close();
			this.connection = DriverManager.getConnection(DATABASE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return this.connection;
	}

	@Override
	public DAODatabase generaDAODatabase() {
		if(this.daoDatabase == null) {
			this.daoDatabase = new DAODatabaseImp();
		}

		return this.daoDatabase;
	}

}
