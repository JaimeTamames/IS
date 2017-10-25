package is.apptel.negocio.cliente;

public class TClienteEmpresa extends TCliente {

	private final int DESCUENTO = 20;

	public TClienteEmpresa() {}
	
	public TClienteEmpresa(int id, String nombre, String apellidos, String nif,
			String direccion, int telefono, String mail) {
		super(id, nombre, apellidos, nif, direccion, telefono, mail);
	}

    public TClienteEmpresa(String nombre, String apellidos, String nif,
                           String direccion, int telefono, String mail) {
        super(nombre, apellidos, nif, direccion, telefono, mail);
    }

	@Override
	public String toString() {
		return "TClienteEmpresa [DESCUENTO=" + DESCUENTO + ", getId()="
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
