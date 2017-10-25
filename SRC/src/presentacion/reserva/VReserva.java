package is.apptel.presentacion.reserva;

import is.apptel.negocio.cliente.TCliente;
import is.apptel.negocio.habitacion.THabitacion;
import is.apptel.negocio.reserva.TReserva;
import is.apptel.negocio.servicio.TServicio;
import is.apptel.presentacion.Main;
import is.apptel.presentacion.cliente.VCliente;
import is.apptel.presentacion.cliente.VModificarCliente;
import is.apptel.presentacion.controlador.Controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VReserva extends JDialog {
	
	private JPanelPrincipal panel;
	private TCliente cliente;
	private JTable tabla;
	private MiModelo modelo;
	private panelLista lista;
	private JScrollPane scroll;
	private panelBotones botones;
	private boolean tieneCliente;
	private TReserva reserva;
	private VAnadirReserva vAnadir;
	
	
	public VReserva(JFrame frame) {
		super(frame,"Reservas",true);
		vAnadir = new VAnadirReserva(this);
	
	}
	
	private class MiModelo extends DefaultTableModel {
		   
		public boolean isCellEditable (int row, int column) {
	       return false;
	   }
	}
	
	public void initGUI() {
		
		tieneCliente = false;
		panel = new JPanelPrincipal();
		Controlador c = Controlador.getInstancia();
		c.accion("leerTodasReservas",null);
		botones.setVisible(false);
		ImageIcon icon = new ImageIcon(Main.class.getResource("/img/Logo.png"));
		this.setIconImage(icon.getImage());
		this.setContentPane(panel);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void initGUI(TCliente cliente) {
		tieneCliente = true;
		this.cliente = cliente;
		panel = new JPanelPrincipal();
		Controlador c = Controlador.getInstancia();
		c.accion("leerReservaUnCliente",cliente.getId());
		ImageIcon icon = new ImageIcon(Main.class.getResource("/img/Logo.png"));
		this.setIconImage(icon.getImage());
		this.setContentPane(panel);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}	
	
	private class JPanelPrincipal extends JPanel {
		
		public JPanelPrincipal(){
			setLayout(new BorderLayout());
			botones = new panelBotones();
			lista = new panelLista();
			JButtonExit exit = new JButtonExit();
			add(botones, BorderLayout.NORTH);
			add(lista, BorderLayout.CENTER);
			add(exit,BorderLayout.SOUTH);
		}

		public void setTabla(ArrayList<TReserva> reservas) {
			lista.setTabla(reservas);
		}

		public void pintarTabla() {
			this.remove(lista);
			lista = new panelLista();
			if(tieneCliente){
			Controlador c = Controlador.getInstancia();
			c.accion("leerReservaUnCliente",cliente.getId());
			}
			else{
				Controlador c = Controlador.getInstancia();
				c.accion("leerTodasReservas",null);
				}
			add(lista, BorderLayout.CENTER);
			this.revalidate();
			
		}
		
	}
	
	
	private class panelBotones extends JPanel {
		
		public panelBotones(){
			JButton anadir = new JButton("Crear Reserva");
			JButton modificar = new JButton("Modificar Reserva");
			JButton eliminar = new JButton("Eliminar Reserva");
			ImageIcon deleteIcon = new ImageIcon(Main.class.getResource("/img/trash.png"));
			eliminar.setIcon(deleteIcon);
			ImageIcon updateIcon = new ImageIcon(Main.class.getResource("/img/update.gif"));
			modificar.setIcon(updateIcon);
			ImageIcon addIcon = new ImageIcon(Main.class.getResource("/img/add.png"));
			anadir.setIcon(addIcon);
			setLayout(new FlowLayout());
			
			anadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					desaparecer();
					vAnadir.initGUI(cliente);
					VReserva.this.pintartabla();
					aparecer();
					
				}
			});
			
			modificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tabla.getSelectedRow() != -1){
					int id = (int) modelo.getValueAt(tabla.getSelectedRow(), 0);
					Controlador c = Controlador.getInstancia();
					c.accion("leerReserva", id);
					desaparecer();
					new VModificarReserva(VReserva.this,reserva);
					VReserva.this.pintartabla();
					aparecer();
					}
					else
						JOptionPane.showMessageDialog(null, "No has seleccionado ninguna reserva", "ERROR", JOptionPane.ERROR_MESSAGE);
					
				}
			});
			
			eliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tabla.getSelectedRow() != -1){
						if(JOptionPane.showConfirmDialog(null, "Desea eliminar?", "Eliminar", JOptionPane.YES_NO_OPTION) == 0){
					Controlador c = Controlador.getInstancia();
					c.accion("eliminarReserva", modelo.getValueAt(tabla.getSelectedRow(), 0));
					VReserva.this.pintartabla();
						}
					}
					else
						JOptionPane.showMessageDialog(null, "No has seleccionado ninguna reserva", "ERROR", JOptionPane.ERROR_MESSAGE);
			
		}
	});
			
			add(anadir);
			add(modificar);
			add(eliminar);
		}
	}
	
	private class panelLista extends JPanel {
		
		public panelLista() {
			tabla = new JTable();
	        modelo = new MiModelo();
	        scroll = new JScrollPane(tabla);
	        String[] columnas = {"ID", "Fecha Inicio", "Fecha Fin","Nombre","Apellidos","Importe"};
	        
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
			
	        tabla.addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent me) {
	                JTable table =(JTable) me.getSource();
	                Point p = me.getPoint();
	                int row = table.rowAtPoint(p);
	                if (me.getClickCount() == 2) {
	                	if(tabla.getSelectedRow() != -1){	
	                		desaparecer();
	                		int id = (int) modelo.getValueAt(tabla.getSelectedRow(), 0);
							Controlador c = Controlador.getInstancia();
							c.accion("leerReserva", id);
							new DatosReserva(VReserva.this, reserva);
							aparecer(); 
						}
						else
							JOptionPane.showMessageDialog(null, "No has seleccionado ninguna reserva", "ERROR", JOptionPane.ERROR_MESSAGE);			     
	                }
	            }
	        });
			
		}
		private void hideColumnIDTable(){
			 
			    tabla.getColumnModel().getColumn(0).setMaxWidth(0);
			    tabla.getColumnModel().getColumn(0).setMinWidth(0);
			    tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
			    tabla.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
			}
		
		public void setTabla(ArrayList<TReserva> reserva) {
			TReserva re;
			int tam = reserva.size();
			 for(int i = 0;i < tam;i++) {
				 re = reserva.get(i);
				 modelo.addRow( new Object[] {re.getId(), new SimpleDateFormat("dd-MM-yyyy").format(re.getFecha_inicio()), new SimpleDateFormat("dd-MM-yyyy").format(re.getFecha_fin()),re.getCliente().getNombre(),re.getCliente().getApellidos(), re.getImporte()}); 
			 }
			
		}
}

	private class JButtonExit extends JPanel {
		private JButton Exit;
		public JButtonExit() {			
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
			JButton atras = new JButton("Atras");
			ImageIcon backIcon = new ImageIcon(Main.class.getResource("/img/back.png"));
			atras.setIcon(backIcon); 
			atras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//esto es para cerrar la ventana
					VReserva.this.setVisible(false);
					VReserva.this.dispose();
				}
			});
			
			panelFactura f = new panelFactura();
			add(Exit,BorderLayout.EAST);
			add(f, BorderLayout.CENTER);
			add(atras,BorderLayout.WEST);
		}
	}
		  
	private class panelFactura extends JPanel {
		public panelFactura(){
			JButton Generar = new JButton("Generar Factura");
			Generar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(tabla.getSelectedRow() != -1){
						int id = (int) modelo.getValueAt(tabla.getSelectedRow(), 0);
						Controlador c = Controlador.getInstancia();
						c.accion("GenerarFactura", id);
						JOptionPane.showMessageDialog(null, "Factura generada", "EXITO", JOptionPane.INFORMATION_MESSAGE);
						}
						else
							JOptionPane.showMessageDialog(null, "No has seleccionado ninguna reserva", "ERROR", JOptionPane.ERROR_MESSAGE);
					
					
				}
			});
			JButton Consultar = new JButton("Consultar Factura");
			Consultar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tabla.getSelectedRow() != -1){
						int id = (int) modelo.getValueAt(tabla.getSelectedRow(), 0);
						String sFichero = "F_"+id+".pdf";
						File fichero = new File(sFichero);
						if(fichero.exists()){
							Controlador c = Controlador.getInstancia();
							c.accion("ConsultarFactura", id);
						}
						else
							{
							JOptionPane.showMessageDialog(null, "No has generado la factura de la reserva", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}
						else
							JOptionPane.showMessageDialog(null, "No has seleccionado ninguna reserva", "ERROR", JOptionPane.ERROR_MESSAGE);
			
					
				}
			});
			
			Generar.setPreferredSize(new Dimension(140,40));
			Consultar.setPreferredSize(new Dimension(140,40));
			this.setLayout(new FlowLayout());
			add(Generar);
			add(Consultar);
			
		}
	} 
	private void desaparecer() {
		this.setVisible(false);
	}
	
	protected void pintartabla() {
		panel.pintarTabla();
		
	}
	private void aparecer() {
		this.setVisible(true);
	}
	public void setTabla(ArrayList<TReserva> reservas) {
		panel.setTabla(reservas);
		
	}

	public void setReserva(TReserva reserva) {
		this.reserva = reserva;
		
	}

	public void setHabitacion(THabitacion read) {
		vAnadir.setHabitacion(read);
		
	}

	public void setServicio(TServicio read) {
		vAnadir.setServicio(read);
		
	}

	public void setTablaServicio(ArrayList<TServicio> servicios) {
		vAnadir.setTablaServicio(servicios);
		
	}

	public void setHabitacionesDisponibles(ArrayList<THabitacion> habitacion) {
		vAnadir.setHabitacionesDisponibles(habitacion);
		
	}
	
}
