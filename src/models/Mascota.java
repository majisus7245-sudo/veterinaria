package models;

import includes.BD_expedientes;
import includes.BD_mascotas;

public class Mascota {
	
	private int id, cliente;
	private String nombre, raza, tipo, sexo, edad, peso, color, estado;
	private Expediente expediente;
	
	public Mascota(int cliente, String nombre, String raza, String tipo, String sexo, String edad, String peso, String color) {
		this.cliente = cliente;
		this.nombre = nombre;
		this.raza = raza;
		this.tipo = tipo;
		this.sexo = sexo;
		this.edad = edad;
		this.peso = peso;
		this.color = color;
		
		BD_mascotas.insert(this);
		id = BD_mascotas.count();
		
		expediente = new Expediente(id);
		
		BD_expedientes.insert(expediente);
		
		expediente.setId(BD_expedientes.count());
	}
	
	public Mascota(int id, int cliente, String nombre, String raza, String tipo, String sexo, String edad, String peso, String color, String estado) {
		this.id = id;
		this.cliente = cliente;
		this.nombre = nombre;
		this.raza = raza;
		this.tipo = tipo;
		this.sexo = sexo;
		this.edad = edad;
		this.peso = peso;
		this.color = color;
		this.estado = estado;
		
		System.out.println("Mascota ->" + toString());
		
		this.expediente = BD_expedientes.where("mascota", id + "");
	}
	
	public String toString() {
		return id + " " + nombre;
	}
	
	public void crearConsulta(int idMedico) {
		expediente.crearConsulta(idMedico);
	}
	
	public void guardarInformacion(String sintomas, String diagnostico, String observaciones) {
		expediente.guardarInformacion(sintomas, diagnostico, observaciones);
	}
	
	public void finalizarConsulta() {
		expediente.generarReciboPago(raza);
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCliente() {
		return cliente;
	}

	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public String getValues() {
		return "'" + cliente + "', '" + nombre +"', '" + raza + "', '" + tipo + "', '" + sexo + "', '" + edad + "', '" + peso + "', '" + color + "'";
	}
	
	

}
