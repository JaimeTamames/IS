package is.apptel.integracion.cliente;

import is.apptel.integracion.database.DAODatabase;
import is.apptel.integracion.factoria.FactoriaDB;
import is.apptel.negocio.cliente.TCliente;
import is.apptel.negocio.cliente.TClienteEmpresa;
import is.apptel.negocio.cliente.TClienteParticular;
import is.apptel.negocio.excepciones.ClienteExistsException;
import is.apptel.negocio.excepciones.ClienteNotExists;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOClienteImp implements DAOCliente {

	@Override
	public void create(TCliente cliente) {
        DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();
        String tipo;

        try {
            checkExists(cliente.getNif());

            if(cliente instanceof TClienteEmpresa) {
                tipo = "EMPRESA";
            } else {
                tipo = "PARTICULAR";
            }

            String query = "INSERT INTO CLIENTES " +
                    "(NOMBRE, APELLIDOS, NIF, DIRECCION, TELEFONO, MAIL, TIPO) VALUES (" +
                    "'" + cliente.getNombre() + "', " +
                    "'" + cliente.getApellidos() + "', " +
                    "'" + cliente.getNif() + "', " +
                    "'" + cliente.getDireccion() + "', " +
                    "" + cliente.getTelefono() + ", " +
                    "'" + cliente.getMail() + "', " +
                    "'" + tipo + "')";


            daoDatabase.insert(query);
        } catch (ClienteExistsException e) {
            System.err.println(e);
        }
    }

	@Override
	public void delete(int idCliente) {
        DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

        String query = "DELETE FROM CLIENTES WHERE ID=" + idCliente;

        daoDatabase.delete(query);
	}

	@Override
	public TCliente read(int idCliente) {
		DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

        String query = "SELECT * FROM CLIENTES WHERE ID=" + idCliente;
        TCliente cliente = null;

        try {
            ResultSet resultSet = daoDatabase.select(query);
            if(!resultSet.next()) {
                throw new ClienteNotExists();
            }

            if(resultSet.getString("TIPO").compareToIgnoreCase("EMPRESA") == 0) {
                cliente = new TClienteEmpresa();
            } else {
                cliente = new TClienteParticular();
            }

            cliente.setId(resultSet.getInt("ID"));
            cliente.setNombre(resultSet.getString("NOMBRE"));
            cliente.setApellidos(resultSet.getString("APELLIDOS"));
            cliente.setNif(resultSet.getString("NIF"));
            cliente.setDireccion(resultSet.getString("DIRECCION"));
            cliente.setTelefono(resultSet.getInt("TELEFONO"));
            cliente.setMail(resultSet.getString("MAIL"));
        } catch (SQLException e) {
            System.err.println(e);
        } catch (ClienteNotExists e) {
            System.err.println(e);
        }

        return cliente;
	}

	@Override
	public TCliente read(String nif) {
        DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

        String query = "SELECT * FROM CLIENTES WHERE NIF='" + nif + "'";
        TCliente cliente = null;

        try {
            ResultSet resultSet = daoDatabase.select(query);
            if(!resultSet.next()) {
                throw new ClienteNotExists();
            }

            if(resultSet.getString("TIPO").compareToIgnoreCase("EMPRESA") == 0) {
                cliente = new TClienteEmpresa();
            } else {
                cliente = new TClienteParticular();
            }

            cliente.setId(resultSet.getInt("ID"));
            cliente.setNombre(resultSet.getString("NOMBRE"));
            cliente.setApellidos(resultSet.getString("APELLIDOS"));
            cliente.setNif(resultSet.getString("NIF"));
            cliente.setDireccion(resultSet.getString("DIRECCION"));
            cliente.setTelefono(resultSet.getInt("TELEFONO"));
            cliente.setMail(resultSet.getString("MAIL"));
        } catch (SQLException e) {
            System.err.println(e);
        } catch (ClienteNotExists e) {
            System.err.println(e);
        }

        return cliente;
	}

	@Override
	public ArrayList<TCliente> readAll() {
        DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();

        String query = "SELECT * FROM CLIENTES";

        TCliente cliente = null;
        ArrayList<TCliente> clientes = new ArrayList<TCliente>();

        try {
            ResultSet resultSet = daoDatabase.select(query);
            if(!resultSet.isBeforeFirst()) {
                throw new ClienteNotExists();
            }

            while(resultSet.next()) {
                if(resultSet.getString("TIPO").compareToIgnoreCase("EMPRESA") == 0) {
                    cliente = new TClienteEmpresa();
                } else {
                    cliente = new TClienteParticular();
                }

                cliente.setId(resultSet.getInt("ID"));
                cliente.setNombre(resultSet.getString("NOMBRE"));
                cliente.setApellidos(resultSet.getString("APELLIDOS"));
                cliente.setNif(resultSet.getString("NIF"));
                cliente.setDireccion(resultSet.getString("DIRECCION"));
                cliente.setTelefono(resultSet.getInt("TELEFONO"));
                cliente.setMail(resultSet.getString("MAIL"));

                clientes.add(cliente);
            }
        } catch (SQLException e) {
           System.err.println(e);
        } catch (ClienteNotExists e) {
           System.err.println(e);
        }

        return clientes;
	}

	@Override
	public void update(TCliente cliente) {
		DAODatabase daoDatabase = FactoriaDB.obtenerInstancia().generaDAODatabase();
        
        String query = "UPDATE CLIENTES SET "
        		+ "NOMBRE='"+cliente.getNombre()+"', "
				+ "APELLIDOS='"+cliente.getApellidos()+"', "
				+ "NIF='"+cliente.getNif()+"', "
				+ "DIRECCION='"+cliente.getDireccion()+"', "
				+ "TELEFONO="+cliente.getTelefono()+", "
				+ "MAIL='"+cliente.getMail()+"' "
				+ "WHERE ID=" + cliente.getId();

        daoDatabase.update(query);
	}

    @Override
    public void checkExists(String nif) throws ClienteExistsException {
        TCliente cliente1 = read(nif);

        if(cliente1 != null) {
            throw new ClienteExistsException(nif);
        }
    }

}
