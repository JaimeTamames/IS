package is.apptel.presentacion.habitacion;

import is.apptel.negocio.habitacion.THabitacion;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VModificarHabitacion extends JDialog {
	private THabitacion habitacion;
	private LabelText label1,label2,label3;;
	
	public VModificarHabitacion(VHabitacion cliente, THabitacion habitacion) {
		super(cliente, "Modificar Habitacion",true);
		this.habitacion = habitacion;
		initGUI();
		
}
	public void initGUI(){
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
			label1 = new LabelText("Numero",String.valueOf(habitacion.getNumero()));
			label2 = new LabelText("Precio",String.valueOf(habitacion.getPrecio()));
			label3 = new LabelText("Tipo",habitacion.getTipo());
			
			add(label1);
			add(label2);
			add(label3);
		
		}
	}
	
	private class LabelText extends JPanel {
		JTextField text;
		public LabelText(String mensaje, String mod) {
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
					VModificarHabitacion.this.setVisible(false);
					VModificarHabitacion.this.dispose();
				}
			});
			aceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int numero;
					double precio;
					String tipo;
					if(label1.getText() != "")
						numero =  Integer.parseInt(label1.getText());
					else
						numero =  habitacion.getNumero();
					if(label2.getText() != "")
						precio =  Double.parseDouble(label2.getText());
					else
						precio = habitacion.getPrecio();
					if(label3.getText() != "")
						tipo = label3.getText();
					else
						tipo = habitacion.getTipo();
					
					habitacion = new THabitacion(habitacion.getId(),numero,tipo ,precio);
					Controlador c = Controlador.getInstancia();
					c.accion("modificarHabitacion", habitacion);
					//esto es para cerrar la ventana
					VModificarHabitacion.this.setVisible(false);
					VModificarHabitacion.this.dispose();
				}
			});
			setLayout(new FlowLayout());
			add (aceptar);
			add (cancelar);
		}
	}
	
}
