package is.apptel.negocio.servicio;
import is.apptel.negocio.excepciones.ServicioExistsException;

import java.util.ArrayList;

public interface SAServicio {
	
	public void create(TServicio servicio);
	
	public void delete(int id);
	
	public TServicio read(int id);
	
	public ArrayList<TServicio> readAll();
	
	public void update(TServicio servicio);
	
	public void exists(TServicio servicio) throws ServicioExistsException;
	
}
