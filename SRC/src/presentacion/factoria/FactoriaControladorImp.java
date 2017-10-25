package is.apptel.presentacion.factoria;

import is.apptel.presentacion.controlador.Controlador;
import is.apptel.presentacion.controlador.ControladorImp;

public class FactoriaControladorImp extends FactoriaControlador {

	private Controlador controlador = null;
	
	@Override
	public Controlador generaControlador() {
		if(this.controlador == null) {
			this.controlador = new ControladorImp();
		}
		
		return this.controlador;
	}
	
}
