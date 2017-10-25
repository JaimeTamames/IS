package is.apptel.negocio.excepciones;

public class ServicioNotExists extends Exception {
	private String mensaje;

    public ServicioNotExists() {
        this.mensaje = "El servicio no existe.";
    }

    public String toString() {
        return this.mensaje;
    }
}
