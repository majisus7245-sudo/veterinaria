package vistas;

import javax.swing.*;

import controllers.LoginController;
import controllers.MainController;
import includes.BD_usuarios;
import includes.DataBase;

import models.Usuario;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class ViewLogin extends JFrame implements ActionListener{
	private JTextField txtNombre;
	private JPasswordField txtPassword;
	public String usuarioParaBD;
	public String claveParaBD;
	private JLabel errorNombre;
	private JLabel errorPassword;
	private JButton btnIngresar;	
	private JButton btnCancelar;
	private MainController mainController;
	public ViewLogin(MainController mainController){
		super("Veterinaria - Login");
		this.mainController = mainController;
		if(!DataBase.Connect()){
			JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(920, 560));
		this.setResizable(false);
		hazInterfaz();
		hazEscuchadores();
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private void hazInterfaz(){
		UIManager.put("TextField.font", new Font("SansSerif", Font.PLAIN, 16));
		UIManager.put("PasswordField.font", new Font("SansSerif", Font.PLAIN, 16));
		UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 15));
		FondoPanel contenedor = new FondoPanel();
		txtNombre = new JTextField(20);
		txtPassword = new JPasswordField(20);
		contenedor.setLayout(new GridBagLayout());
		contenedor.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
		JPanel layout = new JPanel(new BorderLayout());
		layout.setOpaque(false);
		layout.setPreferredSize(new Dimension(860, 500));
		JPanel panelIzquierdo = new JPanel(new GridBagLayout());
		panelIzquierdo.setBackground(new Color(35, 122, 85));
		panelIzquierdo.setPreferredSize(new Dimension(340, 500));
		GridBagConstraints left = new GridBagConstraints();
		left.gridx = 0;
		left.gridy = 0;
		left.insets = new Insets(10, 24, 10, 24);
		left.anchor = GridBagConstraints.CENTER;
		left.fill = GridBagConstraints.HORIZONTAL;
		left.weightx = 1;
		JLabel icono = new JLabel("\u2665", SwingConstants.CENTER);
		icono.setForeground(new Color(255, 243, 217));
		icono.setFont(new Font("SansSerif", Font.BOLD, 64));
		panelIzquierdo.add(icono, left);
		left.gridy++;
		JLabel nombre = new JLabel("Veterinaria", SwingConstants.CENTER);
		nombre.setForeground(Color.WHITE);
		nombre.setFont(new Font("SansSerif", Font.BOLD, 28));
		panelIzquierdo.add(nombre, left);
		left.gridy++;
		JLabel slogan = new JLabel("Cuidamos a quienes más quieres", SwingConstants.CENTER);
		slogan.setForeground(new Color(225, 245, 236));
		slogan.setFont(new Font("SansSerif", Font.PLAIN, 16));
		panelIzquierdo.add(slogan, left);
		left.gridy++;
		JTextArea descripcion = new JTextArea("Acceso seguro para el personal de la clínica, con una interfaz pensada para trabajar rápido y con claridad.");
		descripcion.setWrapStyleWord(true);
		descripcion.setLineWrap(true);
		descripcion.setOpaque(false);
		descripcion.setEditable(false);
		descripcion.setFocusable(false);
		descripcion.setForeground(new Color(235, 248, 242));
		descripcion.setFont(new Font("SansSerif", Font.PLAIN, 14));
		descripcion.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		panelIzquierdo.add(descripcion, left);
		JPanel panelDerecho = new JPanel(new GridBagLayout());
		panelDerecho.setOpaque(false);
		TarjetaPanel tarjeta = new TarjetaPanel();
		tarjeta.setLayout(new GridBagLayout());
		tarjeta.setPreferredSize(new Dimension(420, 480));
		tarjeta.setBorder(BorderFactory.createEmptyBorder(28, 30, 28, 30));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JLabel titulo = new JLabel("Iniciar sesión", SwingConstants.LEFT);
		titulo.setFont(new Font("SansSerif", Font.BOLD, 26));
		titulo.setForeground(new Color(33, 43, 54));
		tarjeta.add(titulo, gbc);
		gbc.gridy++;
		JLabel subtitulo = new JLabel("Ingresa tus credenciales para continuar");
		subtitulo.setForeground(new Color(92, 107, 119));
		subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tarjeta.add(subtitulo, gbc);
		gbc.gridy++;
		gbc.gridwidth = 1;
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(54, 65, 80));
		tarjeta.add(lblNombre, gbc);
		gbc.gridy++;
		txtNombre.setPreferredSize(new Dimension(260, 40));
		txtNombre.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(196, 210, 201), 1, true),
			BorderFactory.createEmptyBorder(8, 12, 8, 12)
		));
		tarjeta.add(txtNombre, gbc);
		gbc.gridy++;
		// etiqueta de error para nombre
		errorNombre = new JLabel("");
		errorNombre.setForeground(new Color(180, 30, 30));
		errorNombre.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tarjeta.add(errorNombre, gbc);
		gbc.gridy++;
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(54, 65, 80));
		tarjeta.add(lblPassword, gbc);
		gbc.gridy++;
		txtPassword.setPreferredSize(new Dimension(260, 40));
		txtPassword.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(196, 210, 201), 1, true),
			BorderFactory.createEmptyBorder(8, 12, 8, 12)
		));
		tarjeta.add(txtPassword, gbc);
		gbc.gridy++;
		// etiqueta de error para password
		errorPassword = new JLabel("");
		errorPassword.setForeground(new Color(180, 30, 30));
		errorPassword.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tarjeta.add(errorPassword, gbc);
		JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		acciones.setOpaque(false);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFocusPainted(false);
		btnCancelar.setBackground(new Color(232, 239, 235));
		btnCancelar.setForeground(new Color(54, 65, 80));
		btnCancelar.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setFocusPainted(false);
		btnIngresar.setBackground(new Color(35, 122, 85));
		btnIngresar.setForeground(Color.WHITE);
		btnIngresar.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
		acciones.add(btnCancelar);
		acciones.add(btnIngresar);
		gbc.gridy++;
		tarjeta.add(acciones, gbc);
		GridBagConstraints center = new GridBagConstraints();
		center.gridx = 0;
		center.gridy = 0;
		center.weightx = 1;
		center.weighty = 1;
		center.fill = GridBagConstraints.NONE;
		center.anchor = GridBagConstraints.CENTER;
		panelDerecho.add(tarjeta, center);
		layout.add(panelIzquierdo, BorderLayout.WEST);
		layout.add(panelDerecho, BorderLayout.CENTER);
		contenedor.add(layout);
		this.setContentPane(contenedor);
	}
	private void hazEscuchadores(){
		btnIngresar.addActionListener(this);
		btnCancelar.addActionListener(ev -> {
			txtNombre.setText("");
			txtPassword.setText("");
			errorNombre.setText("");
			errorPassword.setText("");
		});
	}
	public void conectarControlador(LoginController controlador){
		btnIngresar.addActionListener(controlador);
	}
	public JButton getBtnIngresar() {
		return btnIngresar;
	}
	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	public JTextField getTxtNombre() {
		return txtNombre;
	}
	public JPasswordField getTxtPassword() {
		return txtPassword;
	}
	public JLabel getErrorNombre() {
		return errorNombre;
	}
	public JLabel getErrorPassword() {
		return errorPassword;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnIngresar){
			boolean ok = true;
			String nombre = txtNombre.getText() == null ? "" : txtNombre.getText().trim();
			String pass = txtPassword.getPassword() == null ? "" : new String(txtPassword.getPassword()).trim();
			if(nombre.isEmpty()){
				errorNombre.setText("Ingrese nombre");
				ok = false;
			} else {
				errorNombre.setText("");
			}
			if(pass.isEmpty()){
				errorPassword.setText("Ingrese password");
				ok = false;
			} else {
				errorPassword.setText("");
			}
			if(!ok) return;

			Usuario usuario = BD_usuarios.where("nombre", nombre);
			if(usuario == null){
				errorNombre.setText("Usuario no encontrado");
				usuarioParaBD = null;
				claveParaBD = null;
				txtNombre.setText("");
				txtPassword.setText("");
				txtNombre.requestFocusInWindow();
				return;
			}
			if(!usuario.revisarPassword(pass)){
				errorPassword.setText("Password incorrecto");
				usuarioParaBD = null;
				claveParaBD = null;
				txtPassword.setText("");
				txtPassword.requestFocusInWindow();
				return;
			}
			usuarioParaBD = nombre;
			claveParaBD = pass;
			errorNombre.setText("");
			errorPassword.setText("");
			mainController.redireccional(usuario);
		}
	}
	private static class FondoPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			GradientPaint gradient = new GradientPaint(0, 0, new Color(241, 248, 244), getWidth(), getHeight(), new Color(220, 235, 228));
			g2.setPaint(gradient);
			g2.fillRect(0, 0, getWidth(), getHeight());
			g2.setColor(new Color(255, 255, 255, 80));
			g2.fillOval(-120, -70, 260, 260);
			g2.fillOval(getWidth() - 180, getHeight() - 160, 240, 240);
			g2.dispose();
		}
	}
	private static class TarjetaPanel extends JPanel {
		TarjetaPanel() {
			setOpaque(false);
		}
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(new Color(0, 0, 0, 35));
			g2.fillRoundRect(8, 10, getWidth() - 16, getHeight() - 16, 28, 28);
			g2.setColor(Color.WHITE);
			g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 16, getHeight() - 16, 28, 28));
			g2.dispose();
			super.paintComponent(g);
		}
		@Override
		protected void paintBorder(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(new Color(214, 226, 220));
			g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 16, getHeight() - 16, 28, 28));
			g2.dispose();
		}
		
	}
}