package is.apptel.integracion.factoria;

import is.apptel.integracion.cliente.DAOCliente;
import is.apptel.integracion.habitacion.DAOHabitacion;
import is.apptel.integracion.reserva.DAOReserva;
import is.apptel.integracion.servicio.DAOServicio;

public abstract class FactoriaDAO {

	private static FactoriaDAO factoria = null;
	
	public static FactoriaDAO obtenerInstancia() {
		if(factoria == null) {
			factoria = new FactoriaDAOImp();
		}
		
		return factoria;
	}
	
	public abstract DAOCliente generaDAOCliente();
	
	public abstract DAOHabitacion generaDAOHabitacion();

	public abstract DAOServicio generaDAOServicio();
	
	public abstract DAOReserva generaDAOReserva();
	
}
