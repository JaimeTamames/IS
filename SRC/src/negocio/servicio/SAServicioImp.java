package is.apptel.negocio.servicio;

import java.util.ArrayList;

import is.apptel.integracion.factoria.FactoriaDAO;
import is.apptel.integracion.servicio.DAOServicio;
import is.apptel.negocio.excepciones.ServicioExistsException;

public class SAServicioImp implements SAServicio{

	public void create(TServicio servicio) {
		DAOServicio daoServicio = FactoriaDAO.obtenerInstancia().generaDAOServicio();
		try {
			exists(servicio);
			daoServicio.create(servicio);
		} catch (ServicioExistsException e) {
			System.err.println(e);
		}
	}

	public void delete(int id) {
		DAOServicio daoServicio = FactoriaDAO.obtenerInstancia().generaDAOServicio();
		daoServicio.delete(id);
	}

	public TServicio read(int id) {
		DAOServicio daoServicio = FactoriaDAO.obtenerInstancia().generaDAOServicio();
		return daoServicio.read(id);
	}

	public ArrayList<TServicio> readAll() {
		DAOServicio daoServicio =FactoriaDAO.obtenerInstancia().generaDAOServicio();
		return daoServicio.readAll();
	}

	public void update(TServicio servicio) {
		DAOServicio daoServicio = FactoriaDAO.obtenerInstancia().generaDAOServicio();
		daoServicio.update(servicio);
	}

	public void exists(TServicio servicio) throws ServicioExistsException {
		DAOServicio daoServicio = FactoriaDAO.obtenerInstancia().generaDAOServicio();
		TServicio servicio1 = daoServicio.read(servicio.getId());
		if(servicio1!=null){
			throw new ServicioExistsException(servicio.getNombre());
		}
		
	}
}
