package is.apptel.negocio.servicio;

public class TServicio {

    private int id;
    private String nombre;
    private double precio;
    public TServicio(){} 
    public TServicio(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    public TServicio(int id ,String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double d) {
        this.precio = d;
    }
    @Override
    public String toString() {
        return "TSERVICIO{" +
                "id=" + id +
                ", nombre=" + nombre+
                ", precio=" + precio +
                '}';
    }
}
