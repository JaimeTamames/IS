package is.apptel.negocio.habitacion;

import is.apptel.integracion.habitacion.DAOHabitacion;
import is.apptel.integracion.reserva.DAOReserva;
import is.apptel.integracion.factoria.FactoriaDAO;
import is.apptel.negocio.excepciones.HabitacionExistsException;
import is.apptel.negocio.habitacion.THabitacion;
import is.apptel.negocio.reserva.TReserva;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class SAHabitacionImp implements SAHabitacion {

	public void create(THabitacion habitacion) {
		DAOHabitacion daoHabitacion = FactoriaDAO.obtenerInstancia().generaDAOHabitacion();
        try {
            exists(habitacion);
            daoHabitacion.create(habitacion);
        } catch (HabitacionExistsException e) {
            System.err.println(e);
        }
	}

	@Override
	public void delete(int id) {
		DAOHabitacion daohabitacion = FactoriaDAO.obtenerInstancia().generaDAOHabitacion();
		daohabitacion.delete(id);
	}

	@Override
	public THabitacion read(int id) {
		DAOHabitacion daohabitacion = FactoriaDAO.obtenerInstancia().generaDAOHabitacion();
        return daohabitacion.read(id);
	}

	@Override
	public ArrayList<THabitacion> readAll() {
		DAOHabitacion daohabitacion = FactoriaDAO.obtenerInstancia().generaDAOHabitacion();
        return daohabitacion.readAll();
	}

	@Override
	public void update(THabitacion habitacion) {
		DAOHabitacion daohabitacion = FactoriaDAO.obtenerInstancia().generaDAOHabitacion();
        daohabitacion.update(habitacion);
	}

	@Override
	public void exists(THabitacion habitacion) throws HabitacionExistsException {
		DAOHabitacion daohabitacion = FactoriaDAO.obtenerInstancia().generaDAOHabitacion();
        THabitacion habitacion1 = daohabitacion.read(habitacion.getId());

        if(habitacion1 != null) {
            throw new HabitacionExistsException(habitacion.getNumero());
        }
	}

	
	@Override
	public ArrayList<THabitacion> getHabitacionesDisponibles(Date inicio, Date fin) {
		DAOReserva daoReserva = FactoriaDAO.obtenerInstancia().generaDAOReserva();
		DAOHabitacion daoHabitacion = FactoriaDAO.obtenerInstancia().generaDAOHabitacion();
		
		ArrayList<THabitacion> disponibles = new ArrayList<THabitacion>();
		ArrayList<TReserva> reservas = new ArrayList<TReserva>();
		
		disponibles = daoHabitacion.readAll();
		reservas = daoReserva.readAll();
		
		for(TReserva reserva : reservas) {
			 if((inicio.compareTo(reserva.getFecha_inicio()) > 0 || inicio.equals(reserva.getFecha_inicio())) && (fin.compareTo(reserva.getFecha_fin()) < 0 || fin.equals(reserva.getFecha_fin()))){
				// fechas intermedias de la reserva
				for(THabitacion habitacion : reserva.getHabitaciones()) {
					Iterator<THabitacion> it = disponibles.iterator();
					while(it.hasNext()) {
						THabitacion h = it.next();
						if(habitacion.getId() == h.getId()) {
							it.remove();
						}
					}
				}
			}
				if(inicio.compareTo(reserva.getFecha_fin()) < 0 && fin.compareTo(reserva.getFecha_fin()) > 0){ //el inicio < fin reserva
					// TODO completar la condicion para que compare las fechas.
					for(THabitacion habitacion : reserva.getHabitaciones()) {
						Iterator<THabitacion> it = disponibles.iterator();
						while(it.hasNext()) {
							THabitacion h = it.next();
							if(habitacion.getId() == h.getId()) {
								it.remove();
								}
							}
						}
					}
				if(fin.compareTo(reserva.getFecha_inicio()) > 0 && inicio.compareTo(reserva.getFecha_inicio()) < 0){//el fin < inici reserca
					for(THabitacion habitacion : reserva.getHabitaciones()) {
						Iterator<THabitacion> it = disponibles.iterator();
						while(it.hasNext()) {
							THabitacion h = it.next();
							if(habitacion.getId() == h.getId()) {
								it.remove();
								}
							}
						}
					}

		}
		
		return disponibles;
	}

}
