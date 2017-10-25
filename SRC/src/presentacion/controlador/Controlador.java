package is.apptel.presentacion.controlador;


public abstract class Controlador {
	private static Controlador instancia = null;
	public abstract void run();
	public abstract void accion(String evento , Object Datos) ;
	public static Controlador getInstancia(){
		if(instancia == null){
			instancia = new ControladorImp();
		}
		return instancia;
	}
	
}
