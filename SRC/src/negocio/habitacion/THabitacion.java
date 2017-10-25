package is.apptel.negocio.habitacion;

public class THabitacion {

    private int id;
    private int numero;
    private String tipo;
    private Double precio;

    public THabitacion() {}

    public THabitacion(int numero, String tipo, Double precio) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
    }

    public THabitacion(int id, int numero, String tipo, double precio) {
    	this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.id = id;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "THabitacion{" +
                "id=" + id +
                ", numero=" + numero +
                ", tipo='" + tipo + '\'' +
                ", precio=" + precio +
                '}';
    }
}
