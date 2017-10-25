package is.apptel.presentacion.reserva;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import is.apptel.negocio.cliente.TCliente;
import is.apptel.negocio.habitacion.THabitacion;
import is.apptel.negocio.reserva.TReserva;
import is.apptel.negocio.servicio.TServicio;
import is.apptel.presentacion.Main;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DatosReserva extends JDialog{
	private TReserva reserva;
	private JPanelPrincipal panel;
	private JScrollPane scroll;
	private JScrollPane scroll2;
	private JTable tabla;
	private JTable tabla2;
	private MiModelo modelo;
	private MiModelo modelo2;
	
	public DatosReserva(VReserva frame,TReserva reserva){
		super(frame, "Datos de la Reserva",true);
		this.reserva = reserva;
		initGUI();
	}
	public void initGUI() {
			panel = new JPanelPrincipal();
			ImageIcon icon = new ImageIcon(Main.class.getResource("/img/Logo.png"));
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
				panelBotones botones = new panelBotones();
				JPanelTablas tablas = new JPanelTablas();
				JPanelDatos datos = new JPanelDatos();
				add(datos,BorderLayout.NORTH);
				add(botones, BorderLayout.SOUTH);
				add(tablas, BorderLayout.CENTER);
			
			}
		}

		private class JPanelDatos extends JPanel{
			public JPanelDatos(){
				panelLabels label1 = new panelLabels("Fecha de Inicio",new SimpleDateFormat("dd-MM-yyyy").format(reserva.getFecha_inicio()));
				panelLabels label2 = new panelLabels("Fecha de Fin",new SimpleDateFormat("dd-MM-yyyy").format(reserva.getFecha_fin()));
				panelLabels label3 = new panelLabels("Importe",String.valueOf(reserva.getImporte()) + "\u20AC");
				TCliente cliente = reserva.getCliente();
				panelLabels label4 = new panelLabels("Nombre del Cliente",cliente.getNombre());
				panelLabels label5 = new panelLabels("Apellidos del Cliente",cliente.getApellidos());
				panelLabels label6 = new panelLabels("NIF del Cliente",cliente.getNif());
				setLayout(new GridLayout(3,2));
				add(label1);
				add(label4);
				add(label2);
				add(label5);
				add(label3);
				add(label6);
			}
			
		}
		
		private class JPanelTablas extends JPanel{
			public JPanelTablas(){
				panelLista lista = new panelLista();
				panelListaHab lista2 = new panelListaHab();
				lista.setBorder(BorderFactory.createTitledBorder("Servicios"));
				lista2.setBorder(BorderFactory.createTitledBorder("Habitaciones"));
				setLayout(new FlowLayout());
				
				add(lista);
				add(lista2);
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
				ArrayList<TServicio> servicio = reserva.getServicios() ;
				TServicio se;
				int tam = servicio.size();
				 for(int i = 0;i < tam;i++) {
					 se = servicio.get(i);
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
				ArrayList<THabitacion> habitacion = reserva.getHabitaciones();
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
		private class panelBotones extends JPanel {
			
			public panelBotones() {
				
				JButton aceptar = new JButton("Aceptar");
				
				aceptar.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//esto es para cerrar la ventana
						DatosReserva.this.setVisible(false);
						DatosReserva.this.dispose();
					}
				});
				add(aceptar);
			}
	}
		private class panelLabels extends JPanel{
			public panelLabels(String mensaje,String dato){
				JLabel msg = new JLabel(mensaje);
				JLabel data = new JLabel(dato);
				setLayout(new FlowLayout());
				msg.setPreferredSize(new Dimension(100,15));
				data.setPreferredSize(new Dimension(200,15));
				add (msg);
				add (data);
			}
		}
		
}
