package controllers;

import models.Cliente;

public class RegistroController {
	
	private Cliente cliente;
	
	public void inciarRegistro() {
		cliente = new Cliente();
	}
	
	public void datosCliente(String nombre, String domicilio, String celCasa, String celPersonal, String email) {
		cliente.setDatos(nombre, domicilio, celCasa, celPersonal, email);
		
		
	}

}