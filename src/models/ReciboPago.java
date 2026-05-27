package models;

public class ReciboPago {
	
	private int id, consulta; 
	private float precio;
	private String fecha;
	
	public ReciboPago(float Precio, String fecha) {
		this.precio = precio;
		this.fecha = fecha;
	}
	
	public ReciboPago(int id, int consulta, float precio, String fecha) {
		this.id = id;
		this.consulta = consulta;
		this.precio = precio;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getConsulta() {
		return consulta;
	}

	public void setConsulta(int consulta) {
		this.consulta = consulta;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	

}
