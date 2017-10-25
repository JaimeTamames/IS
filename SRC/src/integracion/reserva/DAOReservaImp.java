package is.apptel.integracion.reserva;

import is.apptel.integracion.cliente.DAOCliente;
import is.apptel.integracion.database.DAODatabase;
import is.apptel.integracion.factoria.FactoriaDAO;
import is.apptel.integracion.factoria.FactoriaDB;
import is.apptel.integracion.habitacion.DAOHabitacion;
import is.apptel.integracion.servicio.DAOServicio;
import is.apptel.negocio.cliente.TCliente;
import is.apptel.negocio.habitacion.THabitacion;
import is.apptel.negocio.reserva.TReserva;
import is.apptel.negocio.servicio.TServicio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DAOReservaImp implements DAOReserva {
    
	@Override
    public void create(TReserva reserva) {
    	DAODatabase daoDataBase = FactoriaDB.obtenerInstancia().generaDAODatabase();
    	
    	String query = "INSERT INTO RESERVAS (INICIO, FIN, IMPORTE) VALUES ("
    			+ "'" + new SimpleDateFormat("dd-MM-yyyy").format(reserva.getFecha_inicio()) + "', "
				+ "'" + new SimpleDateFormat("dd-MM-yyyy").format(reserva.getFecha_fin()) + "', "
				+ "" + reserva.getImporte() + ")";
    	daoDataBase.insert(query);
    	
    	query = "INSERT INTO RESERVA_CLIENTE (ID_RESERVA, ID_CLIENTE) VALUES (" + daoDataBase.getLastID("RESERVAS") + ", " + reserva.getCliente().getId() + ")";
    	daoDataBase.insert(query);
    	
    	if(reserva.getHabitaciones() != null) {
    		for (THabitacion habitacion : reserva.getHabitaciones()) {
        		query = "INSERT INTO RESERVA_HABITACION (ID_RESERVA, ID_HABITACION) VALUES (" + daoDataBase.getLastID("RESERVAS") + ", " + habitacion.getId() + ")";
        		daoDataBase.insert(query);
    		}
    	}
    	
    	if(reserva.getServicios() != null) {
    		for (TServicio servicio : reserva.getServicios()) {
        		query = "INSERT INTO RESERVA_SERVICIO (ID_RESERVA, ID_SERVICIO) VALUES (" + daoDataBase.getLastID("RESERVAS") + ", " + servicio.getId() + ")";
        		daoDataBase.insert(query);
    		}
    	}
    }

    @Override
    public void delete(int id) {
    	DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

        String query = "DELETE FROM RESERVAS WHERE ID=" + id;

        daoDatabase.delete(query);
        
        query = "DELETE FROM RESERVA_CLIENTE WHERE ID_RESERVA=" + id;
        
        daoDatabase.delete(query);
    }

    @Override
    public TReserva read(int id) {
    	DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();
    	DAOCliente daoCliente = FactoriaDAO.obtenerInstancia().generaDAOCliente();
    	DAOServicio daoServicio = FactoriaDAO.obtenerInstancia().generaDAOServicio();
    	DAOHabitacion daoHabitacion = FactoriaDAO.obtenerInstancia().generaDAOHabitacion();
    	
    	String query = "SELECT * FROM RESERVAS WHERE ID=" + id;
    	ResultSet resultSet = null;
    	TReserva reserva = new TReserva();
    	TCliente cliente = null;
        THabitacion habitacion = null;
    	ArrayList<THabitacion> habitaciones = new ArrayList<THabitacion>();
        TServicio servicio = null;
    	ArrayList<TServicio> servicios = new ArrayList<TServicio>();
    	
    	try {
			resultSet = daoDatabase.select(query);
			
			reserva.setId(resultSet.getInt("ID"));
			reserva.setFecha_inicio(new SimpleDateFormat("dd-MM-yyyy").parse(resultSet.getString("INICIO")));
            reserva.setFecha_fin(new SimpleDateFormat("dd-MM-yyyy").parse(resultSet.getString("FIN")));
            reserva.setImporte(resultSet.getDouble("IMPORTE"));
			
			query = "SELECT ID_CLIENTE FROM RESERVA_CLIENTE WHERE ID_RESERVA=" + reserva.getId();
			resultSet = daoDatabase.select(query);
			cliente = daoCliente.read(resultSet.getInt("ID_CLIENTE"));
			reserva.setCliente(cliente);
			
			query = "SELECT ID_HABITACION FROM RESERVA_HABITACION WHERE ID_RESERVA=" + reserva.getId();
			resultSet = daoDatabase.select(query);
            ArrayList<Integer> idHabitaciones = new ArrayList<Integer>();
            while(resultSet.next()) {
                idHabitaciones.add(resultSet.getInt("ID_HABITACION"));
            }
            for(int idHabitacion : idHabitaciones) {
                habitacion = daoHabitacion.read(idHabitacion);
                habitaciones.add(habitacion);
            }
			reserva.setHabitaciones(habitaciones);
			
			query = "SELECT ID_SERVICIO FROM RESERVA_SERVICIO WHERE ID_RESERVA=" + reserva.getId();
			resultSet = daoDatabase.select(query);
            ArrayList<Integer> idServicios = new ArrayList<Integer>();
            while(resultSet.next()) {
                idServicios.add(resultSet.getInt("ID_SERVICIO"));
            }
            for(int idServicio : idServicios) {
                servicio = daoServicio.read(idServicio);
                servicios.add(servicio);
            }
			reserva.setServicios(servicios);
		} catch (Exception e) {
			System.err.println(e);
		}
    	
    	return reserva;
    }

    @Override
    public ArrayList<TReserva> readAll() {
        DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

    	String query = "SELECT * FROM RESERVAS";
        ResultSet resultSet = null;
        ArrayList<Integer> idReservas = new ArrayList<Integer>();
        ArrayList<TReserva> reservas = new ArrayList<TReserva>();
        TReserva reserva = null;

        try {
            resultSet = daoDatabase.select(query);
            while(resultSet.next()) {
                idReservas.add(resultSet.getInt("ID"));
            }
            for(int id : idReservas) {
                reserva = read(id);
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return reservas;
    }

    @Override
	public ArrayList<TReserva> readAll(int idCliente) {
		DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();
        String query = "SELECT ID_RESERVA FROM RESERVA_CLIENTE WHERE ID_CLIENTE=" + idCliente;
        ResultSet resultSet = daoDatabase.select(query);
        ArrayList<Integer> idReservas = new ArrayList<Integer>();
        ArrayList<TReserva> reservas = new ArrayList<TReserva>();

        try {
            while(resultSet.next()) {
                idReservas.add(resultSet.getInt("ID_RESERVA"));
            }
            for(int idReserva : idReservas) {
                reservas.add(read(idReserva));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservas;
	}
    
    @Override
    public void update(TReserva reserva) {
    	DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

        String query = "UPDATE RESERVAS SET "
        		+ "INICIO='" + new SimpleDateFormat("dd-MM-yyyy").format(reserva.getFecha_inicio()) + "', "
				+ "FIN='" + new SimpleDateFormat("dd-MM-yyyy").format(reserva.getFecha_fin()) + "', "
				+ "IMPORTE=" + reserva.getImporte() + " "
				+ "WHERE ID=" + reserva.getId() + "";

        daoDatabase.update(query);
    }

	
}
