package _public;

import includes.DataBaseConect;

public class Main {

	public static void main(String[] args) {
		if(!DataBaseConect.Connect())
			System.out.println(DataBaseConect.getMsg());
		else 
			System.out.println("Conexion exitosa");
	}

}