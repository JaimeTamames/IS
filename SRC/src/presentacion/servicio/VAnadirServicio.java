package is.apptel.presentacion.servicio;

import is.apptel.negocio.servicio.TServicio;
import is.apptel.presentacion.Main;
import is.apptel.presentacion.controlador.Controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class VAnadirServicio extends JDialog {
	private LabelText label1,label2;
		public VAnadirServicio(VServicio cliente) {
			super(cliente, "Anadir Servicio",true);
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
			label1 = new LabelText("Nombre");
			label2 = new LabelText("Precio");
			add(label1);
			add(label2);
		
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
						VAnadirServicio.this.setVisible(false);
						VAnadirServicio.this.dispose();
					}
				});
				aceptar.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						TServicio servicio;
					
						double precio;
						String nombre;
						try{
						precio =  Double.parseDouble(label2.getText());
						if(label1.getText() !=""){
						nombre = label1.getText();
						
						servicio = new TServicio(nombre ,precio);
							Controlador c = Controlador.getInstancia();
							c.accion("nuevoServicio", servicio);
							//esto es para cerrar la ventana
							VAnadirServicio.this.setVisible(false);
							VAnadirServicio.this.dispose();
						}
						else
							JOptionPane.showMessageDialog(null, "No has introducido todos los datos necesarios", "ERROR", JOptionPane.ERROR_MESSAGE);						}
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