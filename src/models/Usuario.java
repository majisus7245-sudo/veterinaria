package models;

public class Usuario {
	
	private int id;
	private String nombre, password, rol, estado;
	
	public Usuario(String nombre, String password) {
		this.nombre = nombre;
		this.password = password;
	}
	
	public Usuario(int id, String nombre, String password, String rol, String estado) {
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.rol = rol;
		this.estado = estado;
	}

}
