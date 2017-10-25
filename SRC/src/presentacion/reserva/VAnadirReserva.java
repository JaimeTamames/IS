package is.apptel.presentacion.reserva;

import is.apptel.negocio.cliente.TCliente;
import is.apptel.negocio.factoria.FactoriaSA;
import is.apptel.negocio.habitacion.SAHabitacion;
import is.apptel.negocio.habitacion.THabitacion;
import is.apptel.negocio.reserva.SAReserva;
import is.apptel.negocio.reserva.TReserva;
import is.apptel.negocio.servicio.SAServicio;
import is.apptel.negocio.servicio.TServicio;
import is.apptel.presentacion.controlador.Controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;
import org.freixas.jcalendar.JCalendarCombo;

public class VAnadirReserva extends JDialog {
	
	private TCliente cliente;
	private JScrollPane scroll;
	private JScrollPane scroll2;
	private JTable tabla;
	private JTable tabla2;
	private MiModelo modelo;
	private MiModelo modelo2;
	private JPanelPrincipal panel;

	private LabelCalendar comboCalendarInicio;
	private LabelCalendar comboCalendarFin;
	private THabitacion hab;
	private TServicio servicio;
	private ArrayList<TServicio> servicios;
	private ArrayList<THabitacion> habitacion;
	
		public VAnadirReserva(VReserva reserva) {
			super(reserva, "Anadir Reserva", true);
			
	}
		public void initGUI( TCliente cliente) {
			this.cliente = cliente;
			panel = new JPanelPrincipal();
			ImageIcon icon = new ImageIcon("res/img/Logo.png");
			this.setIconImage(icon.getImage());
			this.setContentPane(panel);
			this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);	
			this.setResizable(false);
			this.pack();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		}	
		
		public void ocultar(){
			this.setVisible(false);
		}
		
		private class JPanelPrincipal extends JPanel {
			private JPanelTablas tablas;
			public JPanelPrincipal(){
				setLayout(new BorderLayout());
				panelLabels label = new panelLabels();
				tablas = new JPanelTablas();
				panelbotones botones = new panelbotones();
				add(label,BorderLayout.NORTH);
				add(tablas, BorderLayout.CENTER);
				add(botones,BorderLayout.SOUTH);
			}

			public void pintarHabitaciones() {
				tablas.pintarHabitaciones();
				
			}
		}
		
		private class JPanelTablas extends JPanel{
			panelListaHab lista2;
			public JPanelTablas(){
				panelLista lista = new panelLista();
				lista2 = new panelListaHab();
				setLayout(new FlowLayout());
				lista.setBorder(BorderFactory.createTitledBorder("Servicios"));
				lista2.setBorder(BorderFactory.createTitledBorder("Habitaciones"));
				add(lista);
				add(lista2);
			}

			public void pintarHabitaciones() {
				this.remove(lista2);
				lista2 = new panelListaHab();
				lista2.setBorder(BorderFactory.createTitledBorder("Habitaciones"));
				add(lista2);
				this.revalidate();
				
			}
		}
		
		private class panelLabels extends JPanel {
			
			public panelLabels(){
				
				comboCalendarInicio = new  LabelCalendar("Fecha Inicio");
				comboCalendarFin = new  LabelCalendar("Fecha Fin");
				
				this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
				
				add(comboCalendarInicio);
				add(comboCalendarFin);
			}
		}
		
		private class panelLista extends JPanel {
			
			
			public panelLista() {
				tabla = new JTable();
		        modelo = new MiModelo();
		        scroll = new JScrollPane(tabla);
		        String[] columnas = {"Id ", "Nombre" , "Precio"};
		        
		        modelo.setColumnIdentifiers(columnas);
		        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		        tabla.setFillsViewportHeight(true); 
		        this.setTabla();
		        tabla.setModel(modelo);
		        this.setVisible(true);
		        tabla.setVisible(true);
				
				scroll.setPreferredSize(new Dimension(400, 250));
				scroll.setVisible(true);
				add(scroll);
				this.hideColumnIDTable();
				
			}
			private void hideColumnIDTable(){
				 
				    tabla.getColumnModel().getColumn(0).setMaxWidth(0);
				    tabla.getColumnModel().getColumn(0).setMinWidth(0);
				    tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
				    tabla.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
				}
			public void setTabla() {
				Controlador c = Controlador.getInstancia();
				c.accion("leerTodosServiciosReserva",null); 
				TServicio se;
				int tam = servicios.size();
				 for(int i = 0;i < tam;i++) {
					 se = servicios.get(i);
					 modelo.addRow( new Object[] {se.getId(), se.getNombre(), se.getPrecio()}); 
				 }
			}
		}
		private class panelListaHab extends JPanel {
			
			
			public panelListaHab() {
				tabla2 = new JTable();
		        modelo2 = new MiModelo();
		        scroll2 = new JScrollPane(tabla2);
		        String[] columnas = {"Id ", "Numero" , "Precio", "Tipo"};
		        
		        modelo2.setColumnIdentifiers(columnas);
		        scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		        scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		        tabla2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		        tabla2.setFillsViewportHeight(true); 
		        this.setTabla();
		        tabla2.setModel(modelo2);
		        this.setVisible(true);
		        tabla2.setVisible(true);
				
				scroll2.setPreferredSize(new Dimension(400, 250));
				scroll2.setVisible(true);
				add(scroll2);
				this.hideColumnIDTable();
				
			}
			private void hideColumnIDTable(){
				 
				    tabla2.getColumnModel().getColumn(0).setMaxWidth(0);
				    tabla2.getColumnModel().getColumn(0).setMinWidth(0);
				    tabla2.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
				    tabla2.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
				}
			public void setTabla() {
				ArrayList<Date> fechas = new ArrayList<Date>() ;
				fechas.add(comboCalendarInicio.calendario.getDate());
				fechas.add(comboCalendarFin.calendario.getDate());
				Controlador c = Controlador.getInstancia();
				c.accion("leerHabitacionDisponible",fechas); 
				
				THabitacion hab;
				int tam = habitacion.size();
				 for(int i = 0;i < tam;i++) {
					 hab = habitacion.get(i);
					 modelo2.addRow( new Object[] {hab.getId(), hab.getNumero(), hab.getPrecio(), hab.getTipo()}); 
				 }
			}
		}
		
		private class MiModelo extends DefaultTableModel {
			   public boolean isCellEditable (int row, int column) {
			       return false;
			   }
			}
		
		private class LabelCalendar extends JPanel {
			private JCalendarCombo calendario;
			public LabelCalendar(String mensaje) {
				setLayout(new FlowLayout());
				JLabel label = new JLabel(mensaje);
				calendario = new  JCalendarCombo();
				calendario.setPreferredSize(new Dimension(191, 25));
				label.setPreferredSize(new Dimension(60, 25));
				add(label);
				add(calendario);
				
				calendario.addDateListener(new DateListener(){
					
					public void dateChanged(DateEvent arg0) {
						VAnadirReserva.this.pintarHabitaciones();
						
					}	
				});	
			}
			
		}
		
		
		
		private class panelbotones extends JPanel {
			
			public panelbotones() {
				
				JButton aceptar = new JButton("Aceptar");
				JButton cancelar = new JButton("Cancelar");
				
				cancelar.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//esto es para cerrar la ventana
						VAnadirReserva.this.setVisible(false);
						VAnadirReserva.this.dispose();
					}
				});
				
				aceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try{
							Date today = new Date();
							Date fechaInicio = comboCalendarInicio.calendario.getDate();
							Date fechaFin = comboCalendarFin.calendario.getDate();
							
							final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
							int dias =(int) (( fechaFin.getTime() - fechaInicio.getTime() )/ MILLSECS_PER_DAY)+1;
							int diasToday =(int) (( fechaInicio.getTime() - today.getTime() )/ MILLSECS_PER_DAY)+1;
							
							if(dias >= 1 && diasToday >= 1){
								ArrayList<TServicio> servicios  = new ArrayList<TServicio>();
								ArrayList<THabitacion> habitaciones = new ArrayList<THabitacion>();
								int[] rows = tabla.getSelectedRows();
								double precio = 0;
								for(int i = 0; i < rows.length; i++){
									Controlador c = Controlador.getInstancia();
									c.accion("leerServicio",(int) modelo.getValueAt(rows[i], 0));
									servicios.add(servicio);
									precio = servicio.getPrecio() + precio;
								}
							
								rows = tabla2.getSelectedRows();
								if(rows.length == 0){
									JOptionPane.showMessageDialog(null, "No has seleccionado ninguna habitacion", "ERROR", JOptionPane.ERROR_MESSAGE);
								}
								else{
									for(int i = 0; i < rows.length; i++){
										Controlador c = Controlador.getInstancia();
										c.accion("leerHabitacion",(int) modelo2.getValueAt(rows[i], 0));
										habitaciones.add(hab);
										precio = hab.getPrecio()*dias + precio;
										}
						
									TReserva treserva = new TReserva(fechaInicio, fechaFin, cliente, habitaciones, servicios, precio);
						
									Controlador c = Controlador.getInstancia();
									c.accion("crearReserva",treserva);
						
									VAnadirReserva.this.setVisible(false);
									VAnadirReserva.this.dispose();
								}
							}
							else{
								
								JOptionPane.showMessageDialog(null, "Hay fechas incorrectas", "ERROR", JOptionPane.ERROR_MESSAGE);
									
							}
						}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Hay datos incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
					
				});
				setLayout(new FlowLayout());
				add (aceptar);
				add (cancelar);
			}
		}

		public void setHabitacion(THabitacion read) {
			hab = read;
			
		}
		protected void pintarHabitaciones() {
			panel.pintarHabitaciones();
			
		}
		public void setServicio(TServicio read) {
			servicio = read;
			
		}
		public void setTablaServicio(ArrayList<TServicio> servicios) {
			this.servicios = servicios;
			
		}
		public void setHabitacionesDisponibles(ArrayList<THabitacion> habitacion) {
			this.habitacion = habitacion;
			
		}
}
