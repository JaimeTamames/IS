package is.apptel.presentacion.controlador;

import java.util.ArrayList;
import java.util.Date;

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

public class ControladorImp extends Controlador{
	private VCliente ventana;
	public ControladorImp(){
		
	}
	public void run(){
		ventana = new VCliente();
		ventana.initGUI();
	}
	
	public void accion(String evento , Object Datos) {
		switch (evento){
		case "nuevoCliente":{ //anadir cliente 
			SACliente saCliente = FactoriaSA.obtenerInstancia().generaSACliente();
			saCliente.create((TCliente)Datos);
			break;
		}
		case "modificarCliente" : { // modificar cliente
			SACliente saCliente = FactoriaSA.obtenerInstancia().generaSACliente();
			saCliente.update((TCliente) Datos);
			break;
			}
		case "eliminarCliente" : { // eliminar cliente
			SACliente saCliente = FactoriaSA.obtenerInstancia().generaSACliente();
			saCliente.delete((int) Datos);
			break;
			}
		case "leerCliente" : { // leer un cliente
			SACliente saCliente = FactoriaSA.obtenerInstancia().generaSACliente();
			ventana.setCliente(saCliente.read((int) Datos));
			break;
			}
		case "leerTodosClientes" : { // leer todos los clientes cliente
			SACliente saCliente = FactoriaSA.obtenerInstancia().generaSACliente();
			ArrayList<TCliente> clientes = saCliente.readAll();
			ventana.setTabla(clientes);
			break;
			}
		case "nuevaHabitacion":{ //anadir habitacion
			SAHabitacion saHabitacion = FactoriaSA.obtenerInstancia().generaSAHabitacion();
			saHabitacion.create((THabitacion)Datos);
			break;
		}
		case "modificarHabitacion" : { // modificar habitacion
			SAHabitacion saHabitacion = FactoriaSA.obtenerInstancia().generaSAHabitacion();
			saHabitacion.update((THabitacion) Datos);
			break;
			}
		case "eliminarHabitacion" : { // eliminar habitacion
			SAHabitacion saHabitacion = FactoriaSA.obtenerInstancia().generaSAHabitacion();
			saHabitacion.delete((int) Datos);
			break;
			}
		case "leerHabitacion" : { // leer una habitacion
			SAHabitacion saHabitacion = FactoriaSA.obtenerInstancia().generaSAHabitacion();
			ventana.setHabitacion(saHabitacion.read((int) Datos));
			break;
			}
		case "leerTodasHabitaciones" : { // leer todas las habitaciones
			SAHabitacion saHabitacion = FactoriaSA.obtenerInstancia().generaSAHabitacion();
			ArrayList<THabitacion> habitaciones = saHabitacion.readAll();
			ventana.setTablaHabitaciones(habitaciones);
			break;
			}
		case "leerHabitacionDisponible" :{
		SAHabitacion sahabitacion = FactoriaSA.obtenerInstancia().generaSAHabitacion();
		ArrayList<Date> fechas = (ArrayList<Date>) Datos;
		Date inicio = fechas.get(0);
		Date fin = fechas.get(1);
		ArrayList<THabitacion> habitacion = sahabitacion.getHabitacionesDisponibles(inicio,fin);
		ventana.setHabitacionesDisponibles(habitacion);
		break;
		}
		case "nuevoServicio":{
			SAServicio saServicio = FactoriaSA.obtenerInstancia().generaSAServicio();
			saServicio.create((TServicio) Datos);
			break;
		}
		case "modificarServicio":{
			SAServicio saServicio = FactoriaSA.obtenerInstancia().generaSAServicio();
			saServicio.update((TServicio) Datos);
			break;
		}
		case "eliminarServicio" :{
			SAServicio saServicio = FactoriaSA.obtenerInstancia().generaSAServicio();
			saServicio.delete((int) Datos);
			break;
		}
		case "leerTodosServicios" :{
			SAServicio saServicio = FactoriaSA.obtenerInstancia().generaSAServicio();
			ArrayList<TServicio> servicios = saServicio.readAll();
			ventana.setTablaServicios(servicios);
			break;
		}
		case "leerTodosServiciosReserva" :{
			SAServicio saServicio = FactoriaSA.obtenerInstancia().generaSAServicio();
			ArrayList<TServicio> servicios = saServicio.readAll();
			ventana.setTablaServiciosReserva(servicios);
			break;
		}
		case "leerServicio" : { // leer un servicio
			SAServicio saServicio = FactoriaSA.obtenerInstancia().generaSAServicio();
			ventana.setServicio(saServicio.read((int) Datos));
			break;
			}
		case "crearReserva" : {
			SAReserva saReserva = FactoriaSA.obtenerInstancia().generaSAReserva();
			saReserva.create((TReserva) Datos);
			break;
		}
		case "modificarReserva" : {
			SAReserva saReserva = FactoriaSA.obtenerInstancia().generaSAReserva();
			saReserva.update((TReserva) Datos);
			break;
		}
		case "eliminarReserva" : {
			SAReserva saReserva = FactoriaSA.obtenerInstancia().generaSAReserva();
			saReserva.delete((int) Datos);
			break;
		}
		case "leerTodasReservas" :{
			SAReserva saReserva = FactoriaSA.obtenerInstancia().generaSAReserva();
			ArrayList<TReserva> reservas = saReserva.readAll();
			ventana.setTablaReservas(reservas);
			break;
		}
		case "leerReservaUnCliente" :{
			SAReserva saReserva = FactoriaSA.obtenerInstancia().generaSAReserva();
			ArrayList<TReserva> reservas = saReserva.readAll((int) Datos);
			ventana.setTablaReservas(reservas);
			break;
		}
		case "leerReserva" :{
			SAReserva saReserva = FactoriaSA.obtenerInstancia().generaSAReserva();
			TReserva reserva = saReserva.read((int)Datos);
			ventana.setReserva(reserva);
			break;
		}
		case "GenerarFactura":{
			SAReserva saReserva = FactoriaSA.obtenerInstancia().generaSAReserva();
			saReserva.generarFactura((int)Datos);
			break;
			}
		case "ConsultarFactura":{
			SAReserva saReserva = FactoriaSA.obtenerInstancia().generaSAReserva();
			saReserva.verFactura((int)Datos);
			break;
			}
		}
	}
}