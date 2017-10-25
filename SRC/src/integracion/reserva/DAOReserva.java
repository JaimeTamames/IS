package is.apptel.integracion.reserva;

import is.apptel.negocio.reserva.TReserva;

import java.util.ArrayList;

public interface DAOReserva {

    public void create(TReserva reserva);

    public void delete(int id);

    public TReserva read(int id);

    public ArrayList<TReserva> readAll();

    public ArrayList<TReserva> readAll(int idCliente);
    
    public void update(TReserva reserva);
    
}
