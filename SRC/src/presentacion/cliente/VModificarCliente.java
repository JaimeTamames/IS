package is.apptel.presentacion.cliente;

import is.apptel.negocio.cliente.TCliente;
import is.apptel.negocio.cliente.TClienteEmpresa;
import is.apptel.negocio.cliente.TClienteParticular;
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

public class VModificarCliente extends JDialog {
	private TCliente cliente;
	private LabelText label1;
	private LabelText label2;
	private LabelText label3;
	private LabelText label4;
	private LabelText label5;
	private LabelText label6;

	public VModificarCliente(VCliente clien, TCliente cl) {
		super(clien, "Modificar Cliente", true);
		this.cliente = cl;
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
		public JPanelPrincipal() {
			setLayout(new BorderLayout());
			panelLabels label = new panelLabels();
			panelbotones botones = new panelbotones();
			add(label, BorderLayout.CENTER);
			add(botones, BorderLayout.SOUTH);
		}
	}

	private class panelLabels extends JPanel {
		public panelLabels() {
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			label1 = new LabelText("NIF",cliente.getNif());
			label2 = new LabelText("Nombre",cliente.getNombre());
			label3 = new LabelText("Apellidos",cliente.getApellidos());
			label4 = new LabelText("Telefono",String.valueOf(cliente.getTelefono()));
			label5 = new LabelText("Direccion",cliente.getDireccion());
			label6 = new LabelText("Mail",cliente.getMail());
			
			add(label1);
			add(label2);
			add(label3);
			add(label4);
			add(label5);
			add(label6);

		}
	}

	private class LabelText extends JPanel {
		private JTextField text;

		public LabelText(String mensaje, String mod) {
			setLayout(new FlowLayout());
			JLabel label = new JLabel(mensaje);
			label.setPreferredSize(new Dimension(70,15));
			this.setAlignmentX(LEFT_ALIGNMENT);
			text = new JTextField(16);
			text.setText(mod);
			add(label);
			add(text);
		}

		public String getText() {
			if (text.getText().isEmpty())
				return "";
			else
				return text.getText();

		}

	}

	private class panelbotones extends JPanel {

		public panelbotones() {
			JButton aceptar = new JButton("Aceptar");
			JButton cancelar = new JButton("Cancelar");
			cancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// esto es para cerrar la ventana
					VModificarCliente.this.setVisible(false);
					VModificarCliente.this.dispose();
				}
			});
			aceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int telefono;
					String nif, nombre, apellidos, direccion, email;
					if (label1.getText() != "")
						nif = label1.getText();
					else
						nif = cliente.getNif();
					if (label2.getText() != "")
						nombre = label2.getText();
					else
						nombre = cliente.getNombre();
					if (label3.getText() != "")
						apellidos = label3.getText();
					else
						apellidos = cliente.getApellidos();
					try{
					if (label4.getText() != "")
						telefono = Integer.parseInt(label4.getText());
					else
						telefono = cliente.getTelefono();
					if (label5.getText() != "")
						direccion = label5.getText();
					else
						direccion = cliente.getDireccion();
					if (label6.getText() != "")
						email = label6.getText();
					else
						email = cliente.getMail();
					
					if (cliente instanceof TClienteEmpresa) {
						cliente = new TClienteEmpresa(cliente.getId(), nombre, apellidos, nif,
								direccion, telefono, email);
					} else {
						cliente = new TClienteParticular(cliente.getId(), nombre, apellidos,
								nif, direccion, telefono, email);
					}

					Controlador c = Controlador.getInstancia();
					c.accion("modificarCliente", cliente);
					
					
					// esto es para cerrar la ventana
					
					VModificarCliente.this.setVisible(false);
					VModificarCliente.this.dispose();
					
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
