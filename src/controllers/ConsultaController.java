package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.*;
import vistas.ViewExpedienteEmergente;
import vistas.ViewRegistrarConsulta;
import vistas.ViewVeterinario;

public class ConsultaController implements ActionListener {
	
	private String msg;
	private Mascota mascota;
	private ViewVeterinario vista;
	private int idVeterinario;
	private ViewRegistrarConsulta vistaRegistrarConsulta;
	private Integer idCitaConsultada;
	private String detalleExpediente;
	
	public ConsultaController() {
		
	}

	public ConsultaController(ViewVeterinario vista, int idVeterinario) {
		this.vista = vista;
		this.idVeterinario = idVeterinario;
		this.vista.hazEscuchadores(this);
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

	private boolean cargarConsulta(int idCita) {
		if (idCitaConsultada != null && idCitaConsultada == idCita && mascota != null) {
			return true;
		}

		if (!iniciarConsulta(idCita, idVeterinario)) {
			return false;
		}

		idCitaConsultada = idCita;
		return true;
	}

	private void mostrarDatosConsultaEnRegistrar() {
		if (vistaRegistrarConsulta == null || mascota == null) {
			return;
		}

		detalleExpediente = construirDetalleExpediente();
		Expediente expediente = consultarExpediente();
		vistaRegistrarConsulta.getLblEstado().setText("Estado: cita cargada correctamente");
	}

	private String construirDetalleExpediente() {
		if (mascota == null) {
			return "";
		}

		Expediente expediente = consultarExpediente();
		return new StringBuilder()
			.append("Cita: ").append(idCitaConsultada == null ? "" : idCitaConsultada).append("\n")
			.append("Mascota: ").append(mascota).append("\n")
			.append("Expediente: ").append(expediente).append("\n")
			.append("Consultas registradas: ").append(expediente.getContenedorConsultas().length()).append("\n")
			.toString();
	}

	private void abrirPopupExpediente() {
		if (detalleExpediente == null || detalleExpediente.isBlank()) {
			detalleExpediente = construirDetalleExpediente();
		}
		ViewExpedienteEmergente popup = new ViewExpedienteEmergente(vistaRegistrarConsulta, detalleExpediente);
		popup.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vista.getBtnIniciarConsulta()) {
			vistaRegistrarConsulta = new ViewRegistrarConsulta();
			vistaRegistrarConsulta.hazEscuchadores(this);
			vistaRegistrarConsulta.setVisible(true);
		}
		if (vistaRegistrarConsulta != null && e.getSource() == vistaRegistrarConsulta.getBtnConsultar()) {
			vistaRegistrarConsulta.getLblError().setText(" ");
			String textoIdCita = vistaRegistrarConsulta.getTxtIdCita().getText().trim();
			if (textoIdCita.isEmpty()) {
				vistaRegistrarConsulta.getLblError().setText("El ID de la cita es obligatorio.");
				return;
			}

			int idCita;
			try {
				idCita = Integer.parseInt(textoIdCita);
			} catch (NumberFormatException ex) {
				vistaRegistrarConsulta.getLblError().setText("El ID de la cita debe ser numérico.");
				vistaRegistrarConsulta.getTxtIdCita().requestFocusInWindow();
				return;
			}

			if (!cargarConsulta(idCita)) {
				String mensaje = getMsg();
				if (mensaje == null || mensaje.isBlank()) {
					mensaje = "No se pudo cargar la cita.";
				}
				vistaRegistrarConsulta.getLblError().setText(mensaje);
				detalleExpediente = null;
				return;
			}

			mostrarDatosConsultaEnRegistrar();
		}
		if (vistaRegistrarConsulta != null && e.getSource() == vistaRegistrarConsulta.getBtnVerExpediente()) {
			if (detalleExpediente == null || detalleExpediente.isBlank()) {
				vistaRegistrarConsulta.getLblError().setText("Consulta primero la cita para ver el expediente.");
				return;
			}
			abrirPopupExpediente();
		}
		if (vistaRegistrarConsulta != null && e.getSource() == vistaRegistrarConsulta.getBtnGuardar()) {
			vistaRegistrarConsulta.getLblError().setText(" ");
			String textoIdCita = vistaRegistrarConsulta.getTxtIdCita().getText().trim();
			String sintomas = vistaRegistrarConsulta.getTxtSintomas().getText().trim();
			String diagnostico = vistaRegistrarConsulta.getTxtDiagnostico().getText().trim();
			String observaciones = vistaRegistrarConsulta.getTxtObservaciones().getText().trim();

			if (textoIdCita.isEmpty() || sintomas.isEmpty() || diagnostico.isEmpty() || observaciones.isEmpty()) {
				vistaRegistrarConsulta.getLblError().setText("Todos los campos son obligatorios.");
				return;
			}

			int idCita;
			try {
				idCita = Integer.parseInt(textoIdCita);
			} catch (NumberFormatException ex) {
				vistaRegistrarConsulta.getLblError().setText("El ID de la cita debe ser numérico.");
				vistaRegistrarConsulta.getTxtIdCita().requestFocusInWindow();
				return;
			}

			if (!cargarConsulta(idCita)) {
				String mensaje = getMsg();
				if (mensaje == null || mensaje.isBlank()) {
					mensaje = "No se pudo iniciar la consulta.";
				}
				vistaRegistrarConsulta.getLblError().setText(mensaje);
				return;
			}
			mostrarDatosConsultaEnRegistrar();

			guardarInformacion(sintomas, diagnostico, observaciones);
			finalizarConsulta();
			Expediente expediente = consultarExpediente();
			JOptionPane.showMessageDialog(
				vistaRegistrarConsulta,
				"Consulta registrada correctamente.\nExpediente: " + expediente,
				"Consulta",
				JOptionPane.INFORMATION_MESSAGE
			);
			vistaRegistrarConsulta.dispose();
		}
	}
	
	
	
}
