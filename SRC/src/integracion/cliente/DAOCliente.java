package is.apptel.integracion.cliente;

import java.util.ArrayList;
import is.apptel.negocio.cliente.TCliente;
import is.apptel.negocio.excepciones.ClienteExistsException;

public interface DAOCliente {

	public void create(TCliente cliente);
	
	public void delete(int idCliente);
	
	public TCliente read(int idCliente);

	public TCliente read(String nif);
	
	public ArrayList<TCliente> readAll();
	
	public void update(TCliente cliente);

	public void checkExists(String nif) throws ClienteExistsException;
	
}
