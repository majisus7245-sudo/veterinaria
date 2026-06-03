package vistas;

import javax.swing.*;

import controllers.ConsultaController;

import java.awt.*;

public class ViewVerExpediente extends JFrame {
	private JTextField txtIdCita;
	private JTextArea txtDetalle;
	private JLabel lblEstado;
	private JLabel lblError;
	private JButton btnBuscar;
	private JButton btnCerrar;

	public ViewVerExpediente() {
		super("Veterinaria - Ver Expediente");
		this.setMinimumSize(new Dimension(820, 560));
		this.setResizable(false);
		crearInterfaz();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void crearInterfaz() {
		UIManager.put("TextField.font", new Font("SansSerif", Font.PLAIN, 16));
		UIManager.put("TextArea.font", new Font("SansSerif", Font.PLAIN, 16));
		UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 15));

		JPanel contenedor = new JPanel(new GridBagLayout());
		contenedor.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
		contenedor.setBackground(new Color(245, 249, 247));

		JPanel tarjeta = new JPanel(new GridBagLayout());
		tarjeta.setBackground(Color.WHITE);
		tarjeta.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(214, 226, 220), 1, true),
			BorderFactory.createEmptyBorder(24, 24, 24, 24)
		));
		tarjeta.setPreferredSize(new Dimension(760, 460));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 10, 8, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;

		JLabel titulo = new JLabel("Ver expediente de la mascota");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
		titulo.setForeground(new Color(33, 43, 54));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		tarjeta.add(titulo, gbc);

		JLabel subtitulo = new JLabel("Ingresa el ID de la cita para cargar los datos del expediente.");
		subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		subtitulo.setForeground(new Color(92, 107, 119));
		gbc.gridy++;
		tarjeta.add(subtitulo, gbc);

		lblError = new JLabel(" ");
		lblError.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblError.setForeground(new Color(180, 30, 30));
		gbc.gridy++;
		tarjeta.add(lblError, gbc);

		txtIdCita = new JTextField();
		txtIdCita.setPreferredSize(new Dimension(260, 38));
		txtIdCita.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(196, 210, 201), 1, true),
			BorderFactory.createEmptyBorder(8, 12, 8, 12)
		));

		JLabel lblIdCita = new JLabel("ID Cita");
		lblIdCita.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblIdCita.setForeground(new Color(54, 65, 80));
		gbc.gridy++;
		gbc.gridwidth = 1;
		tarjeta.add(lblIdCita, gbc);
		gbc.gridy++;
		tarjeta.add(txtIdCita, gbc);

		JLabel lblDetalle = new JLabel("Detalle del expediente");
		lblDetalle.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblDetalle.setForeground(new Color(54, 65, 80));
		gbc.gridx = 1;
		gbc.gridy = 4;
		tarjeta.add(lblDetalle, gbc);

		txtDetalle = new JTextArea(10, 24);
		txtDetalle.setEditable(false);
		txtDetalle.setLineWrap(true);
		txtDetalle.setWrapStyleWord(true);
		txtDetalle.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
		JScrollPane scroll = new JScrollPane(txtDetalle);
		scroll.setPreferredSize(new Dimension(420, 210));
		scroll.setBorder(BorderFactory.createLineBorder(new Color(196, 210, 201), 1, true));
		gbc.gridy++;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1;
		tarjeta.add(scroll, gbc);

		lblEstado = new JLabel("Estado: esperando una cita");
		lblEstado.setFont(new Font("SansSerif", Font.ITALIC, 13));
		lblEstado.setForeground(new Color(92, 107, 119));
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 0;
		tarjeta.add(lblEstado, gbc);

		JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
		acciones.setOpaque(false);
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setFocusPainted(false);
		btnCerrar.setBackground(new Color(232, 239, 235));
		btnCerrar.setForeground(new Color(54, 65, 80));
		btnCerrar.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
		btnBuscar = new JButton("Ver expediente");
		btnBuscar.setFocusPainted(false);
		btnBuscar.setBackground(new Color(20, 90, 150));
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
		acciones.add(btnCerrar);
		acciones.add(btnBuscar);
		gbc.gridy++;
		tarjeta.add(acciones, gbc);

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

	public void hazEscuchadores(ConsultaController controlador) {
		btnCerrar.addActionListener(e -> dispose());
		btnBuscar.addActionListener(controlador);
	}

	public JTextField getTxtIdCita() { return txtIdCita; }
	public JTextArea getTxtDetalle() { return txtDetalle; }
	public JLabel getLblEstado() { return lblEstado; }
	public JLabel getLblError() { return lblError; }
	public JButton getBtnBuscar() { return btnBuscar; }
	public JButton getBtnCerrar() { return btnCerrar; }

}
