package controllers;

import java.awt.event.*;

import javax.swing.JOptionPane;

import includes.BD_usuarios;
import models.Usuario;
import vistas.*;

public class LoginController implements ActionListener{
  private ViewLogin vista;
  private MainController navegador; 

  public LoginController(ViewLogin vista, MainController navegador) {
      this.vista = vista;
      this.navegador = navegador;
      this.vista.conectarControlador(this);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == vista.getBtnIngresar()){
			boolean ok = true;
			String nombre = vista.getTxtNombre().getText() == null ? "" : vista.getTxtNombre().getText().trim();
			String pass = vista.getTxtPassword().getPassword() == null ? "" : new String(vista.getTxtPassword().getPassword()).trim();
			if(nombre.isEmpty()){
				vista.getErrorNombre().setText("Ingrese nombre");
				ok = false;
			} else {
				vista.getErrorNombre().setText("");
			}
			if(pass.isEmpty()){
				vista.getErrorPassword().setText("Ingrese password");
				ok = false;
			} else {
				vista.getErrorPassword().setText("");
			}
			if(!ok) return;

			Usuario usuario = BD_usuarios.where("nombre", nombre);
			if(usuario == null){
				vista.getErrorNombre().setText("Usuario no encontrado");
				String usuarioParaBD = null;
				String claveParaBD = null;
				vista.getTxtNombre().setText("");
				vista.getTxtPassword().setText("");
				vista.getTxtNombre().requestFocusInWindow();
				return;
			}
			if(!usuario.revisarPassword(pass)){
				vista.getErrorPassword().setText("Password incorrecto");
				String usuarioParaBD = null;
				String claveParaBD = null;
				vista.getTxtPassword().setText("");
				vista.getTxtPassword().requestFocusInWindow();
				return;
			}
			String usuarioParaBD = nombre;
			String claveParaBD = pass;
			vista.getErrorNombre().setText("");
			vista.getErrorPassword().setText("");
			navegador.redireccional(usuario);
			JOptionPane.showMessageDialog(vista, "Este usuario no tiene acceso a la pantalla de recepción.", "Login", JOptionPane.WARNING_MESSAGE);
		}
  }
}
