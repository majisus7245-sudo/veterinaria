package models;

import classes.Lista;
import includes.BD_mascota;

public class Cliente {
	
	private int id;
	private String nombre, domicilio, celCasa, celPersonal, email, estado;
	private Lista<Mascota> contenedorMascotas;
	
	public Cliente() {
		contenedorMascotas = new Lista();
	}
	
	public Cliente(int id, String nombre, String domicilio, String celCasa, String celPersonal, String email, String estado) {
		this.id = id;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.celCasa = celCasa;
		this.celPersonal = celPersonal;
		this.email = email;
		this.estado = estado;
		
		System.out.println(getValues());
		
		contenedorMascotas = BD_mascota.whereAll("cliente", this.id + "");
		
	}
	
	public void setDatos(String nombre, String domicilio, String celCasa, String celPersonal, String email) {
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.celCasa = celCasa;
		this.celPersonal = celPersonal;
		this.email = email;
	}
	
	public void nuevaMascota(String nombre, String raza, String tipo, String sexo, String edad, String peso, String color) {
		Mascota mascota = new Mascota(getId(), nombre, raza, tipo, sexo, edad, peso, color);
		
		contenedorMascotas.InsertarFinal(mascota);
	}
	
	public String getValues() {
		return nombre + ", " + domicilio + ", " + ((celCasa.isBlank()) ? "" : celCasa) + ", " + celPersonal + ", " + email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCelCasa() {
		return celCasa;
	}

	public void setCelCasa(String celCasa) {
		this.celCasa = celCasa;
	}

	public String getCelPersonal() {
		return celPersonal;
	}

	public void setCelPersonal(String celPersonal) {
		this.celPersonal = celPersonal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String toString() {
		return id + " " + nombre;
	}

}
