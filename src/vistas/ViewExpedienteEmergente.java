package vistas;

import javax.swing.*;

import java.awt.*;

public class ViewExpedienteEmergente extends JDialog {
	private JTextArea txtDetalle;
	private JButton btnCerrar;

	public ViewExpedienteEmergente(Window owner, String detalle) {
		super(owner, "Veterinaria - Expediente", ModalityType.APPLICATION_MODAL);
		crearInterfaz(detalle);
		this.pack();
		this.setLocationRelativeTo(owner);
	}

	private void crearInterfaz(String detalle) {
		UIManager.put("TextArea.font", new Font("SansSerif", Font.PLAIN, 15));
		UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 14));

		JPanel contenedor = new JPanel(new BorderLayout(0, 12));
		contenedor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		contenedor.setBackground(new Color(245, 249, 247));

		JLabel titulo = new JLabel("Valores del expediente");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
		titulo.setForeground(new Color(33, 43, 54));
		contenedor.add(titulo, BorderLayout.NORTH);

		txtDetalle = new JTextArea(detalle == null ? "" : detalle);
		txtDetalle.setEditable(false);
		txtDetalle.setLineWrap(true);
		txtDetalle.setWrapStyleWord(true);
		txtDetalle.setOpaque(true);
		txtDetalle.setBackground(Color.WHITE);
		txtDetalle.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(196, 210, 201), 1, true),
			BorderFactory.createEmptyBorder(10, 12, 10, 12)
		));
		JScrollPane scroll = new JScrollPane(txtDetalle);
		scroll.setPreferredSize(new Dimension(520, 260));
		scroll.setBorder(BorderFactory.createLineBorder(new Color(214, 226, 220), 1, true));
		contenedor.add(scroll, BorderLayout.CENTER);

		JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		acciones.setOpaque(false);
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setFocusPainted(false);
		btnCerrar.setBackground(new Color(20, 90, 150));
		btnCerrar.setForeground(Color.WHITE);
		btnCerrar.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
		btnCerrar.addActionListener(e -> dispose());
		acciones.add(btnCerrar);
		contenedor.add(acciones, BorderLayout.SOUTH);

		this.setContentPane(contenedor);
		this.setResizable(false);
	}

}
