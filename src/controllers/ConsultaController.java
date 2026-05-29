package controllers;

import models.*;

public class ConsultaController {
	
	private String msg;
	private Mascota mascota;
	
	public ConsultaController() {
		
	}

	public boolean iniciarConsulta(int idCita, int idMedico) {
		
		Clinica clinica = new Clinica();
		Cita cita = clinica.buscar(idCita);
		
		if(cita == null) {
			msg = clinica.getMsg();
			return false;
		}
		
		mascota = cita.getMascota();
		mascota.crearConsulta(idMedico);
		
		return true;
	}
	
	public Expediente consultarExpediente() {
		return mascota.getExpediente();
	}
	
	public void guardarInformacion(String sintomas, String diagnostico, String observaciones) {
		mascota.guardarInformacion(sintomas, diagnostico, observaciones);
	}
	
	public void finalizarConsulta() {
		mascota.finalizarConsulta();
	}

	public String getMsg() {
		return msg;
	}

	public Mascota getMascota() {
		return mascota;
	}
	
	
	
}
