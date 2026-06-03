package controllers;

import java.awt.event.*;

import includes.BD_clientes;
import models.Cliente;
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
	}
}