package models;

public class Usuario {
	
	private int id;
	private String nombre, password, rol, estado;
	
	public Usuario() {}
	
	public Usuario(String nombre, String password) {
		this.nombre = nombre;
		this.password = password;
	}
	
	public Usuario(int id, String nombre, String password, String rol, String estado) {
		this(nombre, password);
		this.id = id;
		this.rol = rol;
		this.estado = estado;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPassword() {
		return password;
	}

	public String getRol() {
		return rol;
	}

	public String getEstado() {
		return estado;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
