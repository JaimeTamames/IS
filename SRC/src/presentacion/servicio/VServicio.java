package is.apptel.presentacion.servicio;


import is.apptel.negocio.servicio.TServicio;
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

public class VServicio extends JDialog {
	
		
		private JPanelPrincipal panel;
		private JScrollPane scroll;
		private JTable tabla;
		private MiModelo modelo;
		private TServicio servicio;
		
		public VServicio(JFrame frame) {
			super(frame, "Servicios",true);
			panel = new JPanelPrincipal();
		
		
		}
		private class MiModelo extends DefaultTableModel {
			   
			public boolean isCellEditable (int row, int column) {
			
		       return false;
		   }
		}
		
		public void initGUI() {
			
			ImageIcon icon = new ImageIcon(Main.class.getResource("/img/Logo.png"));
			Controlador c = Controlador.getInstancia();
			c.accion("leerTodosServicios",null);
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
				c.accion("leerTodosServicios",null);
				add(lista, BorderLayout.CENTER);
				this.revalidate();
				
			}
			public void setTabla(ArrayList<TServicio> servicios) {
				this.remove(lista);
				lista = new panelLista();
				lista.setTabla(servicios);
				add(lista, BorderLayout.CENTER);
				this.revalidate();
				
			}
		}
		
		private class panelBotones extends JPanel {
			
			public panelBotones(){
				JButton anadir = new JButton("Crear Servicio");
				JButton modificar = new JButton("Modificar Servicio");
				JButton eliminar = new JButton("Eliminar Servicio");
				ImageIcon trashIcon = new ImageIcon(Main.class.getResource("/img/trash.png"));
				eliminar.setIcon(trashIcon);
				ImageIcon updateIcon = new ImageIcon(Main.class.getResource("/img/update.gif"));
				modificar.setIcon(updateIcon);
				ImageIcon addIcon = new ImageIcon(Main.class.getResource("/img/add.png"));
				anadir.setIcon(addIcon);
				setLayout(new FlowLayout());
				
				anadir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						desaparecer();
						new VAnadirServicio(VServicio.this);
						VServicio.this.pintartabla();
						aparecer();
						
					}
				});
				modificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(tabla.getSelectedRow() != -1){
							int id = (int) modelo.getValueAt(tabla.getSelectedRow(), 0);
							Controlador c = Controlador.getInstancia();
							c.accion("leerServicio", id);
							desaparecer();
							new VModificarServicio(VServicio.this,servicio);
							VServicio.this.pintartabla();
							aparecer();
							}
							else
								JOptionPane.showMessageDialog(null, "No has seleccionado ningun servicio", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				});
				eliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(tabla.getSelectedRow() != -1){
							if(JOptionPane.showConfirmDialog(null, "Desea eliminar?", "Eliminar", JOptionPane.YES_NO_OPTION) == 0){
						Controlador c = Controlador.getInstancia();
						c.accion("eliminarServicio", modelo.getValueAt(tabla.getSelectedRow(), 0));
						VServicio.this.pintartabla();
							}
						}
						else
							JOptionPane.showMessageDialog(null, "No has seleccionado ningun servicio", "ERROR", JOptionPane.ERROR_MESSAGE);
						
				
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
		        String[] columnas = {"Id ", "Nombre" , "Precio"};
		        
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
			
			public void setTabla(ArrayList<TServicio> servicio) {
				
				TServicio serv;
				int tam = servicio.size();
				 for(int i = 0;i < tam;i++) {
					 serv = servicio.get(i);
					 modelo.addRow( new Object[] {serv.getId(),serv.getNombre(), serv.getPrecio()});
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
						VServicio.this.setVisible(false);
						VServicio.this.dispose();
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
		public void setHabitacion(TServicio read) {
			servicio = read;
			
		}
		public void setTabla(ArrayList<TServicio> servicios) {
			panel.setTabla(servicios);
			
		}
		public void setServicio(TServicio read) {
			this.servicio = read;
			
		}
		
	}