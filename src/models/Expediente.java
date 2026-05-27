package models;

import classes.Lista;

public class Expediente {

	private int id, mascota;
	private String fechaCreacion;
	private Lista<Consulta> contenedorConsultas;
	
	public Expediente(int id, int mascota, String fechaCreacion) {
		this.id = id;
		this.mascota = mascota;
		this.fechaCreacion = fechaCreacion;
	}
	
	public Expediente(int mascota, String fechaCreacion) {
		this.mascota = mascota;
		this.fechaCreacion = fechaCreacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMascota() {
		return mascota;
	}

	public void setMascota(int mascota) {
		this.mascota = mascota;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Lista<Consulta> getContenedorConsultas() {
		return contenedorConsultas;
	}

	public void setContenedorConsultas(Lista<Consulta> contenedorConsultas) {
		this.contenedorConsultas = contenedorConsultas;
	}
	
	
}