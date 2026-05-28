package pruebas;

import controllers.RegistroController;
import includes.DataBase;

public class UC1 {

	public static void main(String[] args) {
		if(!DataBase.Connect()) {
			System.out.println(DataBase.getMsg());
			return;
		}
		
		RegistroController controlador = new RegistroController();
		controlador.inciarRegistro();
		System.out.println("Registro iniciado");
		
		controlador.datosCliente("Pedrito", "Culiacan", "1234567890", "1234567890", "correo@correo.com");
		controlador.registrarMascota("perro", "buldoser", "perro", "M", "2", "4", "negro");
		
		

	}

}
