package vistas;

import javax.swing.*;

import controllers.RegistroController;

import java.awt.*;

public class ViewRecepcionista extends JFrame {
	private JButton btnAgregarMascota;
	private ViewAgregarMascota vistaAgregarMascota;
	private JPanel contenedor;
	private JPanel tarjeta;
	private JLabel titulo;
	private JLabel subtitulo;
	private JLabel ayuda;
	public ViewRecepcionista() {
		super("Veterinaria - Recepción");
		this.setMinimumSize(new Dimension(700, 420));
		this.setResizable(false);
		hazInterfaz();
		this.pack();
		this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// Getters
	public JButton getBtnAgregarMascota() {
		return btnAgregarMascota;
	}

	private void hazInterfaz() {
		contenedor = new JPanel(new GridBagLayout());
		contenedor.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
		contenedor.setBackground(new Color(239, 246, 242));
		tarjeta = new JPanel(new GridBagLayout());
		tarjeta.setBackground(Color.WHITE);
		tarjeta.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(214, 226, 220), 1, true),
			BorderFactory.createEmptyBorder(30, 30, 30, 30)
		));
		tarjeta.setPreferredSize(new Dimension(560, 270));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		titulo = new JLabel("Panel de recepción", SwingConstants.CENTER);
		titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
		titulo.setForeground(new Color(33, 43, 54));
		tarjeta.add(titulo, gbc);
		gbc.gridy++;
		subtitulo = new JLabel("Solo tienes la opción de registrar mascotas", SwingConstants.CENTER);
		subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		subtitulo.setForeground(new Color(92, 107, 119));
		tarjeta.add(subtitulo, gbc);
		gbc.gridy++;
		btnAgregarMascota = new JButton("Agregar mascota");
		btnAgregarMascota.setFocusPainted(false);
		btnAgregarMascota.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnAgregarMascota.setBackground(new Color(35, 122, 85));
		btnAgregarMascota.setForeground(Color.WHITE);
		btnAgregarMascota.setBorder(BorderFactory.createEmptyBorder(12, 22, 12, 22));
		tarjeta.add(btnAgregarMascota, gbc);
		gbc.gridy++;
		ayuda = new JLabel("Presiona el botón para abrir el formulario de registro.", SwingConstants.CENTER);
		ayuda.setFont(new Font("SansSerif", Font.PLAIN, 13));
		ayuda.setForeground(new Color(120, 132, 140));
		tarjeta.add(ayuda, gbc);
		GridBagConstraints root = new GridBagConstraints();
		root.gridx = 0;
		root.gridy = 0;
		root.weightx = 1;
		root.weighty = 1;
		root.fill = GridBagConstraints.NONE;
		root.anchor = GridBagConstraints.CENTER;
		contenedor.add(tarjeta, root);
		this.setContentPane(contenedor);
	}

	public void hazEscuchadores(RegistroController controlador) {
		btnAgregarMascota.addActionListener(controlador);
	}
	public void crearVistaRegistro(RegistroController controlador){
		vistaAgregarMascota = new ViewAgregarMascota();
		vistaAgregarMascota.hazEscuchadores(controlador);
		vistaAgregarMascota.setVisible(true);
	}
	public ViewAgregarMascota getVistaAgregarMascota() {
		return vistaAgregarMascota;
	}
	
}