package is.apptel.presentacion.reserva;

import is.apptel.negocio.habitacion.THabitacion;
import is.apptel.negocio.reserva.TReserva;
import is.apptel.negocio.servicio.TServicio;
import is.apptel.presentacion.controlador.Controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.freixas.jcalendar.JCalendarCombo;

public class VModificarReserva extends JDialog {
	
	private LabelCalendar comboCalendarInicio;
	private LabelCalendar comboCalendarFin;
	private TReserva reserva;
	
	public VModificarReserva(VReserva res,TReserva reserva) {
		super(res, "Modificar Reserva",true);
		this.reserva = reserva;
		initGUI();
}
	public void initGUI(){
		JPanelPrincipal panel = new JPanelPrincipal();
		ImageIcon icon = new ImageIcon("res/img/Logo.png");
		this.setIconImage(icon.getImage());
		this.setContentPane(panel);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}	
	
	private class JPanelPrincipal extends JPanel {
		public JPanelPrincipal(){
			setLayout(new BorderLayout());
			panelLabels label = new panelLabels();
			panelbotones botones = new panelbotones();
			add(label,BorderLayout.NORTH);
			add(botones,BorderLayout.SOUTH);
		}
	}
	
	private class panelLabels extends JPanel {
		
		public panelLabels(){
			comboCalendarInicio = new  LabelCalendar("Fecha Inicio",reserva.getFecha_inicio());
			comboCalendarFin = new  LabelCalendar("Fecha Fin",reserva.getFecha_fin());
			
			this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			add(comboCalendarInicio);
			add(comboCalendarFin);
		}
	}
	
	private class LabelCalendar extends JPanel {
		private JCalendarCombo calendario;
		public LabelCalendar(String mensaje,Date Fecha) {
			setLayout(new FlowLayout());
			JLabel label = new JLabel(mensaje);
			calendario = new  JCalendarCombo();
			calendario.setPreferredSize(new Dimension(191, 25));
			calendario.setDate(Fecha);
			label.setPreferredSize(new Dimension(60, 25));
			add(label);
			add(calendario);

			calendario.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					
				}	
			});	
		}
		
	}
	
	
	private class panelbotones extends JPanel {
		
		public panelbotones(){
			JButton aceptar = new JButton("Aceptar");
			JButton cancelar = new JButton("Cancelar");
			cancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//esto es para cerrar la ventana
					VModificarReserva.this.setVisible(false);
					VModificarReserva.this.dispose();
				}
			});
			aceptar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Date today = new Date();
					Date fechaInicio = comboCalendarInicio.calendario.getDate();
					Date fechaFin = comboCalendarFin.calendario.getDate();
					final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
					int dias =(int) (( fechaFin.getTime() - fechaInicio.getTime() )/ MILLSECS_PER_DAY)+1;
					int diasToday =(int) (( fechaInicio.getTime() - today.getTime() )/ MILLSECS_PER_DAY)+1;
					if(dias >= 1 && diasToday >= 1){
					double importe = 0;
					for(THabitacion habitacion : reserva.getHabitaciones()){
						importe = dias * habitacion.getPrecio() + importe;
					}
					for(TServicio servicio : reserva.getServicios()){
						importe = servicio.getPrecio() + importe;
					}
					TReserva res = new TReserva(reserva.getId(),fechaInicio,fechaFin,reserva.getCliente(),reserva.getHabitaciones(),reserva.getServicios(),importe);
					Controlador c = Controlador.getInstancia();
					c.accion("modificarReserva",res);
					//esto es para cerrar la ventana
					VModificarReserva.this.setVisible(false);
					VModificarReserva.this.dispose();
				}
					else{
						
						JOptionPane.showMessageDialog(null, "Hay fechas incorrectas", "ERROR", JOptionPane.ERROR_MESSAGE);
							
					}
				}
				
			});
			setLayout(new FlowLayout());
			add (aceptar);
			add (cancelar);
		}
	}
	
}
