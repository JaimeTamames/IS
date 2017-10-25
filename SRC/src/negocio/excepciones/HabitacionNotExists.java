package is.apptel.negocio.excepciones;

public class HabitacionNotExists extends Exception {

    private String mensaje;

    public HabitacionNotExists() {
        this.mensaje = "La habitacion no existe.";
    }

    public String toString() {
        return this.mensaje;
    }

}
