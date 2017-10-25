package is.apptel.negocio.reserva;

import java.util.ArrayList;

public interface SAReserva {

    public void create(TReserva reserva);

    public void delete(int id);

    public TReserva read(int id);

    public ArrayList<TReserva> readAll();

    public ArrayList<TReserva> readAll(int idCliente);

    public void update(TReserva reserva);
    
    public void generarFactura(int idReserva);
    
    public void verFactura(int idReserva);
    
}
