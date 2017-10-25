package is.apptel.negocio.habitacion;

import is.apptel.negocio.excepciones.HabitacionExistsException;

import java.util.ArrayList;
import java.util.Date;

public interface SAHabitacion {

	public void create(THabitacion habitacion);
	
	public void delete(int id);
	
	public THabitacion read(int id);
	
	public ArrayList<THabitacion> readAll();
	
	public void update(THabitacion habitacion);
	
	public void exists(THabitacion habitacion) throws HabitacionExistsException;
	
	public ArrayList<THabitacion> getHabitacionesDisponibles(Date inicio, Date fin);
	
}
