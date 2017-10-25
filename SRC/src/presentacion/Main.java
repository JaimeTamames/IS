package is.apptel.presentacion;

import is.apptel.integracion.database.DAODatabase;
import is.apptel.integracion.factoria.FactoriaDB;
import is.apptel.negocio.cliente.SACliente;
import is.apptel.negocio.cliente.TCliente;
import is.apptel.negocio.factoria.FactoriaSA;
import is.apptel.negocio.habitacion.SAHabitacion;
import is.apptel.negocio.habitacion.THabitacion;
import is.apptel.negocio.reserva.SAReserva;
import is.apptel.negocio.reserva.TReserva;
import is.apptel.negocio.servicio.SAServicio;
import is.apptel.negocio.servicio.TServicio;
import is.apptel.presentacion.cliente.VCliente;
import is.apptel.presentacion.controlador.Controlador;
import is.apptel.presentacion.controlador.ControladorImp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main {

	public static void main(String args[]) {		
		DAODatabase database = FactoriaDB.obtenerInstancia().generaDAODatabase();
		database.up();

		Controlador c = Controlador.getInstancia();
		c.run();
		
	}

}
