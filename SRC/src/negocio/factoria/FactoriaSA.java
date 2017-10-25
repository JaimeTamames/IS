package is.apptel.negocio.factoria;


import is.apptel.negocio.cliente.SACliente;
import is.apptel.negocio.habitacion.SAHabitacion;
import is.apptel.negocio.reserva.SAReserva;
import is.apptel.negocio.servicio.SAServicio;

public abstract class FactoriaSA {

	private static FactoriaSA factoria = null;
	
	public static FactoriaSA obtenerInstancia() {
		if(factoria == null) {
			factoria = new FactoriaSAImp();
		}
		
		return factoria;
	}
	
	public abstract SACliente generaSACliente();

	public abstract SAHabitacion generaSAHabitacion();

	public abstract SAServicio generaSAServicio();
	
	public abstract SAReserva generaSAReserva();
}
