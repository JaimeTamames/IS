package is.apptel.negocio.excepciones;

public class HabitacionExistsException extends Exception {
    private int numero;

    public HabitacionExistsException(int numero) {
        this.numero = numero;
    }

    public String toString() {
        return "La habitacion numero: " + this.numero + " ya existe.";
    }
}
