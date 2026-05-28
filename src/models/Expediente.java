package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import classes.Lista;
import includes.BD_consultas;

public class Expediente {

	private int id;
	private String fecha;
	private Lista<Consulta> contenedorConsultas;
	
	public Expediente() {
		contenedorConsultas = new Lista();
		fecha = date();
	}
	
	public String date() {
		LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fechaActual.format(formato);
	}
	
	public Expediente(int id, String fechaCreacion) {
		this.id = id;
		this.fecha = fechaCreacion;
		
		System.out.println("Expediente -> " + getValues());
		
		contenedorConsultas = BD_consultas.whereAll("expediente", this.id + "");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fecha = fechaCreacion;
	}

	public Lista<Consulta> getContenedorConsultas() {
		return contenedorConsultas;
	}

	public void setContenedorConsultas(Lista<Consulta> contenedorConsultas) {
		this.contenedorConsultas = contenedorConsultas;
	}

	public String getValues() {
		return "'" + fecha + "'";
	}
	
	
}