package pruebas;

import controllers.RegistroController;
import includes.BD_clientes;
import includes.DataBase;
import models.Cliente;
import vistas.ViewRecepcionista;

public class UC1 {

	public static void main(String[] args) {
		if(!DataBase.Connect()) {
			System.out.println(DataBase.getMsg());
			return;
		}
		
//		RegistroController controlador = new RegistroController(new ViewRecepcionista());
//		controlador.inciarRegistro();
//		System.out.println("Registro iniciado");
//		int id = BD_clientes.count();
//		
//		controlador.datosCliente("Pedrito" + id, "Culiacan", "1234567890", "1234567890", "correo@correo.com");
//		
//		controlador.registrarMascota("perro", "buldoser", "perro", "M", "2", "4", "negro");
//		controlador.registrarMascota("gato", "miau", "miau", "M", "2", "4", "negro");
		
		BD_clientes.all();
		
	}

}