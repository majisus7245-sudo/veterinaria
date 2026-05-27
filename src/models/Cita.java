package models;

public class Cita {

	private int id, mascota;
	
	public Cita(int id, int mascota) {
		this.id = id;
		this.mascota = mascota;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMascota() {
		return mascota;
	}

	public void setMascota(int mascota) {
		this.mascota = mascota;
	}
	
	
	
}
