package controllers;

import includes.BD_clientes;
import models.Cliente;

public class RegistroController {
	
	private Cliente cliente;
	private String msg;
	
	public RegistroController() {
		
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

}