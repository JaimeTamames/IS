package is.apptel.negocio.reserva;

import is.apptel.negocio.cliente.TCliente;
import is.apptel.negocio.habitacion.THabitacion;
import is.apptel.negocio.servicio.TServicio;

import java.util.ArrayList;
import java.util.Date;

public class TReserva {

    private int id;
    private Date fecha_inicio;
    private Date fecha_fin;
    private TCliente cliente;
    private ArrayList<THabitacion> habitaciones;
    private ArrayList<TServicio> servicios;
    private double importe;

    public TReserva() {}

	public TReserva(Date fecha_inicio, Date fecha_fin, TCliente cliente,
			ArrayList<THabitacion> habitaciones,
			ArrayList<TServicio> servicios, double importe) {
		super();
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.cliente = cliente;
		this.habitaciones = habitaciones;
		this.servicios = servicios;
		this.importe = importe;
	}

	public TReserva(int id, Date fecha_inicio, Date fecha_fin,
			TCliente cliente, ArrayList<THabitacion> habitaciones,
			ArrayList<TServicio> servicios, double importe) {
		super();
		this.id = id;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.cliente = cliente;
		this.habitaciones = habitaciones;
		this.servicios = servicios;
		this.importe = importe;
	}

	public int getId() {
		return id;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public TCliente getCliente() {
		return cliente;
	}

	public ArrayList<THabitacion> getHabitaciones() {
		return habitaciones;
	}

	public ArrayList<TServicio> getServicios() {
		return servicios;
	}

	public double getImporte() {
		return importe;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public void setCliente(TCliente cliente) {
		this.cliente = cliente;
	}

	public void setHabitaciones(ArrayList<THabitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public void setServicios(ArrayList<TServicio> servicios) {
		this.servicios = servicios;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "TReserva [id=" + id + ", fecha_inicio=" + fecha_inicio
				+ ", fecha_fin=" + fecha_fin + ", cliente=" + cliente
				+ ", habitaciones=" + habitaciones + ", servicios=" + servicios
				+ ", importe=" + importe + "]";
	}
    
}
