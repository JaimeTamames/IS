package is.apptel.negocio.excepciones;

public class ServicioExistsException extends Exception {
	private String nombre;

	    public ServicioExistsException(String nombre) {
	        this.nombre = nombre;
	    }

	    public String toString() {
	        return "El servicio " + this.nombre + " ya existe.";
	    }

}
