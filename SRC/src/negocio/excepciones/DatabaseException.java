package is.apptel.negocio.excepciones;

public class DatabaseException extends Exception {

    private String error;

    public DatabaseException(String error) {
        this.error = error;
    }

    public String toString() {
        return "Database error: " + this.error;
    }

}
