package is.apptel.presentacion.cliente;

import is.apptel.negocio.cliente.SACliente;
import is.apptel.negocio.cliente.TCliente;
import is.apptel.negocio.factoria.FactoriaSA;
import is.apptel.negocio.habitacion.THabitacion;
import is.apptel.negocio.reserva.TReserva;
import is.apptel.negocio.servicio.TServicio;
import is.apptel.presentacion.Main;
import is.apptel.presentacion.controlador.Controlador;
import is.apptel.presentacion.controlador.ControladorImp;
import is.apptel.presentacion.habitacion.VHabitacion;
import is.apptel.presentacion.reserva.VReserva;
import is.apptel.presentacion.servicio.VServicio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class VCliente extends JFrame {
	
	private JPanelPrincipal panel;
	private JScrollPane scroll;
	private JTable tabla;
	private MiModelo modelo;
	private TCliente cliente;
	private VHabitacion vHabitacion;
	private VServicio vServicio;
	private VReserva vReserva;
	
	public VCliente() {
		super("Clientes");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vHabitacion = new VHabitacion(this);
		vServicio = new VServicio(this);
		vReserva = new VReserva(this);
		
	}
	
	public void initGUI() {
		panel = new JPanelPrincipal();
		Controlador c = Controlador.getInstancia();
		c.accion("leerTodosClientes",null);
		ImageIcon appIcon = new ImageIcon(Main.class.getResource("/img/Logo.png"));
		this.setIconImage(appIcon.getImage());
		this.setContentPane(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}	
	private class MiModelo extends DefaultTableModel {
		   public boolean isCellEditable (int row, int column) {
		       return false;
		   }
		}
	private class JPanelPrincipal extends JPanel {
		private panelLista lista;
		public JPanelPrincipal(){
			setLayout(new BorderLayout());
			panelBotones botones = new panelBotones();
			lista = new panelLista();
			JButtonExit exit = new JButtonExit();
			add(botones, BorderLayout.NORTH);
			add(lista, BorderLayout.CENTER);
			add(exit,BorderLayout.SOUTH);
			
		}
		public void pintartabla() {
			this.remove(lista);
			lista = new panelLista();
			Controlador c = Controlador.getInstancia();
			c.accion("leerTodosClientes",null);
			add(lista, BorderLayout.CENTER);
			this.revalidate();
			
		}
		public void setTabla(ArrayList<TCliente> clientes) {
			lista.setTabla(clientes);
			
		}
	}
	
	private class panelBotones extends JPanel {
		public panelBotones() {
			JButton anadir = new JButton("Crear Cliente");
			JButton modificar = new JButton("Modificar Cliente");
			JButton eliminar = new JButton("Eliminar Cliente");
			ImageIcon trashIcon = new ImageIcon(Main.class.getResource("/img/trash.png"));
			eliminar.setIcon(trashIcon);
			ImageIcon updateIcon = new ImageIcon(Main.class.getResource("/img/update.gif"));
			modificar.setIcon(updateIcon);
			ImageIcon nuevoIcon = new ImageIcon(Main.class.getResource("/img/add.png"));
			anadir.setIcon(nuevoIcon);
			setLayout(new FlowLayout());
			
			anadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					desaparecer();
					new VAnadirCliente(VCliente.this);
					VCliente.this.pintartabla();
					aparecer();
					
				}
			});
			modificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tabla.getSelectedRow() != -1){
					int id = (int) modelo.getValueAt(tabla.getSelectedRow(), 0);
					
					Controlador c = Controlador.getInstancia();
					c.accion("leerCliente", id);
					desaparecer();
					new VModificarCliente(VCliente.this,cliente);
					VCliente.this.pintartabla();
					}
					else
						JOptionPane.showMessageDialog(null, "No has seleccionado ningun cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
					aparecer();
				}
			});
					eliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(tabla.getSelectedRow() != -1){
								if(JOptionPane.showConfirmDialog(null, "Desea eliminar?", "Eliminar", JOptionPane.YES_NO_OPTION) == 0){
							Controlador c = Controlador.getInstancia();
							c.accion("eliminarCliente", modelo.getValueAt(tabla.getSelectedRow(), 0));
							VCliente.this.pintartabla();
								}
							}
							else
								JOptionPane.showMessageDialog(null, "No has seleccionado ningun cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
							
					
				}
			});
					JButton reservasCliente = new JButton("Reservas del Cliente");
					reservasCliente.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							desaparecer();
		                	if(tabla.getSelectedRow() != -1){	
		                		int id = (int) modelo.getValueAt(tabla.getSelectedRow(), 0);
								Controlador c = Controlador.getInstancia();
								c.accion("leerCliente", id);
								vReserva.initGUI(cliente);
								
							}
							else
								JOptionPane.showMessageDialog(null, "No has seleccionado ningun cliente", "ERROR", JOptionPane.ERROR_MESSAGE);			     
		                	aparecer(); 
						}
					});
			reservasCliente.setPreferredSize(new Dimension(160,40));
			add(anadir);
			add(modificar);
			add(eliminar);
			add(reservasCliente);
		}
	}
	
	private class panelLista extends JPanel {
		
		
		public panelLista() {
			tabla = new JTable();
	        modelo = new MiModelo();
	        scroll = new JScrollPane(tabla);
	        String[] columnas = { "Id","Nombre" , "Apellidos","NIF","Direccion","Telefono","Mail"};
	        
	        modelo.setColumnIdentifiers(columnas);
	        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	        tabla.setFillsViewportHeight(true); 
	        tabla.setModel(modelo);
	        this.setVisible(true);
	        tabla.setVisible(true);		
			scroll.setPreferredSize(new Dimension(800, 250));
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
		
		public void setTabla(ArrayList<TCliente> clientes) {
			TCliente cl;
			int tam = clientes.size();
			 for(int i = 0;i < tam;i++) {
				 cl = clientes.get(i);
				 modelo.addRow( new Object[] {cl.getId(), cl.getNombre(), cl.getApellidos(), cl.getNif(),cl.getDireccion(),cl.getTelefono(),cl.getMail()}); 
			 }
		}
		
		
}
	
	private class JButtonExit extends JPanel {
		private JButton Exit;
		public JButtonExit(){			
			Exit = new JButton("Salir");
			setLayout(new BorderLayout());
			ImageIcon exitIcon = new ImageIcon(Main.class.getResource("/img/exit.png"));
			Exit.setIcon(exitIcon); 
			Exit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(JOptionPane.showConfirmDialog(null, "Desea salir?", "EXIT", JOptionPane.YES_NO_OPTION) == 0)
					System.exit(0);
				}
			});
			panelVentana v = new panelVentana();
			add (v,BorderLayout.CENTER);
			add(Exit,BorderLayout.EAST);
		}
	}
	
	private class panelVentana extends JPanel {
		public panelVentana(){
			JButton habitacion = new JButton("Habitaciones");
			habitacion.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					desaparecer();
					vHabitacion.initGUI();
					aparecer();
				}
			});
			JButton servicios = new JButton("Servicios");
			servicios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					desaparecer();
					vServicio.initGUI();
					aparecer();
					
				}
			});
			JButton reservas = new JButton("Reservas");
			reservas.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					desaparecer();
					vReserva.initGUI();
					aparecer();
				}
			});
			
			
			habitacion.setPreferredSize(new Dimension(100,40));
			servicios.setPreferredSize(new Dimension(100,40));
			reservas.setPreferredSize(new Dimension(100,40));
		
			this.setLayout(new FlowLayout());
			
			add(habitacion);
			add(servicios);
			add(reservas);
			
		}
	} 
		                
	private void desaparecer() {
		this.setState(Frame.ICONIFIED);
	}
		


	protected void pintartabla() {
		panel.pintartabla();
		
	}

	private void aparecer() {
		this.setState(Frame.NORMAL);
		
	}

	public void setCliente(TCliente read) {
		cliente = read;
		
	}

	public void setTabla(ArrayList<TCliente> clientes) {
		panel.setTabla(clientes);
		
	}

	public void setHabitacion(THabitacion read) {
		vHabitacion.setHabitacion(read);
		vReserva.setHabitacion(read);
		
	}

	public void setTablaHabitaciones(ArrayList<THabitacion> habitaciones) {
		vHabitacion.setTabla(habitaciones);
		
	}

	public void setTablaServicios(ArrayList<TServicio> servicios) {
		vServicio.setTabla(servicios);
		
		
	}

	public void setServicio(TServicio read) {
		vServicio.setServicio(read);
		vReserva.setServicio(read);
		
	}

	public void setTablaReservas(ArrayList<TReserva> reservas) {
		vReserva.setTabla(reservas);
		
	}

	public void setReserva(TReserva reserva) {
		vReserva.setReserva(reserva);
		
	}

	public void setHabitacionesDisponibles(ArrayList<THabitacion> habitacion) {
		vReserva.setHabitacionesDisponibles(habitacion);
		
	}

	public void setTablaServiciosReserva(ArrayList<TServicio> servicios) {
		vReserva.setTablaServicio(servicios);
		
	}

	
}
