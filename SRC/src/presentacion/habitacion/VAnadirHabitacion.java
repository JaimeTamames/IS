package is.apptel.presentacion.habitacion;

import is.apptel.negocio.cliente.TCliente;
import is.apptel.negocio.cliente.TClienteEmpresa;
import is.apptel.negocio.habitacion.THabitacion;
import is.apptel.presentacion.Main;
import is.apptel.presentacion.cliente.VAnadirCliente;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class VAnadirHabitacion extends JDialog {
	private LabelText label1,label2,label3;
		public VAnadirHabitacion(VHabitacion cliente) {
			super(cliente, "Anadir Habitacion",true);
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
			label1 = new LabelText("Numero");
			label2 = new LabelText("Precio");
			label3 = new LabelText("Tipo");
			add(label1);
			add(label2);
			add(label3);
			}
		}
		
		private class LabelText extends JPanel {
			JTextField text;
			public LabelText(String mensaje) {
			setLayout(new FlowLayout());
			JLabel label = new JLabel(mensaje);
			label.setPreferredSize(new Dimension(70,15));
			text = new JTextField(10);
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
				cancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//esto es para cerrar la ventana
						VAnadirHabitacion.this.setVisible(false);
						VAnadirHabitacion.this.dispose();
					}
				});
				aceptar.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						THabitacion habitacion;
						int numero;
						double precio;
						String tipo;
						try{
						numero =  Integer.parseInt(label1.getText());
						precio =  Double.parseDouble(label2.getText());
						tipo = label3.getText();
						if(label3.getText() !=""){
							tipo = label3.getText();
						
							habitacion = new THabitacion(numero, tipo, precio);
							Controlador c = Controlador.getInstancia();
							c.accion("nuevaHabitacion", habitacion);
							//esto es para cerrar la ventana
							VAnadirHabitacion.this.setVisible(false);
							VAnadirHabitacion.this.dispose();
							}
						else
							JOptionPane.showMessageDialog(null, "No has introducido todos los datos necesarios", "ERROR", JOptionPane.ERROR_MESSAGE);
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
		
}
