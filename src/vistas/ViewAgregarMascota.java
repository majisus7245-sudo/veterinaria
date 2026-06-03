package vistas;

import javax.swing.*;

import includes.BD_mascotas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Mascota;

public class ViewAgregarMascota extends JFrame implements ActionListener{
	private JTextField txtCliente;
	private JTextField txtNombre;
	private JTextField txtRaza;
	private JTextField txtTipo;
	private JComboBox<String> cboSexo;
	private JTextField txtEdad;
	private JTextField txtPeso;
	private JTextField txtColor;
	private JLabel lblEstado;
	private JLabel lblError;
	private JButton btnGuardar;
	private JButton btnCerrar;

	public ViewAgregarMascota() {
		super("Veterinaria - Agregar Mascota");
		this.setMinimumSize(new Dimension(920, 700));
		this.setResizable(false);
		crearInterfaz();
		hacerEscuchadores();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void crearInterfaz() {
		UIManager.put("TextField.font", new Font("SansSerif", Font.PLAIN, 16));
		UIManager.put("ComboBox.font", new Font("SansSerif", Font.PLAIN, 16));
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
		tarjeta.setPreferredSize(new Dimension(820, 520));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 10, 8, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		JLabel titulo = new JLabel("Agregar mascota");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
		titulo.setForeground(new Color(33, 43, 54));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		tarjeta.add(titulo, gbc);
		JLabel subtitulo = new JLabel("Completa los datos de la mascota para crear el registro y su expediente.");
		subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		subtitulo.setForeground(new Color(92, 107, 119));
		gbc.gridy++;
		tarjeta.add(subtitulo, gbc);
		lblError = new JLabel(" ");
		lblError.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblError.setForeground(new Color(180, 30, 30));
		gbc.gridy++;
		tarjeta.add(lblError, gbc);
		txtCliente = new JTextField();
		txtNombre = new JTextField();
		txtRaza = new JTextField();
		txtTipo = new JTextField();
		cboSexo = new JComboBox<>(new String[]{"macho", "hembra"});
		txtEdad = new JTextField();
		txtPeso = new JTextField();
		txtColor = new JTextField();
		lblEstado = new JLabel("Estado: activo por defecto");
		lblEstado.setFont(new Font("SansSerif", Font.ITALIC, 13));
		lblEstado.setForeground(new Color(92, 107, 119));
		int fila = 3;
		añadirCampo(tarjeta, gbc, fila, 0, "ID Cliente", txtCliente);
		añadirCampo(tarjeta, gbc, fila, 2, "Nombre", txtNombre);
		fila += 2;
		añadirCampo(tarjeta, gbc, fila, 0, "Raza", txtRaza);
		añadirCampo(tarjeta, gbc, fila, 2, "Tipo", txtTipo);
		fila += 2;
		añadirCampo(tarjeta, gbc, fila, 0, "Sexo", cboSexo);
		añadirCampo(tarjeta, gbc, fila, 2, "Edad", txtEdad);
		fila += 2;
		añadirCampo(tarjeta, gbc, fila, 0, "Peso", txtPeso);
		añadirCampo(tarjeta, gbc, fila, 2, "Color", txtColor);
		gbc.gridx = 0;
		gbc.gridy = fila + 2;
		gbc.gridwidth = 4;
		tarjeta.add(lblEstado, gbc);
		JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
		acciones.setOpaque(false);
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setFocusPainted(false);
		btnCerrar.setBackground(new Color(232, 239, 235));
		btnCerrar.setForeground(new Color(54, 65, 80));
		btnCerrar.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
		btnGuardar = new JButton("Guardar mascota");
		btnGuardar.setFocusPainted(false);
		btnGuardar.setBackground(new Color(35, 122, 85));
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
		if (componente instanceof JComboBox) {
			componente.setPreferredSize(new Dimension(320, 38));
		}
		tarjeta.add(componente, campos);
	}

	private void hacerEscuchadores() {
		btnCerrar.addActionListener(e -> dispose());
		btnGuardar.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGuardar) {
			lblError.setText(" ");
			String clienteTexto = txtCliente.getText().trim();
			String nombre = txtNombre.getText().trim();
			String raza = txtRaza.getText().trim();
			String tipo = txtTipo.getText().trim();
			String sexo = cboSexo.getSelectedItem() == null ? "" : cboSexo.getSelectedItem().toString();
			String edad = txtEdad.getText().trim();
			String peso = txtPeso.getText().trim();
			String color = txtColor.getText().trim();
			if (clienteTexto.isEmpty() || nombre.isEmpty() || raza.isEmpty() || tipo.isEmpty() || sexo.isEmpty() || edad.isEmpty() || peso.isEmpty() || color.isEmpty()) {
				lblError.setText("Todos los campos son obligatorios.");
				return;
			}
			int cliente;
			try {
				cliente = Integer.parseInt(clienteTexto);
			} catch (NumberFormatException ex) {
				lblError.setText("El ID del cliente debe ser numérico.");
				txtCliente.requestFocusInWindow();
				return;
			}
			Mascota mascota = new Mascota(cliente, nombre, raza, tipo, sexo, edad, peso, color);
			if(!BD_mascotas.insert(mascota)) {
				JOptionPane.showMessageDialog(this, "Error al guardar la mascota.", "Error", JOptionPane.ERROR_MESSAGE);
				txtCliente.setText("");
				txtNombre.setText("");
				txtRaza.setText("");
				txtTipo.setText("");
				cboSexo.setSelectedIndex(0);
				txtEdad.setText("");
				txtPeso.setText("");
				txtColor.setText("");
				txtCliente.requestFocusInWindow();
			} else {
				JOptionPane.showMessageDialog(this, "Mascota guardada correctamente.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}