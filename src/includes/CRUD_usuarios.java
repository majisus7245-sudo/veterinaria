package includes;

import java.sql.*;

import classes.Lista;
import models.Usuario;

public class CRUD_usuarios {
	private static String tabla = "usuarios";
	private static String [] columnas = {"id", "nombre", "password", "rol", "estado"};
	
	public static Lista<Usuario> all() {
		Lista<Usuario> usuarios = new Lista();
		try {
			String sql = "Select * From usuarios";
			Statement stmt = DataBaseConect.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println("ID: " + rs.getInt(1) + " usuario " + rs.getString(2));
				usuarios.InsertarFinal(
						new Usuario(
								rs.getInt(1), 
								rs.getString(2), 
								rs.getString(3), 
								rs.getString(4), 
								rs.getString(5)
								)
						);
			}
			return usuarios;
		} catch (SQLException e) {
			System.out.println("algo suedio");
			return usuarios;
		}
	}
	
}
