package is.apptel.negocio.cliente;

public class TClienteParticular extends TCliente {

	private final int DESCUENTO = 10;

	public TClienteParticular() {}

	public TClienteParticular(int id, String nombre, String apellidos,
			String nif, String direccion, int telefono, String mail) {
		super(id, nombre, apellidos, nif, direccion, telefono, mail);
	}

    public TClienteParticular(String nombre, String apellidos,
                              String nif, String direccion, int telefono, String mail) {
        super(nombre, apellidos, nif, direccion, telefono, mail);
    }

	@Override
	public String toString() {
		return "TClienteParticular [DESCUENTO=" + DESCUENTO + ", getId()="
				+ getId() + ", getNombre()=" + getNombre()
				+ ", getApellidos()=" + getApellidos() + ", getNif()="
				+ getNif() + ", getDireccion()=" + getDireccion()
				+ ", getTelefono()=" + getTelefono() + ", getMail()="
				+ getMail() + "]";
	}
	public int getDescuento(){
		return DESCUENTO;
	}

}
