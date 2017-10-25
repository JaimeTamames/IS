package is.apptel.integracion.habitacion;

import is.apptel.negocio.excepciones.HabitacionExistsException;
import is.apptel.negocio.habitacion.THabitacion;

import java.util.ArrayList;
import java.util.Date;

public interface DAOHabitacion {

    public void create(THabitacion habitacion);

    public void delete(int id);

    public THabitacion read(int id);

    public THabitacion readByNumero(int numero);

    public ArrayList<THabitacion> readAll();

    public void update(THabitacion habitacion);

    public void checkExists(int numero) throws HabitacionExistsException;
    
}
