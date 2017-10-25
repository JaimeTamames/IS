package is.apptel.negocio.reserva;

import is.apptel.integracion.factoria.FactoriaDAO;
import is.apptel.integracion.habitacion.DAOHabitacion;
import is.apptel.integracion.reserva.DAOReserva;
import is.apptel.negocio.cliente.TClienteParticular;
import is.apptel.negocio.habitacion.THabitacion;
import is.apptel.negocio.servicio.TServicio;
import is.apptel.presentacion.Main;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.ImageIcon;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.parser.Line;

public class SAReservaImp implements SAReserva {

	@Override
	public void create(TReserva reserva) {
		DAOReserva daoReserva = FactoriaDAO.obtenerInstancia().generaDAOReserva();
        daoReserva.create(reserva);
	}

	@Override
	public void delete(int id) {
		DAOReserva daoReserva = FactoriaDAO.obtenerInstancia().generaDAOReserva();
		daoReserva.delete(id);
	}

	@Override
	public TReserva read(int id) {
		DAOReserva daoReserva = FactoriaDAO.obtenerInstancia().generaDAOReserva();
		return daoReserva.read(id);
	}

	@Override
	public ArrayList<TReserva> readAll() {
		DAOReserva daoReserva = FactoriaDAO.obtenerInstancia().generaDAOReserva();
		return daoReserva.readAll();
	}

	@Override
	public ArrayList<TReserva> readAll(int idCliente) {
		DAOReserva daoReserva = FactoriaDAO.obtenerInstancia().generaDAOReserva();
		return  daoReserva.readAll(idCliente);
	}

	@Override
	public void update(TReserva reserva) {
		DAOReserva daoReserva = FactoriaDAO.obtenerInstancia().generaDAOReserva();
		daoReserva.update(reserva);
	}

	@Override
	public void generarFactura(int idReserva) {
		
		Document document = new Document();
		FileOutputStream pdf;
		TReserva reserva = read(idReserva);
		double importeTotal = reserva.getImporte();
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al d�a 
		int noches =(int) (( reserva.getFecha_fin().getTime() - reserva.getFecha_inicio().getTime() )/ MILLSECS_PER_DAY)+1;
	
		int descuento = reserva.getCliente().getDescuento();
		double descuentoEuros = importeTotal*descuento/100;
		
		
		try {
			pdf = new FileOutputStream("F_" + reserva.getId() + ".pdf");
			PdfWriter.getInstance(document, pdf).setInitialLeading(20);
			LineSeparator line = new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -2);
			
			document.open();
			Image logo = Image.getInstance(Main.class.getResource("/img/logo_big.png"));
			logo.setAlignment(Chunk.ALIGN_MIDDLE);
			document.add(logo);
			document.add(new Paragraph("Factura para reserva numero: " + reserva.getId()));
			document.add(new Paragraph("NIF: " + reserva.getCliente().getNif()));
			document.add(new Paragraph("Cliente: " + reserva.getCliente().getNombre() + " " + reserva.getCliente().getApellidos()));
			document.add(new Paragraph("Direccion: " + reserva.getCliente().getDireccion()));
			document.add(new Paragraph("Telefono: " + reserva.getCliente().getTelefono()));
			document.add(Chunk.NEWLINE);
			document.add(new Paragraph("Lista de habitaciones"));
			document.add(line);
			for(THabitacion habitacion : reserva.getHabitaciones()) {
				Paragraph parrafo = new Paragraph();
				parrafo.setAlignment(Element.ALIGN_RIGHT);
				parrafo.add("Numero: " + habitacion.getNumero() + " Tipo: " + habitacion.getTipo() + " Noches: "+ noches +" Importe: " + habitacion.getPrecio() + "\u20AC" + "/Noche");
				document.add(parrafo);
			}
			document.add(Chunk.NEWLINE);
			document.add(new Paragraph("Lista de servicios"));
			document.add(line);
			for(TServicio servicio : reserva.getServicios()) {
				Paragraph parrafo = new Paragraph();
				parrafo.setAlignment(Element.ALIGN_RIGHT);
				parrafo.add("Concepto: " + servicio.getNombre() + " Importe: " + servicio.getPrecio() + "\u20AC" );
				document.add(parrafo);
			}
			document.add(Chunk.NEWLINE);
			document.add(new Paragraph("IMPORTE TOTAL: " + importeTotal + "\u20AC"));
			importeTotal = importeTotal - descuentoEuros;
			document.add(new Paragraph("% "+"DESCUENTO "+": " + descuento + "%"));
			document.add(new Paragraph("\u20AC"+" DESCUENTO "+": " + descuentoEuros + "\u20AC"));
			document.add(new Paragraph("IMPORTE TOTAL CON DESCUENTO: " + importeTotal + "\u20AC"));
			document.add(new Paragraph("IMPORTE TOTAL IVA INC: " + (importeTotal + (importeTotal*0.21)) + "\u20AC"));
			document.close();
		} catch(DocumentException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void verFactura(int idReserva) {
		File factura = new File("F_" + idReserva + ".pdf");
		
		try {
			Desktop.getDesktop().open(factura);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
