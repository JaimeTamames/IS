package is.apptel.negocio.excepciones;

public class ClienteExistsException extends Exception {

    private String nif;

    public ClienteExistsException(String nif) {
        this.nif = nif;
    }

    public String toString() {
        return "El cliente con nif: " + this.nif + " ya existe.";
    }

}
