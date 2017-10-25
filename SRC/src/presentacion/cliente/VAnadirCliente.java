package is.apptel.presentacion.cliente;

import is.apptel.negocio.cliente.TCliente;
import is.apptel.negocio.cliente.TClienteEmpresa;
import is.apptel.negocio.cliente.TClienteParticular;
import is.apptel.presentacion.Main;
import is.apptel.presentacion.controlador.Controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class VAnadirCliente extends JDialog {
	private LabelText label1;
	private LabelText label2;
	private LabelText label3;
	private LabelText label4;
	private LabelText label5;
	private LabelText label6;
	private JCheckBox tipoCliente;
	
		public VAnadirCliente(VCliente cliente) {
			super(cliente, "Anadir Cliente", true);
			initGUI();
			
		}
		public void initGUI() {
			JPanelPrincipal panel = new JPanelPrincipal();
			ImageIcon icon = new ImageIcon(Main.class.getResource("/img/Logo.png"));
			this.setIconImage(icon.getImage());
			this.setContentPane(panel);
			this.pack();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		}
		
		private class JPanelPrincipal extends JPanel {
			public JPanelPrincipal(){
				setLayout(new BorderLayout());
				panelLabels label = new panelLabels();
				panelbotones botones = new panelbotones();
				add(label,BorderLayout.CENTER);
				add(botones,BorderLayout.SOUTH);
			}
		}
		
		private class panelLabels extends JPanel {
			public panelLabels(){
			setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
			
			label1 = new LabelText("NIF");
			label2 = new LabelText("Nombre");
			label3 = new LabelText("Apellidos");
			label4 = new LabelText("Telefono");
			label5 = new LabelText("Direccion");
			label6 = new LabelText("Mail");
			
			add(label1);
			add(label2);
			add(label3);
			add(label4);
			add(label5);
			add(label6);
			tipoCliente = new JCheckBox("Empresa");
			
			add(tipoCliente);
			}
		}
		
		private class LabelText extends JPanel {
			private JTextField text;
			public LabelText(String mensaje){
			setLayout(new FlowLayout());
			JLabel label = new JLabel(mensaje);
			label.setPreferredSize(new Dimension(70,15));
			
			text = new JTextField(16);
			add(label);
			add(text);
			}
			public String getText(){
				if(text.getText().isEmpty())
					return "";
				else
					return text.getText();
			}
		}
		
		private class panelbotones extends JPanel {
			
			public panelbotones(){
				JButton aceptar = new JButton("Aceptar");
				JButton cancelar = new JButton("Cancelar");
				cancelar.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//esto es para cerrar la ventana
						VAnadirCliente.this.setVisible(false);
						VAnadirCliente.this.dispose();
					}
				});
				aceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TCliente cliente;
						int telefono;
						String nif =  label1.getText();
						String nombre =  label2.getText();
						String apellidos = label3.getText();
						try{
						telefono = Integer.parseInt(label4.getText());
						String direccion = label5.getText();
						String email = label6.getText();
						if(nif == "" || nombre == "" || apellidos == ""  || direccion == "" || email== ""){
							JOptionPane.showMessageDialog(null, "No has introducido todos los datos necesarios", "ERROR", JOptionPane.ERROR_MESSAGE);		
						}
						else if(tipoCliente.isSelected()){
							cliente = new TClienteEmpresa(nombre, apellidos, nif, direccion, telefono, email);
							Controlador c = Controlador.getInstancia();
							c.accion("nuevoCliente", cliente);
							//esto es para cerrar la ventana
							VAnadirCliente.this.setVisible(false);
							VAnadirCliente.this.dispose();
						}
						else {
						
							cliente = new TClienteParticular( nombre,apellidos, nif, direccion, telefono, email);
							Controlador c = Controlador.getInstancia();
							c.accion("nuevoCliente", cliente);
							//esto es para cerrar la ventana
							VAnadirCliente.this.setVisible(false);
							VAnadirCliente.this.dispose();
						}
						
						}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Hay datos incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						
						
					}
					
					
					
				});
				setLayout(new FlowLayout());
				add(aceptar);
				add(cancelar);
			}
		}
		
}
