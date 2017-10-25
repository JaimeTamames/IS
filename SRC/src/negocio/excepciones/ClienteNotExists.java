package is.apptel.negocio.excepciones;

public class ClienteNotExists extends Exception {

    private String mensaje;

    public ClienteNotExists() {
        this.mensaje = "El cliente no existe.";
    }

    public String toString() {
        return this.mensaje;
    }

}
