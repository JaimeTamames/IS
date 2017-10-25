package is.apptel.integracion.habitacion;

import is.apptel.integracion.database.DAODatabase;
import is.apptel.integracion.factoria.FactoriaDB;
import is.apptel.negocio.excepciones.HabitacionExistsException;
import is.apptel.negocio.excepciones.HabitacionNotExists;
import is.apptel.negocio.habitacion.THabitacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DAOHabitacionImp implements DAOHabitacion {
    @Override
    public void create(THabitacion habitacion) {
        DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();
        try {
            checkExists(habitacion.getNumero());

            String query = "INSERT INTO HABITACIONES " +
                    "(NUMERO, TIPO, PRECIO) VALUES (" +
                    "" + habitacion.getNumero() + ", " +
                    "'" + habitacion.getTipo() + "', " +
                    "" + habitacion.getPrecio() + " )";

            daoDatabase.insert(query);
        } catch (HabitacionExistsException e) {
            System.err.println(e);
        }
    }

    @Override
    public void delete(int id) {
        DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

        String query = "DELETE FROM HABITACIONES WHERE ID=" + id;

        daoDatabase.delete(query);
    }

    @Override
    public THabitacion read(int id) {
        DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

        String query = "SELECT * FROM HABITACIONES WHERE ID=" + id;

        THabitacion habitacion = null;

        try {
            ResultSet resultSet = daoDatabase.select(query);

            if (!resultSet.next()) {
                throw new HabitacionNotExists();
            }

            habitacion = new THabitacion();
            habitacion.setId(resultSet.getInt("ID"));
            habitacion.setNumero(resultSet.getInt("NUMERO"));
            habitacion.setTipo(resultSet.getString("TIPO"));
            habitacion.setPrecio(resultSet.getDouble("PRECIO"));
        } catch (SQLException e) {
            System.err.println(e);
        } catch (HabitacionNotExists e) {
            System.err.println(e);
        }

        return habitacion;
    }

    @Override
    public THabitacion readByNumero(int numero) {
        DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

        String query = "SELECT * FROM HABITACIONES WHERE NUMERO=" + numero;

        THabitacion habitacion = null;

        try {
            ResultSet resultSet = daoDatabase.select(query);

            if (!resultSet.next()) {
                throw new HabitacionNotExists();
            }

            habitacion = new THabitacion();
            habitacion.setId(resultSet.getInt("ID"));
            habitacion.setNumero(resultSet.getInt("NUMERO"));
            habitacion.setTipo(resultSet.getString("TIPO"));
            habitacion.setPrecio(resultSet.getDouble("PRECIO"));
        } catch (SQLException e) {
            System.err.println(e);
        } catch (HabitacionNotExists e) {
            System.err.println(e);
        }

        return habitacion;
    }

    @Override
    public ArrayList<THabitacion> readAll() {
        DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

        String query = "SELECT * FROM HABITACIONES";

        THabitacion habitacion = null;

        ArrayList<THabitacion> habitaciones = new ArrayList<THabitacion>();

        try {
            ResultSet resultSet = daoDatabase.select(query);

            while (resultSet.next()) {
                habitacion = new THabitacion();
                habitacion.setId(resultSet.getInt("ID"));
                habitacion.setNumero(resultSet.getInt("NUMERO"));
                habitacion.setTipo(resultSet.getString("TIPO"));
                habitacion.setPrecio(resultSet.getDouble("PRECIO"));

                habitaciones.add(habitacion);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return habitaciones;
    }

    @Override
    public void update(THabitacion habitacion) {
        DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

        String query = "UPDATE HABITACIONES SET " +
                "NUMERO=" + habitacion.getNumero() + ", " +
                "TIPO='" + habitacion.getTipo() + "', " +
                "PRECIO=" + habitacion.getPrecio() + " " +
                "WHERE ID=" + habitacion.getId();

        daoDatabase.update(query);
    }

    @Override
    public void checkExists(int numero) throws HabitacionExistsException {
        THabitacion habitacion1 = readByNumero(numero);

        if(habitacion1 != null) {
            throw new HabitacionExistsException(numero);
        }
    }

}
