package vistas;

import javax.swing.*;

import controllers.VeterinarioController;

import java.awt.*;

public class ViewRegistrarConsulta extends JFrame {
	private JTextField txtIdCita;
	private JTextField txtSintomas;
	private JTextField txtDiagnostico;
	private JTextArea txtObservaciones;
	private JLabel lblEstado;
	private JLabel lblError;
	private JButton btnGuardar;
	private JButton btnCerrar;

	public ViewRegistrarConsulta() {
		super("Veterinaria - Registrar Consulta");
		this.setMinimumSize(new Dimension(920, 620));
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
		tarjeta.setPreferredSize(new Dimension(840, 520));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 10, 8, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;

		JLabel titulo = new JLabel("Registrar consulta");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
		titulo.setForeground(new Color(33, 43, 54));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		tarjeta.add(titulo, gbc);

		JLabel subtitulo = new JLabel("Completa los datos clínicos de la consulta activa.");
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
		txtSintomas = new JTextField();
		txtDiagnostico = new JTextField();
		txtObservaciones = new JTextArea(4, 20);
		txtObservaciones.setLineWrap(true);
		txtObservaciones.setWrapStyleWord(true);
		txtObservaciones.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(196, 210, 201), 1, true),
			BorderFactory.createEmptyBorder(8, 12, 8, 12)
		));

		int fila = 3;
		añadirCampo(tarjeta, gbc, fila, 0, "ID Cita", txtIdCita);
		añadirCampo(tarjeta, gbc, fila, 2, "Síntomas", txtSintomas);
		fila += 2;
		añadirCampo(tarjeta, gbc, fila, 0, "Diagnóstico", txtDiagnostico);
		añadirCampoArea(tarjeta, gbc, fila, 2, "Observaciones", txtObservaciones);
		fila += 3;

		lblEstado = new JLabel("Estado: consulta pendiente de guardar");
		lblEstado.setFont(new Font("SansSerif", Font.ITALIC, 13));
		lblEstado.setForeground(new Color(92, 107, 119));
		gbc.gridx = 0;
		gbc.gridy = fila + 1;
		gbc.gridwidth = 4;
		tarjeta.add(lblEstado, gbc);

		JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
		acciones.setOpaque(false);
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setFocusPainted(false);
		btnCerrar.setBackground(new Color(232, 239, 235));
		btnCerrar.setForeground(new Color(54, 65, 80));
		btnCerrar.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
		btnGuardar = new JButton("Guardar consulta");
		btnGuardar.setFocusPainted(false);
		btnGuardar.setBackground(new Color(20, 90, 150));
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
		acciones.add(btnCerrar);
		acciones.add(btnGuardar);
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

	private void añadirCampo(JPanel tarjeta, GridBagConstraints gbc, int fila, int columna, String etiqueta, JComponent componente) {
		GridBagConstraints etiquetas = (GridBagConstraints) gbc.clone();
		etiquetas.gridx = columna;
		etiquetas.gridy = fila;
		etiquetas.gridwidth = 1;
		JLabel lbl = new JLabel(etiqueta);
		lbl.setFont(new Font("SansSerif", Font.BOLD, 13));
		lbl.setForeground(new Color(54, 65, 80));
		tarjeta.add(lbl, etiquetas);

		GridBagConstraints campos = (GridBagConstraints) gbc.clone();
		campos.gridx = columna;
		campos.gridy = fila + 1;
		campos.gridwidth = 1;
		if (componente instanceof JTextField) {
			((JTextField) componente).setPreferredSize(new Dimension(320, 38));
			((JTextField) componente).setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(196, 210, 201), 1, true),
				BorderFactory.createEmptyBorder(8, 12, 8, 12)
			));
		}
		tarjeta.add(componente, campos);
	}

	private void añadirCampoArea(JPanel tarjeta, GridBagConstraints gbc, int fila, int columna, String etiqueta, JTextArea componente) {
		GridBagConstraints etiquetas = (GridBagConstraints) gbc.clone();
		etiquetas.gridx = columna;
		etiquetas.gridy = fila;
		etiquetas.gridwidth = 1;
		JLabel lbl = new JLabel(etiqueta);
		lbl.setFont(new Font("SansSerif", Font.BOLD, 13));
		lbl.setForeground(new Color(54, 65, 80));
		tarjeta.add(lbl, etiquetas);

		GridBagConstraints campos = (GridBagConstraints) gbc.clone();
		campos.gridx = columna;
		campos.gridy = fila + 1;
		campos.gridwidth = 1;
		componente.setPreferredSize(new Dimension(320, 110));
		JScrollPane scroll = new JScrollPane(componente);
		scroll.setPreferredSize(new Dimension(320, 110));
		scroll.setBorder(BorderFactory.createLineBorder(new Color(196, 210, 201), 1, true));
		tarjeta.add(scroll, campos);
	}

	public void hazEscuchadores(VeterinarioController controlador) {
		btnCerrar.addActionListener(e -> dispose());
		btnGuardar.addActionListener(controlador);
	}

	public JTextField getTxtIdCita() { return txtIdCita; }
	public JTextField getTxtSintomas() { return txtSintomas; }
	public JTextField getTxtDiagnostico() { return txtDiagnostico; }
	public JTextArea getTxtObservaciones() { return txtObservaciones; }
	public JLabel getLblEstado() { return lblEstado; }
	public JLabel getLblError() { return lblError; }
	public JButton getBtnGuardar() { return btnGuardar; }
	public JButton getBtnCerrar() { return btnCerrar; }

}
