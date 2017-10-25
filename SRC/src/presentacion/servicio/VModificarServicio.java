package is.apptel.presentacion.servicio;


import is.apptel.negocio.servicio.TServicio;
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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VModificarServicio extends JDialog {
	private LabelText label1,label2;
	private TServicio servicio;
	public VModificarServicio(VServicio servicio,TServicio serv) {
		super(servicio, "Anadir Servicio",true);
		this.servicio = serv;
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
		label1 = new LabelText("Nombre",servicio.getNombre());
		label2 = new LabelText("Precio",String.valueOf(servicio.getPrecio()));
		add(label1);
		add(label2);
	
		}
	}
	
	private class LabelText extends JPanel {
		JTextField text;
		public LabelText(String mensaje,String mod) {
		setLayout(new FlowLayout());
		JLabel label = new JLabel(mensaje);
		label.setPreferredSize(new Dimension(70,15));
		
		text = new JTextField(10);
		text.setText(mod);
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
					VModificarServicio.this.setVisible(false);
					VModificarServicio.this.dispose();
				}
			});
			aceptar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					TServicio serv;
				
					double precio;
					String nombre;
					try{
					precio =  Double.parseDouble(label2.getText());
					if(label1.getText() =="")
						nombre = servicio.getNombre();
					else
						nombre = label1.getText();
					
					serv = new TServicio(servicio.getId(),nombre ,precio);
						Controlador c = Controlador.getInstancia();
						c.accion("modificarServicio", serv);
						//esto es para cerrar la ventana
						VModificarServicio.this.setVisible(false);
						VModificarServicio.this.dispose();
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