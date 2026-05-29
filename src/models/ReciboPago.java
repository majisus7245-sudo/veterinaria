package models;

public class ReciboPago {
	
	private int id, consulta; 
	private double precio;
	private String fecha;
	
	public ReciboPago(String fecha, double precio, int consulta) {
		this.precio = precio;
		this.fecha = fecha;
		this.consulta = consulta;
	}
	
	public ReciboPago(int id, int consulta, double precio, String fecha) {
		this.id = id;
		this.consulta = consulta;
		this.precio = precio;
		this.fecha = fecha;
		
		System.out.println("Recibo de Pago ->" + toString());
	}
	
	public String toString() {
		return id + " " + precio;
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

	public double getPrecio() {
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

	public String getValues() {
		return "'" + consulta + "', '" + precio + "', '" + fecha + "'";
	}
	
	

}
