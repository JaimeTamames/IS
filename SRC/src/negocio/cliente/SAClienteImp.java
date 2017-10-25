package is.apptel.negocio.cliente;

import is.apptel.integracion.cliente.DAOCliente;
import is.apptel.integracion.factoria.FactoriaDAO;
import is.apptel.negocio.excepciones.ClienteExistsException;

import java.util.ArrayList;

public class SAClienteImp implements SACliente {

	@Override
	public void create(TCliente cliente) {
		DAOCliente daoCliente = FactoriaDAO.obtenerInstancia().generaDAOCliente();

        try {
            exists(cliente);
            daoCliente.create(cliente);
        } catch (ClienteExistsException e) {
            System.err.println(e);
        }
	}

	@Override
	public void delete(int id) {
		DAOCliente daoCliente = FactoriaDAO.obtenerInstancia().generaDAOCliente();
		daoCliente.delete(id);
	}

	@Override
	public TCliente read(int id) {
		DAOCliente daoCliente = FactoriaDAO.obtenerInstancia().generaDAOCliente();
        return daoCliente.read(id);
	}

	@Override
	public ArrayList<TCliente> readAll() {
		DAOCliente daoCliente = FactoriaDAO.obtenerInstancia().generaDAOCliente();
        return daoCliente.readAll();
	}

	@Override
	public void update(TCliente cliente) {
		DAOCliente daoCliente = FactoriaDAO.obtenerInstancia().generaDAOCliente();
        daoCliente.update(cliente);
	}

	@Override
	public void exists(TCliente cliente) throws ClienteExistsException{
		DAOCliente daoCliente = FactoriaDAO.obtenerInstancia().generaDAOCliente();
        TCliente cliente1 = daoCliente.read(cliente.getNif());

        if(cliente1 != null) {
            throw new ClienteExistsException(cliente.getNif());
        }
	}

}
