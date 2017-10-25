package is.apptel.integracion.servicio;

import is.apptel.negocio.excepciones.ServicioExistsException;
import is.apptel.negocio.servicio.TServicio;

import java.util.ArrayList;

public interface DAOServicio {

    public void create(TServicio servicio);

    public void delete(int id);

    public TServicio read(int id);

    public ArrayList<TServicio> readAll();

    public void update(TServicio servicio);

    public  TServicio readByNombre(String nombre);

    public void checkExists(String nombre) throws ServicioExistsException;
}
