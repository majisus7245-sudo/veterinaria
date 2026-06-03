package pruebas;

import controllers.ConsultaController;
import includes.BD_cita;
import includes.BD_mascotas;
import includes.DataBase;
import models.Cita;
import models.Expediente;

public class UC3 {

	public static void main(String[] args) {

		if(!DataBase.Connect()) {
			System.out.println(DataBase.getMsg());
			return;
		}
		
		ConsultaController controlador = new ConsultaController();
		
		Cita cita = new Cita(BD_mascotas.count());
		BD_cita.insert(cita);
		cita.setId(BD_cita.count());
		
		controlador.iniciarConsulta(cita.getId(), 2);

		System.out.println("MOTRANDO DATOS MASCOTA");
		
		Expediente expediente = controlador.consultarExpediente();
		System.out.println(expediente.toString());
		System.out.println("MOTRANDO DATOS EXPEDIENTE'''''''''''''''''''''");
		
		controlador.guardarInformacion("dolor de panza", "se comio un zapato", "debe reposar");
		
		controlador.finalizarConsulta();
		
		System.out.println("------------------------------------------------------");
		System.out.println();
		
		BD_cita.where("id", cita.getId() + "");

	}

}
