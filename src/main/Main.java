package main;

import includes.DataBase;

public class Main {

	public static void main(String[] args) {
		if(!DataBase.Connect())
			System.out.println(DataBase.getMsg());
		else 
			System.out.println("Conexion exitosa");
	}
 
}