package is.apptel.negocio.cliente;

import is.apptel.negocio.excepciones.ClienteExistsException;

import java.util.ArrayList;

public interface SACliente {

	public void create(TCliente cliente);
	
	public void delete(int id);
	
	public TCliente read(int id);
	
	public ArrayList<TCliente> readAll();
	
	public void update(TCliente cliente);
	
	public void exists(TCliente cliente) throws ClienteExistsException;
	
}
