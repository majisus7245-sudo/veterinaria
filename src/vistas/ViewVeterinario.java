package vistas;

import javax.swing.*;

import controllers.VeterinarioController;

import java.awt.*;

public class ViewVeterinario extends JFrame {

	private JButton btnIniciarConsulta;

	public ViewVeterinario() {
		super("Veterinaria - Veterinario");
		this.setMinimumSize(new Dimension(700, 420));
		this.setResizable(false);
		hazInterfaz();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void hazInterfaz() {
		JPanel contenedor = new JPanel(new GridBagLayout());
		contenedor.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
		contenedor.setBackground(new Color(239, 246, 242));

		JPanel tarjeta = new JPanel(new GridBagLayout());
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

		JLabel titulo = new JLabel("Panel de veterinario", SwingConstants.CENTER);
		titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
		titulo.setForeground(new Color(33, 43, 54));
		tarjeta.add(titulo, gbc);

		gbc.gridy++;
		JLabel subtitulo = new JLabel("Gestiona las consultas clínicas desde este menú", SwingConstants.CENTER);
		subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		subtitulo.setForeground(new Color(92, 107, 119));
		tarjeta.add(subtitulo, gbc);

		gbc.gridy++;
		btnIniciarConsulta = new JButton("Iniciar consulta");
		btnIniciarConsulta.setFocusPainted(false);
		btnIniciarConsulta.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnIniciarConsulta.setBackground(new Color(20, 90, 150));
		btnIniciarConsulta.setForeground(Color.WHITE);
		btnIniciarConsulta.setBorder(BorderFactory.createEmptyBorder(12, 22, 12, 22));
		tarjeta.add(btnIniciarConsulta, gbc);

		gbc.gridy++;
		JLabel ayuda = new JLabel("Presiona el botón para comenzar una consulta con una cita existente.", SwingConstants.CENTER);
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

	public void hazEscuchadores(VeterinarioController controlador) {
		btnIniciarConsulta.addActionListener(controlador);
	}

	public JButton getBtnIniciarConsulta() {
		return btnIniciarConsulta;
	}

}
