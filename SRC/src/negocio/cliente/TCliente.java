package is.apptel.negocio.cliente;

public abstract class TCliente {

	private int id;
	private String nombre;
	private String apellidos;
	private String nif;
	private String direccion;
	private int telefono;
	private String mail;

	public TCliente() {}

	public TCliente(int id, String nombre, String apellidos, String nif,
			String direccion, int telefono, String mail) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nif = nif;
		this.direccion = direccion;
		this.telefono = telefono;
		this.mail = mail;
	}

	public TCliente(String nombre, String apellidos, String nif, String direccion, int telefono, String mail) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nif = nif;
		this.direccion = direccion;
		this.telefono = telefono;
		this.mail = mail;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getNif() {
		return nif;
	}

	public String getDireccion() {
		return direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	public abstract int getDescuento();
	
}
