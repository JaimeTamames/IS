package is.apptel.integracion.database;


import java.sql.ResultSet;

public interface DAODatabase {

    public void up();

    public ResultSet select(String query);

    public void insert(String query);

    public void delete(String query);

    public void update(String query);

    public void createTable(String query);
    
    public int getLastID(String table);

}
