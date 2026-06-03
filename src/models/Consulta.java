package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import includes.*;

public class Consulta {
	
	private int id, veterinario, expediente;
	private String sintomas, diagnostico, observaciones, fecha;
	private ReciboPago reciboPago;
	
	public Consulta(int veterinario, int expediente) {
		this.expediente = expediente;
		this.veterinario = veterinario;
		
		fecha = date();
	}
	
	public Consulta(int id, int expediente, int veterinario, String sintomas, String diagnostico, String observaciones, String fecha) {
		this.id = id;
		this.expediente = expediente;
		this.veterinario = veterinario;
		this.sintomas = sintomas;
		this.diagnostico = diagnostico;
		this.observaciones = observaciones;
		this.fecha = fecha;
		
		System.out.println("Consulta ->" + toString());
		
		reciboPago = BD_recibosPago.where("consulta", id +"");
	}
	
	// @Override
	// public String toString() {
	// 	return id + " " + fecha;
	// }
	
	public void setInformacion(String sintomas, String diagnostico, String observaciones) {
		this.sintomas = sintomas;
		this.diagnostico = diagnostico;
		this.observaciones = observaciones;
	}
	
	public void generarReciboPago(String raza) {
		double precio = precio(raza);
		String fecha = date();
		
		reciboPago = new ReciboPago(fecha, precio, id);
		
		BD_recibosPago.insert(reciboPago);
	}
	
	private double precio(String raza) {
		
		//Validaciones de precio por raza
		
		return 50.0;
	}
	
	private String date() {
		LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fechaActual.format(formato);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(int veterinario) {
		this.veterinario = veterinario;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public ReciboPago getReciboPago() {
		return reciboPago;
	}

	public void setReciboPago(ReciboPago reciboPago) {
		this.reciboPago = reciboPago;
	}

	public int getExpediente() {
		return expediente;
	}

	public void setExpediente(int expediente) {
		this.expediente = expediente;
	}

	public String getValues() {
		return "'" + expediente + "', '" + veterinario + "', '" + sintomas + "', '" + diagnostico + "', '" + observaciones + "', '" + fecha + "'";
	}
	@Override
	public String toString(){
		return "Consulta{id=" + id + ", expediente=" + expediente + ", veterinario=" + veterinario + ", sintomas='" + sintomas + "', diagnostico='" + diagnostico + "', observaciones='" + observaciones + "', fecha='" + fecha + "'}";
	}
}
