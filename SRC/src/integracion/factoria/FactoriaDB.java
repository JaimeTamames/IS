package is.apptel.integracion.factoria;

import is.apptel.integracion.database.DAODatabase;

import java.sql.Connection;

public abstract class FactoriaDB {

	private static FactoriaDB factoria = null;
	
	public static FactoriaDB obtenerInstancia() {
		if(factoria == null) {
			factoria = new FactoriaDBImp();
		}
		
		return factoria;
	}
	
	public abstract Connection getConnection();

	public abstract DAODatabase generaDAODatabase();
	
}
