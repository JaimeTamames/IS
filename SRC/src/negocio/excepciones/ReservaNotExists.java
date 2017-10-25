package is.apptel.negocio.excepciones;

public class ReservaNotExists extends Exception {
	private String mensaje;

    public ReservaNotExists() {
        this.mensaje = "La reserva no existe.";
    }

    public String toString() {
        return this.mensaje;
    }

}
