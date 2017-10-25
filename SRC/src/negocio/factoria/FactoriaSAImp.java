package is.apptel.negocio.factoria;


import is.apptel.negocio.cliente.SACliente;
import is.apptel.negocio.cliente.SAClienteImp;
import is.apptel.negocio.habitacion.SAHabitacion;
import is.apptel.negocio.habitacion.SAHabitacionImp;
import is.apptel.negocio.reserva.SAReserva;
import is.apptel.negocio.reserva.SAReservaImp;
import is.apptel.negocio.servicio.SAServicio;
import is.apptel.negocio.servicio.SAServicioImp;

public class FactoriaSAImp extends FactoriaSA {

	private SACliente saCliente = null;
	private SAHabitacion saHabitacion = null;
	private SAServicio saServicio = null;
	private SAReserva saReserva = null;
	
	@Override
	public SACliente generaSACliente() {
		if(saCliente == null) {
			this.saCliente = new SAClienteImp();
		}
		
		return this.saCliente;
	}

	@Override
    public SAHabitacion generaSAHabitacion() {
        if(saHabitacion == null) {
            this.saHabitacion = new SAHabitacionImp();
        }

        return this.saHabitacion;
    }

    @Override
    public SAServicio generaSAServicio() {
		if(saServicio == null) {
			this.saServicio = new SAServicioImp();
		}
		
		return this.saServicio;
	}
    

	@Override
	public SAReserva generaSAReserva() {
		if(saReserva == null) {
			this.saReserva = new SAReservaImp();
		}
		
		return this.saReserva;
	}

}
