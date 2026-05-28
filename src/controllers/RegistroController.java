package controllers;

import includes.BD_clientes;
import models.Cliente;

public class RegistroController {
	
	private Cliente cliente;
	
	public void inciarRegistro() {
		cliente = new Cliente();
	}
	
	public void datosCliente(String nombre, String domicilio, String celCasa, String celPersonal, String email) {
		cliente.setDatos(nombre, domicilio, celCasa, celPersonal, email);
		
		BD_clientes.insert(cliente);
		cliente.setId(BD_clientes.count());
	}
	
	public void registrarMascota(String nombre, String raza, String tipo, String sexo, String edad, String peso, String color) {
		cliente.nuevaMascota(nombre, raza, tipo, sexo, edad, peso, color);
	}

}