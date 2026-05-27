package includes;

import java.sql.*;

import classes.Lista;
import models.Usuario;

public class BD_usuarios{
	
	private static String tabla = "usuarios";
	private static String [] columnas = {"id", "nombre", "password", "rol", "estado"};
	private static String msg; 
	
	public static Lista<Usuario> all() {
		Lista<Usuario> usuarios = new Lista();
		try {
			String sql = "Select * From " + tabla;
			Statement stmt = DataBase.getConn().createStatement();
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
			msg = e.getMessage();
			return usuarios;
		}
	}
	
	public static Usuario where(String columna, String valor) {
		Usuario usuario = null;
		try {
			String sql = "Select top 1 * From " + tabla + " where " + columna + " = '" + valor + "'";
	        
	        Statement stmt = DataBase.getConn().createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        if (rs.next()) {
	            usuario = new Usuario(
	                    rs.getInt(1), 
	                    rs.getString(2), 
	                    rs.getString(3), 
	                    rs.getString(4), 
	                    rs.getString(5)
	            );
	        }
			return usuario;
		} catch (SQLException e) {
			msg = e.getMessage();
			return usuario;
		}
	}
	
}
