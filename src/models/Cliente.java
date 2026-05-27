package models;

public class Cliente {
	
	private int id;
	private String nombre, domicilio, celCasa, celPersonal, email, estado;
	
	public Cliente() {}
	
	public Cliente(int id, String nombre, String domicilio, String celCasa, String celPersonal, String email, String estado) {
		this.id = id;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.celCasa = celCasa;
		this.celPersonal = celPersonal;
		this.email = email;
		this.estado = estado;
	}
	
	public void setDatos(String nombre, String domicilio, String celCasa, String celPersonal, String email, String estado) {
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.celCasa = celCasa;
		this.celPersonal = celPersonal;
		this.email = email;
		this.estado = estado;
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
	
	

}
