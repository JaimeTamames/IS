package is.apptel.presentacion.habitacion;


import is.apptel.negocio.habitacion.THabitacion;
import is.apptel.presentacion.Main;
import is.apptel.presentacion.controlador.Controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class VHabitacion extends JDialog {
	
	private JPanelPrincipal panel;
	private JScrollPane scroll;
	private JTable tabla;
	private MiModelo modelo;
	private THabitacion habitacion;
	
	public VHabitacion(JFrame frame) {
		super(frame, "Habitaciones",true);
	
	
	}
	private class MiModelo extends DefaultTableModel {
		   
		public boolean isCellEditable (int row, int column) {
	       return false;
	   }
	}
	
	public void initGUI() {
		
		panel = new JPanelPrincipal();
		ImageIcon icon = new ImageIcon(Main.class.getResource("/img/Logo.png"));
		Controlador c = Controlador.getInstancia();
		c.accion("leerTodasHabitaciones",null);
		this.setIconImage(icon.getImage());
		this.setContentPane(panel);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
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
			c.accion("leerTodasHabitaciones",null);
			add(lista, BorderLayout.CENTER);
			this.revalidate();
			
		}
		public void setTabla(ArrayList<THabitacion> habitaciones) {
			lista.setTabla(habitaciones);
			
		}
	}
	
	private class panelBotones extends JPanel {
		
		public panelBotones(){
			JButton anadir = new JButton("Crear Habitacion");
			JButton modificar = new JButton("Modificar Habitacion");
			JButton eliminar = new JButton("Eliminar Habitacion");
			ImageIcon eliminarIcon = new ImageIcon(Main.class.getResource("/img/trash.png"));
			eliminar.setIcon(eliminarIcon);
			ImageIcon updateIcon = new ImageIcon(Main.class.getResource("/img/update.gif"));
			modificar.setIcon(updateIcon);
			ImageIcon addIcon = new ImageIcon(Main.class.getResource("/img/add.png"));
			anadir.setIcon(addIcon);
			setLayout(new FlowLayout());
			
			anadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					desaparecer();
					new VAnadirHabitacion(VHabitacion.this);
					VHabitacion.this.pintartabla();
					aparecer();
					
				}
			});
			modificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tabla.getSelectedRow() != -1){
						int id = (int) modelo.getValueAt(tabla.getSelectedRow(), 0);
						Controlador c = Controlador.getInstancia();
						c.accion("leerHabitacion", id);
						desaparecer();
						new VModificarHabitacion(VHabitacion.this,habitacion);
						VHabitacion.this.pintartabla();
						aparecer();
						}
						else
							JOptionPane.showMessageDialog(null, "No has seleccionado ninguna habitacion", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			});
			eliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tabla.getSelectedRow() != -1){
						if(JOptionPane.showConfirmDialog(null, "Desea eliminar?", "Eliminar", JOptionPane.YES_NO_OPTION) == 0){
					Controlador c = Controlador.getInstancia();
					c.accion("eliminarHabitacion", modelo.getValueAt(tabla.getSelectedRow(), 0));
					VHabitacion.this.pintartabla();
						}
					}
					else
						JOptionPane.showMessageDialog(null, "No has seleccionado ninguna habitacion", "ERROR", JOptionPane.ERROR_MESSAGE);
					
			
		}
	});
			
			add(anadir);
			add(modificar);
			add(eliminar);
		}
	}
	
	private class panelLista extends JPanel {
		
		
		public panelLista(){
			tabla = new JTable();
	        modelo = new MiModelo();
	        scroll = new JScrollPane(tabla);
	        String[] columnas = {"Id ", "Numero" , "Precio", "Tipo"};
	        
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
		public void setTabla(ArrayList<THabitacion> habitaciones) {
			
			THabitacion ha;

			int tam = habitaciones.size();
			 for(int i = 0;i < tam;i++) {
				 ha = habitaciones.get(i);
				 modelo.addRow( new Object[] {ha.getId(),ha.getNumero(), ha.getPrecio(), ha.getTipo()});
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
			JButton atras = new JButton("Atras");
			ImageIcon backIcon = new ImageIcon(Main.class.getResource("/img/back.png"));
			atras.setIcon(backIcon); 
			atras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//esto es para cerrar la ventana
					VHabitacion.this.setVisible(false);
					VHabitacion.this.dispose();
				}
			});
			add(Exit,BorderLayout.EAST);
			add(atras,BorderLayout.WEST);
		}
	}
	
		                
	private void desaparecer() {
		this.setVisible(false);
	}
	public void pintartabla() {
		panel.pintartabla();
			
		}
	private void aparecer() {
		this.setVisible(true);
	}
	public void setHabitacion(THabitacion read) {
		habitacion = read;
		
	}
	public void setTabla(ArrayList<THabitacion> habitaciones) {
		panel.setTabla(habitaciones);
		
	}
	
}
