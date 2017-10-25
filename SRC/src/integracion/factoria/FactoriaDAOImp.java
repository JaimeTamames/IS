package is.apptel.integracion.factoria;

import is.apptel.integracion.cliente.DAOCliente;
import is.apptel.integracion.cliente.DAOClienteImp;
import is.apptel.integracion.habitacion.DAOHabitacion;
import is.apptel.integracion.habitacion.DAOHabitacionImp;
import is.apptel.integracion.reserva.DAOReserva;
import is.apptel.integracion.reserva.DAOReservaImp;
import is.apptel.integracion.servicio.DAOServicio;
import is.apptel.integracion.servicio.DAOServicioImp;

public class FactoriaDAOImp extends FactoriaDAO {

	private DAOCliente 		daoCliente;
	private DAOHabitacion 	daoHabitacion;
	private DAOReserva		daoReserva;
	private DAOServicio		daoServicio;
	
	@Override
	public DAOCliente generaDAOCliente() {
		if(this.daoCliente == null) {
			this.daoCliente = new DAOClienteImp();
		}
		
		return this.daoCliente;
	}

	@Override
	public DAOHabitacion generaDAOHabitacion() {
		if(this.daoHabitacion == null) {
			this.daoHabitacion = new DAOHabitacionImp();
		}

		return this.daoHabitacion;
	}

	@Override
	public DAOServicio generaDAOServicio() {
		if(this.daoServicio == null) {
			this.daoServicio = new DAOServicioImp();
		}

		return this.daoServicio;
	}

	@Override
	public DAOReserva generaDAOReserva() {
		if(this.daoReserva == null) {
			this.daoReserva = new DAOReservaImp();
		}

		return this.daoReserva;
	}
	
}
