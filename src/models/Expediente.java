package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import classes.Lista;
import includes.BD_consultas;

public class Expediente {

	private int id, mascota;
	private String fecha;
	private Lista<Consulta> contenedorConsultas;
	private Consulta consulta;
	
	public Expediente(int mascota) {
		this.mascota = mascota;
		fecha = date();
		contenedorConsultas = new Lista();
	}
	
	public String date() {
		LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fechaActual.format(formato);
	}
	
	public Expediente(int id, String fechaCreacion) {
		this.id = id;
		this.fecha = fechaCreacion;
		
		System.out.println("Expediente ->" + toString());
		
		contenedorConsultas = BD_consultas.whereAll("expediente", this.id + "");
	}
	
	public String toString() {
		return id + " - " + fecha;
	}
	
	public void crearConsulta(int idMedico) {
		consulta = new Consulta(idMedico, id);
	}
	
	public void guardarInformacion(String sintomas, String diagnostico, String observaciones) {
		
		consulta.setInformacion(sintomas, diagnostico, observaciones);
		
		BD_consultas.insert(consulta);
		
		consulta.setId(BD_consultas.count());
	}
	
	public void generarReciboPago(String raza) {
		consulta.generarReciboPago(raza);
		
		contenedorConsultas.InsertarFinal(consulta);
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
		return "'" + mascota + "', '" + fecha + "'";
	}
	
	
}