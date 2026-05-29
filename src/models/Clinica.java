package models;

import classes.Lista;
import includes.BD_cita;

public class Clinica {
	
	private Lista<Cita> contenedorCitas;
	private String msg;
	
	public Clinica() {
		contenedorCitas = BD_cita.all();
		System.out.println("---------------------");
	}
	
	public Cita buscar(int idCita) {
		if(!contenedorCitas.Buscar(new Cita(idCita, 0))) {
			msg = "Cita no encontrada";
			return null;
		}
		
		return contenedorCitas.getDr();
	}

	public String getMsg() {
		return msg;
	}
	
	

}
