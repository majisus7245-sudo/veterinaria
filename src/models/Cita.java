package models;

import includes.BD_mascotas;

public class Cita {

	private int id, mascota;
	
	public Cita(int id, int mascota) {
		this.id = id;
		this.mascota = mascota;
		
		System.out.println("Cita ->" + toString());
		
		BD_mascotas.where("id", mascota + "");
	}
	
	public Cita(int mascota) {
		this.mascota = mascota;
	}
	
	public Mascota getMascota() {
		return BD_mascotas.where("id", mascota + "");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return id + "";
	}

	public String getValues() {
		return mascota + "";
	}
	
}
