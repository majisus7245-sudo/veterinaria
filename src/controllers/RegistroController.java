package controllers;

import java.awt.event.*;

import javax.swing.JOptionPane;

import includes.BD_clientes;
import includes.BD_mascotas;
import models.Cliente;
import models.Mascota;
import vistas.*;

public class RegistroController implements ActionListener{
	private ViewRecepcionista vista;
	private Cliente cliente;
	private String msg;
	
	public RegistroController(ViewRecepcionista vista) {
		this.vista = vista;
		this.vista.hazEscuchadores(this);
	}
	
	public void inciarRegistro() {
		cliente = new Cliente();
	}
	
	public boolean datosCliente(String nombre, String domicilio, String celCasa, String celPersonal, String email) {
		cliente.setDatos(nombre, domicilio, celCasa, celPersonal, email);
		
		BD_clientes.insert(cliente);
		
		cliente.setId(BD_clientes.count());
		return true;
	}
	
	public void registrarMascota(String nombre, String raza, String tipo, String sexo, String edad, String peso, String color) {
		cliente.nuevaMascota(nombre, raza, tipo, sexo, edad, peso, color);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getMsg() {
		return msg;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vista.getBtnAgregarMascota()) {
	      ViewAgregarMascota agregarMascota = new ViewAgregarMascota();
	      agregarMascota.setVisible(true);
	    }
	      
		if(e.getSource() == vista.getBtnRegistrarCliente()) {
			inciarRegistro();
			vista.crearVistaRegistroCliente(this);
			return;
		}
		// if(e.getSource() == vista.getBtnRegistrarCliente()) {
		// 	vista.crearVistaRegistro(this);
		// }
		
		if (vista.getVistaAgregarCliente() != null && e.getSource() == vista.getVistaAgregarCliente().getBtnGuardar()) {
			vista.getVistaAgregarCliente().getLblError().setText(" ");
			String nombre = vista.getVistaAgregarCliente().getTxtNombre().getText().trim();
			String domicilio = vista.getVistaAgregarCliente().getTxtDomicilio().getText().trim();
			String celCasa = vista.getVistaAgregarCliente().getTxtCelCasa().getText().trim();
			String celPersonal = vista.getVistaAgregarCliente().getTxtCelPersonal().getText().trim();
			String email = vista.getVistaAgregarCliente().getTxtEmail().getText().trim();
			if (nombre.isEmpty() || domicilio.isEmpty() || celPersonal.isEmpty() || email.isEmpty()) {
				vista.getVistaAgregarCliente().getLblError().setText("Todos los campos son obligatorios excepto Tel. casa.");
				return;
			}
			datosCliente(nombre, domicilio, celCasa, celPersonal, email);
			JOptionPane.showMessageDialog(vista.getVistaAgregarCliente(), "Cliente registrado correctamente.");
			vista.getVistaAgregarCliente().dispose();
			vista.crearVistaRegistroMascota(this);
			return;
		}
		if (e.getSource() == vista.getVistaAgregarMascota().getBtnGuardar()) {
			vista.getVistaAgregarMascota().getLblError().setText(" ");
			String clienteTexto = vista.getVistaAgregarMascota().getTxtCliente().getText().trim();
			String nombre = vista.getVistaAgregarMascota().getTxtNombre().getText().trim();
			String raza = vista.getVistaAgregarMascota().getTxtRaza().getText().trim();
			String tipo = vista.getVistaAgregarMascota().getTxtTipo().getText().trim();
			String sexo = vista.getVistaAgregarMascota().getCboSexo().getSelectedItem() == null ? "" : vista.getVistaAgregarMascota().getCboSexo().getSelectedItem().toString();
			String edad = vista.getVistaAgregarMascota().getTxtEdad().getText().trim();
			String peso = vista.getVistaAgregarMascota().getTxtPeso().getText().trim();
			String color = vista.getVistaAgregarMascota().getTxtColor().getText().trim();
			if (clienteTexto.isEmpty() || nombre.isEmpty() || raza.isEmpty() || tipo.isEmpty() || sexo.isEmpty() || edad.isEmpty() || peso.isEmpty() || color.isEmpty()) {
				vista.getVistaAgregarMascota().getLblError().setText("Todos los campos son obligatorios.");
				return;
			}
			int cliente;
			try {
				cliente = Integer.parseInt(clienteTexto);
			} catch (NumberFormatException ex) {
				vista.getVistaAgregarMascota().getLblError().setText("El ID del cliente debe ser numérico.");
				vista.getVistaAgregarMascota().getTxtCliente().requestFocusInWindow();
				return;
			}
			registrarMascota(nombre, raza, tipo, sexo, edad, peso, color);
			return;
		}
	}
}