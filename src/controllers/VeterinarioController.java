package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.Expediente;
import vistas.ViewVeterinario;
import vistas.ViewRegistrarConsulta;

public class VeterinarioController implements ActionListener {

	private ViewVeterinario vista;
	private int idVeterinario;
	private ConsultaController consultaController;
	private ViewRegistrarConsulta vistaRegistrarConsulta;

	public VeterinarioController(ViewVeterinario vista, int idVeterinario) {
		this.vista = vista;
		this.idVeterinario = idVeterinario;
		this.consultaController = new ConsultaController();
		this.vista.hazEscuchadores(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vista.getBtnIniciarConsulta()) {
			vistaRegistrarConsulta = new ViewRegistrarConsulta();
			vistaRegistrarConsulta.hazEscuchadores(this);
			vistaRegistrarConsulta.setVisible(true);
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

			if (!consultaController.iniciarConsulta(idCita, idVeterinario)) {
				String mensaje = consultaController.getMsg();
				if (mensaje == null || mensaje.isBlank()) {
					mensaje = "No se pudo iniciar la consulta.";
				}
				vistaRegistrarConsulta.getLblError().setText(mensaje);
				return;
			}

			consultaController.guardarInformacion(sintomas, diagnostico, observaciones);
			consultaController.finalizarConsulta();
			Expediente expediente = consultaController.consultarExpediente();
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
