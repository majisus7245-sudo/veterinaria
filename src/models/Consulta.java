package models;

public class Consulta {
	
	private int id, expediente, veterinario;
	private String sintomas, diagnostico, observaciones, fecha;
	private ReciboPago reciboPago;
	
	public Consulta(int veterinario) {
		this.veterinario = veterinario;
	}
	
	public Consulta(int id, int expediente, int veterinario, String sintomas, String diagnostico, String observaciones, String fecha) {
		this.id = id;
		this.expediente = expediente;
		this.veterinario = veterinario;
		this.sintomas = sintomas;
		this.diagnostico = diagnostico;
		this.observaciones = observaciones;
		this.fecha = fecha;
	}
	
	public void guardarInformacion(String sintomas, String diagnostico, String observaciones) {
		this.sintomas = sintomas;
		this.diagnostico = diagnostico;
		this.observaciones = observaciones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExpediente() {
		return expediente;
	}

	public void setExpediente(int expediente) {
		this.expediente = expediente;
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
	
	

}
