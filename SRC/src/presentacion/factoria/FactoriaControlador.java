package is.apptel.presentacion.factoria;

import is.apptel.presentacion.controlador.Controlador;

public abstract class FactoriaControlador {
	
	private static FactoriaControlador factoria = null;
	
	public static FactoriaControlador obtenerInstancia() {
		if(factoria == null) {
			factoria = new FactoriaControladorImp();
		}
		
		return factoria;
	}
	
	public abstract Controlador generaControlador();
	
}
